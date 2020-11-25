
package demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.bean.ClienteJuridicoBean;
import demo.dao.ClienteJuridicoDAO;
import demo.dao.ClienteJuridicoDAOImpl;

/**
 * Servlet implementation class ServletRegistroClienteJuridico
 */
@WebServlet("/ServletInsertUpdateClienteJuridico")
public class ServletInsertUpdateClienteJuridico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInsertUpdateClienteJuridico() {
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
		
		System.out.println("Inicia metodo doPost ServletInsertUpdateClienteJuridico");
		
		// datos de entrada
		String accion = request.getParameter("hdnAccion");
		System.out.println("accion " + accion );
		
		String direccionForm = request.getParameter("Pdireccion_cliente");
		String telefonoForm =request.getParameter("Ptelefono_cliente");
		Long  codigoDistritoForm = Long.parseLong(request.getParameter("Pcodigo_distrito"));
		String correoForm = request.getParameter("Pcorreo_cliente");
		String razonSocialForm = request.getParameter("Prazon_social");
		String rucForm = request.getParameter("Pruc");
		String contactoClienteJuridicoForm = request.getParameter("Pcontacto_cliente");
		
		
		System.out.println("Datos de ingresados direccion " + direccionForm + " telefono " + telefonoForm + " codigo distrito" + 
							codigoDistritoForm + " correo " + correoForm + " razon social "+ razonSocialForm
						    + " ruc "+ rucForm + " contacto cliente " + contactoClienteJuridicoForm);
		
		ClienteJuridicoDAO clienteJuridicoDAO = new ClienteJuridicoDAOImpl();
		
		ClienteJuridicoBean clienteJuridico = null;
		int estado =0;
		String mensaje = "";
		
		if("insert".equals(accion)) {
			
			clienteJuridico = new ClienteJuridicoBean(razonSocialForm,rucForm,contactoClienteJuridicoForm,direccionForm,
														telefonoForm,codigoDistritoForm,correoForm);
			estado= clienteJuridicoDAO.insertarJuridicoCliente(clienteJuridico);
			mensaje="<strong>Ingresado!</strong> Datos Ingresado correctamente la base de datos.";
			
		}else if ("update".equals(accion)) {
			System.out.println("entro a actualizacion");
			String idClienteJuridico = request.getParameter("hdnIdClienteJuridico");
			
			clienteJuridico = new ClienteJuridicoBean(Long.valueOf(idClienteJuridico),razonSocialForm,rucForm,contactoClienteJuridicoForm,
															direccionForm,telefonoForm,codigoDistritoForm,correoForm);
			estado = clienteJuridicoDAO.actualizarClienteJuridico(clienteJuridico);
			mensaje="<strong>Actualizado!</strong> Datos actualizados a la base de datos .";
			
		}
		
		
		PrintWriter salida = response.getWriter();
		RequestDispatcher disparador = null;
		
		if(estado==1) {
			request.setAttribute("ingresado", true);
			request.setAttribute("msg", mensaje);
			disparador=request.getRequestDispatcher("/ServletDistrito?formulario=J");
		}else {
			salida.println("<html><body>");
			salida.println("<h3>Alerta</h3>");
			salida.println("empleado Invalido!");
			salida.println("</html></body>");
			
		}
		disparador.forward(request, response);
		System.out.println("fin del metodo doPost() ServletInsertUpdateClienteJuridico");
	}

}
