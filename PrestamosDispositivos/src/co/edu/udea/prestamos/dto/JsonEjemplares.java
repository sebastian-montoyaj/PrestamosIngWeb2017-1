package co.edu.udea.prestamos.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonEjemplares {

	private int id;
	private String nombre;	
	private String estado;
	
	public JsonEjemplares(){
		
	}
	
	public JsonEjemplares(int id, String nombre, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
