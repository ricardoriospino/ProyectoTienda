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
import demo.bean.DistritoBean;
import demo.dao.ClienteDAO;
import demo.dao.ClienteDAOImpl;
import demo.dao.DistritoDAO;
import demo.dao.DistritoDAOImpl;


/**
 * Servlet implementation class ServletGestionClienteNatural
 */
@WebServlet("/ServletGestionClienteNatural")
public class ServletGestionClienteNatural extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		System.out.println("init: ServletGestionClienteNatural - doGet ");
		
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

			System.out.println("accion:"+accion);
				
			ClienteDAO dao = new ClienteDAOImpl();	
			int id_cliente =Integer.parseInt(request.getParameter("id"));			
			int bandera = dao.eliminarClienteNatural(id_cliente);
			
			if(bandera == 0 ) {
				request.setAttribute("error",true);
				System.out.println("error true");
				
			}else if(bandera == 1 ) {
				request.setAttribute("eliminado", true);
				System.out.println("eliminado correctamente");
			}
			
			List<ClienteBean>lstClienteNatural = dao.listaClienteNatural();
			request.setAttribute("listaClienteNatural", lstClienteNatural);
			despachador = request.getRequestDispatcher("/listados/listaClienteNatural.jsp");
		
		}
		
		despachador.forward(request, response);		
		System.out.println("fin: ServletGestionClienteNatural - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
