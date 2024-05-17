package com.sunbeam.beans;

import com.sunbeam.daos.CategoryDao;
import com.sunbeam.entities.Category;

public class FindCategoryBean {

    private int id;
    private Category category;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
   public void fetchCategory()
    {
        try(CategoryDao categoryDao=new CategoryDao())
        {
            category=categoryDao.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
