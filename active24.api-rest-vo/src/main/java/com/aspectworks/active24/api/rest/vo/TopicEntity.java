package com.aspectworks.active24.api.rest.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicEntity {
    private Long id;
    private String tittle;
    private List<Comment> comments;
    private Date date;

    public TopicEntity(Long id, String tittle) {
        this.id = id;
        this.tittle = tittle;
        this.comments = new ArrayList<>();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
