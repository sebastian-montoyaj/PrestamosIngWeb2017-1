package co.edu.udea.prestamos.bl;

//Importes necesarios para la clase
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.prestamos.dao.UsuarioDAOImpl;
import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dao.interfaces.EstadoPrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.ItemsPrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.PrestamoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.dto.EstadoDispositivo;
import co.edu.udea.prestamos.dto.EstadoPrestamo;
import co.edu.udea.prestamos.dto.ItemsPrestamo;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.dto.Usuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
* Clase para realizar las solicitudes de prestamo, confirmar la entrega y las devoluciones de los dispositivos
* @author César Muñoz Roldan
* @version 1.0 - 03/05/2017
*/
@Transactional // Esto le indica a la clase que las operaciones que se van a hacer son transaccionales
public class PrestamosBL
{
	// Variables para enlazar/inyectar la informacion de los objetos necesarios que requieren los prestamos en la base de datos
	private PrestamoDAO prestamoDAO;
	private UsuarioDAOImpl usuarioDAOImpl;
	private EstadoPrestamoDAO estadoPrestamoDAO;
	private ItemsPrestamoDAO itemsPrestamoDAO;
	private EjemplarDispositivoDAO ejemplarDispositivoDAO;
	private static final int CODROLINVESTIGADOR=2;
	private static final int CODESTADOPRESTADO=5;
	private static final int COD_ESTADO_DISPOSITIVO_DISPONIBLE=1;
	private static final int COD_ESTADO_PRESTAMO_CADUCADO=4;
	
	// Metodos getter/setter para las variables que requiere esta clase
	public PrestamosBL(){
		
	}
	
	public PrestamoDAO getPrestamoDAO() {
		return prestamoDAO;
	}

	public void setPrestamoDAO(PrestamoDAO prestamoDAO) {
		this.prestamoDAO = prestamoDAO;
	}
	
	public UsuarioDAOImpl getUsuarioDAOImpl() {
		return usuarioDAOImpl;
	}

	public void setUsuarioDAOImpl(UsuarioDAOImpl usuarioDAOImpl) {
		this.usuarioDAOImpl = usuarioDAOImpl;
	}

	public EstadoPrestamoDAO getEstadoPrestamoDAO() {
		return estadoPrestamoDAO;
	}

	public void setEstadoPrestamoDAO(EstadoPrestamoDAO estadoPrestamoDAO) {
		this.estadoPrestamoDAO = estadoPrestamoDAO;
	}

	public ItemsPrestamoDAO getItemsPrestamoDAO() {
		return itemsPrestamoDAO;
	}

	public void setItemsPrestamoDAO(ItemsPrestamoDAO itemsPrestamoDAO) {
		this.itemsPrestamoDAO = itemsPrestamoDAO;
	}
	
	public EjemplarDispositivoDAO getEjemplarDispositivoDAO() {
		return ejemplarDispositivoDAO;
	}

	public void setEjemplarDispositivoDAO(EjemplarDispositivoDAO ejemplarDispositivoDAO) {
		this.ejemplarDispositivoDAO = ejemplarDispositivoDAO;
	}
	
