package demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.bean.DistritoBean;
import demo.bean.EmpleadoBean;
import demo.dao.DistritoDAO;
import demo.dao.DistritoDAOImpl;
import demo.dao.EmpleadoDAO;
import demo.dao.EmpleadoDAOImpl;


/**
 * Servlet implementation class ServletGestionEmpleados
 */
@WebServlet("/ServletGestionEmpleado")
public class ServletGestionEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("init: ServletGestionEmpleados - doGet ");
		
		String accion = request.getParameter("p_accion");
		
		RequestDispatcher despachador = null;
				
		if("editar".equals(accion)){
			
			String idEmpleado = (String) request.getParameter("idEmpleado");
			System.out.println("idEmpleado: "+ idEmpleado);
			
			EmpleadoDAO dao = new EmpleadoDAOImpl();
			EmpleadoBean empleado = dao.obtenerEmpleadoById(Long.valueOf(idEmpleado));
			request.setAttribute("objEmpleado", empleado);
			
			DistritoDAO Distrito = new DistritoDAOImpl();
			List<DistritoBean>lista = Distrito.listaDistrito();	
			request.setAttribute("lstDistrito", lista);
			
			request.setAttribute("idEmpleado",idEmpleado );
			request.setAttribute("btnAccion", "update");
			
			despachador = request.getRequestDispatcher("/formularios/formularioEmpleado.jsp");
			 
					
		}else if("eliminar".equals(accion)) {
	
			System.out.println("accion:"+accion);
					
			EmpleadoDAO dao = new EmpleadoDAOImpl();				
			Long id_empleado = Long.parseLong(request.getParameter("idEmpleado"));		
			int bandera = dao.eliminarEmpleado(id_empleado);
			
			if(bandera == 0) {
				request.setAttribute("error",true);
				System.out.println("error true");
				
			}else if(bandera ==1) {
				request.setAttribute("eliminado", true);
				System.out.println("eliminado correctamente");
				
			}			
			List<EmpleadoBean>lstEmpleado = dao.listaEmpleado();
			request.setAttribute("listaEmpleado", lstEmpleado);
			despachador = request.getRequestDispatcher("/listados/listaEmpleado.jsp");
		
		}
		
		despachador.forward(request, response);
		
		System.out.println("fin: ServletGestionEmpleados - doGet ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
