package co.edu.udea.prestamos.dto;

/**
 * POJO para la tabla ItemsPrestamo
 * @author CesarMuñozRoldan
 * @version 1.0
 */
public class ItemsPrestamo
{
	// Atributos de la clase que se mapearan con cada una de las columnas de la tabla ItemsPrestamo
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
