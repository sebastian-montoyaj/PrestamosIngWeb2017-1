package co.edu.udea.prestamos.dto;

import java.util.Date;

/**
 * 
 * @author CesarMu�ozRoldan
 * @version 1.0
 */
public class Prestamo {
	
	private int idPrestamo;
	private Date fechaSolicitud;
	private Usuario usuarioSolicita;
	private Usuario usuarioAprueba;
	private Date fechaPrestamo;
	private Date fechaEntrega;
	private EstadoPrestamo estado;
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Usuario getUsuarioSolicita() {
		return usuarioSolicita;
	}
	public void setUsuarioSolicita(Usuario usuarioSolicita) {
		this.usuarioSolicita = usuarioSolicita;
	}
	public Usuario getUsuarioAprueba() {
		return usuarioAprueba;
	}
	public void setUsuarioAprueba(Usuario usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public EstadoPrestamo getEstado() {
		return estado;
	}
	public void setEstado(EstadoPrestamo estado) {
		this.estado = estado;
	}
			
}
