package co.edu.udea.prestamos.dto;

/**
 * 
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class ItemsPrestamo {
	
	private int idItem;
	private Prestamo prestamo;
	private EjemplarDispositivo ejemplar;
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public EjemplarDispositivo getEjemplar() {
		return ejemplar;
	}
	public void setEjemplar(EjemplarDispositivo ejemplar) {
		this.ejemplar = ejemplar;
	}
		
}
