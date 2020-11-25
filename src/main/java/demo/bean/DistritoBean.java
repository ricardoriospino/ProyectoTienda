package demo.bean;

import java.io.Serializable;

public class DistritoBean implements Serializable {
	private int id;
	private String descripcion;
	
	public DistritoBean() {
		
	}

	public DistritoBean(int id) {
		super();
		this.id = id;
	}

	public DistritoBean(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public DistritoBean(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
