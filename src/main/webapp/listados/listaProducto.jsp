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
	<div class="container ">
		<h3 class="text-center ">Listado de Producto </h3>
		<hr>
		<c:if test="${error == true}">
			<div class="alert alert-danger">
				<strong>Error!</strong> Producto en boleta no podemos eliminar.
			</div>
		</c:if>
		<c:if test="${eliminado == true}">
			<div class="alert alert-success" role="alert">
				<strong>Eliminado!</strong> Eliminado Correctamente
			</div>
		</c:if>
		<div id=botonAgregar class="container text-left ">
			<a href="<%=request.getContextPath()%>/ServletTipoProductos" class="btn btn-success">Agregar Producto</a>
			<a href="<%=request.getContextPath()%>/ServletListaExportarExcel?p_reporte=REPLISTAPRODUCTO0001" class="btn btn-success">Exportar a Excel</a>
		</div>
		<br>
		<table id="tblProducto" class="table table-striped table-bordered table-sm "  cellspacing="0" width="100%">

			<thead>
				<tr>
					<th>ID </th>
					<th>NOMBRE PRODUCTO</th>
					<th>PRECIO</th>
					<th>STOCK ACTUAL</th>
					<th>TIPO PRODUCTO</th>
					<th>EDITAR/ELIMINAR</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="varProducto" items="${listaProducto}">
					<tr>
						<td>${varProducto.id_producto}</td>
						<td>${varProducto.nombre_producto}</td>
						<td>S/.${varProducto.precio}0</td>
						<td>${varProducto.stock}</td>
						<td>${varProducto.nombreTipoProducto}</td>
						<td><a href="<%=request.getContextPath()%>/ServletGestionProductos?p_accion=editar&idProducto=${varProducto.id_producto}">Editar</a>
						&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/ServletGestionProductos?p_accion=eliminar&idProducto=${varProducto.id_producto}">Eliminar</a></td>
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
			$('#tblProducto').DataTable({
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