package rios.demo.bean;

import java.io.Serializable;

public class EmpleadoBean implements Serializable {
	
	private long id;
	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private String fecha_nacimiento;
	private String direccion;
	private String telefono;
	private long codigo_distrito;
	private String correo;
	private String fecha_ingreso;
	private int codigo_supervisor;
	private int sub_acargo;
	private String ususario;
	private String clave;
	
	// extra
	private String distrito;
	
	
	public EmpleadoBean() {
		
	}
	
	public EmpleadoBean(long id, String nombre, String apellido_materno, String apellido_paterno,
			String fecha_nacimiento, String direccion, String telefono, long codigo_distrito, String correo,
			String fecha_ingreso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
		this.fecha_ingreso = fecha_ingreso;
	}

	public EmpleadoBean(String nombre, String apellido_materno, String apellido_paterno, String fecha_nacimiento,
			String direccion, String telefono, long codigo_distrito, String correo, String fecha_ingreso) {
		super();
		this.nombre = nombre;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
		this.fecha_ingreso = fecha_ingreso;
	}

	public EmpleadoBean(String nombre, String apellido_materno, String apellido_paterno, String fecha_nacimiento,
			String direccion, String telefono, long codigo_distrito, String correo, String fecha_ingreso,
			int codigo_supervisor, int sub_acargo, String ususario, String clave) {
		super();
		this.nombre = nombre;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
		this.fecha_ingreso = fecha_ingreso;
		this.codigo_supervisor = codigo_supervisor;
		this.sub_acargo = sub_acargo;
		this.ususario = ususario;
		this.clave = clave;
	}


	public EmpleadoBean(long id, String nombre, String apellido_materno, String apellido_paterno,
			String fecha_nacimiento, String direccion, String telefono, long codigo_distrito, String correo,
			String fecha_ingreso, int codigo_supervisor, int sub_acargo, String ususario, String clave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo_distrito = codigo_distrito;
		this.correo = correo;
		this.fecha_ingreso = fecha_ingreso;
		this.codigo_supervisor = codigo_supervisor;
		this.sub_acargo = sub_acargo;
		this.ususario = ususario;
		this.clave = clave;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido_materno() {
		return apellido_materno;
	}


	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}


	public String getApellido_paterno() {
		return apellido_paterno;
	}


	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public long getCodigo_distrito() {
		return codigo_distrito;
	}


	public void setCodigo_distrito(long codigo_distrito) {
		this.codigo_distrito = codigo_distrito;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getFecha_ingreso() {
		return fecha_ingreso;
	}


	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}


	public int getCodigo_supervisor() {
		return codigo_supervisor;
	}


	public void setCodigo_supervisor(int codigo_supervisor) {
		this.codigo_supervisor = codigo_supervisor;
	}


	public int getSub_acargo() {
		return sub_acargo;
	}


	public void setSub_acargo(int sub_acargo) {
		this.sub_acargo = sub_acargo;
	}


	public String getUsusario() {
		return ususario;
	}


	public void setUsusario(String ususario) {
		this.ususario = ususario;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getDistrito() {
		return distrito;
	}


	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
}
	
	

