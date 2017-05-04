package co.edu.udea.prestamos.bl;

//Importes necesarios para la clase
import org.springframework.transaction.annotation.Transactional;
import co.edu.udea.prestamos.dao.interfaces.DispositivoDAO;
import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dao.interfaces.EstadoDispositivoDAO;
import co.edu.udea.prestamos.dao.interfaces.EstadoPrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.PrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.TipoDispositivoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.dto.EstadoDispositivo;
import co.edu.udea.prestamos.dto.EstadoPrestamo;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.dto.TipoDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Logica del negocio para las funciones asociadas al administrador del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 03/05/2017
 */
@Transactional // Esto le indica a la clase que las operaciones que se van a hacer son transaccionales
public class AdministradorBL
{
	// Variables para enlazar/inyectar la informacion de los objetos necesarios que requiere el administrador a la base de datos
	private PrestamoDAO prestamoDAO;
	private EstadoPrestamoDAO estadoPrestamoDAO;
	private DispositivoDAO dispositivoDAO;
	private TipoDispositivoDAO tipoDispositivoDAO;
	private EstadoDispositivoDAO estadoDispositivoDAO;
	private EjemplarDispositivoDAO ejemplarDispositivoDAO;
	
	// Metodos getter/setter para las variables que requiere esta clase
	public PrestamoDAO getPrestamoDAO() {
		return prestamoDAO;
	}

	public void setPrestamoDAO(PrestamoDAO prestamoDAO) {
		this.prestamoDAO = prestamoDAO;
	}

	public EstadoPrestamoDAO getEstadoPrestamoDAO() {
		return estadoPrestamoDAO;
	}

	public void setEstadoPrestamoDAO(EstadoPrestamoDAO estadoPrestamoDAO) {
		this.estadoPrestamoDAO = estadoPrestamoDAO;
	}

	public DispositivoDAO getDispositivoDAO() {
		return dispositivoDAO;
	}

	public void setDispositivoDAO(DispositivoDAO dispositivoDAO) {
		this.dispositivoDAO = dispositivoDAO;
	}

	public TipoDispositivoDAO getTipoDispositivoDAO() {
		return tipoDispositivoDAO;
	}

	public void setTipoDispositivoDAO(TipoDispositivoDAO tipoDispositivoDAO) {
		this.tipoDispositivoDAO = tipoDispositivoDAO;
	}

	public EstadoDispositivoDAO getEstadoDispositivoDAO() {
		return estadoDispositivoDAO;
	}

	public void setEstadoDispositivoDAO(EstadoDispositivoDAO estadoDispositivoDAO) {
		this.estadoDispositivoDAO = estadoDispositivoDAO;
	}

	public EjemplarDispositivoDAO getEjemplarDispositivoDAO() {
		return ejemplarDispositivoDAO;
	}

	public void setEjemplarDispositivoDAO(EjemplarDispositivoDAO ejemplarDispositivoDAO) {
		this.ejemplarDispositivoDAO = ejemplarDispositivoDAO;
	}
	
	/**
	 * RFN3 & RFN5 - Metodo para cambiar el estado de cualquier peticion de prestamo
	 * @param idPrestamo Campo con el numero identificador del comprobante de prestamo
	 * @param nuevoEstado Campo con el codigo/numero del nuevo estado que se le quiere otorgar al prestamo
	 * @throws ExcepcionPrestamos Ocurre cuando se tuvo un problema ya sea obtiendo el prestamo, el estado o actualizando los datos en la BD
	 */
	public void cambiarEstadoPrestamo(int idPrestamo, int nuevoEstado) throws ExcepcionPrestamos
	{
		try
		{
			// En primer lugar, recuperamos la informacion del prestamo que se quiere modificar su estado
			Prestamo prestamo = prestamoDAO.obtener(idPrestamo);
			
			// Luego, obtenemos la informacion del nuevo estado que se quiere asociar al prestamo
			EstadoPrestamo estado = estadoPrestamoDAO.obtener(nuevoEstado);
			
			// Y por ultimo se realiza el cambio de estado al objeto prestamo en la base de datos
			prestamo.setEstado(estado);
			prestamoDAO.actualizar(prestamo);
		}
		catch(Exception e) // En caso de error recuperamos la excepcion y la guardamos en el log
		{
			throw new ExcepcionPrestamos("Error cambiando el estado del prestamo!", e);
		}
	}
	
