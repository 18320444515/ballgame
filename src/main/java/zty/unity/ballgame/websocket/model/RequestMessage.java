package zty.unity.ballgame.websocket.model;

import lombok.Data;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/28 下午5:46
 * @Version: 1.0
 */
@Data
public class RequestMessage {

    /***
     * 请求消息
     */
    private String message;

    private String senderName;

    private int senderId;

}
