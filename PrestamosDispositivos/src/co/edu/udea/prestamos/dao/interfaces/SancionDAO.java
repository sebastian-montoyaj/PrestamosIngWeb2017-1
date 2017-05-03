package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.Sancion;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para las sanciones del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface SancionDAO
{
	/**
	 * Obtiene la lista de todos las sanciones que existen en el sistema
	 * @return Lista de sanciones realizadas a los usuarios del sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<Sancion> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de una sancion especifica dado su codigo
	 * @param codigo Codigo de la sancion que se desea obtener
	 * @return Objeto {@link Sancion} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public Sancion obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de una nuevo sancion en la base de datos
	 * @param sancion Objeto {@link Sancion} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(Sancion sancion) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de una sancion ya existente en la base de datos
	 * @param sancion Objeto {@link Sancion} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(Sancion sancion) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de una sancion ya existente en la base de datos
	 * @param sancion Objeto {@link Sancion} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(Sancion sancion) throws ExcepcionPrestamos;
}
