package rios.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bd.Miconexion;
import rios.demo.bean.ClienteBean;
import rios.demo.bean.ClienteNaturalyJuridicoBean;
import rios.demo.bean.ProductoBean;
import rios.demo.dao.ClienteDAO;
import rios.demo.dao.ClienteDAOImpl;
import rios.demo.dao.ClienteNaturalyJuridicoDAO;
import rios.demo.dao.ClienteNaturalyJuridicoDAOimpl;
import rios.demo.dao.ProductoDAO;
import rios.demo.dao.ProductoDAOImpl;

/**
 * Servlet implementation class ServletGestionVenta
 */
@WebServlet("/ServletGestionVenta")
public class ServletGestionVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletGestionVenta.class);
       
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
		
		log.info(" ini : ServletGestionVenta - doGe()");
		
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
		log.info("fin: ServletGestionVenta - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
