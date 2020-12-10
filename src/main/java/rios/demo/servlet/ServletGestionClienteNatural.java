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

import rios.demo.bean.ClienteBean;
import rios.demo.bean.DistritoBean;
import rios.demo.dao.ClienteDAO;
import rios.demo.dao.ClienteDAOImpl;
import rios.demo.dao.DistritoDAO;
import rios.demo.dao.DistritoDAOImpl;


/**
 * Servlet implementation class ServletGestionClienteNatural
 */
@WebServlet("/ServletGestionClienteNatural")
public class ServletGestionClienteNatural extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletGestionClienteNatural.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionClienteNatural() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info("init: ServletGestionClienteNatural - doGet ");
		
		String accion = request.getParameter("p_accion");
		
		RequestDispatcher despachador = null;
				
		if("editar".equals(accion)){
			
			String idClienteNatural = (String) request.getParameter("idClienteNatural");
			System.out.println("idClienteNatural: "+ idClienteNatural);
			
			ClienteDAO dao = new ClienteDAOImpl();
			ClienteBean clienteNatural = dao.obtenerClienteById(Integer.valueOf(idClienteNatural));
			request.setAttribute("objClienteNatural",clienteNatural );
			
			DistritoDAO Distrito = new DistritoDAOImpl();
			List<DistritoBean>lista = Distrito.listaDistrito();	
			request.setAttribute("lstDistrito", lista);
			
			request.setAttribute("idClienteNatural", idClienteNatural);
			request.setAttribute("btnAccion", "update");
			
			despachador = request.getRequestDispatcher("/formularios/formularioCliente.jsp");
					
		}else if("eliminar".equals(accion)) {

			log.debug("accion:"+accion);
				
			ClienteDAO dao = new ClienteDAOImpl();	
			int id_cliente =Integer.parseInt(request.getParameter("id"));			
			int bandera = dao.eliminarClienteNatural(id_cliente);
			
			if(bandera == 0 ) {
				request.setAttribute("error",true);
				log.debug("error true");
				
			}else if(bandera == 1 ) {
				request.setAttribute("eliminado", true);
				log.debug("eliminado correctamente");
			}
			
			List<ClienteBean>lstClienteNatural = dao.listaClienteNatural();
			request.setAttribute("listaClienteNatural", lstClienteNatural);
			despachador = request.getRequestDispatcher("/listados/listaClienteNatural.jsp");
		
		}
		
		despachador.forward(request, response);		
		log.info("fin: ServletGestionClienteNatural - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
