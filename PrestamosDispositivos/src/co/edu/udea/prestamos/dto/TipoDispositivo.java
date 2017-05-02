package co.edu.udea.prestamos.dto;

/**
 * POJO para la tabla TipoDispositivo
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class TipoDispositivo
{
	// Atributos de la clase que se mapearan con cada una de las columnas de la tabla TipoDispositivo
	private int idTipoDisp;
	private String nombre;
	
	public int getIdTipoDisp() {
		return idTipoDisp;
	}
	
	public void setIdTipoDisp(int idTipoDisp) {
		this.idTipoDisp = idTipoDisp;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
