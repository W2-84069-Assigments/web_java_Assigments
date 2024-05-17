<!DOCTYPE html>
<html lang="en">
<head>
       <title>Logout</title>
</head>
<body>
    <h3>${initParam.appTitle} </h3>
    <% session.invalidate(); %>
    Thank you for visiting our blogs <br/><br/>
    <a href="index.jsp"> Login again</a>
</body>
</html>