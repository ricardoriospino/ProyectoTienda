package rios.demo.dao;

import java.util.List;

import rios.demo.bean.ClienteNaturalyJuridicoBean;

public interface ClienteNaturalyJuridicoDAO {
	
	public List<ClienteNaturalyJuridicoBean>listaClienteNaturalyJuridico();
	public ClienteNaturalyJuridicoBean obtenerClienteNaturalJuridicoById (int id);

}
