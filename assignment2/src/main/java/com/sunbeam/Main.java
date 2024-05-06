package com.sunbeam;

import java.util.List;
import java.util.Scanner;

// Hackathon Implementation -- Assignment
public class Main {
    // you may keep whole user object here
    public static User CUR_USER = null;
    public static void userMenu(Scanner sc) {
        int choice;
        do {
            System.out.print("\n0. Exit\n1. Show Categories\n2. Create Blog\n 3.Add category \n"+
            "4.Display blogs for logged in user\n"+ "5.Display all blogs\n"+"6.Delete blog by blogid\n"+
             "7. Search blog by BlogId\n" +"8.Find blog by word\n"+"9.find blog by category id \n"+
             "10. update blog "+"\nEnter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 0: // Log out
                    CUR_USER = null;
                    break;
                case 1: // Show Categories
                    displayCategories(sc);
                    break;
                case 2: // Create Blog
                    createNewBlog(sc);
                    break;
                case 3 ://Add new category
                    addCategory(sc);
                break;
                case 4 : // Edit blog
                   searchBlogByUserId(sc); 
                break;
                case 5 ://display all blogs
                  displayallblogs(sc);
                break;
                case 6://delete blog
                    deleteBlogById(sc);
                break;
                case 7:
                    searchBlogById(sc);
                    break;
                case 8:
                    searchBlogByContent(sc);
                    break;
                case 9 :
                    searchBlogByCatId(sc);
                    break;
                case 10 :
                    editBlogByUserId(sc);
                    break;

            }
        }while(choice != 0);
    }

    private static void editBlogByUserId(Scanner sc) {
        if(sc.hasNextLine())
            sc.nextLine(); // read and discard \n
        try(BlogDao blogDao = new BlogDao()) {
            System.out.println("Enter blog id u want to edit");
            int b_id= sc.nextInt();
            int check_id = CUR_USER.getId();
            sc.nextLine();
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter contents: ");
            String contents = sc.nextLine();
            int userId = CUR_USER.getId();
            System.out.print("Enter category id: ");
            int categoryId = sc.nextInt();
            Blog b = new Blog(b_id, title, contents, userId, categoryId, null);
            int count = blogDao.update(b, check_id);
            if(count==0)
            {
                System.out.println("User id and blog id mismatch");
            }
            else{
            System.out.println("Rows Affected: " + count);
            }
        } // userDao.close();
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    private static void searchBlogByCatId(Scanner sc) {
        try (BlogDao bd = new BlogDao()) {
            System.out.println("Enter category id for blogs you want to see");
            int cat_Id= sc.nextInt();
            List <Blog> list =bd.findByCategoryId(cat_Id);
            
            for(Blog c:list)
                System.out.println(c);
        } // catDao.close();
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchBlogByContent(Scanner sc) {
        try (BlogDao bd = new BlogDao()) {
            System.out.println("Enter word for which  you want to search for blog:");
            sc.nextLine();
            String blog_word= sc.nextLine();
            List <Blog> list =bd.findByLikeContent(blog_word);
            
            for(Blog c:list)
                System.out.println(c);
        } // catDao.close();
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void searchBlogById(Scanner sc) {
        try (BlogDao bd = new BlogDao()) {
            System.out.println("Enter blog id you want to see");
            int blog_Id= sc.nextInt();
            List <Blog> list =bd.findBlogById(blog_Id);
            
            for(Blog c:list)
                System.out.println(c);
        } // catDao.close();
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void deleteBlogById(Scanner sc)
    {
        try(BlogDao bd = new BlogDao())
        {
            System.out.println("Enter blog id you want to delete :");
            int blogId=sc.nextInt();
            int userid = CUR_USER.getId();
            int count=bd.deleteById(blogId, userid);
            System.out.println("Rows affected "+count);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    private static void displayallblogs(Scanner sc) {
        try (BlogDao bd = new BlogDao()) {
            List<Blog> list = bd.findAll();
            for(Blog c:list)
                System.out.println(c);
        } // catDao.close();
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayCategories(Scanner sc) {
        try (CategoryDao catDao = new CategoryDao()) {
            List<Category> list = catDao.findAll();
            for(Category c:list)
                System.out.println(c);
        } // catDao.close();
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createNewBlog(Scanner sc) {
        if(sc.hasNextLine())
            sc.nextLine(); // read and discard \n
        try(BlogDao blogDao = new BlogDao()) {
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter contents: ");
            String contents = sc.nextLine();
            int userId = CUR_USER.getId();
            System.out.print("Enter category id: ");
            int categoryId = sc.nextInt();
            Blog b = new Blog(0, title, contents, userId, categoryId, null);
            int count = blogDao.save(b);
            System.out.println("Rows Affected: " + count);
        } // userDao.close();
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    private static void addCategory(Scanner sc) {
        if(sc.hasNextLine())
            sc.nextLine(); // read and discard \n
        try(CategoryDao catDao = new CategoryDao()) {
            System.out.print("Enter category title: ");
            String title = sc.nextLine();
            System.out.print("Enter description of category: ");
            String desc = sc.nextLine();
            
            Category c =  new Category(0, title, desc);
            int count = catDao.save(c);
            System.out.println("Rows Affected: " + count);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
       
    private static void searchBlogByUserId(Scanner sc) {
        try (BlogDao bdao = new BlogDao()) {
            int user_id=CUR_USER.getId();
            List <Blog>list= bdao.findBlogByUserId(user_id);
            for(Blog b : list)
               System.out.println( b.toString());
         }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void mainMenu(Scanner sc) {
        int choice;
        do {
            System.out.print("\n0. Exit\n1. Sign In\n2. Sign Up\nEnter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: // Sign In
                    CUR_USER = authenticate(sc);
                    if(CUR_USER != null)
                        userMenu(sc);
                    else
                        System.out.println("Invalid Login.");
                    break;
                case 2: // Sign Up
                    registerUser(sc);
                    break;
            }
        }while(choice != 0);
    }

    private static void registerUser(Scanner sc) {
        if(sc.hasNextLine())
            sc.nextLine(); // read and discard \n
        try(UserDao userDao = new UserDao()) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String passwd = sc.nextLine();
            System.out.print("Enter phone: ");
            String phone = sc.nextLine();
            User u = new User(0, name, email, passwd, phone, null);
            int count = userDao.save(u);
            System.out.println("Rows Affected: " + count);
        } // userDao.close();
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static User authenticate(Scanner sc) {
        if(sc.hasNextLine())
            sc.nextLine(); // read and discard \n
        String email, password;
        System.out.print("Enter email: ");
        email = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        try(UserDao userDao = new UserDao()) {
            User user = userDao.findByEmailAndPassword(email, password);
            return user;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenu(sc);
        sc.close();
    }
}