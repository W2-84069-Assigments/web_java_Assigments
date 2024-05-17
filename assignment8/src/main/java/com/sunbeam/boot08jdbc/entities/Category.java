package com.sunbeam.boot08jdbc.entities;

public class Category 
{
    private int id;
    private String title;
    private String descripton;
    public Category() {
    }
    public Category(int id, String title, String descripton) {
        this.id = id;
        this.title = title;
        this.descripton = descripton;
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
    public String getDescripton() {
        return descripton;
    }
    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
    @Override
    public String toString() {
        return "Category [id=" + id + ", title=" + title + ", descripton=" + descripton + "]";
    }

    
}
