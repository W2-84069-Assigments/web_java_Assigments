<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
     <title>Blog</title>
</head>
<body>
    <sf:form method="post" action="editblog">
        <sf:hidden path="id" />
    Title: <sf:input path="title" /><br/>
    Contents :  <sf:textarea path="content" rows="6" cols="60"/> <br/>
    <sf:hidden path="userId"/>
    Category : <sf:select path="categoryId" items="${categoryList}" itemValue="id" itemLabel="title"/> <br/>
    <input type="submit" value="submit">
    
</sf:form>
</body>
</html>