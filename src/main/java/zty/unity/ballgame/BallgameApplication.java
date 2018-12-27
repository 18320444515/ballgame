package zty.unity.ballgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zty.unity.ballgame.socket.FightServer;
import zty.unity.ballgame.socket.Server;
import zty.unity.ballgame.utils.SocketServerUtil;

@SpringBootApplication
public class BallgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BallgameApplication.class, args);
		try {
			Server chatServer = new Server();
			chatServer.Cstart();
			FightServer fightServer = new FightServer();
			fightServer.Fstart();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

