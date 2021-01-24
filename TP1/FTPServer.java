import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.*;
import java.util.Date;
import java.sql.Timestamp;


public class FTPServer {

    public static void main(String[] args) throws Exception
    {
        ServerSocket scontrol = new ServerSocket(8080);
        Date date = new Date();
        System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Server ON");
       
        Socket c = new Socket(); 
        int id = 1;

        try
        {
            while(true)
            {
                c = scontrol.accept();
                Runnable rThread = new MyRunnableThread(c,id);
                Thread newThread = new Thread(rThread);
                newThread.start();
                id += 1;
            }
        }
        catch(Exception ex)
        {
            System.err.println("An error occured : ");
			ex.printStackTrace();
        }
    }

    // à voir si utile (renvoyer un tableau du split par exemple)
    public static String read(BufferedReader br) throws Exception
    {
        return br.readLine();
    }

    public static void write(BufferedWriter wr,String message) throws Exception
    {
        wr.write(message);
        wr.flush();
    }

    public static void processLIST()
    {

    }

}

class MyRunnableThread implements Runnable 
{
    Socket c;
    int id = 0;

    File directory;
    File root;

    int portPORT;
    InetAddress ipPORT;    

    BufferedReader br;
    BufferedWriter wr;
    String message = "";
    String response = "";

    public MyRunnableThread(Socket newSocket,int id) 
    {
        this.c = newSocket;
        this.id = id;
    }

    public void run() 
    {   
        Date date = new Date();
        try
        {   
            System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Client " + id + " connected");

            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            wr = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));

            FTPServer.write(wr,Config.CODE_220);
            response = br.readLine();
            //permet de récupérer juste le username (voir @FTPRequestAnnotation)
            response = (response.split(" ")[1]);

