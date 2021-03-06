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

import rios.demo.bean.ClienteJuridicoBean;
import rios.demo.dao.ClienteJuridicoDAO;
import rios.demo.dao.ClienteJuridicoDAOImpl;

/**
 * Servlet implementation class ServletListaClienteJuridico
 */
@WebServlet("/ServletListaClienteJuridico")
public class ServletListaClienteJuridico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletListaClienteJuridico.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListaClienteJuridico() {
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
		
		ClienteJuridicoDAO dao = new ClienteJuridicoDAOImpl();
		
		List<ClienteJuridicoBean>lstClienteJuridico = dao.listaClienteJuridico();
		
		request.setAttribute("listaClienteJuridico", lstClienteJuridico);
		
		RequestDispatcher despachador = null;
		
		despachador = request.getRequestDispatcher("/listados/listaClienteJuridico.jsp");
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
