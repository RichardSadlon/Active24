package com.aspectworks.active24.api.rest.vo;

import javax.persistence.*;

@Entity
public class CommentVO {
    String text;
    String userName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
