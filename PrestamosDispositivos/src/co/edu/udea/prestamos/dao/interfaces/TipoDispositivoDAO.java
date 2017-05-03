package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.TipoDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los tipos de dispositivos que maneja el sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface TipoDispositivoDAO
{
	/**
	 * Obtiene la lista de todos los tipos de dispositivo que existen en el sistema
	 * @return Lista de los tipos de dispositivo definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<TipoDispositivo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un tipo de dispositivo especifico dado su codigo
	 * @param idTipo Codigo/Identificador del tipo de dispositivo que se desea obtener
	 * @return Objeto {@link TipoDispositivo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public TipoDispositivo obtener(int idTipo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo tipo de dispositivo en la base de datos
	 * @param tipoDispositivo Objeto {@link TipoDispositivo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un tipo de dispositivo ya existente en la base de datos
	 * @param tipoDispositivo Objeto {@link TipoDispositivo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un tipo de dispositivo ya existente en la base de datos
	 * @param tipoDispositivo Objeto {@link TipoDispositivo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos;
}
