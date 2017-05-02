package co.edu.udea.prestamos.dto;

/**
 * POJO para la tabla Rol
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class Rol
{
	// Atributos de la clase que se mapearan con cada una de las columnas de la tabla Rol
	private int codRol;
	private String nombre;
	
	public int getCodRol() {
		return codRol;
	}
	
	public void setCodRol(int codRol) {
		this.codRol = codRol;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
