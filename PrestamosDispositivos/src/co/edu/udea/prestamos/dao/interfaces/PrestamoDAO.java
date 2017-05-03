package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los prestamos del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface PrestamoDAO
{
	/**
	 * Obtiene la lista de todos los prestamos que existen en el sistema
	 * @return Lista de prestamo realizados en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<Prestamo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un prestamo especifico dado su numero/codigo
	 * @param id Numero/Codigo del prestamo que se desea obtener
	 * @return Objeto {@link Prestamo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public Prestamo obtener(int id) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo prestamo en la base de datos
	 * @param prestamo Objeto {@link Prestamo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(Prestamo prestamo) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un prestamo ya existente en la base de datos
	 * @param prestamo Objeto {@link Prestamo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(Prestamo prestamo) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un prestamo ya existente en la base de datos
	 * @param prestamo Objeto {@link Prestamo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(Prestamo prestamo) throws ExcepcionPrestamos;
}
