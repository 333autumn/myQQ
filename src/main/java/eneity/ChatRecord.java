package eneity;

import java.time.LocalDateTime;

public class ChatRecord {
    /**
     * 主键
     */
    private int id;

    /**
     * 创建日期
     */
    private LocalDateTime createdDate;
    /**
     * 发送者qq
     */
    private String senderQq;
    /**
     * 接收者qq
     */
    private String receiverQq;

    /**
     * 聊天内容
     */
    private String chatContent;

    public ChatRecord() {
    }

    public ChatRecord( LocalDateTime createdDate, String senderQq, String receiverQq, String chatContent) {
        this.createdDate = createdDate;
        this.senderQq = senderQq;
        this.receiverQq = receiverQq;
        this.chatContent = chatContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCreatedDate() {
        return createdDate.toString();
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getSenderQq() {
        return senderQq;
    }

    public void setSenderQq(String senderQq) {
        this.senderQq = senderQq;
    }

    public String getReceiverQq() {
        return receiverQq;
    }

    public void setReceiverQq(String receiverQq) {
        this.receiverQq = receiverQq;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String sql() {
        return
                ", createDate=" + createdDate +
                        ", senderQq='" + senderQq + '\'' +
                        ", receiverQq='" + receiverQq + '\'' +
                        ", chatContent='" + chatContent + '\'';
    }
}
