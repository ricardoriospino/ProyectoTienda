package rios.demo.dao;

import java.util.List;

import rios.demo.bean.ClienteBean;
import rios.demo.bean.EmpleadoBean;


// interface solo obtienen metodos 
public interface ClienteDAO {
	// creando metodos 
	public int insertarCliente (ClienteBean cliente);
	public List<ClienteBean> listaClienteNatural();
	public int eliminarClienteNatural ( int id_cliente);
	public ClienteBean obtenerClienteById(int id_cliente);
	public int actualizarClienteNatural (ClienteBean clienteNatural);
	

}
