package rios.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ServletRegistroCliente
 */
@WebServlet("/ServletInsertUpdateCliente")
public class ServletInsertUpdateCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletInsertUpdateCliente.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertUpdateCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		log.info("Inicia metodo doPost");
		
		//datos de entrada
		
		String accion = request.getParameter("hdnAccion");
		log.debug("accion " + accion );
		
		String dniForm = request.getParameter("Pdni");
		String direccionForm = request.getParameter("Pdireccion_cliente");
		String telefonoForm =request.getParameter("Ptelefono_cliente");
		Long  codigoDistritoForm = Long.parseLong(request.getParameter("Pcodigo_distrito"));
		String correoForm = request.getParameter("Pcorreo_cliente");
		String nombreForm = request.getParameter("Pnombre");
		String apellido_paForm = request.getParameter("Papellido_paterno");
		String apellido_maForm= request.getParameter("Papellido_materno");
		
		log.debug("Datos de ingresados dni " + dniForm +  " direccion " + direccionForm + " telefono " + telefonoForm + " codigo distrito" + 
							codigoDistritoForm + " correo " + correoForm +
							" nombre es " + nombreForm + " apellido paterno " + apellido_paForm +
							" apellido materno " + apellido_maForm);
		
		ClienteDAO clienteDAO = new ClienteDAOImpl(); // inicializando clienteDAO
		
		ClienteBean clienteNatural= null;
		int estado =0;
		String mensaje = "";
		
		if("insert".equals(accion)) {
			
			clienteNatural = new ClienteBean(dniForm,direccionForm,telefonoForm,codigoDistritoForm,correoForm,
											nombreForm,apellido_paForm,apellido_maForm);
			estado= clienteDAO.insertarCliente(clienteNatural);
			mensaje="<strong>Ingresado!</strong> Datos Ingresado correctamente a la base de datos.";
			
		}else if ("update".equals(accion)) {
			String idClienteNatural = request.getParameter("hdnIdClienteNatural");
			
			clienteNatural = new ClienteBean(Integer.valueOf(idClienteNatural),dniForm,nombreForm,apellido_paForm,apellido_maForm,direccionForm,
					  telefonoForm,codigoDistritoForm,correoForm);
			
			
			estado = clienteDAO.actualizarClienteNatural(clienteNatural);
			mensaje="<strong>Actualizado!</strong> Datos actualizados a la base de datos .";	
			
		}
		
		
		PrintWriter salida= response.getWriter();
		RequestDispatcher disparador = null;// lanzador de otras pantallas
		
		if(estado ==1) {
			request.setAttribute("ingresado", true);
			request.setAttribute("msg", mensaje);
			disparador= request.getRequestDispatcher("/ServletDistrito?formulario=C");
			
		}else {
			salida.println("<html><body>");
			salida.println("<h3>Alerta</h3>");
			salida.println("empleado Invalido!");
			salida.println("</html></body>");
			
		}
		disparador.forward(request, response);

		log.info("fin del metodo doPost()");
	}

}
