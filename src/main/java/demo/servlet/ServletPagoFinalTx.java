package demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demo.bean.CarritoCompraBean;
import demo.bean.ClienteNaturalyJuridicoBean;
import demo.bean.EmpleadoBean;
import demo.bean.ProductoBean;
import demo.dao.BoletaDAO;
import demo.dao.BoletaDAOImpl;
import demo.dao.ProductoDAO;
import demo.dao.ProductoDAOImpl;

/**
 * Servlet implementation class ServletPagoFinalTx
 */
@WebServlet("/ServletPagoFinalTx")
public class ServletPagoFinalTx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		System.out.println(" ini : ServletPagoFinalTx - doPost()");
		
		HttpSession misession = request.getSession(true); 
		
		// recuperamos cabecera
		
		// cliente
		ClienteNaturalyJuridicoBean cliente = (ClienteNaturalyJuridicoBean) misession.getAttribute("clienteComprador");
		System.out.println("Nombre " + cliente.getNombre_o_razon_social());
		// empleado 	
		EmpleadoBean empleado = (EmpleadoBean) misession.getAttribute("usuarioSession");
		System.out.println("Empleado "+ empleado.getNombre());
		
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
			System.out.println("stock actual "  + unidadesActuales);	
					
			carrito.setStockActual(unidadesActuales);;
		}
		
		BoletaDAO boletaDAO = new BoletaDAOImpl();
		boolean flag = boletaDAO.insertarVenta(empleado, cliente, lstCarrito, totalBoleta);
					
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher("/menuPrincipalTienda.jsp");
		despachador.forward(request, response);
		
		System.out.println(" Fin : ServletPagoFinalTx - doPost()");
	}

}
