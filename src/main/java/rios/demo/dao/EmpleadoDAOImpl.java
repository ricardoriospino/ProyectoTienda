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
import rios.demo.bean.EmpleadoBean;

public class EmpleadoDAOImpl implements EmpleadoDAO {
	
	private static final Logger log= LogManager.getLogger(EmpleadoDAOImpl.class);
	
	@Override
	public int insertarEmpleado(EmpleadoBean empleado) {
		
		/*INSERT INTO tb_empleado(nombres,apellido_ma,apellido_pa,fecha_nacimiento,direccion_emp,telefono,codigo_distrito,correo,fecha_ingreso)
		 VALUE ('carlos','mora','nu�ez','1980-02-10','angeles','342323',1,'angeles@hotail.com','2020-03-15');*/

		Connection cnx =null;
		PreparedStatement pst = null;
		int registros = 0;
		
		try{
			
			//Paso 1: Crear La Conexion
			//cnx = new Miconexion().getConexion(); 
			cnx = PoolConexiones.getConexion();
			
			//Paso 2: Preparamos la sentencia SQL
			String sql ="INSERT INTO tb_empleado(nombres,apellido_ma,apellido_pa,fecha_nacimiento,direccion_emp,telefono,codigo_distrito,correo,fecha_ingreso)" + 
					"VALUE (?,?,?,?,?,?,?,?,?)";
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setString(1, empleado.getNombre());
			pst.setString(2, empleado.getApellido_materno());
			pst.setString(3, empleado.getApellido_paterno());
			pst.setString(4, empleado.getFecha_nacimiento());
			pst.setString(5, empleado.getDireccion());
			pst.setString(6, empleado.getTelefono());
			pst.setLong(7, empleado.getCodigo_distrito());
			pst.setString(8, empleado.getCorreo());
			pst.setString(9, empleado.getFecha_ingreso());
			
			//paso 3: Enviamos sentencia SQL a la base de datos
			registros = pst.executeUpdate();//para insert , update,delete
			
			log.debug("Registros Insertados: "+ registros);
	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("error en Mi conexion insertarEmpleado" + e);
		}finally {
			
			try{
				//cerrar la conexion a la base de datos
				if(pst !=null) pst.close();
				if(cnx !=null) cnx.close();
			
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion insertarEmpleado" + e);
			}	
		}
		
		return registros;

	}
	
