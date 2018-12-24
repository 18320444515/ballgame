package zty.unity.ballgame.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/5/29 上午10:41
 * @Version: 1.0
 */
@Configuration
@EnableWebSocketMessageBroker//注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /***
     * 注册 Stomp的终端
     * addEndpoint：添加STOMP协议的终端。提供WebSocket或SockJS客户端访问的地址
     * withSockJS：使用SockJS协议
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/C_lobby") // 广播式 stompClient 的注册方式
                .withSockJS() ;

        registry.addEndpoint("/C_player") // 对点式stompClient 的注册方式
                .setAllowedOrigins("*")   // 添加允许跨域访问
                .withSockJS() ;
    }

    /**
     * 配置消息代理
     * 启动Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //registry.enableSimpleBroker("/player", "/lobby");  // 推送消息前缀（可有可无）
        registry.setApplicationDestinationPrefixes("/send");  // 玩家发消息的前缀 服务器听消息
        registry.setUserDestinationPrefix("/player");  // 推送玩家信息的前缀
    }

}
