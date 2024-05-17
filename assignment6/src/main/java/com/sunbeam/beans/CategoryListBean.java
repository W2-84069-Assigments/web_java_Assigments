package com.sunbeam.beans;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CategoryDao;
import com.sunbeam.entities.Category;

public class CategoryListBean 
 {
    private List<Category> list = new ArrayList<>();

    public CategoryListBean() {
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public void fetchCategories(){
        try(CategoryDao categoryDao=new CategoryDao())
        {
                list=categoryDao.findAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

 }
