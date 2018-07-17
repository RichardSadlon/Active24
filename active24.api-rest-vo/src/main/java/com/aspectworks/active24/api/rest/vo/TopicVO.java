package com.aspectworks.active24.api.rest.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TopicVO {
    private Long id;
    private String tittle;
    private Date date;

    public TopicVO(Long id, String tittle, Date date) {
        this.id = id;
        this.tittle = tittle;
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

}
