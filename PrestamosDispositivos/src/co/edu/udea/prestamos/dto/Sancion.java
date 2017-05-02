package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class Sancion {
	
	private int idSancion;
	private String tipoSancion;
	private Prestamo prestamo;
	private String descripcion;
	private Usuario usuarioSanciona;
	
	public int getIdSancion() {
		return idSancion;
	}
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}
	public String getTipoSancion() {
		return tipoSancion;
	}
	public void setTipoSancion(String tipoSancion) {
		this.tipoSancion = tipoSancion;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Usuario getUsuarioSanciona() {
		return usuarioSanciona;
	}
	public void setUsuarioSanciona(Usuario usuarioSanciona) {
		this.usuarioSanciona = usuarioSanciona;
	}
	
	
}
