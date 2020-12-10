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

import rios.demo.bean.EmpleadoBean;
import rios.demo.dao.EmpleadoDAO;
import rios.demo.dao.EmpleadoDAOImpl;

/**
 * Servlet implementation class ServletListaEmpleado
 */
@WebServlet("/ServletListaEmpleado")
public class ServletListaEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletListaEmpleado.class);
       
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
		
		log.info(" ini : ServletListaEmpleado - doGe()");
		
		EmpleadoDAO dao = new EmpleadoDAOImpl();	
		List<EmpleadoBean>lstEmpleado = dao.listaEmpleado();	
		request.setAttribute("listaEmpleado", lstEmpleado);
		
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher("/listados/listaEmpleado.jsp");
		despachador.forward(request, response);
				
		log.info("fin: ServletListaClienteJuridico - doGet ");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
