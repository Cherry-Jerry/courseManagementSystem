package com.atcyj.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String identity;
    private String institute;
    private String grade;
    private String cls;

    public User() {}

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, String identity, String institute, String grade, String cls) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.institute = institute;
        this.grade = grade;
        this.cls = cls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", institute='" + institute + '\'' +
                ", grade='" + grade + '\'' +
                ", cls='" + cls + '\'' +
                '}';
    }
}
