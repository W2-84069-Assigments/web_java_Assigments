<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Blog List</title>
    <link rel="stylesheet" href="css/site.css">
</head>
<body>
    <h3> ${initParam.appTitle} </h3>
   <%--
    <jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session" />
    Hello, <jsp:getProperty name="lb" property="email" />
--%>

Hello, ${lb.user.name} <hr/>

<a href="bloglist.jsp">All blogs</a>&nbsp;
<a href="bloglist.jsp?userid=${lb.user.id}">My blogs</a>&nbsp;
<a href="newblog.jsp">Create blogs</a>&nbsp;
<a href="findblog.jsp">Find blogs</a>&nbsp;
<a href="categories.jsp">Show Categories</a>&nbsp;
<a href="newcategory.jsp">Create Category</a>&nbsp;
<a href="logout.jsp">Sign out</a>&nbsp;
<br/>
<br/>



<jsp:useBean id="fcb" class="com.sunbeam.beans.FindCategoryBean" />

<jsp:useBean id="blb" class="com.sunbeam.beans.BlogListBean" />

<jsp:useBean id="fub" class="com.sunbeam.beans.FindUserBean"/>

<jsp:setProperty name="blb" property="userId" param="userid" />

${ blb.fetchBlogs() }
<table border="1">
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Category</th>
        <th>User</th>
        <th>Created</th>
        <th>Action</th>
    </tr>

    <c:forEach var="b" items="${blb.blogList}" >
        <jsp:setProperty name="fcb" property="id" value="${b.categoryId}" />
        ${fcb.fetchCategory()}
        <jsp:setProperty name="fub" property="id" value="${b.userId}"/>
        ${fub.fetchName()}
    <tr>
        <td>${b.id}</td>
        <td>${b.title}</td>
        <td>${fcb.category.title}</td>
       <!-- <td>${b.userId}</td>   -->
       <td>${fub.user.name}</td>
        <td>
            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${b.createdOn}" />
        </td>
        <td>
            <!-- <a href="editblog.jsp?id=${b.id}">Edit </a>
            <a href="delblog.jsp?id=${b.id}">Delete</a> -->
        
         <c:if test="${b.userId==lb.user.id}">
            <a href="editblog.jsp?id=${b.id}">Edit</a>
                <a href="delblog.jsp?id=${b.id}">Delete</a>

         </c:if>
        </td>

    </tr>

    </c:forEach>
</table>

</body>
</html>