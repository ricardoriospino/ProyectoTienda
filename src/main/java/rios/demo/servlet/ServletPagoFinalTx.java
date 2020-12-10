package rios.demo.servlet;

import java.io.IOException;

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

import rios.demo.bean.CarritoCompraBean;
import rios.demo.bean.ClienteNaturalyJuridicoBean;
import rios.demo.bean.EmpleadoBean;
import rios.demo.dao.BoletaDAO;
import rios.demo.dao.BoletaDAOImpl;


/**
 * Servlet implementation class ServletPagoFinalTx
 */
@WebServlet("/ServletPagoFinalTx")
public class ServletPagoFinalTx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletPagoFinalTx.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagoFinalTx() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		log.info(" ini : ServletPagoFinalTx - doPost()");
		
		HttpSession misession = request.getSession(true); 
		
		// recuperamos cabecera
		
		// cliente
		ClienteNaturalyJuridicoBean cliente = (ClienteNaturalyJuridicoBean) misession.getAttribute("clienteComprador");
		log.debug("Nombre " + cliente.getNombre_o_razon_social());
		// empleado 	
		EmpleadoBean empleado = (EmpleadoBean) misession.getAttribute("usuarioSession");
		log.debug("Empleado "+ empleado.getNombre());
		
		//detalle venta 	
		List<CarritoCompraBean> lstCarrito = (List<CarritoCompraBean>) misession.getAttribute("carritoCompras");
		
		// calculo total de venta 
		double totalBoleta = 0;
		for (CarritoCompraBean carrito : lstCarrito) {
			totalBoleta+= carrito.getSubTotal();
		}
		
		// actualizar venta 	
		int unidadesActuales = 0;				
		for (CarritoCompraBean carrito : lstCarrito) {
					
			int stock = carrito.getStockActual();				
			unidadesActuales = stock - carrito.getCantidad();					
			log.debug("stock actual "  + unidadesActuales);	
					
			carrito.setStockActual(unidadesActuales);;
		}
		
		BoletaDAO boletaDAO = new BoletaDAOImpl();
		boolean flag = boletaDAO.insertarVenta(empleado, cliente, lstCarrito, totalBoleta);
		
		//
		
		if(flag) {
			
			// remover el carrito de compras 
			// remover el cliente comprador 
		}
					
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher("ventaFinal.jsp");
		despachador.forward(request, response);
		
		log.info(" Fin : ServletPagoFinalTx - doPost()");
	}

}
