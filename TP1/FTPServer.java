import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.Date;
import java.sql.Timestamp;


public class FTPServer {

    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        ServerSocket s = new ServerSocket(port);
        Date date = new Date();
        System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Server ON");
       
        Socket c = new Socket(); 
        int id = 1;

        try
        {
            while(true)
            {
                c = s.accept();
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
}

class MyRunnableThread implements Runnable 
{
    Socket c = new Socket();
    int id = 0;


    public static String login = "user";
    public static String passwd = "pwd";
    public static final String CODE_215 = "215 Remote system type is UNIX.\r\n";
    public static final String CODE_220 = "220 Service ready for new user.\r\n";
    public static final String CODE_221 = "221 Service closing control connection.\r\n";
    public static final String CODE_230 = "230 User logged in.\r\n"; 
    public static final String CODE_331 = "331 User name okay, need password.\r\n";
    public static final String CODE_530 = "530 Not logged in.\r\n";


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
            System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Client " + id + " connected");

            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            wr = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));

            FTPServer.write(wr,CODE_220);
            response = br.readLine();
            //permet de récupérer juste le username (voir @FTPRequestAnnotation)
            response = (response.split(" ")[1]);

            if(response.equals(login))
            {
                FTPServer.write(wr,CODE_331);
                response = br.readLine();
                response = (response.split(" ")[1]);
                if(response.equals(passwd))
                {    
                    FTPServer.write(wr,CODE_230);     
                    FTPServer.write(wr,CODE_215);   

                    System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Client " + id + " logged in");
                    
                    while(!c.isClosed())
                    {
                        response = br.readLine();
                        switch(response.split(" ")[0])
                        {
                            case "QUIT":
                                FTPServer.write(wr,CODE_221);
                                c.close();

                                System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Client " + id + " quitted");
                                break;
                            default:
                                break;
                        }
                    }
                }
                else
                {
                    FTPServer.write(wr,CODE_530);
                    c.close();

                    System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Client " + id + " disconnected : bad password");
                }
            }
            else
            {
                FTPServer.write(wr,CODE_530);
                c.close();

                System.out.println("[" + new Timestamp(date.getTime()) + "] INFO -" + " Client " + id + " disconnected : bad loggin");
            }
        }
        catch(Exception ex)
        {
            System.err.println("[" + new Timestamp(date.getTime()) + "] ERROR -" + " An exception occured");
            ex.printStackTrace();
        }
    }
}