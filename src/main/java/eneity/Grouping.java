package eneity;

import java.time.LocalDate;

public class Grouping {
    private int id;   //主键
    private LocalDate createdDate;    //创建日期
    private String name;   //分组名称
    private String userQQ;   //所有者qq

    public Grouping() {
    }

    public Grouping(LocalDate createdDate, String name, String userQQ) {
        this.createdDate = createdDate;
        this.name = name;
        this.userQQ = userQQ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public String sql() {
        return
                "createdDate=" + createdDate +
                        ", name='" + name + '\'' +
                        ", userQQ='" + userQQ + '\'';
    }
}
