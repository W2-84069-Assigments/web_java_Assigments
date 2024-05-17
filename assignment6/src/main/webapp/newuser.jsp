<!DOCTYPE html>
<html lang="en">
<head>
     <title>Register user</title>
</head>
<body>
    <h3> ${initParam.appTitle}</h3>
    
    <form method="post" action="register.jsp" >
        Name: <input type="text" name="name"/><br/>
        Email: <input type="email" name="email"/><br/>
        Password: <input type="password" name="password" /> <br/>
        Mobile no: <input type="text" name="phone" /> <br/>
        <input type="submit" value="Sign up"/>

    </form>
</body>
</html>