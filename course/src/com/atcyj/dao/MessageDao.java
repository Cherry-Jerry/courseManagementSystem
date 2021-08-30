package com.atcyj.dao;
import com.atcyj.pojo.Message;
import java.util.List;

/**
 * @author chenyujie
 */

public interface MessageDao {
    /**
     * 根据发送者id返回该发送者发送的所有信息
     * @param senderId 发送者id
     * @return 信息列表
     */
    List<Message> queryMessageBySenderId(String senderId);

    /**
     * 根据接受者id返回发给该接受者的所有信息
     * @param receiverId 接受者id
     * @return 信息列表
     */
    List<Message> queryMessageByReceiverId(String receiverId);

    /**
     * 持久化message
     * @param message 要保存的Message对象
     * @return 修改的行数
     */
    int addMessage(Message message);
}
