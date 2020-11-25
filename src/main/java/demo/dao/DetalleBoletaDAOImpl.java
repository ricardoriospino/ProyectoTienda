package demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import demo.bd.PoolConexiones;
import demo.bean.CarritoCompraBean;

public class DetalleBoletaDAOImpl implements DetalleBoletaDAO {

	@Override
	public int insertarDetalleBoleta(CarritoCompraBean detalleBoleta) {
		
		// INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,sub_total)
		// VALUES (1,3,4,10.00)
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="INSERT INTO tb_detalle_boleta(id_boleta,id_producto,cantidad,sub_total)" + 
					"VALUES (?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setInt(1, detalleBoleta.getIdBoleta());
			pst.setLong(2, detalleBoleta.getId_producto());
			pst.setInt(3, detalleBoleta.getCantidad());
			pst.setDouble(4, detalleBoleta.getSubTotal());
			
			
			//paso 3: Enviamos sentencia SQL a la base de datos
			registros = pst.executeUpdate();//para insert , update,delete
			
			System.out.println("Registros Insertados: "+ registros);
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try{
				//cerrar la conexion a la base de datos
				if(pst !=null) pst.close();
				if(cnx !=null) cnx.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return registros;
		
	}

}
