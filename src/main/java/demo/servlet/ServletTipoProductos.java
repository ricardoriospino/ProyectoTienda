package demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.bean.TipoProductoBean;
import demo.dao.TipoProductoDAOImpl;
import demo.dao.TipoProductoDao;

/**
 * Servlet implementation class ServletTipoProductos
 */
@WebServlet("/ServletTipoProductos")
public class ServletTipoProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
				System.out.println("init: ServletTipoProductos - doGet ");
		
				TipoProductoDao dao = new TipoProductoDAOImpl();
				
				List<TipoProductoBean> lista = dao.listaTipoProducto();
				
				request.setAttribute("lstTipos", lista);
				request.setAttribute("btnAccion", "insert");
				
				RequestDispatcher despachador = null;
				despachador = request.getRequestDispatcher("/formularios/formularioProducto.jsp");
				
				despachador.forward(request, response);
				
				System.out.println("Fin: ServletTipoProductos - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("enit: ServletTipoProductos - doPost ");
		
		
		
		System.out.println("fin: ServletProductos - doPost ");
	}

}
