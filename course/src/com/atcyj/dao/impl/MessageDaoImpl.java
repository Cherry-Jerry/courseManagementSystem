package com.atcyj.dao.impl;

import com.atcyj.dao.MessageDao;
import com.atcyj.pojo.Message;
import java.util.List;

/**
 * @author chenyujie
 */
public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public List<Message> queryMessageBySenderId(String senderId) {
        String sql = "select * from t_message where senderId=?";
        return queryForList(Message.class,sql,senderId);
    }

    @Override
    public List<Message> queryMessageByReceiverId(String receiverId) {
        String sql = "select * from t_message where receiverId=?";
        return queryForList(Message.class,sql,receiverId);
    }

    @Override
    public int addMessage(Message message) {
        String sql = "insert into t_message (senderId,receiverId,message) value(?,?,?)";
        return update(sql,message.getSenderId().toString(),message.getReceiverId().toString(),message.getMessage());
    }
}
