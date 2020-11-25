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
import java.sql.PreparedStatement; // gregado
import demo.bd.PoolConexiones;
import demo.bean.ClienteBean;




public class ClienteDAOImpl implements ClienteDAO {

	// pone oberride por que ya esta creado en clienteDAO
	@Override
	public int insertarCliente(ClienteBean cliente) {
		
		//CALL stp_cliente_natural ('angeles ate','24323',2,'angelesaf@hotmail.com','mmmonolo','carrazco','diaz');
		
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx= PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="CALL stp_cliente_natural (?,?,?,?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1, cliente.getDni());
			pst.setString(2,cliente.getDireccion());
			pst.setString(3,cliente.getTelefono());
			pst.setLong(4,cliente.getCodigo_distrito());
			pst.setString(5,cliente.getCorreo());
			pst.setString(6,cliente.getNombre());
			pst.setString(7,cliente.getApellido_pa());
			pst.setString(8,cliente.getApellido_ma());
			
	
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
	public List<ClienteBean> listaClienteNatural() {
		
		// SELECT * FROM tb_cliente_per_natural;
		
		List<ClienteBean>lst = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			//cnx = new Miconexion().getConexion();
			cnx= PoolConexiones.getConexion();
			String sql="SELECT * FROM tb_cliente_per_natural";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			ClienteBean clienteNatural =null;
			
			while(rs.next()) {
				
				clienteNatural = new ClienteBean();
				clienteNatural.setId_cliente(rs.getInt("id_per_natural"));
				clienteNatural.setId(rs.getInt("id_cliente"));
				clienteNatural.setDni(rs.getString("dni"));
				clienteNatural.setNombre(rs.getString("nombre"));
				clienteNatural.setApellido_pa(rs.getString("apellido_paterno"));
				clienteNatural.setApellido_ma(rs.getString("apellido_materno"));
				
				lst.add(clienteNatural);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lst;	
		
	}
	

	@Override
	public int eliminarClienteNatural(int id_cliente) {
		Connection cnx =null;
		PreparedStatement pst = null;
		int bandera = 0;
		
		
		try{
			
			//cnx = new Miconexion().getConexion(); 
			cnx= PoolConexiones.getConexion();
			CallableStatement cs= cnx.prepareCall("{CALL stp_borra_cliente_natural(?,?)}");
			
			cs.setInt(1, id_cliente);
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
	public ClienteBean obtenerClienteById(int id_cliente) {
		/*SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito,
			a.correo_cliente,b.id_per_natural,b.nombre, b.apellido_paterno, b.apellido_materno
			FROM tb_cliente AS a JOIN tb_cliente_per_natural AS b
			ON a.id_cliente = b.id_cliente WHERE a.id_cliente = 50 */
		
		ClienteBean clienteNatural = null;
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		try {
			//cnx = new Miconexion().getConexion();
			cnx= PoolConexiones.getConexion();
			String sql="SELECT a.id_cliente, a.direccion_cliente, a.telefono_cliente, a.codigo_distrito, \r\n" + 
					"			a.correo_cliente,b.id_per_natural,b.dni ,b.nombre, b.apellido_paterno, b.apellido_materno \r\n" + 
					"			FROM tb_cliente AS a JOIN tb_cliente_per_natural AS b \r\n" + 
					"			ON a.id_cliente = b.id_cliente WHERE a.id_cliente = ?";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setInt(1, id_cliente);
			pst.executeQuery();
			rs= pst.getResultSet();
			
			
			while(rs.next()) { // solo entra si hay resultado
				
				clienteNatural = new ClienteBean();
				
				clienteNatural.setId(rs.getInt("id_cliente"));
				clienteNatural.setId_cliente(rs.getInt("id_per_natural"));
				clienteNatural.setDni(rs.getString("dni"));
				clienteNatural.setNombre(rs.getString("nombre"));
				clienteNatural.setApellido_ma(rs.getString("apellido_materno"));
				clienteNatural.setApellido_pa(rs.getString("apellido_paterno"));
				clienteNatural.setDireccion(rs.getString("direccion_cliente"));
				clienteNatural.setTelefono(rs.getString("telefono_cliente"));
				clienteNatural.setCodigo_distrito(rs.getLong("codigo_distrito"));
				clienteNatural.setCorreo(rs.getString("correo_cliente"));
			
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return clienteNatural; // null o lleno
	}

	@Override
	public int actualizarClienteNatural(ClienteBean clienteNatural) {
		
		// CALL stp_cliente_natural_update ('canta obrajillo','11111',1,'canta@gmail.com','marcelino','pino','mayta', 59);
		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx= PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="CALL stp_cliente_natural_update (?,?,?,?,?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1,clienteNatural.getDireccion());
			pst.setString(2,clienteNatural.getTelefono());
			pst.setLong(3,clienteNatural.getCodigo_distrito());
			pst.setString(4,clienteNatural.getCorreo());
			pst.setString(5, clienteNatural.getDni());
			pst.setString(6,clienteNatural.getNombre());
			pst.setString(7,clienteNatural.getApellido_pa());
			pst.setString(8,clienteNatural.getApellido_ma());
			pst.setInt(9,clienteNatural.getId());
			
	
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
