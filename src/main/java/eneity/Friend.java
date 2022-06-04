package eneity;

import java.time.LocalDate;

public class Friend {
    private int id;      //主键
    private boolean isAlive;        //是否存在
    private LocalDate createdDate;    //创建日期
    private int groupingId;       //分组id
    private String selfQQ;      //自己的qq
    private String friendQQ;       //好友的qq

    public Friend() {
    }

    public Friend( boolean isAlive, LocalDate createdDate, int groupingId, String selfQQ, String friendQQ) {
        this.isAlive = isAlive;
        this.createdDate = createdDate;
        this.groupingId = groupingId;
        this.selfQQ = selfQQ;
        this.friendQQ = friendQQ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(int groupingId) {
        this.groupingId = groupingId;
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

    public String getSql() {
        return
                "isAlive=" + isAlive +
                ", createdDate=" + createdDate +
                ", groupingId=" + groupingId +
                ", selfQQ='" + selfQQ + '\'' +
                ", friendQQ='" + friendQQ + '\'';
    }
}
