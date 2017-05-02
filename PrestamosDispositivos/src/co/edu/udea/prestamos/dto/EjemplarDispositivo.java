package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class EjemplarDispositivo {
	
	private int idEjemplar;
	private Dispositivo dispositivo;
	private EstadoDispositivo estado;
	
	public int getIdEjemplar() {
		return idEjemplar;
	}
	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	public EstadoDispositivo getEstado() {
		return estado;
	}
	public void setEstado(EstadoDispositivo estado) {
		this.estado = estado;
	}
	

}
