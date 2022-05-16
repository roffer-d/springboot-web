package com.roffer.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Roffer
 * @description websocket的处理类, 作用相当于HTTP请求中的controller
 * @date 2022/5/13 15:12
 */
@Component
@Service
@Slf4j
@ServerEndpoint("/api/websocket/{userId}")
public class WebSocketServer {
    /** 踢出登录用户标识 **/
    public static final String OFF_LINE_USER = "OFF_LINE_USER";

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //加入set中
            webSocketMap.put(userId, this);
        } else {
            //加入set中
            webSocketMap.put(userId, this);
            //在线数加1
            addOnlineCount();
        }
        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
        Map<String, Object> msg = new HashMap<>();
        msg.put("type", "init");
        msg.put("data", new HashMap(){{
            put("connectCount",getOnlineCount());
            put("userId",userId);
        }});
        sendMessage(msg);
    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     *
     * @param message 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                String toUserId = jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
                    webSocketMap.get(toUserId).sendMessage(message);
                } else {
                    //否则不在这个服务器上，发送到mysql或者redis
                    log.error("请求的userId:" + toUserId + "不在该服务器上");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务
     * 器主动推送
     */
    public void sendMessage(Object message) {
        try {
            this.session.getBasicRemote().sendText(JSON.toJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param message 消息对象
     * @param userId  发送对象
     * @description 发送消息给指定对象
     * @author Roffer
     * @date 2022/5/13 16:33
     */
    public static void sendToUser(String handlerKey, String userId, Object message) {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("type", handlerKey);
            msg.put("data", message);
            webSocketMap.get(userId).sendMessage(msg);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * @param handlerKey 前端处理消息对应的key
     * @param message    消息对象
     * @description 发送消息给所有对象
     * @author Roffer
     * @date 2022/5/13 16:33
     */
    public static void sendToAll(String handlerKey, Object message) {
        log.info("推送给所有人的消息，内容:" + JSON.toJSONString(message));

        for (String key : webSocketMap.keySet()) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("type", handlerKey);
            msg.put("data", message);
            webSocketMap.get(key).sendMessage(msg);
        }
    }

    /**
     * 获得此时的
     * 在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 在线人
     * 数加1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 在线人
     * 数减1
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}


