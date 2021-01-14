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
        System.out.println("Server Socket Ready");
        Socket c = new Socket();

        BufferedReader br;
        BufferedWriter wr;
        String message = "";

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
                wr.newLine();
                wr.close();
                
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