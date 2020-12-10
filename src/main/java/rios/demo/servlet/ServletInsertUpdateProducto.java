package rios.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bd.Miconexion;
import rios.demo.bean.ProductoBean;
import rios.demo.dao.ProductoDAO;
import rios.demo.dao.ProductoDAOImpl;


/**
 * Servlet implementation class ServletRegistroProducto
 */
@WebServlet("/ServletInsertUpdateProducto")
public class ServletInsertUpdateProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletInsertUpdateProducto.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertUpdateProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		log.info("Inicia metodo doPost");
		
		// datos de entrada 
		String accion = request.getParameter("hdnAccion");
		log.debug("accion" + accion );
		
		String nombreForm = request.getParameter("nombre_producto");
		double precioFrom = Double.parseDouble(request.getParameter("precio"));
		int stockActualForm =Integer.parseInt(request.getParameter("stock_actual"));
		int id_tipo_productoForm = Integer.parseInt(request.getParameter("id_tipo_producto"));
		
		log.debug("Datos ingresados,el nombre es " + nombreForm + " ,precio es s/. " + precioFrom + 
							" ,el stock actual " + stockActualForm + " ,el tipo de producto es " + id_tipo_productoForm );
		
		ProductoDAO productoDAO = new ProductoDAOImpl(); // inicializando productoDAO
		
		ProductoBean producto = null;
		
		int estado = 0;
		String mensaje="";
		if("insert".equals(accion)) { // inserta 
			
			producto = new ProductoBean(nombreForm,precioFrom,stockActualForm,id_tipo_productoForm);	
			estado = productoDAO.insertarProducto(producto);
			mensaje="<strong>Ingresado!</strong> Datos Ingresado correctamente a la base de datos.";
			
		}else if("update".equals(accion)) { // update 
			
			String idProducto = request.getParameter("hdnIdProducto");
			producto = new ProductoBean(Long.valueOf(idProducto),nombreForm,precioFrom,stockActualForm,id_tipo_productoForm);
			estado = productoDAO.actualizarProducto(producto);
			mensaje="<strong>Actualizado!</strong> Datos Actualizados  la base de datos.";
			
		}
		
		
		PrintWriter salida = response.getWriter();
		RequestDispatcher disparador =null; // lanzador de otras pantallas
		
		if(estado ==1 ) {
			request.setAttribute("ingresado", true);
			request.setAttribute("msg", mensaje);
			disparador= request.getRequestDispatcher("/ServletTipoProductos");
			
			
		}else {
			salida.println("<html><body>");
			salida.println("<h3>Alerta</h3>");
			salida.println("producto invalido!");
			salida.println("</html></body>");
		}
		disparador.forward(request, response);
		
		log.info("fin metodo Post()");
	}

}
