<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
      <title>Category List</title>
</head>
<body>
      <br/><br/>
      <table border="1">
          <tr>
              <th>Title</th>
              <th>Description</th>
            </tr>

            <c:forEach var="c" items="${catList}">
             <tr>
            <td>${c.title} </td>
            <td>${c.description}</td>
            </tr>
            </c:forEach>
            </table>
</body>
</html>