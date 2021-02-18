import java.io.*;
import java.lang.*;
import java.net.*;

public class Config {

    public static String login = "micka";
    public static String passwd = "pwd";
    public static final String CODE_125 = "125 Data connection already open; transfer starting.\r\n";
    public static final String CODE_150 = "150 File status okay; about to open data connection.\r\n";
    public static final String CODE_200 = "200 The requested action has been successfully completed.\r\n";
    public static final String CODE_215 = "215 Remote system type is UNIX.\r\n";
    public static final String CODE_220 = "220 Service ready for new user.\r\n";
    public static final String CODE_221 = "221 Service closing control connection.\r\n";
    public static final String CODE_226 = "226 Closing data connection. Requested file action successful (for example, file transfer or file abort).\r\n";
    public static final String CODE_230 = "230 User logged in.\r\n"; 
    public static final String CODE_331 = "331 User name okay, need password.\r\n";
    public static final String CODE_404 = "404 File not found.\r\n";
    public static final String CODE_530 = "530 Not logged in.\r\n";

    public Config(){

    }
}