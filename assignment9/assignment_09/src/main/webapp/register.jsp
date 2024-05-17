<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
       <title>Register User</title>
       <link rel="stylesheet" href="css/site.css"/>
</head>
<body>
    <sf:form modelAttribute="user" action="createuser">
    Name: <sf:input path="name"/><sf:errors path="name" class="error"/><br/>
        Email:<sf:input path="email"/><sf:errors path="email" class="error"/><br/>
        Password:<sf:input path="password"/><sf:errors path="password" class="error"/><br/>
        Phone no:<sf:input path="phone"/><sf:errors path="phone" class="error"/><br/>
        <input type="submit" value="Sign up">
    </sf:form>    
</body>
</html>