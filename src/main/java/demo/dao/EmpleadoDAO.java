package demo.dao;

import java.util.List;

import demo.bean.EmpleadoBean;

public interface EmpleadoDAO {
	
	
	public int insertarEmpleado(EmpleadoBean empleado);
	
	
	public EmpleadoBean validarUsuarioClave(String usuario, String clave);
	
	 public List<EmpleadoBean> listaEmpleado();
	 public int eliminarEmpleado (Long id_empleado);
	 public EmpleadoBean obtenerEmpleadoById(long idEmpleado);
	 public int actualizarEmpleado (EmpleadoBean empleado);

}
