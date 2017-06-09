package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.prestamos.bl.PrestamosBL;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.dto.JsonDispositivos;
import co.edu.udea.prestamos.dto.JsonEjemplares;
import co.edu.udea.prestamos.dto.JsonPrestamoSolicitudes;
import co.edu.udea.prestamos.dto.JsonResponse;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Servicios webs asociados a las tareas de prestamo
 * @author César Muñoz Roldan
 * 1.0 - 17/05/2017
 */
@Path("Prestamo")
@Component
public class PrestamosWS
{
	@Autowired
	PrestamosBL prestamoBL;
	
	/**
	 * RFN2 - Servicio web para realizar la solicitud de prestamo
	 * @param idUser Campo con la identificacion del usuario que desea hacer la solicitud
	 * @param strListaEjemplares Campo con el arreglo de items/ejemplares que el usuario desea prestar
	 * @param fechaPrestamo fecha en la se desea realizar el prestamo
	 * @return String en formato JSON con un estado y msj correspondiente para devolver al cliente que se consume el servicio
	 * @throws RemoteException Ocurre cuando el usuario no es valido o se presento un error al insertar
	 */
	@POST
	@Path("solicitud")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse solicitud(@QueryParam("idUser")String idUser,@QueryParam("listaEjemplares")String strListaEjemplares,@QueryParam("fechaPrestamo")String fechaPrestamo) throws RemoteException
	{
		JsonResponse jsonResponse=null;
		try{
			int auxIdUser=Integer.parseInt(idUser);
			List<Integer>listIdEjemplares=new ArrayList<Integer>();
			String aux[]=strListaEjemplares.split("-");
			for(int i=0;i<aux.length;i++){
				listIdEjemplares.add(Integer.parseInt(aux[i]));
			}
			if(fechaPrestamo==null)throw new Exception("Parametros no validos");
			if(fechaPrestamo.length()!=10)throw new Exception("Parametros no validos");
			DateFormat formatter ; 
			Date auxFechaPrestamo ; 
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			auxFechaPrestamo = formatter.parse(fechaPrestamo);
			
			boolean result=prestamoBL.solicitud(auxIdUser, listIdEjemplares,auxFechaPrestamo);
			
			if(result)
				jsonResponse=new JsonResponse(true,"Solicitud realizada correctamente");
			else
				jsonResponse=new JsonResponse(false,"No fue posible realizar la solicitud");
			
		}
		catch(NumberFormatException e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch (ExcepcionPrestamos e) {
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch(Exception e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * RFN9 - Servicio web para comprobar que se hace uso del prestamo
	 * @param idPrestamo Identificador del prestamo al cual se le cambiara el estado a "PRESTADO"
	 * @return String en formato JSON con un estado y msj correspondiente para devolver al cliente que se consume el servicio
	 * @throws RemoteException Ocurre cuando se tuvo un problema en la transaccion contra la BD
	 */
	@POST
	@Path("comprobarEntrega")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse comprobarEntrega(@QueryParam("idPrestamo")String idPrestamo) throws RemoteException
	{
		JsonResponse jsonResponse=null;
		try{						
			int auxIdPrestamo=Integer.parseInt(idPrestamo);
			boolean result=prestamoBL.comprobarEntrega(auxIdPrestamo);
			if(result)
				jsonResponse=new JsonResponse(true,"Comprobación exitosa");
			else
				jsonResponse=new JsonResponse(false,"No fue posible comprobar la entrega de los ejemplares");
			
		}
		catch (ExcepcionPrestamos e) {
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch(NumberFormatException e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch(Exception e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * RFN10 - Servicio web para comprobar devolución del dispositivos
	 * @param idPrestamo Identificador del prestamo al cual se le cambiara el estado de los ejemplares a "DISPONIBLES" y el estado del prestamo a "CADUCADO" 
	 * @return String en formato JSON con un estado y msj correspondiente para devolver al cliente que se consume el servicio
	 * @throws RemoteException Ocurre cuando se tuvo un problema en la transaccion contra la BD
	 */
	@POST
	@Path("comprobarDevolucion")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse comprobarDevolucion(@QueryParam("idPrestamo")String idPrestamo) throws RemoteException
	{
		JsonResponse jsonResponse=null;
		try{						
			int auxIdPrestamo=Integer.parseInt(idPrestamo);
			boolean result=prestamoBL.comprobarDevolucion(auxIdPrestamo);			
			if(result)
				jsonResponse=new JsonResponse(true,"Comprobación de devolucion exitosa");
			else
				jsonResponse=new JsonResponse(false,"No fue posible comprobar la devolucion del prestamo");
			
		}		
		catch (ExcepcionPrestamos e) {
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch(NumberFormatException e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		catch(Exception e){
			jsonResponse=new JsonResponse(false,e.getMessage());
		}
		return jsonResponse;
	}
	
	@GET
	@Path("ejemplares")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonEjemplares> ejemplares() throws RemoteException	
	{				
		List<JsonEjemplares> ejemplares = new ArrayList<JsonEjemplares>();
		
		try{		
									
			for(EjemplarDispositivo ejemplarDispositivo:prestamoBL.ejemplares()){
				
				JsonEjemplares jsonEjemplares=
						new JsonEjemplares(
								ejemplarDispositivo.getIdEjemplar(),
								ejemplarDispositivo.getDispositivo().getNombre(),
								ejemplarDispositivo.getEstado().getNombre());
				 						
				ejemplares.add(jsonEjemplares);
 			}			
		}						
		catch (ExcepcionPrestamos e) {
			throw new RemoteException(e.getMessage());
		}
		catch(Exception e){
			throw new RemoteException("No fue posibles obtener la lista de dispositivos");
		}
		return ejemplares;		
	}
	
	@GET
	@Path("prestamosSolicitud")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonPrestamoSolicitudes> prestamosSolicitud() throws RemoteException	
	{				
		List<JsonPrestamoSolicitudes> listPrestamos = new ArrayList<JsonPrestamoSolicitudes>();		
		try{
			for(Prestamo objPrestamo:prestamoBL.prestamosSolicitudes()){
				
				JsonPrestamoSolicitudes jsonPrestamoSolicitudes=
						new JsonPrestamoSolicitudes(
								objPrestamo.getIdPrestamo(),
								objPrestamo.getUsuarioSolicita().getNombre(),
								objPrestamo.getFechaSolicitud().toString(),
								objPrestamo.getFechaPrestamo().toString(),
								objPrestamo.getEstado().getNombre());
				 						
				listPrestamos.add(jsonPrestamoSolicitudes);
 			}
		}					
		catch (ExcepcionPrestamos e) {
			throw new RemoteException(e.getMessage());
		}
		catch(Exception e){
			throw new RemoteException("No fue posibles obtener la lista de prestamos");
		}
		return listPrestamos;		
	}
	
	@GET
	@Path("dispositivos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonDispositivos> dispositivos() throws RemoteException	
	{				
		List<JsonDispositivos> listJsonDispositivos = new ArrayList<JsonDispositivos>();
		
		try{		
									
			for(Dispositivo dispositivo:prestamoBL.dispositivos()){
				
				JsonDispositivos jsonDispositivos=
						new JsonDispositivos(
								dispositivo.getNombre(),
								dispositivo.getIdDispositivo());
				 						
				listJsonDispositivos.add(jsonDispositivos);
 			}			
		}						
		catch (ExcepcionPrestamos e) {
			throw new RemoteException(e.getMessage());
		}
		catch(Exception e){
			throw new RemoteException("No fue posibles obtener la lista de dispositivos");
		}
		return listJsonDispositivos;		
	}
	
}

