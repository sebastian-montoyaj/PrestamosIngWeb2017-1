package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los dispositivos sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface DispositivoDAO
{
	/**
	 * Obtiene la lista de todos los dispositivos que existen en el sistema
	 * @return Lista de los dispositivos registrados en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<Dispositivo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un dispositivo especifico dado su numero de referencia (o codigo)
	 * @param idDispositivo Numero identificador del dispositivo que se desea obtener
	 * @return Objeto {@link Dispositivo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public Dispositivo obtener(int idDispositivo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo dispositivo en la base de datos
	 * @param dispositivo Objeto {@link Dispositivo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(Dispositivo dispositivo) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un dispositivo ya existente en la base de datos
	 * @param dispositivo Objeto {@link Dispositivo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(Dispositivo dispositivo) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un dispositivo ya existente en la base de datos
	 * @param dispositivo Objeto {@link Dispositivo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(Dispositivo dispositivo) throws ExcepcionPrestamos;
}
