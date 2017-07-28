


<%@page import="com.mysql.jdbc.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href='/css?family=Robotohttps://fonts.googleapis.com+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/style.css">
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        
    </head>
    <body>
        <div class="container">
            <div >
                <h1 id="title"  ><span id="logo" >Book Library</h1>
               
            </div>
            
             
            <div class="login-box animated fadeInUp" >
                <img src="images/username.jpg" >
               
                <jsp:useBean id="connect" class="GUI.dbConnectionBean">
                 
                <jsp:setProperty name="connect" property="username" value="${param.username}" /><br>
                <jsp:setProperty name="connect" property="password" value="${param.password}" /><br>
                <%
                    boolean status=connect.LoginValidation();
                    if(status==false){
                        out.println("<form method='get' action='./dbConnection.jsp'><br>");
                        out.println("<input type='text' name='username' placeholder='Enter Username' style='text-align: center; value='&#xf002;' ><br>");
                        out.println("<input type='password' name='password' placeholder='Enter Password' style='text-align: center; value='&#xf002;''><br>");
                        out.println("<button type='submit'>Signin<i class='fa fa-address-book'></i> </button><br>");
                        out.println("</form>");
                        
                    }
                    Connection con=connect.DBConnection();
                    String user=connect.getUsername();
                    String pwd=connect.getPassword();
                    if(status==true){
                        session.setAttribute("connection", con);
                        session.setAttribute("name", user);
                        session.setAttribute("password", pwd);
                        response.sendRedirect("./dbQuery.jsp?TYPE=search");
                    }
                %>
                <a href="#"><p class="small">Forgot your password?</p></a>
            </div>
	</div>
        </jsp:useBean>
    </body>
    <script>
        
	$(document).ready(function () {
    	$('#logo').addClass('animated  fadeInUp');
    	$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
    </script>
</html>
