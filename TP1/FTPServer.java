import java.io.*;
import java.lang.*;
import java.net.*;

public class FTPServer {

    public static String login = "user";
    public static String passwd = "pwd";
    public static boolean serverOn = true;

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
                
                message = "220 Service ready for new user.\r\n";
                wr.write(message);
                wr.flush();
                
                response = br.readLine();

                //permet de récupérer juste le username (voir @FTPRequestAnnotation)
                response = (response.split(" ")[1]);
                System.out.println(response);

                if(response.equals(login))
                {
                    message = "331 User name okay, need password.\r\n";
                    wr.write(message);
                    wr.flush();
                    response = br.readLine();
                    response = (response.split(" ")[1]);
                    if(response.equals(passwd))
                    {
                        message = "230 User logged in, proceed. Logged out if appropriate.\r\n";    
                        wr.write(message);
                        wr.flush();   
                        response = br.readLine();
                        System.out.println(response);
                    }
                    else
                    {
                        message = "530 Not logged in.\r\n";
                        wr.write(message);
                        wr.flush();
                        serverOn = false;
                    }
                }
                else
                {
                    message = "530 Not logged in.\r\n";
                    wr.write(message);
                    wr.flush();
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
}