package com.dhu.pojo;

import java.util.Date;

public class lytable {
    public int id;
    public int userId;
    public Date dates;
    public String title;
    public String content;

    public lytable() {
    }

    public lytable(int id, int userId, Date dates, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.dates = dates;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