	/**
	 * RFN6 - Metodo para registrar un nuevo elemento o dispositivo en el sistema
	 * @param nombre Campo con el nombre del nuevo dispositivo
	 * @param descripcion Campo con la descripcion del dispositivo. Aqui estaria el fabricante, marca y otros datos de interes del elemento
	 * @param idTipo Campo con el numero de identificacion del TipoDispositivo que se le quiere asociar al nuevo elemento
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la insercion de datos en la BD
	 */
	public void registrarNuevoDispositivo(String nombre, String descripcion, int idTipo) throws ExcepcionPrestamos
	{
		// Los siguientes condiciones son para comprobar que los campos no esten vacios
		if (nombre == null || "".equals(nombre))
		{
			throw new ExcepcionPrestamos("El nombre del dispositivo no puede estar vacio!");
		}
		
		if (descripcion == null || "".equals(descripcion))
		{
			throw new ExcepcionPrestamos("La descripcion del dispositivo no puede estar vacia!");
		}
		
		if (idTipo <= 0)
		{
			throw new ExcepcionPrestamos("El identificador del tipo de dispositivo tiene que ser un numero entero!");
		}
		
		// Luego, se recupera el tipo especifico de dispositivo bajo el cual se registrara este nuevo elemento
		TipoDispositivo tipo = tipoDispositivoDAO.obtener(idTipo);
		
		// Ahora, se procede a crear un nuevo dispositivo y se setea su informacion
		Dispositivo dispositivo = new Dispositivo();
		dispositivo.setNombre(nombre);
		dispositivo.setDescripcion(descripcion);
		dispositivo.setTipo(tipo);
		
		// Por ultimo, se guarda el nuevo dispositivo en la base de datos
		dispositivoDAO.insertar(dispositivo);
	}
	
	/**
	 * RFN6 - Metodo para registrar los ejemplares asociados a un dispositivo y los cuales podran prestar los investigadores
	 * @param idDispositivo Campo con el numero de identificacion del dispositivo
	 * @param codigoEstadoDispositivo Campo con el numero de identificacion del estado para el nuevo ejemplar
	 * @throws ExcepcionPrestamos Ocurre cuando hay un problema en la insercion de datos en la BD
	 */
	public void registrarNuevoEjemplarDispositivo(int idDispositivo, int codigoEstadoDispositivo) throws ExcepcionPrestamos
	{
		// Los siguientes condiciones son para comprobar que los campos de identificacion son validos
		if (idDispositivo <= 0)
		{
			throw new ExcepcionPrestamos("El dispositivo de referencia para este ejemplar no puede ser nulo!");
		}
		
		if (codigoEstadoDispositivo <= 0)
		{
			throw new ExcepcionPrestamos("El estado del dispositivo no puede ser nulo!");
		}
		
		// Luego, se recupera la info del dispositivo y estado especifio que se le quiere asociar al ejemplar
		Dispositivo dispositivo = dispositivoDAO.obtener(idDispositivo);
		EstadoDispositivo estado = estadoDispositivoDAO.obtener(codigoEstadoDispositivo);
		
		// Ahora, se procede a crear un nuevo ejemplar y se setea su informacion
		EjemplarDispositivo ejemplar = new EjemplarDispositivo();
		ejemplar.setDispositivo(dispositivo);
		ejemplar.setEstado(estado);
		
		// Por ultimo, se guarda este ejemplar en la base de datos
		ejemplarDispositivoDAO.insertar(ejemplar);
	}
	
	/**
	 * RFN7 - Metodo para remover cualquier dispositivo del sistema
	 * @param idEjemplarDispositivo Campo con el numero identificador del ejemplar que se quiere remover
	 * @throws ExcepcionPrestamos Ocurre cuando se presento un problema al cambiar el estado del ejemplar
	 */
	public void removerEjemplarDispositivo(int idEjemplarDispositivo) throws ExcepcionPrestamos
	{
		try
		{
			// En primer lugar, recuperamos la informacion del ejemplar que se desea remover o dar de baja
			EjemplarDispositivo ejemplarDispositivo = ejemplarDispositivoDAO.obtener(idEjemplarDispositivo);
			
			// Luego, obtenemos la informacion del estado que corresponde a "Dado de baja"
			EstadoDispositivo estado = estadoDispositivoDAO.obtener(3);
			
			// Por ultimo, le asignamos tal estado al ejemplar deseado y actualizamos el ejemplar en la base de datos
			ejemplarDispositivo.setEstado(estado);
			ejemplarDispositivoDAO.actualizar(ejemplarDispositivo);
		}
		catch(Exception e) // En caso de error recuperamos la excepcion y la guardamos en el log
		{
			throw new ExcepcionPrestamos("Error cambiando el estado del prestamo!", e);
		}
	}
}
