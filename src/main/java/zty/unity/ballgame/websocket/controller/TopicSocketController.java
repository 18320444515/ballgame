package zty.unity.ballgame.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import zty.unity.ballgame.websocket.model.RequestMessage;
import zty.unity.ballgame.websocket.model.ResponseMessage;

/**
 * 广播信息，凡是订阅了/topic/getResponse路径的信息都能接受到
 */
@Controller
public class TopicSocketController {

    /**
     * 转发游戏大厅里的所有新消息，任何大厅里的人都可以发消息
     * @param message 装配好的 json 字符串（其内部包含发送者的信息）
     * @return json格式的数据，发送给大厅里的所有人
     */
    @MessageMapping("/toLobby")//浏览器发送请求通过@messageMapping 映射/welcome 这个地址。
    @SendTo("/lobby/newMessage")//服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
    public Object say(RequestMessage message)  {
        System.out.println("大厅消息-----------------------" + message.getMessage());
        return message;
    }

}
