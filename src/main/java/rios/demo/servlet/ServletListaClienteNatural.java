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
import rios.demo.bean.ClienteBean;
import rios.demo.dao.ClienteDAO;
import rios.demo.dao.ClienteDAOImpl;


/**
 * Servlet implementation class ServletListaClienteNatural
 */
@WebServlet("/ServletListaClienteNatural")
public class ServletListaClienteNatural extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletListaClienteNatural.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaClienteNatural() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info(" ini : ServletListaClienteJuridico - doGe()");
		
		ClienteDAO dao = new ClienteDAOImpl();
		List<ClienteBean>lstClienteNatural = dao.listaClienteNatural();
		request.setAttribute("listaClienteNatural", lstClienteNatural);	
		
		RequestDispatcher despachador = null;
		despachador = request.getRequestDispatcher("/listados/listaClienteNatural.jsp");
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
