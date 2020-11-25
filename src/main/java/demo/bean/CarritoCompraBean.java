package demo.bean;

import java.io.Serializable;

public class CarritoCompraBean implements Serializable{
	
	private int idDetalleBoleta;
	private int idBoleta;
	private long id_producto;
	private String nombre_producto;
	private double precio;
	private int cantidad;
	private double subTotal;
	private int stockActual;
	
		
	public CarritoCompraBean() {}

	
	public CarritoCompraBean(long id_producto, String nombre_producto, double precio, int cantidad, double subTotal,int stockActual) {
		super();
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.subTotal = subTotal;
		this.stockActual = stockActual;
	}
	

	public CarritoCompraBean(int idDetalleBoleta, int idBoleta, long id_producto, int cantidad, double subTotal) {
		super();
		this.idDetalleBoleta = idDetalleBoleta;
		this.idBoleta = idBoleta;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.subTotal = subTotal;
	}




	public int getIdDetalleBoleta() {
		return idDetalleBoleta;
	}

	public void setIdDetalleBoleta(int idDetalleBoleta) {
		this.idDetalleBoleta = idDetalleBoleta;
	}

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public long getId_producto() {
		return id_producto;
	}

	public void setId_producto(long id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
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
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}


	public int getStockActual() {
		return stockActual;
	}


	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}
	
	
}
