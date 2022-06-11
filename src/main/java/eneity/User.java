package eneity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class User {
    private String username;
    private String qq;
    private String password;
    /**
     * 创建时间
     */
    private LocalDateTime createDate;


    public User() {
    }

    public User(String username, String qq, String password) {
        this.username = username;
        this.qq = qq;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String time(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00").format(createDate);
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String sql() {
        return
                "username='" + username + '\'' +
                ", qq='" + qq + '\'' +
                ", password='" + password + '\'' +
                ", createDate='" + time() +"'";
    }

}
