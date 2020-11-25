package demo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import demo.bd.Miconexion;
//import java.sql.Connection; // agregado 
//import java.sql.PreparedStatement; // agregado 
//import demo.bd.PoolConexiones;
import demo.bean.ClienteNaturalyJuridicoBean;



public class ClienteNaturalyJuridicoDAOimpl implements ClienteNaturalyJuridicoDAO {

	
	@Override
	public List<ClienteNaturalyJuridicoBean> listaClienteNaturalyJuridico() {
		
		// SELECT * FROM cliente_vista;
		
		List<ClienteNaturalyJuridicoBean>lst = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			cnx = new Miconexion().getConexion();
			//cnx= PoolConexiones.getConexion();
			String sql = "SELECT * FROM cliente_vista";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.execute();
			rs= pst.getResultSet();
			
			ClienteNaturalyJuridicoBean clienteNaturalJuridico = null;
			
			while(rs.next()) {
				clienteNaturalJuridico = new ClienteNaturalyJuridicoBean();
				clienteNaturalJuridico.setId(rs.getInt("id"));
				clienteNaturalJuridico.setNombre_o_razon_social(rs.getString("nombre_o_razon_social"));
				clienteNaturalJuridico.setDni_o_ruc(rs.getString("dni_o_ruc"));
				
				lst.add(clienteNaturalJuridico);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return lst;
	}

	@Override
	public ClienteNaturalyJuridicoBean obtenerClienteNaturalJuridicoById(int id) {
		
		// SELECT id , nombre_o_razon_social , dni_o_ruc FROM cliente_vista WHERE id = 50;
		
		ClienteNaturalyJuridicoBean clienteNaturalJuridico = null;
		
		Connection cnx = null;
		PreparedStatement pst =null;
		ResultSet rs =null;
		
		try {
			cnx = new Miconexion().getConexion();
			//cnx= PoolConexiones.getConexion();
			String sql = " SELECT id , nombre_o_razon_social , dni_o_ruc FROM cliente_vista WHERE id = ?";
			
			pst = (PreparedStatement) cnx.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeQuery();
			rs= pst.getResultSet();
			
			while(rs.next()) { // solo entra si hay resultado
				
				clienteNaturalJuridico = new ClienteNaturalyJuridicoBean();
				
				clienteNaturalJuridico.setId(rs.getInt("id"));
				clienteNaturalJuridico.setNombre_o_razon_social(rs.getString("nombre_o_razon_social"));
				clienteNaturalJuridico.setDni_o_ruc(rs.getString("dni_o_ruc"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return clienteNaturalJuridico;// NULL O LLENO
	}

}
