package com.aspectworks.active24.api.rest.vo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TopicEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String name;
    private Date date;
    private String text;

    @OneToMany(cascade=CascadeType.ALL)
    List<CommentVO> commentVOS = new ArrayList<>();

    public TopicEntity() {}

    public TopicEntity(TopicVO topic) {
        this.name = topic.getName();
        this.date = topic.getDate();
        this.text = topic.getText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date dateCreated) {
        this.date = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public List<CommentVO> getCommentVOS() {
        return commentVOS;
    }

    public void setCommentVOS(List<CommentVO> commentVOS) {
        this.commentVOS = commentVOS;
    }

    public void addComment(CommentVO comment) {
        commentVOS.add(comment);
    }
}