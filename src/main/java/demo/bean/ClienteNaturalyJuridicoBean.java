package demo.bean;

import java.io.Serializable;

public class ClienteNaturalyJuridicoBean implements Serializable {
	
	private int id;
	private String nombre_o_razon_social;
	private String dni_o_ruc;
	
	public ClienteNaturalyJuridicoBean() {
		
	}

	public ClienteNaturalyJuridicoBean(int id, String nombre_o_razon_social, String dni_o_ruc) {
		super();
		this.id = id;
		this.nombre_o_razon_social = nombre_o_razon_social;
		this.dni_o_ruc = dni_o_ruc;
	}

	public ClienteNaturalyJuridicoBean(String nombre_o_razon_social, String dni_o_ruc) {
		super();
		this.nombre_o_razon_social = nombre_o_razon_social;
		this.dni_o_ruc = dni_o_ruc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_o_razon_social() {
		return nombre_o_razon_social;
	}

	public void setNombre_o_razon_social(String nombre_o_razon_social) {
		this.nombre_o_razon_social = nombre_o_razon_social;
	}

	public String getDni_o_ruc() {
		return dni_o_ruc;
	}

	public void setDni_o_ruc(String dni_o_ruc) {
		this.dni_o_ruc = dni_o_ruc;
	}

	
	

}
