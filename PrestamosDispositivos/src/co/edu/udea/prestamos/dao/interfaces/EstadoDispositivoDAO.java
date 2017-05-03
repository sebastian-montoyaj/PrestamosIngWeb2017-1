package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.EstadoDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los estados de dispositivos del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface EstadoDispositivoDAO
{
	/**
	 * Obtiene la lista de todos los estados de dispositivo que existen en el sistema
	 * @return Lista de estados de dispositivo definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<EstadoDispositivo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un estado de dispositivo especifico dado su codigo
	 * @param codigo Codigo del estado de dispositivo que se desea obtener
	 * @return Objeto {@link EstadoDispositivo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public EstadoDispositivo obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo estado de dispositivo en la base de datos
	 * @param estadoD Objeto {@link EstadoDispositivo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(EstadoDispositivo estadoD) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un estado de dispositivo ya existente en la base de datos
	 * @param estadoD Objeto {@link EstadoDispositivo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(EstadoDispositivo estadoD) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un estado de dispositivo ya existente en la base de datos
	 * @param estadoD Objeto {@link EstadoDispositivo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(EstadoDispositivo estadoD) throws ExcepcionPrestamos;
}
