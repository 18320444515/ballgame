package zty.unity.ballgame.websocket.model;

import lombok.Data;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/28 下午5:47
 * @Version: 1.0
 */
@Data
public class ResponseMessage {

    public ResponseMessage(String message, int id,String name) {
        this.message = message;
        this.senderId = id;
        this.senderName = name;
    }

    /**
     * 响应消息
     */
    private String message;

    private String senderName;

    private int senderId;
}
