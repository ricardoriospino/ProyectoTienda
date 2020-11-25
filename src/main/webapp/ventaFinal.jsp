<%@page import="demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<!-- importar libreria a la clase -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
	
<meta charset="ISO-8859-1">

	<!--ini :css -->
	<jsp:include page="css.jsp"/>
	<!--fin :css -->
	
	<!-- INI:dataTable -->
	<jsp:include page="dataTableTienda.jsp" />
	<!-- FIN:dataTable -->
	
<title>Venta final</title>
</head>
<body data-spy="scroll" data-target="#navbar"  data-offset="57">

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
	<jsp:include page="menuTienda.jsp"/>
	<!--fin :Menu --> 
	<br><br>
	
	<header>	
	<div class="col-md-10 offset-md-1">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-0">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h5 class="mb-0">Lista Productos</h3>
					<div class="container">		
						<br>
						
						<table id="tblProducto"class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">

							<thead>
								<tr>							
									<th>NOMBRE PRODUCTO</th>
									<th>PRECIO</th>
									<th>STOCK</th>
									<th>SELECCIONAR</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="varProducto" items="${listaProducto}">
									<tr>
										<td>${varProducto.nombre_producto}</td>
										<td> S/.${varProducto.precio}0 </td>
										<td>${varProducto.stock}</td>
										<td><a 
											href="<%=request.getContextPath()%>/ServletGestionFinalVenta?p_accion=seleccionar&idProducto=${varProducto.id_producto}">
											<button type="button" class="btn btn-dataTable "><i class="fa fa-check-square"></i></button></a>
											
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-10 offset-md-1">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-0">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h3 class="mb-0">Producto Seleccionado</h3>
						<form  class="needs-validation" action="ServletGestionFinalVenta"  role="form" autocomplete="off" method="post" novalidate >
						
							<div class="form-row align-items-center">
							
							<input type="hidden" name="hdnIdProducto" value="${productoVendido.id_producto}"/><!-- es una variable oculta o un comodin -->
							
								<div class=" col-md-5 mb-3">
									<label class="mr-sm-2" for="validationDefault02">Nombre Producto</label>
									<input type="text" name="nombreProducto" class="form-control" id="validationDefault02" disabled="disabled"  value="${productoVendido.nombre_producto }">
									
									
								</div>
								<div class="  col-md-3 mb-3">
									<label class="mr-sm-2" for="validationDefault02">Cantidad</label>
									<input type="number" name="cantidadProducto" class="form-control" id="validationDefault02"  value="" pattern="^[0-9]+" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo con números</div>
									
								</div>	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
								<div class="card-body d-flex float-right  align-items-center">
									<input type="submit" class="btn btn-primary bt-sm" value="Agregar"></input>
								</div>					
							</div>					
						</form>
				</div>
			</div>
		</div>
		<br>
		<main layout:fragment="contenido">
			<div  class="col-md-10 offset-md-1">
				
			<div ><i Class="fa fa-shopping-cart fa-4x"></i></div>
			
				<div class="table-responsive">
					<table class="table table-bordered">
								
						<thead>
							<tr>
								<th>NOMBRE</th>
								<th>PRECIO</th>
								<th>CANTIDAD</th>
								<th>SUB TOTAL</th>
								<th>ELIMINAR</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="varCompra" items="${carritoCompras}">
							
						
								<tr >
								<td >${varCompra.nombre_producto}</td>
								<td >S/.${varCompra.precio}0</td>
								<td>${varCompra.cantidad}</td>
								<td>S/.${varCompra.subTotal}0</td>
								
								<td>
								
									<a href="<%=request.getContextPath()%>/ServletGestionFinalVenta?p_accion=seleccionar&idProducto=${varCompra.id_producto}">
										<button type="button" class="btn btn-dataTable">Eliminar <i class="fa fa-check-square"></i></button></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="form-group row offset-md-4">
					&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="col-lg-1 col-form-label form-control-label"><strong>TOTAL</strong></label>
						&nbsp;&nbsp;&nbsp;
						<div class="col-lg-3">
							<input type="text" class="form-control" id="validationDefault02" disabled="disabled" value="S/.${total}0" required>
						</div>
						
					</div>
				

				<div class="form-group row offset-md-1">
						<label class="col-lg-4 col-form-label form-control-label"></label>
						<div class="col-lg-5">
						
							<form   action="ServletPagoFinalTx"  method="post"  >				
								<input type="reset" class="btn btn-secondary" value="Cancelar">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" class="btn btn-primary" value="Pagar">
							</form>
							
						</div>
					</div>
			</div>
			<br><br>
			</div>
		</main>

	</header>
<!-- ini: configuracion DataTable -->
	<script type="text/javascript">
		//Basic example
		$(document).ready(function() {
			$('#tblProducto').DataTable({
				"paging" : true,
				"pageLength": 5,
				"columnDefs": [
				       {"className": "dt-center", "targets": "_all"}
				    ]
				
				 
			// activar o desactivar paginacion 			
			});
		});
	</script>

	<!-- fin: configuracion DataTable -->


	<!--ini:footer -->
	<jsp:include page="importFooter.jsp"/>	
	<!--fin:footer -->
	<!--ini:footer -->
	<jsp:include page="menuFooter.jsp"/>	
	<!--fin:footer -->

</body>
</html>