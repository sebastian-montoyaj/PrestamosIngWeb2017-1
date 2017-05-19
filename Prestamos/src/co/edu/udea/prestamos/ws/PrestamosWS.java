package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
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
	 * @return String en formato JSON con un estado y msj correspondiente para devolver al cliente que se consume el servicio
	 * @throws RemoteException Ocurre cuando el usuario no es valido o se presento un error al insertar
	 */
	@POST
	@Path("solicitud")
	@Produces(MediaType.APPLICATION_JSON)
	public String solicitud(@QueryParam("idUser")String idUser,@QueryParam("listaEjemplares")String strListaEjemplares) throws RemoteException
	{
		try{
			int auxIdUser=Integer.parseInt(idUser);
			List<Integer>listIdEjemplares=new ArrayList<Integer>();
			String aux[]=strListaEjemplares.split("-");
			for(int i=0;i<aux.length;i++){
				listIdEjemplares.add(Integer.parseInt(aux[i]));
			}
			
			boolean result=prestamoBL.solicitud(auxIdUser, listIdEjemplares,new Date());
			
			if(result)
				return "{estado:true,msj:'Solicitud realizada correctamente'}";
			else
				return "{estado:false,msj:'No fue posible realizar la solicitud'}";
			
		}
		catch(NumberFormatException e){
			return "{estado:false,msj:'Error validando parametros'}";
		}
		catch (ExcepcionPrestamos e) {
			return "{estado:false,msj:'"+e.getMessage()+"'}";
		}
		catch(Exception e){
			return "{estado:false,msj:'"+e.getMessage()+"'}";
		}
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
	public String comprobarEntrega(@QueryParam("idPrestamo")String idPrestamo) throws RemoteException
	{
		try{						
			int auxIdPrestamo=Integer.parseInt(idPrestamo);
			boolean result=prestamoBL.comprobarEntrega(auxIdPrestamo);
			if(result)
				return "{estado:true,msj:'Comprobación exitosa'}";
			else
				return "{estado:false,msj:'No fue posible comprobar la entrega de los ejemplares'}";
			
		}
		catch (ExcepcionPrestamos e) {
			//throw new RemoteException("Error comprobando la entrega de los ejemplares",e);
			return "{estado:false,msj:'"+e.getMessage()+"'}";
		}
		catch(NumberFormatException e){
			return "{estado:false,msj:'Error validando parametros'}";
		}
		catch(Exception e){
			return "{estado:false,msj:'"+e.getMessage()+"'}";
			//throw new Exception("Error validando parametros",e);
		}		
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
	public String comprobarDevolucion(@QueryParam("idPrestamo")String idPrestamo) throws RemoteException
	{
		try{						
			int auxIdPrestamo=Integer.parseInt(idPrestamo);
			boolean result=prestamoBL.comprobarDevolucion(auxIdPrestamo);
			if(result)
				return "{estado:true,msj:'Comprobación de devolucion exitosa'}";
			else
				return "{estado:false,msj:'No fue posible comprobar la devolucion del prestamo'}";
			
		}
		catch (ExcepcionPrestamos e) {
			return "{estado:false,msj:'"+e.getMessage()+"'}";
		}
		catch(NumberFormatException e){
			return "{estado:false,msj:'Error validando parametros'}";
		}	
		catch(Exception e){
			return "{estado:false,msj:'"+e.getMessage()+"'}";
		}		
	}
	

}
