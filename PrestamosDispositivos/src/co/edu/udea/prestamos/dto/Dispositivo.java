package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuņozRoldan
 * @version 1.0
 */
public class Dispositivo {
	
	private int idDispositivo;
	private String nombre;
	private String descripcion;
	private TipoDispositivo tipo;
	
	public int getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
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
	public TipoDispositivo getTipo() {
		return tipo;
	}
	public void setTipo(TipoDispositivo tipo) {
		this.tipo = tipo;
	}
	
	
}
