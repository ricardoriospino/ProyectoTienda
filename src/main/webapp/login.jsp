<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<!-- IMPORTANDO LIBRERIA JSTL CORE, FMT  -->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" 
crossorigin="anonymous">

	<!--ini :login -->
	<jsp:include page="css.jsp"/>
	<!--fin :login -->

<meta charset="ISO-8859-1">
<title>Inicio</title>
</head>

<c:if test="${error == true}" >
	<div class="alert alert-danger">
  	<strong>Error!</strong> Usuario y clave errada.
	</div>
</c:if>	

<body class="login">
<form action="ServletLogeoPost" method="post">
    <div id="login">
        <h3 class="text-center text-white pt-5">Ingresar Datos</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                    
                       
                            <h3 class="text-center text-info">Login</h3>
                            
                            <div class="form-group">
                                <label for="username" class="text-info">Usuario:</label><br>
                                <input type="text" name="usuario" id="username" class="form-control">
                            </div>
                            
                            <div class="form-group">
                                <label for="password" class="text-info">Clave:</label><br>
                                <input type="password" name="clave" id="password" class="form-control" aria-describedby="passwordHelpInline">
	                            	
                            </div>
                            
                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Recuerdame</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
<%--                                 <a href="<%=request.getContextPath()%>/ServletLogeoPost" class="btn btn-info btn-md" >Enviar</a>  --%>
                             
                          	<input type="submit" name="submit" class="btn btn-info btn-md"value="Enviar"> 
							</div>
                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

	<!--ini:footer -->
	<jsp:include page="importFooter.jsp"/>	
	<!--fin:footer -->
</body>
</html>