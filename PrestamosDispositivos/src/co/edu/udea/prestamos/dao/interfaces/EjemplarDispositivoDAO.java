package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los ejemplares de elementos del laboratorio del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface EjemplarDispositivoDAO
{
	/**
	 * Obtiene la lista de todos los ejemplares de dispositivos que existen en el sistema
	 * @return Lista de los ejemplares de dispositivos registrados en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<EjemplarDispositivo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un ejemplar de dispositivo especifico dado su codigo
	 * @param idEjemplar Numero identificador del ejemplar de dispositivo que se desea obtener
	 * @return Objeto {@link EjemplarDispositivo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public EjemplarDispositivo obtener(int idEjemplar) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo ejemplar de dispositivo en la base de datos
	 * @param ejemplar Objeto {@link EjemplarDispositivo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un ejemplar de dispositivo ya existente en la base de datos
	 * @param ejemplar Objeto {@link EjemplarDispositivo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un ejemplar de dispositivo ya existente en la base de datos
	 * @param ejemplar Objeto {@link EjemplarDispositivo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos;
}
