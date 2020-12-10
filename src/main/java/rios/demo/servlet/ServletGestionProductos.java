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

import rios.demo.bean.ProductoBean;
import rios.demo.bean.TipoProductoBean;
import rios.demo.dao.ProductoDAO;
import rios.demo.dao.ProductoDAOImpl;
import rios.demo.dao.TipoProductoDAOImpl;
import rios.demo.dao.TipoProductoDao;

/**
 * Servlet implementation class ServletGestionProductos
 */
@WebServlet("/ServletGestionProductos")
public class ServletGestionProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletGestionProductos.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		log.info("init: ServletGestionProductos - doGet ");
		
		String accion = request.getParameter("p_accion");
		
		RequestDispatcher despachador = null;
				
		if("editar".equals(accion)){
			
			String idProducto = (String) request.getParameter("idProducto");
			log.debug("idProducto: "+ idProducto);
			
			ProductoDAO dao = new ProductoDAOImpl();
			ProductoBean producto = dao.obtenerProductoById(Long.valueOf(idProducto));
			request.setAttribute("objProducto", producto);
			
			TipoProductoDao tipoProducto = new TipoProductoDAOImpl();
			List<TipoProductoBean> lista = tipoProducto.listaTipoProducto();
			request.setAttribute("lstTipos", lista);
			
			request.setAttribute("idProducto",idProducto );
			request.setAttribute("btnAccion", "update");
			
			despachador = request.getRequestDispatcher("/formularios/formularioProducto.jsp");
					
		}else if("eliminar".equals(accion)) {
		
			log.debug("accion:"+accion);
					
			ProductoDAO dao = new ProductoDAOImpl();				
			Long id_producto = Long.parseLong(request.getParameter("idProducto"));		
			int bandera = dao.eliminarProducto(id_producto);
			
			if(bandera == 0) {
				request.setAttribute("error",true);
				log.debug("error true");				
			}else if(bandera ==1) {
				request.setAttribute("eliminado", true);
				log.debug("eliminado correctamente");				
			}			
			List<ProductoBean>lstProducto = dao.listarProducto();// agrega lista otra vez 
			request.setAttribute("listaProducto", lstProducto);
			despachador = request.getRequestDispatcher("/listados/listaProducto.jsp");
		
		}
		
		despachador.forward(request, response);
		log.info("fin: ServletGestionProductos - doGet ");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
