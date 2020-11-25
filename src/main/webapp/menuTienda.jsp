<!--ini: Menu -->
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary  fixed-top"
		id="header">
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/menuPrincipalTienda.jsp"> <img
			src="img/oxxo.png" alt="tienda-logo">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar" aria-controls="navbarSupportedContent"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link active"href="<%=request.getContextPath()%>/menuPrincipalTienda.jsp">Inicio</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/ServletGestionVenta?p_accion=inicioVenta">Venta</a>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
					   role="button" aria-haspopup="true" aria-expanded="false">Mantenimiento
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class=dropdown-item href="<%=request.getContextPath()%>/ServletListaEmpleado">Empleado</a>
						</li>
						<li>
							<a class=dropdown-item href="<%=request.getContextPath()%>/ServletListaProducto?lista=L">Producto</a>
						</li>
							<div class="dropdown-divider"></div>

						<li class="nav-link dropdown-submenu">
							<a class="dropdown-item dropdown-toggle" href="#"
						   	data-toggle="dropdown">Cliente</a>
							<ul class="dropdown-menu">
								<li>
									<a class=dropdown-item href="<%=request.getContextPath()%>/ServletListaClienteNatural">Cliente
										Natural
									</a>
								</li>
								<li>
									<a class=dropdown-item href="<%=request.getContextPath()%>/ServletListaClienteJuridico">Cliente
										Juridico
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Reporte</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="<%=request.getContextPath()%>/ServletLogout">salir</a>
				</li>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<li class="nav-item" >
					<p  class="nav-link" style="color:white"><strong> Visitas: ${contadorvisitas}</strong> </p>
				</li>
			</ul>
			

			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-tienda my-2 my-sm-0" type="submit">Buscar</button>
			</form>
		</div>
	</nav>
</header>

<!-- /fin menu -->