package rios.demo.bean;

import java.io.Serializable;

public class ClienteJuridicoBean implements Serializable {
	
	private long id;
	private int id_cliente_juridico;
	private String razon_social;
	private String ruc;
	private String contacto_cliente_juridico;
	private String direccion;
	private String telefono;
	private long codigo_distrito;
	private String correo;
	
	// constructores 
	public ClienteJuridicoBean() {
		
	}

	public ClienteJuridicoBean(long id, String razon_social, String ruc, String contacto_cliente_juridico,
			String direccion, String telefono, long codigo_distrito, String correo) {
		super();
		this.id = id;
		this.razon_social = razon_social;
		this.ruc = ruc;
		this.contacto_cliente_juridico = contacto_cliente_juridico;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
	}

	public ClienteJuridicoBean(long id, int id_cliente_juridico, String razon_social, String ruc,
			String contacto_cliente_juridico, String direccion, String telefono, long codigo_distrito, String correo) {
		super();
		this.id = id;
		this.id_cliente_juridico = id_cliente_juridico;
		this.razon_social = razon_social;
		this.ruc = ruc;
		this.contacto_cliente_juridico = contacto_cliente_juridico;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
	}
	

	public ClienteJuridicoBean(String razon_social, String ruc, String contacto_cliente_juridico, String direccion,
			String telefono, long codigo_distrito, String correo) {
		super();
		this.razon_social = razon_social;
		this.ruc = ruc;
		this.contacto_cliente_juridico = contacto_cliente_juridico;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
	}

	// metodos getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getId_cliente_juridico() {
		return id_cliente_juridico;
	}

	public void setId_cliente_juridico(int id_cliente_juridico) {
		this.id_cliente_juridico = id_cliente_juridico;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getContacto_cliente_juridico() {
		return contacto_cliente_juridico;
	}

	public void setContacto_cliente_juridico(String contacto_cliente_juridico) {
		this.contacto_cliente_juridico = contacto_cliente_juridico;
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
