package demo.bd;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConexiones {
	
	public static DataSource getDataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("mysql");
		ds.setUrl("jdbc:mysql://localhost:3306/tienda?useSSL=false");
		//Definimos el tamano del pool de conexiones
		ds.setInitialSize(5);//5 Conexiones iniciales
		return ds;
	}
	
	public static Connection getConexion() throws SQLException{
		return getDataSource().getConnection();
	}

}
