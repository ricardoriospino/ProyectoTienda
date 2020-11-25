<%@page import="demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- importar libreria a la clase -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">


<meta charset="ISO-8859-1">
<title>Insert title here</title>

	<!--ini :login -->
	<jsp:include page="../css.jsp" />
	<!--fin :login -->

	<!-- INI:dataTable -->
	<jsp:include page="../dataTableTienda.jsp" />
	<!-- FIN:dataTable -->

	
</head>

<body>
<!-- ini:validamos session -->
<%
boolean valido = false;
EmpleadoBean empleado = (EmpleadoBean)session.getAttribute("usuarioSession");

if(empleado == null)valido = false;
else {valido = true;}

if(!valido){ // si valido es false 
	response.sendRedirect("login.jsp");
}
%>
<!-- fin:validamos session -->

	<!--ini :Menu -->
	<jsp:include page="../menuTienda.jsp" />
	<!--fin :Menu -->
	<br><br><br><br>
	
	<!-- INI:Contenido -->
	<div class="container-fluid ">
		<h3 class="text-center ">Listado de Empleados </h3>
		<hr>
		<c:if test="${error == true}">
			<div class="alert alert-danger">
				<strong>Error!</strong> Empleados tiene Ventas.
			</div>
		</c:if>
		<c:if test="${eliminado == true}">
			<div class="alert alert-success" role="alert">
				<strong>Eliminado!</strong> Eliminado Correctamente
			</div>
		</c:if>
		<div id=botonAgregar class="container text-left text-left">
			<a href="<%=request.getContextPath()%>/ServletDistrito?formulario=E" class="btn btn-success">Agregar Empleado</a>
		</div>
		<br>
		<table id="tblEmpleado" class="table table-striped table-bordered table-sm "  cellspacing="0" width="100%">

			<thead>
				<tr>
					<th>ID </th>
					<th>NOMBRE</th>
					<th>APELLIDO PATERNO</th>
					<th>APELLIDO MATERNO</th>
					<th>NACIMIENTO</th>
					<th>DIRECCION</th>
					<th>TELEFONO</th>
					<th>DISTRITO</th>
					<th>CORREO</th>
					<th>EDITAR/ELIMINAR</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="varEmpleado" items="${listaEmpleado}">
					<tr>
						<td>${varEmpleado.id}</td>
						<td>${varEmpleado.nombre}</td>
						<td>${varEmpleado.apellido_materno}</td>
						<td>${varEmpleado.apellido_paterno}</td>
						<td>${varEmpleado.fecha_nacimiento}</td>
						<td>${varEmpleado.direccion}</td>
						<td>${varEmpleado.telefono}</td>
						<td>${varEmpleado.distrito}</td>
						<td>${varEmpleado.correo}</td>
						<td><a href="<%=request.getContextPath()%>/ServletGestionEmpleado?p_accion=editar&idEmpleado=${varEmpleado.id}">Editar</a>
						&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/ServletGestionEmpleado?p_accion=eliminar&idEmpleado=${varEmpleado.id}">Eliminar</a></td>
					</tr>							
				</c:forEach>

			</tbody>

		</table>
	</div>
	<br><br>
	<!-- Fin:Contenido -->
	
	<!-- ini: configuracion DataTable -->
	<script type="text/javascript">
		//Basic example
		$(document).ready(function() {
			$('#tblEmpleado').DataTable({
				"paging" : true // activar o desactivar paginacion 			
			});
		});
	</script>

	<!-- fin: configuracion DataTable -->
	<!--ini:footer -->
	<jsp:include page="../importFooter.jsp" />
	<!--/fin:footer -->
	
	<!--ini:footer -->
	<jsp:include page="../menuFooter.jsp"/>	
	<!--fin:footer -->


</body>
</html>