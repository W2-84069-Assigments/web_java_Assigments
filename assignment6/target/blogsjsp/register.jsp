<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
   <title>Registraion page</title>
</head>
<body>
    <h3>${initParam.appTitle}</h3>

 <jsp:useBean id="rb" class="com.sunbeam.beans.RegistrationBean" />
  <jsp:setProperty name="rb" property="*"/>
 <%-- ${ rb.addUser() }  --%>

  <c:choose>
    <c:when test="${rb.addUser()==1}">
        Registration successfull <br/>
        <a href="index.jsp">Login</a>

        </c:when>
        <c:otherwise>
        Registration failed
        </c:otherwise>
        </c:choose>

</body>
</html>