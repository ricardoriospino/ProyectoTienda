package demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.bean.DistritoBean;
import demo.dao.DistritoDAO;
import demo.dao.DistritoDAOImpl;

/**
 * Servlet implementation class ServletDistrito
 */
@WebServlet("/ServletDistrito")
public class ServletDistrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDistrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("init: ServletDistrito - doGet ");
		
		//traemos los distritos de la base de datos.
		
		DistritoDAO dao = new DistritoDAOImpl();
		
		List<DistritoBean>lista = dao.listaDistrito();
		
		request.setAttribute("lstDistrito", lista);
		request.setAttribute("btnAccion", "insert");
		
		RequestDispatcher despachador = null;
		
		String formulario = request.getParameter("formulario");
		
		if("E".equals(formulario)) { //empleado
			
			despachador = request.getRequestDispatcher("/formularios/formularioEmpleado.jsp");
			
		}else if("C".equals(formulario)) {//cliente
			
			despachador = request.getRequestDispatcher("/formularios/formularioCliente.jsp");
			
		}else if("J".equals(formulario)) {// cliente juridico
			
			despachador = request.getRequestDispatcher("/formularios/formularioClienteJuridico.jsp");
			
		}
		
		despachador.forward(request, response);
		
		
		System.out.println("fin: ServletDistrito - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}




