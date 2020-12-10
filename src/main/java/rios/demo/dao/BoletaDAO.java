package rios.demo.dao;

import java.util.List;

import rios.demo.bean.BoletaBean;
import rios.demo.bean.CarritoCompraBean;
import rios.demo.bean.ClienteNaturalyJuridicoBean;
import rios.demo.bean.DetalleBoletaCompraBean;
import rios.demo.bean.EmpleadoBean;

public interface BoletaDAO {
	
	public int insertarBoleta(BoletaBean boleta);
	
	public boolean insertarVenta (EmpleadoBean empleado, 
	ClienteNaturalyJuridicoBean cliente, List<CarritoCompraBean> lstCarrito, double totalBoleta);
	
	public DetalleBoletaCompraBean obtenerIdBoleta();
	
	public DetalleBoletaCompraBean obtenerDetalleBoletaById(int idBoleta);
	
	public List<DetalleBoletaCompraBean> listarDetalleCompraProductos (int idBoleta);
}
