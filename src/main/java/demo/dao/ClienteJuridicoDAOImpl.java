package demo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import demo.bd.Miconexion;
import java.sql.Connection; // agregado 
import java.sql.PreparedStatement; // agregado 

import demo.bd.PoolConexiones;
import demo.bean.ClienteJuridicoBean;



public class ClienteJuridicoDAOImpl implements ClienteJuridicoDAO {

	@Override
	public int insertarJuridicoCliente(ClienteJuridicoBean clienteJuridico) {
		
		// CALL stp_cliente_juridico ('grifo tokio','234256',1,'tokiohotel@gmail.com','tokio sac','20434325','telefono');
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="CALL stp_cliente_juridico (?,?,?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1,clienteJuridico.getDireccion());
			pst.setString(2,clienteJuridico.getTelefono());
			pst.setLong(3,clienteJuridico.getCodigo_distrito());
			pst.setString(4,clienteJuridico.getCorreo());
			pst.setString(5,clienteJuridico.getRazon_social());
			pst.setString(6,clienteJuridico.getRuc());
			pst.setString(7,clienteJuridico.getContacto_cliente_juridico());
			
	
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

	@Override
	public List<ClienteJuridicoBean> listaClienteJuridico() {
		
		// SELECT * FROM tb_cliente_per_juridica;
		
		List<ClienteJuridicoBean>lst = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			
			String sql="SELECT * FROM tb_cliente_per_juridica";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			ClienteJuridicoBean clienteJuridico =null;
			
			while(rs.next()) {
				
				clienteJuridico = new ClienteJuridicoBean();
				clienteJuridico.setId_cliente_juridico(rs.getInt("id_per_juridica"));
				clienteJuridico.setId(rs.getLong("id_cliente"));
				clienteJuridico.setRazon_social(rs.getString("razon_social"));
				clienteJuridico.setRuc(rs.getString("ruc"));
				clienteJuridico.setContacto_cliente_juridico(rs.getString("contacto_cliente"));
				
				
				
				
				lst.add(clienteJuridico);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;	
	}

	@Override
	public int eliminarClienteJuridico(Long idClienteJuridico) {
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int bandera = 0;
		
		
		try{
	
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			CallableStatement cs= cnx.prepareCall("{CALL stp_borra_cliente_juridico(?,?)}");
			
			cs.setLong(1, idClienteJuridico);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			
			 bandera = cs.getInt(2);
						
			System.out.println(" bandera : "+bandera);
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
		return bandera;
	}

	@Override
	public ClienteJuridicoBean obtenerClienteJuridicoById(Long idClienteJuridico) {
		
		/* SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito,
			a.correo_cliente, b.id_per_juridica, b.razon_social, b.ruc, b.contacto_cliente 
			FROM tb_cliente AS a JOIN tb_cliente_per_juridica AS b
			ON a.id_cliente = b.id_cliente WHERE a.id_cliente = ?;              */
		
		ClienteJuridicoBean clienteJuridico = null;
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		try {
			//cnx = new Miconexion().getConexion();
			cnx = PoolConexiones.getConexion();
			String sql="SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito,\r\n" + 
					"			a.correo_cliente, b.id_per_juridica, b.razon_social, b.ruc, b.contacto_cliente \r\n" + 
					"			FROM tb_cliente AS a JOIN tb_cliente_per_juridica AS b \r\n" + 
					"			ON a.id_cliente = b.id_cliente WHERE a.id_cliente = ?";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setLong(1, idClienteJuridico);
			pst.executeQuery();
			rs= pst.getResultSet();
			
			
			while(rs.next()) { // solo entra si hay resultado
				
				clienteJuridico = new ClienteJuridicoBean();
				
				clienteJuridico.setId(rs.getLong("id_cliente"));
				clienteJuridico.setId_cliente_juridico(rs.getInt("id_per_juridica"));
				clienteJuridico.setRazon_social(rs.getString("razon_social"));
				clienteJuridico.setRuc(rs.getString("ruc"));
				clienteJuridico.setDireccion(rs.getString("direccion_cliente"));
				clienteJuridico.setTelefono(rs.getString("telefono_cliente"));
				clienteJuridico.setCodigo_distrito(rs.getLong("codigo_distrito"));
				clienteJuridico.setCorreo(rs.getString("correo_cliente"));
				clienteJuridico.setContacto_cliente_juridico(rs.getString("contacto_cliente"));

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return clienteJuridico; // null o lleno
	}

	@Override
	public int actualizarClienteJuridico(ClienteJuridicoBean clienteJuridico) {
		
		//CALL stp_cliente_juridico_update ('catacao','22222',3,'catacaosa@gmail.com','catacaos sac','9999999999','telefono', 44);
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="CALL stp_cliente_juridico_update (?,?,?,?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1,clienteJuridico.getDireccion());
			pst.setString(2,clienteJuridico.getTelefono());
			pst.setLong(3,clienteJuridico.getCodigo_distrito());
			pst.setString(4,clienteJuridico.getCorreo());
			pst.setString(5,clienteJuridico.getRazon_social());
			pst.setString(6,clienteJuridico.getRuc());
			pst.setString(7,clienteJuridico.getContacto_cliente_juridico());
			pst.setLong(8,clienteJuridico.getId());
			
	
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
