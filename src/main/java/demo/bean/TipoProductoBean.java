package demo.bean;

import java.io.Serializable;

public class TipoProductoBean implements Serializable {
	
	private int id;
	private String descripcion;
	
	public TipoProductoBean() {
		
	}

	public TipoProductoBean(int id) {
		super();
		this.id = id;
	}

	public TipoProductoBean(String descripcion) {
		super();
		this.descripcion = descripcion;
	}


	public TipoProductoBean(int id, String descripcion) {
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
