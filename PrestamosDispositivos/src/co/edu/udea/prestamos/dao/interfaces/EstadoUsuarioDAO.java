package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.EstadoUsuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los estados de usuario del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface EstadoUsuarioDAO
{
	/**
	 * Obtiene la lista de todos los estados de usuario que existen en el sistema
	 * @return Lista de los estados de usuario definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<EstadoUsuario> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un estado de usuario especifico dado su codigo
	 * @param codigo Codigo del estado de usuario que se desea recuperar
	 * @return Objeto {@link EstadoUsuario} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public EstadoUsuario obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo estado de usuario en la base de datos
	 * @param estadoU Objeto {@link EstadoUsuario} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(EstadoUsuario estadoU) throws ExcepcionPrestamos;

	/**
	 * Modifica la informacion de un estado de usuario ya existente en la base de datos
	 * @param estadoU Objeto {@link EstadoUsuario} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(EstadoUsuario estadoU) throws ExcepcionPrestamos;

	/**
	 * Elimina la informacion de un estado de usuario ya existente en la base de datos
	 * @param estadoU Objeto {@link EstadoUsuario} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(EstadoUsuario estadoU) throws ExcepcionPrestamos;
}
