package co.edu.udea.prestamos.dto;

/**
 * POJO para la tabla EstadoUsuario
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EstadoUsuario
{
	// Atributos de la clase que se mapearan con cada una de las columnas de la tabla EstadoUsuario
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
