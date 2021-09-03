package com.atcyj.dao.impl;

import com.atcyj.dao.MessageDao;
import com.atcyj.pojo.Message;
import java.util.List;

/**
 * @author chenyujie
 */
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {
    @Override
    public List<Message> queryMessageBySenderId(String senderId) {
        String sql = "select * from t_message where sender_id=?";
        return queryForList(Message.class,sql,senderId);
    }

    @Override
    public List<Message> queryMessageByReceiverId(String receiverId) {
        String sql = "select * from t_message where receiver_id=?";
        return queryForList(Message.class,sql,receiverId);
    }

    @Override
    public int addMessage(Message message) {
        String sql = "insert into t_message (sender_id,receiver_id,message) value(?,?,?)";
        return manipulate(sql,message.getSenderId(),message.getReceiverId(),message.getMessage());
    }
}
