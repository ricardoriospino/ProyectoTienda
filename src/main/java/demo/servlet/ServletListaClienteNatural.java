package demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import demo.bean.ClienteBean;
import demo.dao.ClienteDAO;
import demo.dao.ClienteDAOImpl;


/**
 * Servlet implementation class ServletListaClienteNatural
 */
@WebServlet("/ServletListaClienteNatural")
public class ServletListaClienteNatural extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		System.out.println(" ini : ServletListaClienteJuridico - doGe()");
		
		ClienteDAO dao = new ClienteDAOImpl();
		
		List<ClienteBean>lstClienteNatural = dao.listaClienteNatural();
		
		request.setAttribute("listaClienteNatural", lstClienteNatural);
		
		RequestDispatcher despachador = null;
		
		despachador = request.getRequestDispatcher("/listados/listaClienteNatural.jsp");
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
