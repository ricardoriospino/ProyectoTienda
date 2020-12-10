<%@page import="rios.demo.bean.EmpleadoBean"%>
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
	
<title>Datos Venta</title>
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
	<br><br><br>
 
	<header>
		<div class="col-md-8 offset-md-2">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-0">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h5 class="mb-0">Datos vendedor</h3>
					<form>
						<div class="form-row">
							<div class="col-md-2 mb-3">
								<label for="validationDefault01">Id</label> <input
									type="text" class="form-control" id="validationDefault01"
									  disabled="disabled"  value="<%=empleado.getId() %>" required>
							</div>
							<div class="col-md-5 mb-3">
								<label for="validationDefault02">Nombre</label> <input
									type="text" class="form-control" id="validationDefault02"
									  disabled="disabled"  value="<%=empleado.getNombre() %>" required>
							</div>
							<div class="col-md-5 mb-3">
								<label for="validationDefault02">Apellido</label> <input
									type="text" class="form-control" id="validationDefault02"
									  disabled="disabled"  value="<%=empleado.getApellido_paterno() + " " + empleado.getApellido_materno() %>" required>
							</div>
						</div>					
					</form>
				</div>
			</div>
		</div>
		
		<div class="col-md-8 offset-md-2">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-0">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h5 class="mb-0">Datos cliente</h3>

					<div class="container">
						
						<br>
						<table id="tblClienteNatural"
							class="table table-striped table-bordered table-sm"
							cellspacing="0" width="100%">

							<thead>
								<tr>					
									<th>ID</th>
									<th>NOMBRE/RAZÓN SOCIAL</th>
									<th>DNI/RUC</th>
									<th>SELECCIONAR</th>
								</tr>
							</thead>
							<tbody>
									<c:forEach var="varClienteNaturalJuridico"
										items="${listaClienteNaturalJuridico}">
										<tr>
<%-- 											<td>${varClienteNatural.id_cliente}</td> --%>
											<td>${varClienteNaturalJuridico.id}</td> 
											<td>${varClienteNaturalJuridico.nombre_o_razon_social}</td>
											<td>${varClienteNaturalJuridico.dni_o_ruc}</td>
											
											<td><a
												href="<%=request.getContextPath()%>/ServletGestionVenta?p_accion=seleccionar&idCliente=${varClienteNaturalJuridico.id}">
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

			<div class="col-md-8 offset-md-2">
				<span class="anchor" id="formUserEdit"></span>
				<hr class="my-0">

				<div class="card card-outline-secondary">
					<div class="card-header">
						<h5 class="mb-0">Datos seleccionados cliente</h3>
						<form>
							<div class="form-row">
								<div class="col-md-2 mb-3">
									<label for="validationDefault01">id cliente</label> <input
										type="text" class="form-control" id="validationDefault01"
										 disabled="disabled" value="${clienteComprador.id}" required>
								</div>
								<div class="col-md-5 mb-3">
									<label for="validationDefault02">Nombre/Razon Social</label> <input
										type="text" class="form-control" id="validationDefault02"
										 disabled="disabled" value="${clienteComprador.nombre_o_razon_social}" required>
								</div>
								<div class="col-md-5 mb-3">
									<label for="validationDefault02">DNI/RUC</label> <input
										type="text" class="form-control" id="validationDefault02"
										 disabled="disabled" value="${clienteComprador.dni_o_ruc}" required>
								</div>
							</div>					
						</form>
						<div id=botonSiguiente class="container col-md-6">
							
							<input type="reset" class="btn btn-secondary" value="cancelar">
							&nbsp;&nbsp;&nbsp;
							<a href="<%=request.getContextPath()%>/ServletGestionFinalVenta?p_accion=inicioVentaFinal" class="btn btn-primary">siguiente</a>
						</div>					
					</div>				
				</div>
				<br><br>
			</div>
	</header>	
	<!-- ini: configuracion DataTable -->
	<script type="text/javascript">
		//Basic example
		$(document).ready(function() {
			$('#tblClienteNatural').DataTable({
				"paging" : true, // activar o desactivar paginacion 	
				"pageLength": 5,
				"columnDefs": [
				       {"className": "dt-center", "targets": "_all"}
				    ]
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