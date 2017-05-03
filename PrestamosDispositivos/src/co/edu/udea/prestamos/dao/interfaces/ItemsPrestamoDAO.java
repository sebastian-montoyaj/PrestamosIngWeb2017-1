package co.edu.udea.prestamos.dao.interfaces;

//Importes necesarios para la clase
import java.util.List;
import co.edu.udea.prestamos.dto.ItemsPrestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Interfaz que define los metodos permitidos para los items de prestamos del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public interface ItemsPrestamoDAO
{
	/**
	 * Obtiene la lista de todos los items de prestamo que existen en el sistema
	 * @return Lista de items de prestamo definidos en el sistema
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public List<ItemsPrestamo> obtenerTodo() throws ExcepcionPrestamos;
	
	/**
	 * Obtiene la informacion de un item de prestamo especifico dado su codigo
	 * @param codigo Codigo del item de prestamo que se desea obtener
	 * @return Objeto {@link ItemsPrestamo} con la informacion que se recupero
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la conexion con la BD u otra situacion relacionada
	 */
	public ItemsPrestamo obtener(int codigo) throws ExcepcionPrestamos;
	
	/**
	 * Guarda la informacion de un nuevo item de prestamo en la base de datos
	 * @param item Objeto {@link ItemsPrestamo} que se desea almacenar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al insertar
	 */
	public void insertar(ItemsPrestamo item) throws ExcepcionPrestamos;
	
	/**
	 * Modifica la informacion de un item de prestamo ya existente en la base de datos
	 * @param item Objeto {@link ItemsPrestamo} que se desea actualizar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al actualizar
	 */
	public void actualizar(ItemsPrestamo item) throws ExcepcionPrestamos;
	
	/**
	 * Elimina la informacion de un item de prestamo ya existente en la base de datos
	 * @param item Objeto {@link ItemsPrestamo} que se desea eliminar en la base de datos
	 * @throws ExcepcionPrestamos Ocurre cuando se presenta un error al eliminar
	 */
	public void eliminar(ItemsPrestamo item) throws ExcepcionPrestamos;
}
