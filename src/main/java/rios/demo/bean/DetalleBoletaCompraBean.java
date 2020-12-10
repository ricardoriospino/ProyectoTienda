package rios.demo.bean;

import java.util.Date;

public class DetalleBoletaCompraBean {
	
	private int idBoleta;
	private int idEmpleado;
	private Date fechaBoleta;
	private int idCliente;
	private String nombreEmpleado;
	private String nombreORazonSocial;
	private String dniORuc;
	private double totalVenta;
	private int idProducto;
	private String nombreProducto;
	private double precio;
	private int cantidad;
	private double SubTotal;
	
	
	public DetalleBoletaCompraBean() {
		
	}
	
	public DetalleBoletaCompraBean(int idBoleta) {
		super();
		this.idBoleta = idBoleta;
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

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getNombreORazonSocial() {
		return nombreORazonSocial;
	}

	public void setNombreORazonSocial(String nombreORazonSocial) {
		this.nombreORazonSocial = nombreORazonSocial;
	}

	public String getDniORuc() {
		return dniORuc;
	}

	public void setDniORuc(String dniORuc) {
		this.dniORuc = dniORuc;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubTotal() {
		return SubTotal;
	}

	public void setSubTotal(double subTotal) {
		SubTotal = subTotal;
	}

}
