package zty.unity.ballgame.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import zty.unity.ballgame.websocket.model.RequestMessage;
import zty.unity.ballgame.websocket.model.ResponseMessage;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/29 下午1:48
 * @Version: 1.0
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // IoC 注入工具类

//    /**
//     * 提供一个转发消息的渠道，将用户发过来的消息原封不动地转发到其他订阅者（游戏进行时的对手）
//     * @param message 来自玩家的数据（已装配好的 json 字符串）
//     * @param playerId 当前玩家的id
//     * @return json格式的数据，发送给当前对战的双方
//     */
//    @MessageMapping("/byPlayer/{playerId}")   // 这样是不行的
//    public Object toPlayer(RequestMessage message,
//                                    @PathVariable(name = "playerId")int playerId) {
//        System.out.println("player "+playerId+" ----------------" + message.getMessage());
//        this.messagingTemplate.convertAndSendToUser(""+playerId,"/say", message.getMessage());
//        return message;
//    }

    /**
     * 主要功能为实现玩家之间的广播（即对点式即时通信）
     * @param message 已经装配好的 json 字符串
     * @return json原样返回
     */
    @MessageMapping("/byPlayer")
    public Object toPlayer(RequestMessage message) {
        System.out.println("player "+message.getSenderId()+" ----------------" + message.getMessage());
        this.messagingTemplate.convertAndSendToUser(""+message.getSenderId(),"/say", message);
        return message;
    }

}
