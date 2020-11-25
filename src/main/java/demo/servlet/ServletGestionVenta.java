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

import demo.bean.ClienteBean;
import demo.bean.ClienteNaturalyJuridicoBean;
import demo.bean.ProductoBean;
import demo.dao.ClienteDAO;
import demo.dao.ClienteDAOImpl;
import demo.dao.ClienteNaturalyJuridicoDAO;
import demo.dao.ClienteNaturalyJuridicoDAOimpl;
import demo.dao.ProductoDAO;
import demo.dao.ProductoDAOImpl;

/**
 * Servlet implementation class ServletGestionVenta
 */
@WebServlet("/ServletGestionVenta")
public class ServletGestionVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println(" ini : ServletGestionVenta - doGe()");
		
		String pagina ="datosVenta.jsp";
		String accion = request.getParameter("p_accion");				
		ClienteNaturalyJuridicoDAO dao = new ClienteNaturalyJuridicoDAOimpl();			
		HttpSession misession = request.getSession(true);  
			
		if("inicioVenta".equals(accion)) {
			// borramos session de clientes seleccionado
			misession.removeAttribute("clienteComprador");		
			List<ClienteNaturalyJuridicoBean>lstClienteNaturalJuridico = dao.listaClienteNaturalyJuridico();
			request.setAttribute("listaClienteNaturalJuridico", lstClienteNaturalJuridico);
			
		}else if("seleccionar".equals(accion)) {
			
			String idCliente = request.getParameter("idCliente");
			
			ClienteNaturalyJuridicoBean clienteNaturalJuridico = dao.obtenerClienteNaturalJuridicoById(Integer.parseInt(idCliente));
			misession.setAttribute("clienteComprador", clienteNaturalJuridico); // vivir durante toda ala aplicacion 	
			
			List<ClienteNaturalyJuridicoBean>lstClienteNaturalJuridico = dao.listaClienteNaturalyJuridico();
			request.setAttribute("listaClienteNaturalJuridico", lstClienteNaturalJuridico);
			
			
		}
			
		RequestDispatcher despachador = null;
		
		despachador = request.getRequestDispatcher(pagina);
		despachador.forward(request, response);		
		System.out.println("fin: ServletGestionVenta - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
