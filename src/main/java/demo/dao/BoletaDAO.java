package demo.dao;

import java.util.List;

import demo.bean.BoletaBean;
import demo.bean.CarritoCompraBean;
import demo.bean.ClienteNaturalyJuridicoBean;
import demo.bean.EmpleadoBean;

public interface BoletaDAO {
	
	public int insertarBoleta(BoletaBean boleta);
	
	public boolean insertarVenta (EmpleadoBean empleado, 
	ClienteNaturalyJuridicoBean cliente, List<CarritoCompraBean> lstCarrito, double totalBoleta);

}
