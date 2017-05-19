package co.edu.udea.prestamos.ws;

//Importes necesarios para la clase
import java.rmi.RemoteException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.udea.prestamos.bl.AdministradorBL;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Servicios webs asociados al administrador del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 17/05/2017
 */
@Path("Administrador") // Esta anotacion sirve para definir la ruta o url con la que se va a responder el servicio
@Component // Esta otra anotacion indica que esta clase es un componente de Spring
public class AdministradorWS
{
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	AdministradorBL adminBL;
	
	/**
	 * RFN3 & RFN5 - Servicio web para cambiar el estado de cualquier peticion de prestamo
	 * @param idPrestamo Campo con el numero identificador del comprobante de prestamo
	 * @param nuevoEstado Campo con el codigo/numero del nuevo estado que se le quiere otorgar al prestamo
	 * @return Mensaje que indica si se realizo con exito o no la operacion
	 * @throws RemoteException Ocurre cuando se tuvo un problema ya sea obtiendo el prestamo, el estado o actualizando los datos en la BD
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("cambiarEstadoPrestamo") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_PLAIN) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String cambiarEstadoPrestamo(@QueryParam("idPrestamo")int idPrestamo, @QueryParam("nuevoEstado")int nuevoEstado) throws RemoteException
	{
		try
		{
			adminBL.cambiarEstadoPrestamo(idPrestamo, nuevoEstado); // Se invoca el metodo que realiza el cambio de estado para el prestamo
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces se retorna un mensaje de error al respecto
		{
			// throw new RemoteException("Error cambiando el estado" + e);
			return "Se presento un error durante el proceso. Intentelo de nuevo!";
		}
		
		// Si se pasa aqui es porque el cambio de estado se hizo sin problemas y por tanto se retorna un mensaje al respecto
		return "Se realizo el cambio de estado exitosamente!";
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/Administrador/cambiarEstadoPrestamo?idPrestamo=1&nuevoEstado=3
	
	/**
	 * RFN6 - Servicio web para registrar un nuevo elemento o dispositivo en el sistema
	 * @param nombre Campo con el nombre del nuevo dispositivo
	 * @param descripcion Campo con la descripcion del dispositivo. Aqui estaria el fabricante, marca y otros datos de interes del elemento
	 * @param idTipo Campo con el numero de identificacion del TipoDispositivo que se le quiere asociar al nuevo elemento
	 * @return Mensaje que indica si se realizo con exito o no la operacion
	 * @throws RemoteException Ocurre cuando hay un problema en la insercion de datos en la BD
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("registrarNuevoDispositivo") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_HTML) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String registrarNuevoDispositivo(@QueryParam("nombre")String nombre, @QueryParam("descripcion")String descripcion, @QueryParam("idTipo")int idTipo) throws RemoteException
	{
		try
		{
			adminBL.registrarNuevoDispositivo(nombre, descripcion, idTipo); // Se invoca el metodo que realiza la inserccion o creacion del nuevo dispositivo
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces se retorna un mensaje de error al respecto
		{
			// throw new RemoteException("Error cambiando el estado" + e);
			return "Se presento un error durante el proceso. Intentelo de nuevo!";
		}
		
		// Si se pasa aqui es porque la creacion se hizo sin problemas y por tanto se retorna un mensaje al respecto
		return "Se realizo la inserccion del nuevo dispositivo exitosamente!";
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/Administrador/registrarNuevoDispositivo?nombre=Bascula&descripcion=Tiger2 - Industrial 300KgMax&idTipo=3
	
	/**
	 * RFN6 - Servicio web para registrar los ejemplares asociados a un dispositivo y los cuales podran prestar los investigadores
	 * @param idDispositivo Campo con el numero de identificacion del dispositivo
	 * @param codigoEstadoDispositivo Campo con el numero de identificacion del estado para el nuevo ejemplar
	 * @return Mensaje que indica si se realizo con exito o no la operacion
	 * @throws RemoteException  Ocurre cuando hay un problema en la insercion de datos en la BD
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("registrarNuevoEjemplar") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_HTML) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String registrarNuevoEjemplarDispositivo(@QueryParam("idDispositivo")int idDispositivo, @QueryParam("codigoEstadoDispositivo")int codigoEstadoDispositivo) throws RemoteException
	{
		try
		{
			adminBL.registrarNuevoEjemplarDispositivo(idDispositivo, codigoEstadoDispositivo); // Se invoca el metodo que realiza la inserccion o creacion del nuevo ejemplar
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces se retorna un mensaje de error al respecto
		{
			// throw new RemoteException("Error cambiando el estado" + e);
			return "Se presento un error durante el proceso. Intentelo de nuevo!";
		}
		
		// Si se pasa aqui es porque la creacion se hizo sin problemas y por tanto se retorna un mensaje al respecto
		return "Se realizo la inserccion del nuevo ejemplar exitosamente!";
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/Administrador/registrarNuevoEjemplar?idDispositivo=7&codigoEstadoDispositivo=1
	
	/**
	 * RFN7 - Servicio web para remover cualquier dispositivo del sistema
	 * @param idEjemplarDispositivo Campo con el numero identificador del ejemplar que se quiere remover
	 * @return Mensaje que indica si se realizo con exito o no la operacion
	 * @throws RemoteException Ocurre cuando se presento un problema al cambiar el estado del ejemplar
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("removerEjemplar") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_HTML) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String removerEjemplarDispositivo(@QueryParam("idEjemplarDispositivo")int idEjemplarDispositivo) throws RemoteException
	{
		try
		{
			adminBL.removerEjemplarDispositivo(idEjemplarDispositivo); // Se invoca el metodo que realiza la eliminacion del ejemplar
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces se retorna un mensaje de error al respecto
		{
			// throw new RemoteException("Error cambiando el estado" + e);
			return "Se presento un error durante el proceso. Intentelo de nuevo!";
		}
		
		// Si se pasa aqui es porque la eliminacion se hizo sin problemas y por tanto se retorna un mensaje al respecto
		return "Se realizo la eliminacion del ejemplar exitosamente!";
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/Administrador/removerEjemplar?idEjemplarDispositivo=14
}
