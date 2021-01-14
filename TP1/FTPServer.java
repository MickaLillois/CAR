import java.io.*;
import java.lang.*;
import java.net.*;

public class FTPServer {

    public static String login = "ftpclient-test";
    public static String passwd = "testacces";
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
                wr.newLine();
                wr.flush();
                
                response = br.readLine();

                //permet de récupérer juste le username (voir @FTPRequestAnnotation)
                response = response.substring(5);


                if(response.equals(login))
                {
                    System.out.println("OK");
                    message = "331 User name okay, need password.\r\n";
                    wr.write(message);
                    wr.newLine();
                    wr.flush();
                }
                else
                {
                    System.out.println("KO");
                    message = "530 Not logged in.\r\n";
                    wr.write(message);
                    wr.newLine();
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