package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.bean.CarritoCompraBean;
import demo.bean.DetalleBoletaCompraBean;
import demo.dao.BoletaDAO;
import demo.dao.BoletaDAOImpl;


/**
 * Servlet implementation class ServletBoletaVentaExportExcel
 */
@WebServlet("/ServletBoletaVentaExportExcel")
public class ServletBoletaVentaExportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBoletaVentaExportExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("ini: get ServletBoletaVentaExportExcel()");
		
		HttpSession misession = request.getSession(true); 
		
		response.setHeader("Content-Disposition", "attachment; filename=BoletaVenta.xls");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-store");
		
		PrintWriter out = response.getWriter();
		
		
		BoletaDAO dao = new BoletaDAOImpl();	
		DetalleBoletaCompraBean boleta = dao.obtenerIdBoleta();	
		int idBoleta = boleta.getIdBoleta();	
		System.out.println("idBoleta " + idBoleta);
		
		DetalleBoletaCompraBean datosBoleta = dao.obtenerDetalleBoletaById(idBoleta);	
		System.out.println("fecha de la boleta " + datosBoleta.getFechaBoleta());
		
		
		List<DetalleBoletaCompraBean> lstDetalleCompra = dao.listarDetalleCompraProductos(idBoleta);
		
		
		
		
		out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
				out.println("<h1>BOLETA DE VENTA: </h1>");
				out.println("<P>FECHA Y HORA:  " + datosBoleta.getFechaBoleta() + "</P>" );
				out.println("<P>ID BOLETA:  " + idBoleta + "</P>" );
				out.println("<P>ID EMPLEADO:  "+ datosBoleta.getIdEmpleado() + "</P>");
				out.println("<P>CAJERO:  "+ datosBoleta.getNombreEmpleado() + "</P>" );
				out.println("<P>CLIENTE:  " + datosBoleta.getNombreORazonSocial() + "</P>" );
				out.println("<P>DNI O RUC:  " + datosBoleta.getDniORuc() + "</P>" );
				out.println("<P>---------------------------------------------------------------------</P>");
					out.println("<table>");
						
							out.println("<thead><tr><th>ID</th><th>PRODUCTO</th><th>PRECIO</th><th>CANTIDAD</th>" + 
							"<th>SUBTOTAL</th></tr></thead>");
							for (int i=0; i <lstDetalleCompra.size();i++) {
								DetalleBoletaCompraBean detalleCompra = lstDetalleCompra.get(i);
							
							out.println("<tr><td >"+ detalleCompra.getIdProducto() +
										"</td><td >" + detalleCompra.getNombreProducto() +
										"</td><td >S/."+ detalleCompra.getPrecio() +
										"</td><td >" + detalleCompra.getCantidad() +
										"</td><td >S/." + detalleCompra.getSubTotal() +"</td></tr> ");
							
							}
							out.println("<tr>" + 
									" <td class=\"negro\" colspan=\"4\" >PRECIO TOTAL: </td>" + 
									" <br>" + 
									" <td class=\"espacio\"> S/." + datosBoleta.getTotalVenta()  + "</td>" + 
									" </tr>");
					out.println("</table>");
					
			out.println("</body>");
		
		out.println("</html>");
		
		
		
		System.out.println("fin: get ServletBoletaVentaExportExcel()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
