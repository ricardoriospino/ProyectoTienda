package rios.demo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rios.demo.bean.EmpleadoBean;
import rios.demo.dao.EmpleadoDAO;
import rios.demo.dao.EmpleadoDAOImpl;

/**
 * Servlet implementation class ServletLogeoPost
 */
@WebServlet("/ServletLogeoPost")
public class ServletLogeoPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LogManager.getLogger(ServletLogeoPost.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogeoPost() {
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
		

		log.info("Inicio metodo doPost");
		
		// captura datos de entrada
		String usuarioForm = request.getParameter("usuario");
		String claveForm = request.getParameter("clave");
		
		
		log.debug(" el usuario es " +  usuarioForm + " la clave es " + claveForm);
		
		
		
		// manipulamos la BD
		
		EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
		
		EmpleadoBean empleado = empleadoDAO.validarUsuarioClave(usuarioForm, claveForm);
			
		RequestDispatcher despachador = null;
		
		if(empleado!=null) {// si es correcto 
			
			//INI Configuracion de cookies 
			
			try {
						
				String contadorNuevo = "1" ;
		
				//obtenemos todas las cookies
				Cookie[] cookies = request.getCookies();
				//String contadorparseado = Integer.toString(contador);	
						
				//BUSCAMOS SI YA EXISTE UNA COOKIE CREADA CON ANTERIORIDAD			
				if(cookies!=null) {	
								
					for( Cookie c: cookies ) {
													
						if (c.getName().equals("contadorcookie")) {
							
							contadorNuevo = c.getValue();						
					
							int contadorNumero = Integer.parseInt(contadorNuevo);	
							contadorNumero++;		
																				  		 
							contadorNuevo = Integer.toString(contadorNumero);// parseo		
					}	
				}				
			}	
			
			Cookie contadorCookie = new Cookie("contadorcookie",contadorNuevo );	
			response.addCookie(contadorCookie);
							
		
			//le mando al jsp los valores capturados
			request.setAttribute("contadorvisitas",contadorNuevo );
		
		
			} catch (NumberFormatException nfe) {
				 nfe.printStackTrace();
				 log.error("error en Mi conexion validarUsuarioClave" + nfe);
			}
						
			// FIN configuracion de cookies		
			
			HttpSession misession = request.getSession(true);  
			misession.setAttribute("usuarioSession", empleado);// vivir durante toda ka aplicacion 	
			despachador = request.getRequestDispatcher("/menuPrincipalTienda.jsp");
			
			
		
		}else {
		
			request.setAttribute("error",true);
			despachador = request.getRequestDispatcher("/login.jsp");
			
		}
		despachador.forward(request, response);
		
		log.info("fin metodo doPost()");
		
	}

}
