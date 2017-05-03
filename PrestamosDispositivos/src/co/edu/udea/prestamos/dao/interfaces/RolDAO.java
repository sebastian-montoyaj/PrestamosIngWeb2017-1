package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.Rol;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;


/**
 * Interfaz que define los metodos permitidos para los roles del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface RolDAO
{
	/**
	 * Obtiene la lista de todos los roles que existen en el sistema
	 * @return Lista de Roles definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<Rol> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un rol especifico dado su codigo
	 * @param codigo Codigo del rol que se desea obtener
	 * @return Objeto {@link Rol} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public Rol obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo rol en la base de datos
	 * @param rol Objeto {@link Rol} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(Rol rol) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un rol ya existente en la base de datos
	 * @param rol Objeto {@link Rol} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(Rol rol) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un rol ya existente en la base de datos
	 * @param rol Objeto {@link Rol} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(Rol rol) throws ExcepcionPrestamos;
}
