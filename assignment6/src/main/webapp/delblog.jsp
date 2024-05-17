<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <title>Delete blog</title>
   
</head>
<body>
    <h3> ${initParam.appTitle} </h3>
    <jsp:useBean id="dbb" class="com.sunbeam.beans.DeleteBlogBean" />
    <jsp:setProperty name="dbb" property="bid" param="id"  />
    <c:choose>
    <c:when test="${dbb.removeBlog()==1}">
        Blog deleted succesfully
        </c:when>
        <c:otherwise>
            Blog Deletion failed
        </c:otherwise>
    </c:choose>
<br/><br/><br/>
    <a href="bloglist.jsp">go back</a>
</body>
</html>