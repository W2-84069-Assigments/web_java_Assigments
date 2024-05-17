package com.sunbeam.boot08jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sunbeam.boot08jdbc.daos.BlogDao;
import com.sunbeam.boot08jdbc.daos.CategoryDao;
import com.sunbeam.boot08jdbc.daos.UserDao;
import com.sunbeam.boot08jdbc.entities.Blog;
import com.sunbeam.boot08jdbc.entities.Category;
import com.sunbeam.boot08jdbc.entities.User;

@SpringBootApplication
public class Boot08jdbcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Boot08jdbcApplication.class, args);
	}

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Override
	public void run(String... args) throws Exception 
	{
		// Category c = new Category(0, "Computers","Regarding Computers");
		// int count = categoryDao.save(c);
		// System.out.println("Rows Affected "+count);


		// int count = categoryDao.deleteById(11);
	    // System.out.println("Rows Affected "+count);

		// Category c=categoryDao.findById(4);
		// System.out.println(c.toString());

		// List<Category> list =categoryDao.findAll();
		// for(Category c:list)
		// 	System.out.println(c);


		// User u= new User(0, "Jaju", "Jaju@gmail", "jaju", "90888", null);
		// System.out.println("Rows affected "+userDao.save(u));

			// User u =userDao.findByEmailAndPassword("nilesh@sunbeaminfo.com","ni");
			// System.out.println(u);

			// Blog b = new Blog(0, "python", "python programming language", 3, 5, null);
			// int n= blogDao.save(b);
			// System.out.println("rows affected "+n);

			// int count=blogDao.deleteById(15);
			// System.out.println("Rows affected "+count);

		// List<Blog> l=	blogDao.findAll();
		// for(Blog b:l)
		// System.out.println(b);
		}
}
