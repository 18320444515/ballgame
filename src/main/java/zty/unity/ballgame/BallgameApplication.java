package zty.unity.ballgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zty.unity.ballgame.socket.FightServer;
import zty.unity.ballgame.socket.ChatServer;

@SpringBootApplication
public class BallgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BallgameApplication.class, args);
		try {
			ChatServer chatChatServer = new ChatServer();
			chatChatServer.Cstart();
			FightServer fightServer = new FightServer();
			fightServer.Fstart();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

