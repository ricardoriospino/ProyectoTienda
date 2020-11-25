package demo.dao;

import java.util.List;

import demo.bean.ClienteNaturalyJuridicoBean;

public interface ClienteNaturalyJuridicoDAO {
	
	public List<ClienteNaturalyJuridicoBean>listaClienteNaturalyJuridico();
	public ClienteNaturalyJuridicoBean obtenerClienteNaturalJuridicoById (int id);

}
