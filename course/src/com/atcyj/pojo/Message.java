package com.atcyj.pojo;
/**
 * @author chenyujie
 */
public class Message {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private String message;

    public Message() {}

    public Message(Integer senderId, Integer receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", message='" + message + '\'' +
                '}';
    }
}
