package co.edu.udea.prestamos.ws;

//Importes necesarios para la clase
import java.rmi.RemoteException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.udea.prestamos.bl.UsuarioBL;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Servicios webs asociados al administrador del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 17/05/2017
 */
@Path("Usuario") // Esta anotacion sirve para definir la ruta o url con la que se va a responder el servicio
@Component // Esta otra anotacion indica que esta clase es un componente de Spring
public class UsuarioWS
{
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	UsuarioBL userBL;
	
	/**
	 * RFN1 - Servicio web para validar el usuario que esta ingresando su identificador y contraseña.
	 * @param identificador Campo con el identificador del usuario
	 * @param password Campo con la contraseña del usuario
	 * @return String con la palabra del valor logico que confirma o deniega si el usuario hace parte del sistema
	 * @throws RemoteException Ocurre cuando uno o los dos parametros estan vacios
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("validarUsuario") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_PLAIN) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String esUsuarioValido(@QueryParam("identificador")String identificador, @QueryParam("password")String password) throws RemoteException
	{
		Boolean resultado = false; // Creo una variable para recibir el resultado de la consulta para validar el usuario
		
		try
		{
			resultado = userBL.esUsuarioValido(identificador, password); // Luego, invoco el metodo para validar
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces el usuario no es valido y se lanza una excepcion
		{
			throw new RemoteException("Se tuvo un problema con la validacion." + e);
		}
		
		return resultado.toString(); // Y por ultimo, retorno el resultado de la consulta
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/ingweb/Usuario/validarUsuario?identificador=1&password=1234567
	
	/**
	 * RFN8 - Servicio web para cambiar el estado (inhabilitar/habilitar) del usuario
	 * @param identificador Campo con el identificador del usuario
	 * @param nuevoEstado Campo con el codigo/numero del nuevo estado para el usuario
	 * @return Mensaje que indica si se realizo con exito o no la operacion
	 * @throws RemoteException Ocurre cuando se tuvo un problema ya sea obtiendo el usuario, el estado o modificando este en la BD
	 */
	@POST // Esta anotacion indica que va a responder al metodo POST de http
	@Path("cambiarEstadoUsuario") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_PLAIN) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String cambiarEstadoUsuario(@QueryParam("identificador")String identificador, @QueryParam("nuevoEstado")int nuevoEstado) throws RemoteException
	{
		try
		{
			userBL.cambiarEstadoUsuario(identificador, nuevoEstado); // En primer lugar, se invoca el metodo que realiza el cambio de estado para el usuario
		}
		catch (ExcepcionPrestamos e) // En caso de error entoces se retorna un mensaje de error al respecto
		{
			return "Se presento un error durante el proceso. Intentelo de nuevo!";
		}
		
		return "Se realizo el cambio de estado exitosamente!"; // Si se pasa del bloque anterior es porque el cambio se hizo sin problemas y se retorna un mensaje del exito
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/ingweb/Usuario/cambiarEstadoUsuario?identificador=1&nuevoEstado=2
	
	@GET // Esta anotacion indica que va a responder al metodo GET de http
	@Path("obtenerRolUsuario") // Esta anotacion es para indicar cual url sera la que invoque este servicio
	@Produces(MediaType.TEXT_PLAIN) // Esta anotacion es para indicar que tipo de resultado o retorno se va a devolver al navegador
	// @QueryParam : Y esta anotacion es para saber que parametros se van a extraer del la url y que seran usados para esta clase
	public String obtenerRolUsuario(@QueryParam("identificador")String identificador) throws RemoteException
	{
		try
		{
			return userBL.obtenerRolUsuario(identificador); // Tan solo se invoca y se retorna el resultado del metodo que consulta el rol del usuario
		}
		catch (ExcepcionPrestamos e) // En caso de error entonces se lanza una excepcion
		{
			throw new RemoteException("Se tuvo un problema con la consulta del rol" + e);
		}
		
	}
	
	// Ejemplo de URL para la prueba:
	// http://localhost:8080/Prestamos/ingweb/Usuario/obtenerRolUsuario?identificador=1
}
