package rios.demo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import demo.bd.Miconexion;
import java.sql.Connection; // agregado 
import java.sql.PreparedStatement; // agregado 


import rios.demo.bd.PoolConexiones;
import rios.demo.bean.ProductoBean;

public class ProductoDAOImpl implements ProductoDAO {
	
	private static final Logger log= LogManager.getLogger(ProductoDAOImpl.class);

	@Override
	public int insertarProducto(ProductoBean producto) {
		
		/* INSERT INTO tb_producto(id_producto,nombre_producto,precio,stock_actual,id_tipo_producto)
		VALUES (11,'galletas',0,10,2);*/
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="INSERT INTO tb_producto(nombre_producto,precio,stock_actual,id_tipo_producto)" + 
					"VALUES (?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1, producto.getNombre_producto());
			pst.setDouble(2, producto.getPrecio());
			pst.setInt(3, producto.getStock());
			pst.setLong(4, producto.getId_tipo_producto());
			
			
			//paso 3: Enviamos sentencia SQL a la base de datos
			registros = pst.executeUpdate();//para insert , update,delete
			
			log.debug("Registros Insertados: "+ registros);
	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion insertarProducto" + e);
		}finally {
			
			try{
				//cerrar la conexion a la base de datos
				if(pst !=null) pst.close();
				if(cnx !=null) cnx.close();
			
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion insertarProducto" + e);
			}	
		}
		return registros;
	}

	@Override
	public List<ProductoBean> listarProducto() {
		
		// SELECT * FROM tb_producto;
		
		List<ProductoBean>lst = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			String sql="SELECT  a.id_producto , a.nombre_producto , a.precio ,a.stock_actual , b.descripcion_tipo_producto FROM tb_producto AS a\r\n" + 
					"JOIN tb_tipo_producto AS b ON  a.id_tipo_producto = b.id_tipo_producto;";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			ProductoBean producto =null;
			
			while(rs.next()) {
				
				producto = new ProductoBean();
				producto.setId_producto(rs.getLong("id_producto"));
				producto.setNombre_producto(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock_actual"));
				//producto.setId_tipo_producto(rs.getLong("id_tipo_producto"));
				producto.setNombreTipoProducto(rs.getString("descripcion_tipo_producto"));
								
				lst.add(producto);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion listarProducto" + e);
		}	
		return lst;		
	}

	@Override
	public int eliminarProducto(Long id_producto) {
		Connection cnx =null;
		PreparedStatement pst = null;
		int bandera = 0;
		//{CALL stp_borra_Producto(?,?)}
		
		try{
			
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			CallableStatement cs= cnx.prepareCall("{CALL stp_borra_Producto(?,?)}");
			
			cs.setLong(1, id_producto);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			
			bandera = cs.getInt(2);			
			log.debug(" bandera : "+bandera);
	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try{
				//cerrar la conexion a la base de datos
				if(pst !=null) pst.close();
				if(cnx !=null) cnx.close();
			
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion eliminarProducto" + e);
			}	
		}	
		return bandera;
	}

	@Override
	public ProductoBean obtenerProductoById(long idProducto) {
		
		ProductoBean producto = null;
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			String sql="SELECT * FROM tb_producto WHERE id_producto = ?";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setLong(1, idProducto);
			pst.executeQuery();
			rs= pst.getResultSet();
			
			
			while(rs.next()) { // solo entra si hay resultado
				
						
				producto = new ProductoBean();
				producto.setId_producto(rs.getLong("id_producto"));
				producto.setNombre_producto(rs.getString("nombre_producto"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock_actual"));
				producto.setId_tipo_producto(rs.getLong("id_tipo_producto"));
				
			
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion obtenerProductoById " + e);
		}
		
		return producto; // null o lleno	
	}

	@Override
	public int actualizarProducto(ProductoBean producto) {
		
		// update tb_producto set nombre_producto = 'kn96' , precio = 7, stock_actual=100, id_tipo_producto = 2 WHERE  id_producto= 1 ;
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;	
		try{
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="update tb_producto set nombre_producto = ? , precio = ? , stock_actual=?, id_tipo_producto = ? WHERE  id_producto= ? ";
			
			log.debug(sql);
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1, producto.getNombre_producto());
			pst.setDouble(2, producto.getPrecio());
			pst.setInt(3, producto.getStock());
			pst.setLong(4, producto.getId_tipo_producto());
			pst.setLong(5, producto.getId_producto());
		
			
			//paso 3: Enviamos sentencia SQL a la base de datos
			registros = pst.executeUpdate();//para insert , update,delete
			
			log.debug("Registros actualizados: "+registros);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion actualizarProducto" + e);

		}

		return registros;
		
	}

}
