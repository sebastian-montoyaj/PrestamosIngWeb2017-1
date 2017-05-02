package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class Usuario {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String clave;
	private Rol rol;
	private EstadoUsuario estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public EstadoUsuario getEstado() {
		return estado;
	}
	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}
	
	
}
