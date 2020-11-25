package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.bean.EmpleadoBean;
import demo.dao.EmpleadoDAO;
import demo.dao.EmpleadoDAOImpl;




/**
 * Servlet implementation class ServletRegistroEmpleadoPost
 */
@WebServlet("/ServletInsertUpdateEmpleado")
public class ServletInsertUpdateEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertUpdateEmpleado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
			
		System.out.println("Inicia metodo doPost");

		//datos de entrada
		
		String accion = request.getParameter("hdnAccion");
		System.out.println("accion " + accion );
		
		String nombreForm = request.getParameter("nombres");
		String apellidoMaternoForm = request.getParameter("apellido_ma");
		String apellidoPaternoForm = request.getParameter("apellido_pa");
		String fechaNacimientoForm = request.getParameter("fecha_nacimiento");
		String direccionForm =request.getParameter("direccion_emp");
		String telefonoForm = request.getParameter("telefono");
		Long codigoDistritoForm =  Long.parseLong(request.getParameter("codigo_distrito"));
		String correoForm = request.getParameter("correo");
		String fecha_ingreso= request.getParameter("fecha_ingreso");
		
		
		System.out.println("Datos ingresados "+ nombreForm + "el apellido materno es " + apellidoMaternoForm + 
				" el apellido paterno es " + apellidoPaternoForm + " fecha de nacimiento es" + fechaNacimientoForm +
				" direccion es " + direccionForm + " el telefono es " + telefonoForm + " codigo distrito es " + codigoDistritoForm +
				" correo es " + correoForm + " la fecha de ingreso es " + fecha_ingreso);
		
		EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
		EmpleadoBean empleado = null;
		int estado = 0;
		String mensaje ="";
		
		if("insert".equals(accion)) { // insertar
			
			empleado = new EmpleadoBean(nombreForm, apellidoMaternoForm, apellidoPaternoForm, fechaNacimientoForm, 
										direccionForm, telefonoForm, codigoDistritoForm, correoForm,fecha_ingreso);
			estado = empleadoDAO.insertarEmpleado(empleado);
			mensaje="<strong>Ingresado!</strong> Datos Ingresado correctamente a la base de datos.";
			
		}else if("update".equals(accion)) { // actualizar 
			
			String idEmpleado = request.getParameter("hdnIdEmpleado");
			empleado = new EmpleadoBean(Long.valueOf(idEmpleado),nombreForm, apellidoMaternoForm, apellidoPaternoForm, fechaNacimientoForm, 
										direccionForm, telefonoForm, codigoDistritoForm, correoForm,fecha_ingreso);
			estado = empleadoDAO.actualizarEmpleado(empleado);
			mensaje="<strong>Actualizado!</strong> Datos actualizados a la base de datos .";			
		}
				
		PrintWriter salida = response.getWriter();
		
		RequestDispatcher disparador =null;// lanzador de otras pantallas
		
		if(estado ==1) {
			
			request.setAttribute("ingresado", true);
			request.setAttribute("msg", mensaje);
			disparador= request.getRequestDispatcher("/ServletDistrito?formulario=E");
				
		}else {
			salida.println("<html><body>");
			salida.println("<h3>Alerta</h3>");
			salida.println("empleado Invalido!");
			salida.println("</html></body>");			
		}
		
		disparador.forward(request, response);
		
		System.out.println("fin del metodo doPost()");
	}

}
