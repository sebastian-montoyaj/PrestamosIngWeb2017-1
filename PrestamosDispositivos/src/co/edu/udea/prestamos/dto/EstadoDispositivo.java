package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EstadoDispositivo {
	
	private int codEstadoD;
	private String nombre;
	private String descripcion;
	
	public int getCodEstadoD() {
		return codEstadoD;
	}
	public void setCodEstadoD(int codEstadoD) {
		this.codEstadoD = codEstadoD;
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
