import java.io.*;
import java.lang.*;
import java.net.*;

public class FTPServer {

    public static String login = "user";
    public static String passwd = "pwd";
    public static boolean serverOn = true;


    public static final String CODE_215 = "215 Remote system type is UNIX.\r\n";
    public static final String CODE_220 = "220 Service ready for new user.\r\n";
    public static final String CODE_221 = "221 Service closing control connection.\r\n";
    public static final String CODE_230 = "230 User logged in.\r\n"; 
    public static final String CODE_331 = "331 User name okay, need password.\r\n";
    public static final String CODE_530 = "530 Not logged in.\r\n";

    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        ServerSocket s = new ServerSocket(port);
        System.out.println("Server Socket Ready");
        Socket c = new Socket();

        BufferedReader br;
        BufferedWriter wr;
        String message = "";
        String response = "";

        try
        {
            while(serverOn)
            {
                c = s.accept();
                
                System.out.println("Client connected");

                br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                wr = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
                
                write(wr,CODE_220);
                response = br.readLine();
                //permet de récupérer juste le username (voir @FTPRequestAnnotation)
                response = (response.split(" ")[1]);

                if(response.equals(login))
                {
                    write(wr,CODE_331);
                    response = br.readLine();
                    response = (response.split(" ")[1]);
                    if(response.equals(passwd))
                    {    
                        write(wr,CODE_230);     
                        write(wr,CODE_215);   
                        
                        while(serverOn)
                        {
                            response = br.readLine();
                            switch(response.split(" ")[0])
                            {
                                case "QUIT":
                                    write(wr,CODE_221);
                                    serverOn = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    else
                    {
                        write(wr,CODE_530);
                        serverOn = false;
                    }
                }
                else
                {
                    write(wr,CODE_530);
                    serverOn = false;
                }

            }
        }
        catch(Exception ex)
        {
            System.out.println("An error occured.");
        }
        finally{
            c.close();
            System.out.println("Client disconnected");
            s.close();
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