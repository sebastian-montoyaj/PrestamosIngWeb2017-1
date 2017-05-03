package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.EstadoPrestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los estados de los prestamos del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface EstadoPrestamoDAO
{
	/**
	 * Obtiene la lista de todos los estados de prestamo que existen en el sistema
	 * @return Lista de estados de prestamo definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<EstadoPrestamo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un estado de prestamo especifico dado su codigo
	 * @param codigo Codigo del estado de prestamo que se desea obtener
	 * @return Objeto {@link EstadoPrestamo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public EstadoPrestamo obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo estado de prestamo en la base de datos
	 * @param estadoP Objeto {@link EstadoPrestamo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(EstadoPrestamo estadoP) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un estado de prestamo ya existente en la base de datos
	 * @param estadoP Objeto {@link EstadoPrestamo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(EstadoPrestamo estadoP) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un estado de prestamo ya existente en la base de datos
	 * @param estadoP Objeto {@link EstadoPrestamo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(EstadoPrestamo estadoP) throws ExcepcionPrestamos;
}
