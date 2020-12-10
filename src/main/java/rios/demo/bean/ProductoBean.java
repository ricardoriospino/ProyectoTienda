package rios.demo.bean;

public class ProductoBean {
	
	private long id_producto;
	private String nombre_producto;
	private double precio;
	private int stock;
	private long id_tipo_producto;
	
	// extra
	private String nombreTipoProducto;
	
	
	public ProductoBean() {
		
	}


	public ProductoBean(String nombre_producto, double precio, int stock, long id_tipo_producto) {
		super();
		this.nombre_producto = nombre_producto;
		this.precio = precio;
		this.stock = stock;
		this.id_tipo_producto = id_tipo_producto;
	}


	public ProductoBean(long id_producto, String nombre_producto, double precio, int stock, long id_tipo_producto) {
		super();
		this.id_producto = id_producto;
		this.nombre_producto = nombre_producto;
		this.precio = precio;
		this.stock = stock;
		this.id_tipo_producto = id_tipo_producto;
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


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public long getId_tipo_producto() {
		return id_tipo_producto;
	}


	public void setId_tipo_producto(long id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}


	public String getNombreTipoProducto() {
		return nombreTipoProducto;
	}


	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}
	
}
