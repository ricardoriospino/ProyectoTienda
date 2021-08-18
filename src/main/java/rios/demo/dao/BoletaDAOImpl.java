package rios.demo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import rios.demo.bd.PoolConexiones;
import rios.demo.bean.BoletaBean;
import rios.demo.bean.CarritoCompraBean;
import rios.demo.bean.ClienteNaturalyJuridicoBean;
import rios.demo.bean.DetalleBoletaCompraBean;
import rios.demo.bean.EmpleadoBean;


public class BoletaDAOImpl implements BoletaDAO {
	
	private static final Logger log= LogManager.getLogger(BoletaDAOImpl.class);
		
	@Override
	public int insertarBoleta(BoletaBean boleta) {
		//INSERT INTO tb_boleta(id_empleado,fecha_boleta,id_cliente,estado_boleta,total_venta) 
		//VALUES (1,2020-09-02 22:18:10,5,1,100);
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="INSERT INTO tb_boleta(id_empleado,fecha_boleta,id_cliente,estado_boleta,total_venta)" + 
					"VALUES (?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setInt(1, boleta.getIdEmpleado());
			pst.setDate(2, (Date) boleta.getFechaBoleta());
			pst.setInt(3, boleta.getIdCliente());
			pst.setByte(4, boleta.getEstadoBoleta());
			pst.setDouble(5, boleta.getTotalBoleta());
			
			
			//paso 3: Enviamos sentencia SQL a la base de datos
			registros = pst.executeUpdate();//para insert , update,delete
			
			log.debug("Registros Insertados: "+ registros);
	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion" + e);
		}finally {
			
			try{
				//cerrar la conexion a la base de datos
				if(pst !=null) pst.close();
				if(cnx !=null) cnx.close();
			
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion insertarBoleta" + e);
			}	
		}
		
