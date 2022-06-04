package eneity;

import java.time.LocalDateTime;

public class ChatRecord {
    /**
     * 主键
     */
    private int id;
    /**
     * 更新日期
     */
    private LocalDateTime updatedDate;
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

    public ChatRecord( LocalDateTime updatedDate, LocalDateTime createdDate, String senderQq, String receiverQq, String chatContent) {
        this.updatedDate = updatedDate;
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

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
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

    public String getSql() {
        return
                " updateDate=" + updatedDate +
                ", createDate=" + createdDate +
                ", senderQq='" + senderQq + '\'' +
                ", receiverQq='" + receiverQq + '\'' +
                ", chatContent='" + chatContent + '\'';
    }
}
