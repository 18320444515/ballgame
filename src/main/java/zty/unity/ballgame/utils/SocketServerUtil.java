package zty.unity.ballgame.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tianyi
 * @date 2018-12-26 13:35
 */
@Component
public class SocketServerUtil {

//    @Value("${mysocket.server-host}")
    private static String SERVER_HOST = "127.0.0.1";

//    @Value("${mysocket.server-port}")
    private static int SERVER_PORT = 7788;

    public static void main(String[] args) {
        try {
            //在本机创建一个服务器 端口号为61666
            ServerSocket soo=new ServerSocket(SERVER_PORT);
            System.out.println("java创建服务器成功...");
            Socket so=soo.accept();//等待连接 连接成功才能往下执行
            InputStream in=so.getInputStream();//获取socket的输入流
            byte[]by=new byte[1024];
            while(true) {
                int len=in.read(by);
                System.out.println("java服务器收到消息 长度+" + len);
                System.out.println(new String(by,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initServer(){
        System.out.println(SERVER_HOST + ":" + SERVER_PORT);
    }
}
