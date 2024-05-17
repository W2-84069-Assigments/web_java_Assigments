<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
     <title>BlogList</title>
</head>
<body>
    <a href="blogs">All Blogs</a> &nbsp;
    <a href="blogs?userid=${sessionScope.curusr.id}">My Blogs</a>&nbsp;
    <a href="createblog">Create Blog</a>&nbsp;
    <a href="find">Find Blog</a>&nbsp;
    <a href="categories">Categories</a>&nbsp;
    <a href="createcategory">Create Category</a>&nbsp;
    <a href="logout">Sign Out</a> &nbsp;
    <br/><br/>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Category</th>
            <th>User</th>
            <th>Action</th>

        </tr>
        <c:forEach var="b" items="${blogList}">
            <tr>
                <td>${b.id}</td>
                <td>${b.title}</td>
               <td> ${b.category.title}  </td>
               <td> ${b.user.name}  </td>
                <td>
                    <a href="">Edit</a>
                    <a href=""> Delete</a>
                </td>
              </tr>
            </c:forEach>
    </table>
</body>
</html>