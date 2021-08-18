package rios.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bd.Miconexion;
import rios.demo.bean.ClienteBean;
import rios.demo.bean.ClienteJuridicoBean;
import rios.demo.bean.EmpleadoBean;
import rios.demo.bean.ProductoBean;
import rios.demo.dao.ClienteDAO;
import rios.demo.dao.ClienteDAOImpl;
import rios.demo.dao.ClienteJuridicoDAO;
import rios.demo.dao.ClienteJuridicoDAOImpl;
import rios.demo.dao.EmpleadoDAO;
import rios.demo.dao.EmpleadoDAOImpl;
import rios.demo.dao.ProductoDAO;
import rios.demo.dao.ProductoDAOImpl;

/**
 * Servlet implementation class ServletListaProductoExportarExcel
 */
@WebServlet("/ServletListaExportarExcel")
public class ServletListaExportarExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletListaExportarExcel.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaExportarExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info("ini: get ServletListaProductoExportarExcel()");
		
		String p_reporte = (String) request.getParameter("p_reporte");
		
		// Interpretar abrir un excel "application/vnd.ms-excel"
		//cada archivo tiene su propio ContentType ejmplo pdf : application/pdf
		response.setContentType("application/vnd.ms-excel");
		
		if("REPLISTAPRODUCTO0001".equals(p_reporte)) {
			
			response.setHeader("Content-Disposition", "attachment; filename=ListaProductos.xls");// nombre del archivo
			response.setHeader("Pragma", "no-cache"); // no se almacenan en memoria
			response.setHeader("Cache-control", "no-store");
			
			// escribe las salidas(Informacion) del ProductoDAO
			PrintWriter out = response.getWriter();
			ProductoDAO dao = new ProductoDAOImpl();// instaciamos productoDAO
			
			List<ProductoBean> lstProductos = dao.listarProducto();
			
			//preparamos el reporte en excel a partir de html quien lo comvierte a excel es la cabecera response.setHeader
			
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				out.println("<table>");
				 out.println("<tr><th>Orden </th><th>D </th><th>Nombre producto</th>"
				 		+ "<th>Precio</th><th>Stock actual</th><th>Tipo de producto</th></tr>");
				 for(int i=0;i<lstProductos.size();i++) { // recorre lista empleados 
					 ProductoBean producto = lstProductos.get(i);
					 out.println("<tr><td>"+(i+1)+"</td><td>" + producto.getId_producto() + "</td><td>"+ producto.getNombre_producto()+ "</td><td>"
							 	+ producto.getPrecio() + "</td><td>" + producto.getStock() + "</td><td>" + producto.getNombreTipoProducto() +"</td></tr>");
				 }
				 out.println("</table>");
			out.println("</body>");
		out.println("</html>");
		
		}else if ("REPLISTAEMPLEADO0002".equals(p_reporte)) {
			
			response.setHeader("Content-Disposition", "attachment; filename=ListaEmpleados.xls");
			response.setHeader("Pragma", "no-cache"); 
			response.setHeader("Cache-control", "no-store");
			
			PrintWriter out = response.getWriter();
			EmpleadoDAO dao = new EmpleadoDAOImpl();
			
			List<EmpleadoBean> lstEmpleados = dao.listaEmpleado();
			
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				out.println("<table>");
				 out.println("<tr><th>Orden </th><th>ID </th><th>Nombre Empleado</th>"
				 		+ "<th>Apellido Paterno</th><th>Apellido Materno</th><th>Nacimiento </th>"
				 		+ "<th>Direccion</th><th>Telefono</th><th>Distrito</th><th>Correo</th></tr>");
				 for(int i=0;i<lstEmpleados.size();i++) { // recorre lista empleados 
					 EmpleadoBean empleado = lstEmpleados.get(i);
					 out.println("<tr><td>"+(i+1)+"</td><td>" + empleado.getId() + "</td><td>"+ empleado.getNombre()+ "</td><td>"
							 	+ empleado.getApellido_paterno() + "</td><td>" + empleado.getApellido_materno() + "</td><td>" + empleado.getFecha_nacimiento() +"</td><td>"
							 	+ empleado.getDireccion() + "</td><td>"+ empleado.getTelefono() + "</td><td>" + empleado.getDistrito() + "</td><td>"+ empleado.getCorreo() +"</td></tr>");
				 }
				 out.println("</table>");
			out.println("</body>");
		out.println("</html>");	
		
		}else if("REPLISTACLIENTENATURAL0003".equals(p_reporte)){
			
			response.setHeader("Content-Disposition", "attachment; filename=ListaClienteNatural.xls");
			response.setHeader("Pragma", "no-cache"); 
			response.setHeader("Cache-control", "no-store");
			
			PrintWriter out = response.getWriter();
			ClienteDAO dao = new ClienteDAOImpl();
			List<ClienteBean> lstClienteNatural = dao.listaClienteNatural();
			
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				out.println("<table>");
				 out.println("<tr><th>Orden </th><th>ID </th><th>DNI</th>"
				 		+ "<th>Nombre Cliente</th><th>Apellido Paterno Cliente</th><th>Apellido Materno Cliente  </th></tr>");
				 for(int i=0;i<lstClienteNatural.size();i++) { // recorre lista empleados 
					 ClienteBean clienteNatural = lstClienteNatural.get(i);
					 out.println("<tr><td>"+(i+1)+"</td><td>" + clienteNatural.getId() + "</td><td>"+ clienteNatural.getDni()+ "</td><td>"
							 	+ clienteNatural.getNombre() + "</td><td>" + clienteNatural.getApellido_pa() + "</td><td>" + clienteNatural.getApellido_ma() +"</td></tr>");
							 	
				 }
				 out.println("</table>");
			out.println("</body>");
		out.println("</html>");	
			
		}else if("REPLISTACLIENTEJURIDICO0004".equals(p_reporte)) {
			
			response.setHeader("Content-Disposition", "attachment; filename=ListaClienteJuridico.xls");
			response.setHeader("Pragma", "no-cache"); 
			response.setHeader("Cache-control", "no-store");
			
			PrintWriter out = response.getWriter();
			ClienteJuridicoDAO dao = new ClienteJuridicoDAOImpl();
			List<ClienteJuridicoBean> lstClienteJuridico = dao.listaClienteJuridico();
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				out.println("<table>");
				 out.println("<tr><th>Orden </th><th>ID </th><th>Razón Social</th>"
				 		+ "<th>RUC</th><th>Contacto Cliente</th></tr>");
				 for(int i=0;i<lstClienteJuridico.size();i++) { // recorre lista empleados 
					 ClienteJuridicoBean clienteJuridico = lstClienteJuridico.get(i);
					 out.println("<tr><td>"+(i+1)+"</td><td>" + clienteJuridico.getId() + "</td><td>"+ clienteJuridico.getRazon_social()+ "</td><td>"
							 	+ clienteJuridico.getRuc() + "</td><td>" + clienteJuridico.getContacto_cliente_juridico() + "</td></tr>");
							 	
				 }
				 out.println("</table>");
			out.println("</body>");
		out.println("</html>");	
			
		}
			
		log.info("fin: get ServletListaProductoExportarExcel()");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
