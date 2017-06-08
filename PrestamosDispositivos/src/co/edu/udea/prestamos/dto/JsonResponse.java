package co.edu.udea.prestamos.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonResponse {
	private boolean estado;
	private String msj;
	
	public JsonResponse(){
		
	}

	
	public JsonResponse(boolean estado, String msj) {
		super();
		this.estado = estado;
		this.msj = msj;
	}


	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}
	


	
	
}
