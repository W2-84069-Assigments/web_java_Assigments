<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>
    <h3>${initParam.appTitle}</h3>
  <jsp:useBean id="lb" class="com.sunbeam.beans.LoginBean" scope="session" />
  <jsp:setProperty name="lb" property="*" />
  ${ lb.authenticate()}
  

        <c:choose>
            <c:when test="${lb.user!=null}">
                <c:redirect url="bloglist.jsp"  />
            </c:when>
        <c:otherwise>
            Hello, ${lb.email}<br/><br/>
            <a href="index.jsp">Login</a>
        </c:otherwise>
        </c:choose>
</body>
</html>