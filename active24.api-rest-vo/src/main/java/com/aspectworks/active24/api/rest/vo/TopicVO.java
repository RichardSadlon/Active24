package com.aspectworks.active24.api.rest.vo;

import java.util.Date;

public class TopicVO {

    private String name;
    private Date date;
    private String text;

    public TopicVO() {
    }

    public TopicVO(TopicEntity topicEntity) {
        this.name = topicEntity.getName();
        this.date = topicEntity.getDate();
        this.text = topicEntity.getText();
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

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}