		public EmpleadoBean validarUsuarioClave(String usuario, String clave) {
		
		//SELECT * FROM tb_empleado WHERE usuario='@ronal' AND clave='123' 
		
		EmpleadoBean empleado = null;
		
		
			Connection cnx = null;
			PreparedStatement pst =null;
			ResultSet rs =null;
			try {
				//cnx = new Miconexion().getConexion();
				cnx = PoolConexiones.getConexion();
				String sql="SELECT * FROM tb_empleado WHERE usuario=? AND clave=?";
				
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.setString(1, usuario);
				pst.setString(2, clave);
				
				pst.executeQuery();
				rs= pst.getResultSet();
				
				
				while(rs.next()) { // solo entra si hay resultado
					
					empleado = new EmpleadoBean();
					
					empleado.setId(rs.getLong("id_empleado"));
					empleado.setNombre(rs.getString("nombres"));
					empleado.setUsusario(rs.getString("usuario"));
					empleado.setClave(rs.getString("clave"));
					empleado.setApellido_paterno(rs.getString("apellido_pa"));
					empleado.setApellido_materno(rs.getString("apellido_ma"));
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion validarUsuarioClave" + e);
			}	
			return empleado; // null o lleno	
		}

		@Override
		public List<EmpleadoBean> listaEmpleado() {
			
			// SELECT * FROM tb_empleado;
			
			List<EmpleadoBean>lst = new ArrayList<>();
			Connection cnx = null;
			PreparedStatement pst =null;
			ResultSet rs =null;
			
			try {
				//cnx = new Miconexion().getConexion();
				cnx = PoolConexiones.getConexion();
				String sql="SELECT a.id_empleado, a.nombres, a.apellido_ma, a.apellido_pa, a.fecha_nacimiento ,\r\n" + 
						"a.direccion_emp , a.telefono , b.descripcion , a.correo FROM tb_empleado AS a\r\n" + 
						"JOIN tb_distrito AS b ON a.codigo_distrito = b.codigo_distrito;";
				
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.execute();
				rs= pst.getResultSet();
				
				EmpleadoBean empleado =null;
				
				while(rs.next()) {
					
					empleado = new EmpleadoBean();
					empleado.setId(rs.getLong("id_empleado"));
					empleado.setNombre(rs.getString("nombres"));
					empleado.setApellido_materno(rs.getString("apellido_ma"));
					empleado.setApellido_paterno(rs.getString("apellido_pa"));
					empleado.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
					empleado.setDireccion(rs.getString("direccion_emp"));
					empleado.setTelefono(rs.getString("telefono"));
					empleado.setDistrito(rs.getString("descripcion"));
					//empleado.setCodigo_distrito(rs.getLong("codigo_distrito"));
					empleado.setCorreo(rs.getString("correo"));
										
					lst.add(empleado);
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion listaEmpleado" + e);
			}
			
			return lst;	
		}

		@Override
		public int eliminarEmpleado(Long id_empleado) {
			Connection cnx =null;
			PreparedStatement pst = null;
			int bandera = 0;
			
			//DELETE FROM tb_empleado WHERE id_empleado = 15;
			try{	
				//cnx = new Miconexion().getConexion(); 
				cnx = PoolConexiones.getConexion();
				CallableStatement cs= cnx.prepareCall("{CALL stp_borra_Empleado(?,?)}");
				
				cs.setLong(1, id_empleado);
				cs.registerOutParameter(2, Types.INTEGER);
				cs.execute();
				
				bandera = cs.getInt(2);			
				log.debug(" bandera : "+bandera);
					
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion eliminarEmpleado" + e);
			}finally {
				
				try{
					//cerrar la conexion a la base de datos
					if(pst !=null) pst.close();
					if(cnx !=null) cnx.close();
				
				}catch (Exception e) {
					e.printStackTrace();
					log.error("error en Mi conexion eliminarEmpleado" + e);
				}	
			}		
			return bandera;
		}

		@Override
		public EmpleadoBean obtenerEmpleadoById(long idEmpleado) {
					
			EmpleadoBean empleado = null;
		
			Connection cnx = null;
			PreparedStatement pst =null;
			ResultSet rs =null;
			try {
				//cnx = new Miconexion().getConexion();
				cnx = PoolConexiones.getConexion();
				String sql="SELECT * FROM tb_empleado WHERE id_empleado = ?";
				
				pst = (PreparedStatement) cnx.prepareStatement(sql);
				pst.setLong(1, idEmpleado);
				pst.executeQuery();
				rs= pst.getResultSet();
				
				while(rs.next()) { // solo entra si hay resultado
					
					empleado = new EmpleadoBean();			
					empleado.setId(rs.getLong("id_empleado"));
					empleado.setNombre(rs.getString("nombres"));
					empleado.setApellido_materno(rs.getString("apellido_ma"));
					empleado.setApellido_paterno(rs.getString("apellido_pa"));
					empleado.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
					empleado.setFecha_ingreso(rs.getString("fecha_ingreso"));
					empleado.setDireccion(rs.getString("direccion_emp"));
					empleado.setTelefono(rs.getString("telefono"));
					empleado.setCodigo_distrito(rs.getLong("codigo_distrito"));
					empleado.setCorreo(rs.getString("correo"));
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("error en Mi conexion obtenerEmpleadoById" + e);
			}
			
			return empleado; // null o lleno
		}

		@Override
		public int actualizarEmpleado(EmpleadoBean empleado) {
			
			/*UPDATE tb_empleado SET  nombres = 'elpuma', apellido_ma ='perez',
			apellido_pa ='gomez',fecha_nacimiento='2020-12-20', direccion_emp='la molina vieja',
			telefono='22222', codigo_distrito= 1, correo= 'ronaldi@gmail.com',fecha_ingreso ='2020-01-10' WHERE id_empleado = 15 */
					
					Connection cnx = null;
					PreparedStatement pst = null;
					int registros = 0;	
					try{
						//Paso 1: Crear La Conexion
						//cnx = new Miconexion().getConexion();
						cnx = PoolConexiones.getConexion();
						
						//Paso 2: Preparamos la sentencia SQL
						String sql ="UPDATE tb_empleado SET  nombres = ?, apellido_ma =?, apellido_pa =?, "
								+ "fecha_nacimiento=?, direccion_emp=?, telefono=?, codigo_distrito= ?, correo= ?, "
								+ "fecha_ingreso =? WHERE id_empleado = ?";
						
						System.out.println(sql);
						pst = (PreparedStatement) cnx.prepareStatement(sql);
						pst.setString(1, empleado.getNombre());
						pst.setString(2, empleado.getApellido_materno());
						pst.setString(3, empleado.getApellido_paterno());
						pst.setString(4, empleado.getFecha_nacimiento());
						pst.setString(5, empleado.getDireccion());
						pst.setString(6, empleado.getTelefono());
						pst.setLong(7, empleado.getCodigo_distrito());
						pst.setString(8, empleado.getCorreo());
						pst.setString(9, empleado.getFecha_ingreso());
						pst.setLong(10, empleado.getId());					
						//paso 3: Enviamos sentencia SQL a la base de datos
						registros = pst.executeUpdate();//para insert , update,delete
						
						log.debug("Registros actualizados: "+registros);
												
					}catch (Exception e) {
						e.printStackTrace();
						log.error("error en Mi conexion actualizarEmpleado" + e);	
					}
					return registros;			
		}
	
}
