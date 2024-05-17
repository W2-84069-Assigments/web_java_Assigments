package com.sunbeam.beans;

import com.sunbeam.daos.BlogDao;

public class DeleteBlogBean {
int bid;

public int getBid() {
    return bid;
}

public void setBid(int bid) {
    this.bid = bid;
}

public DeleteBlogBean() {
}

public int removeBlog()
{
    try(BlogDao blogDao=new BlogDao())
    {
     return  blogDao.deleteById(bid);
    }
    catch(Exception e)
    {
        e.printStackTrace();
        return 0;
    }
}
}
