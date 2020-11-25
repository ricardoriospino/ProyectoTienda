<%@page import="demo.bean.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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

	<!-- ini : Contenido -->
	<header>
		<!--ini :Menu -->
		<jsp:include page="../menuTienda.jsp" />
		<!--fin :Menu -->

		<div class="col-md-8 offset-md-2">
			<span class="anchor" id="formUserEdit"></span>
			<hr class="my-5">

			<div class="card card-outline-secondary">
				<div class="card-header">
					<h3 class="mb-0">Ingrese datos Cliente Juridico</h3>
				</div>

				<c:if test="${ingresado == true}">
					<div class="alert alert-success" role="alert">
						${msg}
					</div>
				</c:if>

				<div class="card shadow-lg p-3 mb-5 bg-white">
					<div class="card-header">
						<form class="needs-validation" role="form" autocomplete="off"
							action="ServletInsertUpdateClienteJuridico" method="post" novalidate>
							
							<input type="hidden" name="hdnAccion" value="${btnAccion}"/><!-- es una variable oculta o un comodin -->
							<input type="hidden" name="hdnIdClienteJuridico" value="${idClienteJuridico}"/><!-- es una variable oculta o un comodin -->

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label"
									for="validationTooltip01">Razon Social </label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objClienteJuridico.razon_social}'/>"
										name="Prazon_social" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">RUC</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objClienteJuridico.ruc}'/>" name="Pruc"
										minlength="10" maxlength="10" placeholder="0123456789"
										required> <small id="rucayuda"
										class="form-text text-muted">Se requieren 10 números</small>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Contacto
									Cliente</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objClienteJuridico.contacto_cliente_juridico}'/>"
										name="Pcontacto_cliente" onkeypress="return soloLetras(event)"
										required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Direccion
									domicilio</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objClienteJuridico.direccion}'/>"
										name="Pdireccion_cliente" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Telefono</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" value="<c:out value='${objClienteJuridico.telefono}'/>"
										name="Ptelefono_cliente" minlength="5" maxlength="5"
										pattern="^[0-9]+" placeholder="01234" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo con
										números</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Distrito</label>
								<div class="col-lg-9">
									<select class="form-control" name="Pcodigo_distrito" required>
									
									<c:forEach var="distrito" items="${lstDistrito}">
										<c:choose>
											<c:when test="${distrito.id == objClienteJuridico.codigo_distrito }">
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
								<label class="col-lg-3 col-form-label form-control-label"
									for="exampleInputEmail1">Correo </label>
								<div class="col-lg-9">
									<input class="form-control" type="email" value="<c:out value='${objClienteJuridico.correo}'/>"
										name="Pcorreo_cliente" id="email" aria-describedby="emailHelp"
										placeholder="name@example.com" required>
									<div class="valid-feedback">Dato ingresado correctamente</div>
									<div class="invalid-feedback">complete el campo</div>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label"></label>
								<div class="col-lg-9">
									<input type="reset" class="btn btn-secondary" value="Cancelar">
									<input type="submit" class="btn btn-primary" value="Enviar">
									<a href="<%=request.getContextPath()%>/ServletListaClienteJuridico" class="btn btn-primary offset-lg-6"><i class="fa fa-undo"></i></a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- /fin: contenido -->

	<!--ini:footer -->
	<jsp:include page="../importFooter.jsp" />
	<!--/fin:footer -->
	
	<!--ini:footer -->
	<jsp:include page="../menuFooter.jsp"/>	
	<!--fin:footer -->

</body>
</html>