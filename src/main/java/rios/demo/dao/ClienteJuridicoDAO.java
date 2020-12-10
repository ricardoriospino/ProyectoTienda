package rios.demo.dao;

import java.util.List;

import rios.demo.bean.ClienteJuridicoBean;

public interface ClienteJuridicoDAO {
	
	public int insertarJuridicoCliente(ClienteJuridicoBean clienteJuridico);
	public List<ClienteJuridicoBean> listaClienteJuridico();
	public int eliminarClienteJuridico(Long idClienteJuridico);
	public ClienteJuridicoBean obtenerClienteJuridicoById( Long idClienteJuridico);
	public int actualizarClienteJuridico (ClienteJuridicoBean clienteJuridico);
	

}
