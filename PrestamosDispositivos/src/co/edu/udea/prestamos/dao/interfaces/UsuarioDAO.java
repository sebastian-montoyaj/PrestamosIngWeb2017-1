package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.Usuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los usuarios del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface UsuarioDAO
{
	/**
	 * Obtiene la lista de todos los usuarios que existen en el sistema
	 * @return Lista de usuarios registrados en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<Usuario> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un usuario especifico dado su numero de identificacion
	 * @param id Numero de identificacion del usuario que se desea obtener
	 * @return Objeto {@link Usuario} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public Usuario obtener(int id) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo usuario en la base de datos
	 * @param usuario Objeto {@link Usuario} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(Usuario usuario) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un usuario ya existente en la base de datos
	 * @param usuario Objeto {@link Usuario} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(Usuario usuario) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un usuario ya existente en la base de datos
	 * @param usuario Objeto {@link Usuario} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(Usuario usuario) throws ExcepcionPrestamos;
}
