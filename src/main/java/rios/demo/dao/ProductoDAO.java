package rios.demo.dao;

import java.util.List;

import rios.demo.bean.ProductoBean;

public interface ProductoDAO {
	
	public int insertarProducto(ProductoBean producto);
	public List<ProductoBean> listarProducto();
	public int eliminarProducto (Long id_producto );
	public ProductoBean obtenerProductoById(long idProducto);
	public int actualizarProducto (ProductoBean producto);
	

}