	/**
	 * RFN2 - Metodo para realizar la solicitud de prestamo
	 * @param idUsuario Campo con la identificacion del usuario que desea hacer la solicitud
	 * @param listIdEjemplares Campo con el arreglo de items/ejemplares que el usuario desea prestar
	 * @return Valor logico que permite conocer el resultado de la operacion
	 * @throws ExcepcionPrestamos Ocurre cuando el usuario no es valido o se presento un error al insertar
	 */
	public boolean solicitud(int idUsuario, List<Integer>listIdEjemplares,Date fechaPrestamo) throws ExcepcionPrestamos
	{
		// Creo los objetos necesarios para el metodo
		Prestamo prestamo = new Prestamo();
		EstadoPrestamo estado = new EstadoPrestamo();
		Usuario usuario = new Usuario();				
		List<EjemplarDispositivo> listEjemplaresDispositivos = new ArrayList<EjemplarDispositivo>();
		
		try{
			//Se trae la informacion del usuario que realiza la solicitud por su ID
			usuario=usuarioDAOImpl.obtener(idUsuario);
			
			if(usuario==null){
				throw new ExcepcionPrestamos("Usuario que realiza la solicitud no valido");
			}
			
			//Valida que el usuario este habilitado			
			if(usuario.getEstado().getCodEstadoU()!=1){				
				throw new ExcepcionPrestamos("Usuario que realiza la solicitud no esta habilitado para realizar la solicitud");
			}
			
			//Se valida que el usuario crea tenga rol de investigador
			if(usuario.getEstado().getCodEstadoU()==CODROLINVESTIGADOR){
				throw new ExcepcionPrestamos("Usuario que realiza la solicitud no esta habilitado para realizar la solicitud");
			}
			
			//Se valida que hayan mas de un dispositivo para solicitar el prestamo
			if(listIdEjemplares.size()<=0){
				throw new ExcepcionPrestamos("No se seleccionaron dispositivos a prestar");
			}
			
			//Se trae la informacion del estado pendiente
			estado=estadoPrestamoDAO.obtener(1);
			
			//Se ingresan los datos de la solicitud
			prestamo.setFechaSolicitud(new Date());
			prestamo.setUsuarioSolicita(usuario);
			prestamo.setUsuarioAprueba(null);
			prestamo.setFechaPrestamo(fechaPrestamo);
			prestamo.setFechaEntrega(null);
			prestamo.setEstado(estado);
			
			//Se inserta la solicitud
			prestamoDAO.insertar(prestamo);	
			
			
			System.out.println("prueba id :"+prestamoDAO.ultimoID());			
			
			//Hago copia del prestamo con el identificador correspondiente a la ultima insercion, se hace porque no se puede modificar el id del anterior por sale excepcion de hiberne
			Prestamo prestamoAux=new Prestamo();
			prestamoAux.setIdPrestamo(prestamoDAO.ultimoID());
			prestamoAux.setFechaSolicitud(new Date());
			prestamoAux.setUsuarioSolicita(usuario);
			prestamoAux.setUsuarioAprueba(null);
			prestamoAux.setFechaPrestamo(fechaPrestamo);
			prestamoAux.setFechaEntrega(null);
			prestamoAux.setEstado(estado);
			
			//A continuacion, se procede a registrar un item por cada uno de los dispositivos que el investigador quiere prestar
			int tamLista=listIdEjemplares.size();
			
			EjemplarDispositivo ejemplarDispositivo;
			ItemsPrestamo itemsPrestamo=new ItemsPrestamo();
			int cont=0;
			
			for (Integer object: listIdEjemplares) {
				//Creo instancia de un ejemplar de dispositivos
				ejemplarDispositivo=new EjemplarDispositivo();
				
				//Obtengo la informacion del ejemplar
				ejemplarDispositivo=ejemplarDispositivoDAO.obtener(object.intValue());				
				
				//Creo instancia de un item del prestamo
				itemsPrestamo=new ItemsPrestamo();
				
				//Le seteo su prestamo correspondiente
				itemsPrestamo.setPrestamo(prestamoAux);
				
				//Le seteo el ejemplear correspondiente
				itemsPrestamo.setEjemplar(ejemplarDispositivo);
				System.out.println(ejemplarDispositivo.getIdEjemplar());
				//Inserto el item al prestamo correspondiente
				//itemsPrestamoDAO.insertar(itemsPrestamo);
			    			    
			}
			/*
			EjemplarDispositivo ejemplarDispositivo[]=new EjemplarDispositivo[tamLista];
			ItemsPrestamo itemsPrestamo[]=new ItemsPrestamo[tamLista];
			int cont=0;
			
			for (Integer object: listIdEjemplares) {
				//Creo instancia de un ejemplar de dispositivos
				ejemplarDispositivo[cont]=new EjemplarDispositivo();
				
				//Obtengo la informacion del ejemplar
				ejemplarDispositivo[cont]=ejemplarDispositivoDAO.obtener(object.intValue());				
				
				//Creo instancia de un item del prestamo
				itemsPrestamo[cont]=new ItemsPrestamo();
				
				//Le seteo su prestamo correspondiente
				itemsPrestamo[cont].setPrestamo(prestamoAux);
				
				//Le seteo el ejemplear correspondiente
				itemsPrestamo[cont].setEjemplar(ejemplarDispositivo[cont]);
				
				//Inserto el item al prestamo correspondiente
				itemsPrestamoDAO.insertar(itemsPrestamo[cont]);
			    
			    cont++;
			}*/		
			
			return true;
		}
		catch(ExcepcionPrestamos e){
			throw new ExcepcionPrestamos("Error registrando la solicitud por favor vuelve a intentar",e);
		}
		
	}
	
