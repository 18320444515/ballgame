package zty.unity.ballgame.socket;
//我的github:https://github.com/ygj0930
//我的博客：http://www.cnblogs.com/ygj0930/
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * 这是 fork 过来的
 * 用于启动大厅聊天 Socket 通信服务器
 */
public class ChatServer implements Runnable
{
	int port = 7788;
	private Thread tt;
	List<Socket> clients;
	ServerSocket server;

	public static void main(String[] args)
	{
		try {
			new ChatServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ChatServer() throws IOException {
			clients=new ArrayList<Socket>();
			server=new ServerSocket(port);


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
				Socket socket=server.accept();
				clients.add(socket);
				Mythread mythread=new Mythread(socket);
				mythread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void Cstart() {
		System.out.println("Starting ChatServer 0");
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
				msg = "{\"message\":\"came into lobby\",\"senderName\":\""+ssocket.getLocalAddress()+"\",\"senderId\":0}";

				sendMsg();

				while ((msg = br.readLine()) != null) {

//					msg = "【" + ssocket.getInetAddress() + "】说：" + msg;
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
			System.out.println("Starting ChatServer ok");
			if (t == null) {
				t = new Thread (this);
				t.start ();
			}
		}
	}



}