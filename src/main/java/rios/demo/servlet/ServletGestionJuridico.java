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
import rios.demo.bean.DistritoBean;
import rios.demo.dao.ClienteJuridicoDAO;
import rios.demo.dao.ClienteJuridicoDAOImpl;
import rios.demo.dao.DistritoDAO;
import rios.demo.dao.DistritoDAOImpl;

/**
 * Servlet implementation class ServletGestionJuridico
 */
@WebServlet("/ServletGestionJuridico")
public class ServletGestionJuridico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletGestionJuridico.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionJuridico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info("init: ServletGestionJuridico - doGet ");
		
		String accion = request.getParameter("p_accion");
		
		RequestDispatcher despachador = null;
		
		if("editar".equals(accion)){
			
			String idClienteJuridico = (String) request.getParameter("idClienteJuridico");
			log.debug("idClienteJuridico: "+ idClienteJuridico);
			
			ClienteJuridicoDAO dao = new ClienteJuridicoDAOImpl();
			ClienteJuridicoBean clienteJuridico = dao.obtenerClienteJuridicoById(Long.valueOf(idClienteJuridico));
			request.setAttribute("objClienteJuridico", clienteJuridico);
			
			DistritoDAO Distrito = new DistritoDAOImpl();
			List<DistritoBean>lista = Distrito.listaDistrito();	
			request.setAttribute("lstDistrito", lista);
			
			request.setAttribute("idClienteJuridico", idClienteJuridico);
			request.setAttribute("btnAccion", "update");
			
			despachador = request.getRequestDispatcher("/formularios/formularioClienteJuridico.jsp");
			
		}else if("eliminar".equals(accion)) {

			log.debug("accion:"+accion);
				
			ClienteJuridicoDAO dao = new ClienteJuridicoDAOImpl();	
			Long id_cliente_juridico = Long.parseLong(request.getParameter("id_cliente"));
			int bandera = dao.eliminarClienteJuridico(id_cliente_juridico);
			
			if(bandera == 0) {
				request.setAttribute("error",true);
				log.debug("error true");
				
			}else if(bandera ==1) {
				request.setAttribute("eliminado", true);
				log.debug("eliminado correctamente");
				
			}		
			List<ClienteJuridicoBean>lstClienteJuridico = dao.listaClienteJuridico();
			request.setAttribute("listaClienteJuridico", lstClienteJuridico);
			despachador = request.getRequestDispatcher("/listados/listaClienteJuridico.jsp");		
		}
		
		despachador.forward(request, response);		
		log.info("fin: ServletGestionJuridico - doGet ");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
