package co.edu.udea.prestamos.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonDispositivos {
	
	private String nombre;
	private int id;
	
	public JsonDispositivos(){

	}
	
	public JsonDispositivos(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
