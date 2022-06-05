package eneity;


import java.time.LocalDateTime;

public class User {
    private String username;
    private String gender;
    private Integer age;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;
    private Integer loginInfoId;
    private String qq;
    private String password;
    private String avatar;
    private String email;
    private String signature;


    public User() {
    }

    public User(String username, String gender, Integer age, LocalDateTime createDate, LocalDateTime updatedDate,
                Integer loginInfoId, String qq, String password, String avatar, String email, String signature) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.createDate = createDate;
        this.updatedDate = updatedDate;
        this.loginInfoId = loginInfoId;
        this.qq = qq;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.signature = signature;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateDate() {
        return createDate.toString();
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdatedDate() {
        return updatedDate.toString();
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Integer loginInfoId) {
        this.loginInfoId = loginInfoId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String sql() {
        return
                "username='" + username + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", createDate=" + createDate +
                ", updatedDate=" + updatedDate +
                ", loginInfoId=" + loginInfoId +
                ", qq='" + qq + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' ;
    }
}
