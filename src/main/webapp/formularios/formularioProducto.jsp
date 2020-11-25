<%@page import="demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario Producto</title>

<!-- IMPORTANDO LIBRERIA JSTL CORE, FMT  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<!--ini :login -->
<jsp:include page="../css.jsp" />
<!--fin :login -->

</head>
<body data-spy="scroll" data-target="#navbar" data-offset="57">
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

	<!--ini:formulario -->
	<header>
		<!--ini :Menu -->
		<jsp:include page="../menuTienda.jsp" />
		<!--fin :Menu -->

		<div class="col-md-8 offset-md-2">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-5">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h3 class="mb-0">Ingrese datos productos</h3>
				</div>

				<c:if test="${ingresado == true}">
					<div class="alert alert-success" role="alert">
						${msg}
					</div>
				</c:if>

				<div class="card shadow-lg p-3 mb-5 bg-white">
					<div class="card-header">
						<form class="needs-validation" role="form" autocomplete="off"
							action="ServletInsertUpdateProducto" method="post" novalidate>
							
							<input type="hidden" name="hdnAccion" value="${btnAccion}"/><!-- es una variable oculta o un comodin -->
							<input type="hidden" name="hdnIdProducto" value="${idProducto}"/><!-- es una variable oculta o un comodin -->

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Nombre producto</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objProducto.nombre_producto}'/>"
										name="nombre_producto" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el nombre del producto</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Precio s/.</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objProducto.precio}'/>"
										name="precio"  required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo con números</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Stock
									Actual</label>
								<div class="col-lg-9">
									<input class="form-control" type="number" value="<c:out value='${objProducto.stock}'/>"
										name="stock_actual" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo con números</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Tipo
									Producto</label>
								<div class="col-lg-9">
									<select class="form-control" name="id_tipo_producto">
										<c:forEach var="tipoProducto" items="${lstTipos}">
											<c:choose>
												<c:when test="${tipoProducto.id == objProducto.id_tipo_producto }">
													<option value="${tipoProducto.id}" selected="selected">${tipoProducto.descripcion}</option>
												</c:when>
												<c:otherwise>
													<option value="${tipoProducto.id}">${tipoProducto.descripcion}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label"></label>
								<div class="col-lg-9">
									<input type="reset" class="btn btn-secondary" value="Cancelar">
									<input type="submit" class="btn btn-primary" value="Guardar" >
									<a href="<%=request.getContextPath()%>/ServletListaProducto?lista=L" class="btn btn-primary offset-lg-5"><i class="fa fa-undo"></i></a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!--ini:footer -->
	<jsp:include page="../importFooter.jsp" />
	<!--/fin:footer -->
	
	<!--ini:footer -->
	<jsp:include page="../menuFooter.jsp"/>	
	<!--fin:footer -->
</body>
</html>