package rios.demo.dao;

import java.sql.ResultSet;
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
import rios.demo.bean.TipoProductoBean;

public class TipoProductoDAOImpl implements TipoProductoDao {
	
	private static final Logger log= LogManager.getLogger(TipoProductoDAOImpl.class);

	@Override
	public List<TipoProductoBean> listaTipoProducto() {
		
		// select * from tb_tipo_producto;
		
		List<TipoProductoBean>lst = new ArrayList<TipoProductoBean>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		try {
			//cnx = new Miconexion().getConexion();
			cnx =PoolConexiones.getConexion();
			String sql="select * from tb_tipo_producto";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			TipoProductoBean tipo =null;
			
			while(rs.next()) {
				
				tipo = new TipoProductoBean();
				tipo.setId(rs.getInt("id_tipo_producto"));
				tipo.setDescripcion(rs.getString("descripcion_tipo_producto"));
							
				lst.add(tipo);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion listaTipoProducto" + e);
		}
		
		return lst;
	}
}
