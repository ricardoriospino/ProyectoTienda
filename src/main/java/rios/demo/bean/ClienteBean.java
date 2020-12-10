package rios.demo.bean;

import java.io.Serializable;

public class ClienteBean implements Serializable {
	
	private int id;
	private int id_cliente;
	private String dni;
	private String nombre;
	private String apellido_pa;
	private String apellido_ma;
	private String direccion;
	private String telefono;
	private long codigo_distrito;
	private String correo;
	// sobre carga de constructores 
	public ClienteBean() {
		
	}
	
   // constructores 
	
	public ClienteBean(int id, String dni, String nombre, String apellido_pa, String apellido_ma, String direccion, String telefono,
			long codigo_distrito, String correo) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido_pa = apellido_pa;
		this.apellido_ma = apellido_ma;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
	}
	

	//constructores 
	public ClienteBean(int id, String dni , int id_cliente, String nombre, String apellido_pa, String apellido_ma, String direccion,
			String telefono, long codigo_distrito, String correo) {
		super();
		this.id = id;
		this.dni = dni;
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido_pa = apellido_pa;
		this.apellido_ma = apellido_ma;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
	}
	
	
	public ClienteBean(String dni,String direccion,String telefono,Long codigo_distrito,String correo, String nombre, String apellido_pa, 
						String apellido_ma) {
		super();
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
		this.nombre = nombre;
		this.apellido_pa = apellido_pa;
		this.apellido_ma = apellido_ma;
	}
	


	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_pa() {
		return apellido_pa;
	}

	public void setApellido_pa(String apellido_pa) {
		this.apellido_pa = apellido_pa;
	}

	public String getApellido_ma() {
		return apellido_ma;
	}

	public void setApellido_ma(String apellido_ma) {
		this.apellido_ma = apellido_ma;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getCodigo_distrito() {
		return codigo_distrito;
	}

	public void setCodigo_distrito(long codigo_distrito) {
		this.codigo_distrito = codigo_distrito;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}
