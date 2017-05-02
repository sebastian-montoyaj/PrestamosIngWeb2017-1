package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EstadoUsuario {
	
	private int codEstadoU;
	private String nombre;
	private String descripcion ;
	
	public int getCodEstadoU() {
		return codEstadoU;
	}
	public void setCodEstadoU(int codEstadoU) {
		this.codEstadoU = codEstadoU;
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