	/**
	 * RFN9 - Metodo para comprobar que se hace uso del prestamo
	 * @param id Campo con el numero identificador del prestamo 
	 * @return retorno verdadero en caso de que el procedimiento se haya ejecutado con exito
	 * @throws ExcepcionPrestamos ocurre cuando hay algun inconveniente en las transacciones
	 */
	public boolean comprobarEntrega(int id) throws ExcepcionPrestamos{
		try{
			Prestamo prestamo=new Prestamo();
			EstadoPrestamo estadoPrestamo=new EstadoPrestamo();
			
			//Obtengo el prestamo por su id
			prestamo=prestamoDAO.obtener(id);
			
			//Obtengo el estado prestado parametrizado en la base de datos
			estadoPrestamo=estadoPrestamoDAO.obtener(CODESTADOPRESTADO);

			//Asigno el estado a "Prestado" al prestamo
			prestamo.setEstado(estadoPrestamo);
			
			//se actualiza el prestamo con su nuevo estado
			prestamoDAO.actualizar(prestamo);
			
			return true;
		}
		catch (ExcepcionPrestamos e) {
			throw new ExcepcionPrestamos("No fue posible comprobar la entrega del dispositivo");
		}
	}
	
	/**
	 * RFN10 - Comprobar devolución del dispositivos
	 * @param id Campo con el numero identificador del prestamo
	 * @return retorno verdadero en caso de que el procedimiento se haya ejecutado con exito
	 * @throws ExcepcionPrestamos ocurre cuando hay algun inconveniente en las transacciones
	 */
	public boolean comprobarDevolucion(int id) throws ExcepcionPrestamos{
		try{
			Prestamo prestamo=new Prestamo();
			EstadoPrestamo estadoPrestamo=new EstadoPrestamo();
			List<ItemsPrestamo> itemsPrestamo=new ArrayList<ItemsPrestamo>();
			
			//Obtengo el prestamo por su id
			prestamo=prestamoDAO.obtener(id);
			
			//Obtengo los ejemplares pertenecientes al prestamo
			itemsPrestamo=itemsPrestamoDAO.obtenerEjemplares(prestamo.getIdPrestamo());
			
			for(ItemsPrestamo objects:itemsPrestamo){
				//Creo una instancia de un estado de dispositivo
				EstadoDispositivo estadoDispositivo=new EstadoDispositivo();
				
				//modifico el estado del dispositivo
				estadoDispositivo.setCodEstadoD(COD_ESTADO_DISPOSITIVO_DISPONIBLE);
				
				//le seteo el nuevo estado al ejemplar
				objects.getEjemplar().setEstado(estadoDispositivo);
				
				//Actualizo los item con su nuevo estado 
				ejemplarDispositivoDAO.actualizar(objects.getEjemplar());
				//if(1==1)break;
			}
			
			//Establesco la fecha de entrega del prestamo
			prestamo.setFechaEntrega(new Date());
			
			//Se modifica el estado del prestamo a caducado
			estadoPrestamo.setCodEstadoP(COD_ESTADO_PRESTAMO_CADUCADO);
			
			prestamo.setEstado(estadoPrestamo);
			
			//se actualiza el prestamo con su nuevo estado
			prestamoDAO.actualizar(prestamo);
			
			return true;
		}
		catch (ExcepcionPrestamos e) {
			throw new ExcepcionPrestamos("No fue posible comprobar la devolucion de lo dispositivo");
		}
	}
	

}
