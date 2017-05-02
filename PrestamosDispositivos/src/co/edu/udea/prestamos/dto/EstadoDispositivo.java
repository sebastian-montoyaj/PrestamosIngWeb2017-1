package co.edu.udea.prestamos.dto;

/**
 * POJO para la tabla EstadoDispositivo
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EstadoDispositivo
{
	// Atributos de la clase que se mapearan con cada una de las columnas de la tabla EstadoDispositivo
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
