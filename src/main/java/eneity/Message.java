package eneity;

public class Message {

    //接收人qq
    String receiverQq;

    //要发送的消息
    String message;

    public Message() {
    }

    public Message(String receiverQq, String message) {
        this.receiverQq = receiverQq;
        this.message = message;
    }

    public String getReceiverQq() {
        return receiverQq;
    }

    public void setReceiverQq(String receiverQq) {
        this.receiverQq = receiverQq;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
