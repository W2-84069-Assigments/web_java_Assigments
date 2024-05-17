<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
     <title>Category List</title>
</head>
<body>
    <h3> ${initParam.appTitle} </h3>
    <jsp:useBean id="clb" class="com.sunbeam.beans.CategoryListBean" />

        ${ clb.fetchCategories() }


    <table border="1">
    <tr>
        <th>Title</th>
        <th>Description</th>

    </tr>
     <c:forEach var="c" items="${clb.list}" >
        <tr>
     <td> ${c.title}  </td>
     <td>${c.description}</td>
        </tr>    
    </c:forEach>
</table>
<br/>
<br/>
<a href="bloglist.jsp">Go Back</a>
</body>
</html>