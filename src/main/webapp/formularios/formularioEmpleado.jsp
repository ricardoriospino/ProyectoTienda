<%@page import="rios.demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario Empleado</title>

<!-- IMPORTANDO LIBRERIA JSTL CORE, FMT  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<!--ini :login -->
<jsp:include page="../css.jsp" />
<!--fin :login -->

<!-- ini:calendar -->
<jsp:include page="../calendar.jsp" />
<!-- fin:calendar -->

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

	<!--ini:contenido -->
	<header>
		<!--ini :Menu -->
		<jsp:include page="../menuTienda.jsp" />
		<!--fin :Menu -->


		<div class="col-md-8 offset-md-2">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-5">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h3 class="mb-0">Ingrese datos Empleado</h3>
				</div>

				<c:if test="${ingresado == true}">
					<div class="alert alert-success" role="alert">
						${msg}
					</div>
				</c:if>
				<div class="card shadow-lg p-3 mb-5 bg-white">
					<div class="card-header">
						<form class="needs-validation" role="form" autocomplete="off"
							action="ServletInsertUpdateEmpleado" method="post" novalidate>
							
							<input type="hidden" name="hdnAccion" value="${btnAccion}"/><!-- es una variable oculta o un comodin -->
							<input type="hidden" name="hdnIdEmpleado" value="${idEmpleado}"/><!-- es una variable oculta o un comodin -->
							

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Nombre
								</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objEmpleado.nombre}'/>" name="nombres"
										onkeypress="return soloLetras(event)" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Apellido
									Materno</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objEmpleado.apellido_materno}'/>"
										name="apellido_ma" onkeypress="return soloLetras(event)"
										required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Apellido
									paterno</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objEmpleado.apellido_paterno}'/>"
										name="apellido_pa" onkeypress="return soloLetras(event)"
										required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Fecha
									nacimiento</label>
								<div class="col-lg-9">
									<input class="form-control" id="datepicker" width="276"
										name="fecha_nacimiento" value="<c:out value='${objEmpleado.fecha_nacimiento}'/>" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
									<script>
					                  $('#datepicker').datepicker({
					                    datepicker: true,
					                    uiLibrary : 'bootstrap4',
					                    locale: 'es-es',
					                    format:'yyyy-mm-dd'
					                  });
							  		</script>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Fecha
									Ingreso</label>
								<div class="col-lg-9">
									<input class="form-control" id="datepicker2" width="276"
										name="fecha_ingreso" value="<c:out value='${objEmpleado.fecha_ingreso}'/>" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
									<script>
					                  $('#datepicker2').datepicker({
					                    datepicker: true,
					                    uiLibrary : 'bootstrap4',
					                    locale: 'es-es',
					                    format:'yyyy-mm-dd'
					                  });
							  		</script>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Direccion
									domicilio</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objEmpleado.direccion}'/>"
										name="direccion_emp" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Telefono</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objEmpleado.telefono}'/>"
										name="telefono" minlength="5" maxlength="5" pattern="^[0-9]+"
										placeholder="01234" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo con
										números</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Distrito</label>
								<div class="col-lg-9">
									<select class="form-control" name="codigo_distrito" required>
									
									<c:forEach var="distrito" items="${lstDistrito}">
										<c:choose>
											<c:when test="${distrito.id == objEmpleado.codigo_distrito }">
												<option value="${distrito.id}" selected="selected">${distrito.descripcion}</option>
											</c:when>
											<c:otherwise>
												<option value="${distrito.id}">${distrito.descripcion}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>		
									</select>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Correo
								</label>
								<div class="col-lg-9">
									<input class="form-control" type="email" value="<c:out value='${objEmpleado.correo}'/>" name="correo"
										id="email" aria-describedby="emailHelp"
										placeholder="name@example.com" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo
										correctamente</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label"></label>
								<div class="col-lg-9">
									<input type="reset" class="btn btn-secondary" value="Cancelar">
									<input type="submit" class="btn btn-primary" value="Enviar">
									<a href="<%=request.getContextPath()%>/ServletListaEmpleado" class="btn btn-primary offset-lg-6"><i class="fa fa-undo"></i></a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Fin:Contenido -->
	<!--ini:footer -->
	<jsp:include page="../importFooter.jsp" />
	<!--/fin:footer -->
	
	<!--ini:footer -->
	<jsp:include page="../menuFooter.jsp"/>	
	<!--fin:footer -->
</body>
</html>