            if(response.equals(Config.login))
            {
                FTPServer.write(wr,Config.CODE_331);

                //on récupère l'emplacement du répertoire du user
                directory = new File("./serv_memory/" + response);
                
                response = br.readLine();
                response = (response.split(" ")[1]);
                if(response.equals(Config.passwd))
                {    
                    FTPServer.write(wr,Config.CODE_230);     
                    FTPServer.write(wr,Config.CODE_215);   
                    System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Client " + id + " logged in");
                    
                    //on vérifie si le dossier existe, sinon on le crée
                    if (!directory.exists()) {
                        if (directory.mkdir()) {
                            System.out.println("Directory has been created!");
                        } else {
                            System.out.println("Failed to create directory!");
                        }
                    }
                    root = directory;
                    
                    while(!c.isClosed())
                    {
                        response = br.readLine();
                        switch(response.split(" ")[0])
                        {
                            case "QUIT":
                                FTPServer.write(wr,Config.CODE_221);
                                c.close();
                                System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Client " + id + " left");
                                break;

                            case "LIST":
                                Socket sdataList = new Socket(this.ipPORT,this.portPORT); //sur le port du case PORT + l'IP
                                FTPServer.write(wr,Config.CODE_125);
                                String res = "";
                                for(File in : directory.listFiles())
                                {
                                    Path path = Paths.get(in.getAbsolutePath());
                                    BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                                    if(in.isDirectory())
                                    {
                                        res+= attr.creationTime() + " " + attr.size() + " " + in.getName() + " -d \r\n";
                                    }
                                    else
                                    {
                                        res+=attr.creationTime() + " " + attr.size() + " " + in.getName() + " -f \r\n";
                                    }
                                }
                                DataOutputStream dosList = new DataOutputStream(sdataList.getOutputStream());
                                dosList.writeBytes(res);
                                dosList.flush();
                                FTPServer.write(wr,Config.CODE_226);
                                dosList.flush();
                                sdataList.close();
                                break;

                            case "RETR":
                                Socket sdataRetr = new Socket(this.ipPORT,this.portPORT);
                                String filenameRetr = response.split(" ")[1];
                                File retrFile = new File(directory.getPath() + "/" + filenameRetr);
                                System.out.println(retrFile.getPath());
                                if (!retrFile.exists()) 
                                {
                                    FTPServer.write(wr,Config.CODE_404);    
                                }
                                else
                                {
                                    FTPServer.write(wr,Config.CODE_125);
                                    DataOutputStream dosRetr = new DataOutputStream(sdataRetr.getOutputStream());
                                    FileInputStream fis = new FileInputStream(retrFile);
                                    byte[] tmp = new byte[sdataRetr.getSendBufferSize()];
                                    int readb = fis.read(tmp);
                                    while(readb>0){
                                        dosRetr.write(tmp,0,readb);
                                        readb = fis.read(tmp);
                                    }
                                    fis.close();
                                    dosRetr.flush();
                                    FTPServer.write(wr,Config.CODE_226);
                                    dosRetr.close();
                                }
                                sdataRetr.close();
                                break;

                            case "STOR":
                                Socket sdataStor = new Socket(this.ipPORT,this.portPORT);
                                File f = new File(response.split(" ")[1]);
                                String filenameStor = f.getName();
                                FTPServer.write(wr,Config.CODE_125);
                                DataInputStream disStor = new DataInputStream(sdataStor.getInputStream());
                                FileOutputStream fosStor = new FileOutputStream(this.directory + "/" + filenameStor);
                                
                                byte[] tmp = new byte[sdataStor.getReceiveBufferSize()];
                                int readbStor = disStor.read(tmp);
                                while(readbStor>0){
                                    fosStor.write(tmp,0,readbStor);
                                    readbStor = disStor.read(tmp);
                                }

                                fosStor.flush();
                                fosStor.close();
                                FTPServer.write(wr,Config.CODE_226);
                                disStor.close();
                                sdataStor.close();
                                break;
                            
                            case "CWD":
                                //System.out.println(response);
                                String targetPath = response.split(" ")[1];
                                if(targetPath.equals(".."))
                                {
                                    //TEST DU CD .. : on se palce dans dirtest et on ressort dans user
                                    //directory = new File("./serv_memory/user/dirtest"); 

					                if(directory.equals(root) || directory.equals(root+"/") || (directory+"/").equals(root))
                                    {
                                        FTPServer.write(wr,Config.CODE_200); 
                                    }
                                    else
                                    {
                                        directory = new File(directory.getParent());
                                    }
                                }
                                else
                                {
                                    File dossier = new File(directory+"/"+targetPath);
                                    if(dossier.exists())
                                    {
                                        directory=new File(dossier.getAbsolutePath());
                                    }
                                }
                                FTPServer.write(wr,Config.CODE_200); 
                            break;
                                
                            case "PORT":
                                String[] split = (response.split(" ")[1]).split(",");

                                // 	Récupération de l'adress IP
                                String ip = split[0];
                                for(int i = 1 ; i <= 3 ; i++){
                                    ip += "." + split[i];
                                }

                                // 	Lecture du port
                                int port = Integer.parseInt(split[4]);
                                port *= 256;
                                port += Integer.parseInt(split[5]);

                                this.portPORT = port;
                                this.ipPORT = InetAddress.getByName(ip);
                                FTPServer.write(wr,Config.CODE_200); //commande ok    
                                break;

                            default:
                                break;
                        }
                    }
                }
                else
                {
                    FTPServer.write(wr,Config.CODE_530);
                    c.close();
                    System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Client " + id + " disconnected : bad password");
                }
            }
            else
            {
                FTPServer.write(wr,Config.CODE_530);
                c.close();
                System.out.println("[" + new Timestamp(date.getTime()) + "] - INFO -" + " Client " + id + " disconnected : bad loggin");
            }
        }
        catch(Exception ex)
        {
            System.err.println("[" + new Timestamp(date.getTime()) + "] - ERROR -" + " An exception occured");
            ex.printStackTrace();
        }
    }
}