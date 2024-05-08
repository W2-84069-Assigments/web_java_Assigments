package com.sunbeam;

import java.sql.Timestamp;

public class BlogPojo {
    private int id;
    private int userId;
    private int catId;
    private Timestamp createdOn;
    private String contents;
    private String title;

    public BlogPojo() {
    }
    public BlogPojo(int id, String title2, String contents2, int userId2, int categoryId, Timestamp createdOn) {
        this.id = id;
        this.title = title2;
        this.contents = contents2;
        this.createdOn = createdOn;
        this.catId= categoryId;
        this.userId= userId2;
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
    public int getCatId() {
        return catId;
    }
    public void setCatId(int catId) {
        this.catId = catId;
    }
    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return "BlogPojo [id=" + id + ", userId=" + userId + ", catId=" + catId + ", createdOn=" + createdOn
                + ", contents=" + contents + ", title=" + title + "]";
    }

}


