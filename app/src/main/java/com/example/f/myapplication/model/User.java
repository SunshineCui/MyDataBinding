package com.example.f.myapplication.model;

public class User {
    public String name;
    public String post;
    public String sign;
    public String workSpace;


    public User(String name, String post, String sign, String workSpace) {
        this.name = name;
        this.post = post;
        this.sign = sign;
        this.workSpace = workSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }
}
