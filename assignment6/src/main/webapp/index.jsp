<html>
    <head>
        <title>Login</title>
    </head>
<body>
    <h3> ${initParam.appTitle}</h3>
    <form method="post" action="login.jsp" >
        Email: <input type="text" name="email"/><br/>
        Password: <input type="password" name="passwd" /> <br/>
        <input type="submit" value="Sign in"/>

    </form> &nbsp;&nbsp;

    <form method="post" action="newuser.jsp" >
        <input type="submit" value="Sign up"/>
    </form>

</body>
</html>