		return registros;
	}
	
	@Override
	public boolean insertarVenta(EmpleadoBean empleado, ClienteNaturalyJuridicoBean cliente,
			List<CarritoCompraBean> lstCarrito, double totalBoleta) {
			
		Connection cnx =null;
		PreparedStatement pst = null;
		int registrosAfectados = 0;
		boolean exito = false;
		
		try {
			
			cnx = PoolConexiones.getConexion();		
			cnx.setAutoCommit(false);
			
			//insertar cabecera
			int boleta_emitida = 1;
			long idBoleta;
			
			String sql ="INSERT INTO tb_boleta(id_empleado,fecha_boleta,id_cliente,estado_boleta,total_venta)" + 
					"VALUES (?,?,?,?,?)";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, empleado.getId());
			//pst.setDate(2, convert(new java.util.Date())); solo devuelve la fecha 
			pst.setTimestamp(2, getFechaHoraActual() );
			pst.setInt(3, cliente.getId());
			pst.setInt(4, boleta_emitida);
			pst.setDouble(5, totalBoleta);
			registrosAfectados = pst.executeUpdate();
			
			if(registrosAfectados == 0) {
				// forzar un error para java y que entre al catch
				throw new SQLException("Cabecera boleta no insertada!");
			}
			
			//obtiene el id generado
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	idBoleta = generatedKeys.getLong(1);
		            }else {
		                throw new SQLException("Creacion de Boleta fallida, no tiene Id.");
		            }
		    }
			
			log.debug("id boleta " + idBoleta);
			
			//insertar detalle (recorrer un for)
			for(CarritoCompraBean carrito: lstCarrito) {
				
				 sql ="INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,sub_total)" + 
						"VALUES (?,?,?,?)";
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.setLong(1, idBoleta);
				pst.setLong(2, carrito.getId_producto());
				pst.setInt(3, carrito.getCantidad());
				pst.setDouble(4, carrito.getSubTotal());
				pst.executeUpdate();
							
			}
			
			// actualizar stocks (recorrer con un for)			
			for (CarritoCompraBean carrito: lstCarrito) {
				
				sql ="UPDATE tb_producto SET stock_actual = ? WHERE id_producto = ? ";
				
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.setInt(1, carrito.getStockActual());
				pst.setLong(2, carrito.getId_producto());
				pst.executeUpdate();
			}
			
			// si termina correctamente commitea 
			cnx.commit();
			exito = true;
			
		} catch (Exception e) {
			try {
				// si falla roollback
				cnx.rollback();
				exito = false;
			} catch (SQLException e1) {
				
				e1.printStackTrace();
				log.error("error en Mi conexion" + e1);
			}
			e.printStackTrace();
			log.error("error en Mi conexion" + e);
		}finally {
			try {
				// si falla o no falla cierra la conexion 
				cnx.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
				log.error("error en Mi conexion insertarVenta" + e);
			}
		}
		
		return false;
	}
	// SOLO FECHAS 
    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    //FECHA Y HORA  
    private static java.sql.Timestamp getFechaHoraActual() {	
        return new java.sql.Timestamp(new java.util.Date().getTime());
  
    }

	public BoletaBean obtenerUltimaBoleta() {
		
		BoletaBean boleta = null;
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			String sql="SELECT*FROM tb_boleta ORDER BY id_boleta DESC LIMIT 1 ";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.executeQuery();
			rs= pst.getResultSet();
			
			
			while(rs.next()) { // solo entra si hay resultado
				
						
				boleta = new BoletaBean();
				boleta.setIdBoleta(rs.getInt("id_boleta"));
				boleta.setIdEmpleado(rs.getInt("id_empleado"));
				boleta.setFechaBoleta(rs.getTimestamp("fecha_boleta"));
				boleta.setIdCliente(rs.getInt("id_cliente"));			
			
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion obtenerUltimaBoleta" + e);
		}		
		return boleta;
	}

	@Override
	public DetalleBoletaCompraBean obtenerIdBoleta() {
			
		DetalleBoletaCompraBean boleta = null;
		
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			cnx = PoolConexiones.getConexion();
			String sql= "SELECT id_boleta FROM tb_boleta ORDER BY id_boleta DESC LIMIT 1";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.executeQuery();
			rs= pst.getResultSet();
				
			if(rs.next()) {
				boleta = new DetalleBoletaCompraBean();
				boleta.setIdBoleta(rs.getInt("id_boleta"));
				
			}
								
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion obtenerIdBoleta" + e);	
		}		
		return boleta ;
	}

	@Override
	public DetalleBoletaCompraBean obtenerDetalleBoletaById(int idBoleta) {
		
		DetalleBoletaCompraBean boletaDatos = null;
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			
			cnx = PoolConexiones.getConexion();
				String sql= "SELECT a.id_boleta , a.fecha_boleta , a.total_venta , b.id_empleado , b.nombres , c.razon_social AS nombre_razon_social , c.ruc AS dni_ruc   FROM tb_boleta AS a\r\n" + 
						"	JOIN tb_empleado AS b ON a.id_empleado = b.id_empleado \r\n" + 
						"    JOIN tb_cliente_per_juridica AS c ON a.id_cliente = c.id_cliente\r\n" + 
						"    WHERE a.id_boleta = ? \r\n" + 
						"UNION \r\n" + 
						"SELECT  a.id_boleta , a.fecha_boleta , a.total_venta , b.id_empleado , b.nombres , c.nombre AS nombre_razon_social , c.dni AS dni_ruc FROM tb_boleta AS a\r\n" + 
						"	JOIN tb_empleado AS b ON a.id_empleado = b.id_empleado \r\n" + 
						"    JOIN  tb_cliente_per_natural AS c ON a.id_cliente = c.id_cliente\r\n" + 
						"    WHERE a.id_boleta = ?";
				
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.setInt(1, idBoleta);
				pst.setInt(2, idBoleta);
				pst.executeQuery();
				rs= pst.getResultSet();
				
				if(rs.next()) {
					
					boletaDatos = new DetalleBoletaCompraBean();
					boletaDatos.setIdBoleta(rs.getInt("id_boleta"));
					boletaDatos.setFechaBoleta(rs.getDate("fecha_boleta"));
					boletaDatos.setIdEmpleado(rs.getInt("id_empleado"));
					boletaDatos.setNombreEmpleado(rs.getString("nombres"));
					boletaDatos.setNombreORazonSocial(rs.getString("nombre_razon_social"));
					boletaDatos.setDniORuc(rs.getString("dni_ruc"));
					boletaDatos.setTotalVenta(rs.getDouble("total_venta"));
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion obtenerDetalleBoletaById" + e);	
			
		}
		
		return boletaDatos;
	}

	@Override
	public List<DetalleBoletaCompraBean> listarDetalleCompraProductos(int idBoleta) {
		
		List<DetalleBoletaCompraBean>lst = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			cnx = PoolConexiones.getConexion();
			String sql=	"SELECT   a.id_producto , b.nombre_producto , b.precio , a.cantidad , a.sub_total FROM tb_detalle_boleta AS a \r\n" + 
						"JOIN tb_producto AS b ON a.id_producto = b.id_producto\r\n" + 
						"WHERE id_boleta = ?";
			
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setInt(1, idBoleta);
			pst.execute();
			rs= pst.getResultSet();
			
			DetalleBoletaCompraBean compraDetalle = null;
			
			while(rs.next()) {
				
				compraDetalle = new DetalleBoletaCompraBean();
				
				compraDetalle.setIdProducto(rs.getInt("id_producto"));
				compraDetalle.setNombreProducto(rs.getString("nombre_producto"));
				compraDetalle.setPrecio(rs.getDouble("precio"));
				compraDetalle.setCantidad(rs.getInt("cantidad"));
				compraDetalle.setSubTotal(rs.getDouble("sub_total"));
				lst.add(compraDetalle);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion listarDetalleCompraProductos" + e);
		}
		
		return lst;
	}	
	
}


