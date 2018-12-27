package zty.unity.ballgame.socket;//我的github:https://github.com/ygj0930
//我的博客：http://www.cnblogs.com/ygj0930/
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import zty.unity.ballgame.websocket.model.RequestMessage;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
	public int port = 8877;
	Socket socket = null;
	
	public static void main(String[] args)
	{
		new Client();
	}
	
	public Client()
	{
		 
		try {  
		    socket=new Socket("118.24.117.188",port);
			
			new Cthread().start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket  
                  .getInputStream()));
            String msg1;  
            while ((msg1 = br.readLine()) != null) {
                System.out.println(msg1);  
			}

		}catch (Exception e) {  
  
		}
			
		
	}

	class Cthread extends Thread
	{
		@Override
		public void run() {
			try {

				BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				String msg2;
  
                while (true) {  
				
                    msg2 = re.readLine();
//					RequestMessage toSend = new RequestMessage();
//					toSend.setMessage(msg2);
//					toSend.setSenderId(0);
//					toSend.setSenderName("hello");
					if (msg2.startsWith("{")) {
						pw.println(JSONObject.fromObject(msg2));
					}else {
						pw.println(msg2);
					}
                }  
            }catch (Exception e) {  
                e.printStackTrace();  
            }
		
	
		}
	}

}