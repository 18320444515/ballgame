package zty.unity.ballgame.socket;
//我的github:https://github.com/ygj0930
//我的博客：http://www.cnblogs.com/ygj0930/

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是 fork 过来的
 */
public class FightServer implements Runnable
{
	int port = 8877;

	List<Socket> clients;
	ServerSocket server;
	private Thread tt;
	List<Integer> lastId;

	public static void main(String[] args) {
		try {
			new FightServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FightServer() throws IOException {
			clients=new ArrayList<Socket>();
			server=new ServerSocket(port);
			lastId = new ArrayList<Integer>();


	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
		while(true)
		{
			try {
				Socket socket = server.accept();
				clients.add(socket);
				lastId.add(0);
				Mythread mythread=new Mythread(socket);
				mythread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void Fstart() {
		System.out.println("Starting FightServer 0");
		if (tt == null) {
			tt = new Thread (this);
			tt.start ();
		}
	}


	private class Mythread implements Runnable
	{
		Socket ssocket;
		private BufferedReader br;
		private PrintWriter pw;
		public  String msg;
		private Thread t;

		public Mythread(Socket s)
		{
			ssocket=s;
		}

		@Override
		public void run()
		{

			try{
				br = new BufferedReader(new InputStreamReader(ssocket.getInputStream()));

				//msg = "{\"message\":\"" + clients.size() + "\",\"senderId\":0,\"senderName\":\"againWithId\"}";
				// 第一次返回，客户端可以得知自己的匹配序号
				msg = "0_"+clients.size();
				sendMsg();

				while ((msg = br.readLine()) != null) {
					if (msg.startsWith("{")){
						// 将 json 字符串转化成对象
						JSONObject jo = JSONObject.fromObject(msg);

						// 判断是否属于匹配状态
						if (jo.get("senderName").equals("againWithId")){
							// 该玩家的当前序号
							int index = Integer.parseInt((String) jo.get("message"));
							if ( index % 2 != 0) {
								lastId.set(index-1, (Integer) jo.get("senderId"));
								// 玩家1 需要继续等待
								msg = "-1_0";
							}else {
								// 玩家2 和 玩家1 同时开始
								msg = (index-1)+"_"+lastId.get(index-2)+"_"+jo.get("senderId");
							}

						}
					}// 其他为纯数字

					// 返回消息
					sendMsg();

				}
			}catch(IOException ioe)
			{

			}
		}

		public void sendMsg()
		{
			try{
				System.out.println(msg);

				for(int i = clients.size() - 1; i >= 0; i--)
				{
					pw=new PrintWriter(clients.get(i).getOutputStream(),true);
					pw.println(msg);

					pw.flush();
				}

			}catch(Exception ex)
			{

			}
		}

		public void start() {
			System.out.println("Starting FightServer ok");
			if (t == null) {
				t = new Thread (this);
				t.start ();
			}
		}

	}



}