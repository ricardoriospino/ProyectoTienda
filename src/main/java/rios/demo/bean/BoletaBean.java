package rios.demo.bean;

import java.io.Serializable;
import java.util.Date;

public class BoletaBean implements Serializable {
	
	private int idBoleta;
	private int idEmpleado;
	private Date fechaBoleta;
	private int idCliente;
	private byte estadoBoleta;
	private double totalBoleta;
	
	public BoletaBean() {}
	

	public BoletaBean(int idBoleta, int idEmpleado, Date fechaBoleta, int idCliente, byte estadoBoleta,
			double totalBoleta) {
		super();
		this.idBoleta = idBoleta;
		this.idEmpleado = idEmpleado;
		this.fechaBoleta = fechaBoleta;
		this.idCliente = idCliente;
		this.estadoBoleta = estadoBoleta;
		this.totalBoleta = totalBoleta;
	}

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFechaBoleta() {
		return fechaBoleta;
	}

	public void setFechaBoleta(Date fechaBoleta) {
		this.fechaBoleta = fechaBoleta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public byte getEstadoBoleta() {
		return estadoBoleta;
	}

	public void setEstadoBoleta(byte estadoBoleta) {
		this.estadoBoleta = estadoBoleta;
	}

	public double getTotalBoleta() {
		return totalBoleta;
	}

	public void setTotalBoleta(double totalBoleta) {
		this.totalBoleta = totalBoleta;
	}
	
	
	

}
