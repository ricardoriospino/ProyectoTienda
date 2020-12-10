package rios.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bd.Miconexion;
import rios.demo.bean.TipoProductoBean;
import rios.demo.dao.TipoProductoDAOImpl;
import rios.demo.dao.TipoProductoDao;

/**
 * Servlet implementation class ServletTipoProductos
 */
@WebServlet("/ServletTipoProductos")
public class ServletTipoProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletTipoProductos.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTipoProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//traemos los tipos de productos de la base de datos.
		
				log.info("init: ServletTipoProductos - doGet ");
		
				TipoProductoDao dao = new TipoProductoDAOImpl();
				
				List<TipoProductoBean> lista = dao.listaTipoProducto();
				
				request.setAttribute("lstTipos", lista);
				request.setAttribute("btnAccion", "insert");
				
				RequestDispatcher despachador = null;
				despachador = request.getRequestDispatcher("/formularios/formularioProducto.jsp");
				
				despachador.forward(request, response);
				
				log.info("Fin: ServletTipoProductos - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		log.info("enit: ServletTipoProductos - doPost ");
		
		
		
		log.info("fin: ServletProductos - doPost ");
	}

}
