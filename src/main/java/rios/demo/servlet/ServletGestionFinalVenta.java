package rios.demo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bd.Miconexion;
import rios.demo.bean.BoletaBean;
import rios.demo.bean.CarritoCompraBean;
import rios.demo.bean.ProductoBean;
import rios.demo.dao.BoletaDAO;
import rios.demo.dao.BoletaDAOImpl;
import rios.demo.dao.ProductoDAO;
import rios.demo.dao.ProductoDAOImpl;

/**
 * Servlet implementation class ServletGestionFinalVenta
 */
@WebServlet("/ServletGestionFinalVenta")
public class ServletGestionFinalVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletGestionFinalVenta.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionFinalVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info(" ini : ServletGestionFinalVenta - doGet()");
		
		String pagina="ventaFinal.jsp";
		String accion = request.getParameter("p_accion");
		ProductoDAO dao = new ProductoDAOImpl();
		HttpSession misession = request.getSession(true); 
		
		if("inicioVentaFinal".equals(accion)) {
			
			List<ProductoBean>lstProducto = dao.listarProducto();		
			request.setAttribute("listaProducto", lstProducto);	
			
			
		}else if("seleccionar".equals(accion)) {
			
			String idProducto = request.getParameter("idProducto");
			
			ProductoBean productoVenta = dao.obtenerProductoById(Integer.parseInt(idProducto));
			misession.setAttribute("productoVendido",productoVenta );
			
			List<ProductoBean>lstProducto = dao.listarProducto();		
			request.setAttribute("listaProducto", lstProducto);	
			
		}
		
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher(pagina);
		despachador.forward(request, response);
		log.info("fin: ServletGestionFinalVenta - doGet ");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		log.info(" ini : ServletGestionFinalVenta - doPost()");
		
		String idProducto = request.getParameter("hdnIdProducto");	
		HttpSession misession = request.getSession(true);  
		int cantidadVendida = Integer.parseInt(request.getParameter("cantidadProducto"));
		
		log.debug("idProducto " + idProducto);
		log.debug("cantidadVendida " + cantidadVendida );	
		
		ProductoDAO dao = new ProductoDAOImpl();	
		ProductoBean productoAgregado = dao.obtenerProductoById(Integer.parseInt(idProducto));
		double subTotal = cantidadVendida * productoAgregado.getPrecio();
		int stockActual = productoAgregado.getStock();
		
		//carrito de compras 
		List<CarritoCompraBean> lstCarrito = (List<CarritoCompraBean>) misession.getAttribute("carritoCompras");
		
		if (lstCarrito == null)lstCarrito = new ArrayList<>();
		
		CarritoCompraBean compra = new CarritoCompraBean(Long.valueOf(idProducto), productoAgregado.getNombre_producto(), 
				productoAgregado.getPrecio(), cantidadVendida, subTotal, stockActual);
					
		lstCarrito.add(compra);

		misession.setAttribute("carritoCompras", lstCarrito);
		
		// suma los subtotales 
		double totalLista = 0 ;			
		for (CarritoCompraBean lista  : lstCarrito) 
		totalLista+=lista.getSubTotal(); // subtotal de la lista 					
		log.debug(" total : " +  totalLista);	
												
		List<ProductoBean>lstProducto = dao.listarProducto();	
		request.setAttribute("listaProducto", lstProducto);	
		request.setAttribute("total", totalLista );
			
		String pagina ="ventaFinal.jsp";
		
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher(pagina);
		despachador.forward(request, response);
		
		log.info(" ini : ServletGestionFinalVenta - doPost()");
	}

}


