package eneity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Friend {
    private int id;      //主键
    private LocalDate createdDate;    //创建日期
    private String selfQQ;      //自己的qq
    private String friendQQ;       //好友的qq

    public Friend() {
    }

    public Friend(  LocalDate createdDate, String selfQQ, String friendQQ) {
        this.createdDate = createdDate;
        this.selfQQ = selfQQ;
        this.friendQQ = friendQQ;
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

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }


    public String getSelfQQ() {
        return selfQQ;
    }

    public void setSelfQQ(String selfQQ) {
        this.selfQQ = selfQQ;
    }

    public String getFriendQQ() {
        return friendQQ;
    }

    public void setFriendQQ(String friendQQ) {
        this.friendQQ = friendQQ;
    }

    public String time(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00").format(createdDate);
    }

    public String sql() {
        return
                "createdDate='" + time() +
                "', selfQQ='" + selfQQ + '\'' +
                ", friendQQ='" + friendQQ + '\'';
    }
}
