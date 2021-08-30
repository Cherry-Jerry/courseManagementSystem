package com.atcyj.service;

import com.atcyj.pojo.Message;
import com.atcyj.pojo.User;

import java.util.List;

/**
 * @author chenyujie
 */
public interface MessageService {

    /**
     * 发送一则信息
     * @param sender 发送者
     * @param receiver 接收者
     * @param msg 信息
     * @return 发送信息数
     */

    int sendMessage(User sender, User receiver, String msg);

    /**
     * 查询收到的信息
     * @param receiver 接收者
     * @return 信息列表
     */
    List<Message> showMessageTo(User receiver);

    /**
     * 查询发送的信息
     * @param sender 发送者
     * @return 信息列表
     */
    List<Message> showMessageFrom(User sender);


}
