package demo.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import demo.bean.EmpleadoBean;
import demo.dao.EmpleadoDAO;
import demo.dao.EmpleadoDAOImpl;

/**
 * Servlet implementation class ServletListaEmpleado
 */
@WebServlet("/ServletListaEmpleado")
public class ServletListaEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println(" ini : ServletListaEmpleado - doGe()");
		
		EmpleadoDAO dao = new EmpleadoDAOImpl();
		
		List<EmpleadoBean>lstEmpleado = dao.listaEmpleado();
		
		request.setAttribute("listaEmpleado", lstEmpleado);
		
		RequestDispatcher despachador = null;
		
		despachador = request.getRequestDispatcher("/listados/listaEmpleado.jsp");
		despachador.forward(request, response);
		
		
		System.out.println("fin: ServletListaClienteJuridico - doGet ");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
