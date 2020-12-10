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
import rios.demo.bean.DistritoBean;


public class DistritoDAOImpl implements DistritoDAO {
	
	private static final Logger log= LogManager.getLogger(DistritoDAOImpl.class);

	@Override
	public List<DistritoBean> listaDistrito() {
		
		// SELECT * FROM tb_distrito;
		
		List<DistritoBean>lst = new ArrayList<DistritoBean>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			String sql="SELECT * FROM tb_distrito";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			DistritoBean distrito =null;
			
			while(rs.next()) {
				
				distrito = new DistritoBean();
				distrito.setId(rs.getInt("codigo_distrito"));
				distrito.setDescripcion(rs.getString("descripcion"));
							
				lst.add(distrito);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion listaDistrito" + e);
		}
		return lst;
	}

}
