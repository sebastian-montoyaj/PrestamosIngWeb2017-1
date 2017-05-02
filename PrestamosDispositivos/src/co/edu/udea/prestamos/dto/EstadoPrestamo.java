package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EstadoPrestamo {
	private int codEstadoP;
	private String nombre;
	private String descripcion;
	
	public int getCodEstadoP() {
		return codEstadoP;
	}
	public void setCodEstadoP(int codEstadoP) {
		this.codEstadoP = codEstadoP;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
