package co.edu.udea.prestamos.dto;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonPrestamoSolicitudes {
	
	private int id;
	private String nombreUser;
	private String fechaSolicitud;
	private String fechaPrestamo;
	private String estado;
	
	public JsonPrestamoSolicitudes(){
		
	}
	
	public JsonPrestamoSolicitudes(int id, String nombreUser, String fechaSolicitud, String fechaPrestamo,
			String estado) {
		super();
		this.id = id;
		this.nombreUser = nombreUser;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaPrestamo = fechaPrestamo;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUser() {
		return nombreUser;
	}
	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
