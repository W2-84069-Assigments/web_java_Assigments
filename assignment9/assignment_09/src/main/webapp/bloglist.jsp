<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
     <title>BlogList</title>
     <link rel="stylesheet" href="css/site.css"/>
</head>
<body>
    <a href="blogs" class="linkbtn">All Blogs</a> &nbsp;
    <a href="blogs?userid=${sessionScope.curusr.id}" class="linkbtn">My Blogs</a>&nbsp;
    <a href="createblog" class="linkbtn">Create Blog</a>&nbsp;
    <a href="find" class="linkbtn">Find Blog</a>&nbsp;
    <a href="categories" class="linkbtn">Categories</a>&nbsp;
    <a href="createcategory" class="linkbtn">Create Category</a>&nbsp;
    <a href="logout" class="linkbtn">Sign Out</a> &nbsp;
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
               <!-- <td>${b.categoryId}</td> -->
               <td> ${categoryMap.get(b.categoryId)}  </td>
               <!-- <td>${b.userId}</td> -->
               <td> ${userMap.get(b.userId)}  </td>
                <td>
                    <c:if test="${b.userId==curusr.id}">
                    <a href="editblog?id=${b.id}">Edit</a>
                    <a href="delblog?id=${b.id}"> Delete</a>
                    </c:if>
                </td>
              </tr>
            </c:forEach>
    </table>
</body>
</html>