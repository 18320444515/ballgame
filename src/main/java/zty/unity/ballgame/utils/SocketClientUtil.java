package zty.unity.ballgame.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author tianyi
 * @date 2018-12-26 15:17
 */
public class SocketClientUtil {

    // @Value("${mysocket.server-host}")
    private static String SERVER_HOST = "127.0.0.1";

    // @Value("${mysocket.server-port}")
    private static int SERVER_PORT = 7788;

    public static void main(String[] args) {
        try {
            //第一个参数的服务器的ip 第二个参数是端口号
            Socket so=new Socket(SERVER_HOST,SERVER_PORT);
            System.out.println("java连接服务器成功...");
            OutputStream out=so.getOutputStream();//获取socket的输出流
            Scanner in=new Scanner(System.in);
            while(true) {
                String s=in.nextLine();
                out.write(s.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
