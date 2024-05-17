package com.sunbeam.entities;

import java.sql.Timestamp;

public class Blog {
    private int id;
    private String title;
    private String contents;
    private int userId;
    private int categoryId;
    private Timestamp createdOn;
    public Blog() {
    }
    public Blog(int id, String title, String contents, int userId, int categoryId, Timestamp createdOn) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.categoryId = categoryId;
        this.createdOn = createdOn;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    @Override
    public String toString() {
        return "Blog [id=" + id + ", title=" + title + ", contents=" + contents + ", userId=" + userId + ", categoryId="
                + categoryId + ", createdOn=" + createdOn + "]";
    }

    
}
