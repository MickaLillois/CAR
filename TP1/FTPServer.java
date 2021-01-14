import java.io.*;
import java.lang.*;
import java.net.*;

public class FTPServer {

    public String login = "ftpclient-test";
    public String passwd = "testacces";
    public static boolean serverOn = true;

    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        //InetAddress address = "127.0.0.1";
        ServerSocket s = new ServerSocket(port);
        InputStreamReader reader;
        System.out.println("Server Socket Ready");
        Socket c = new Socket();
        try
        {
            while(serverOn)
            {
                c = s.accept();
                System.out.println("Client connected");
                reader = new InputStreamReader(c.getInputStream());
                

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