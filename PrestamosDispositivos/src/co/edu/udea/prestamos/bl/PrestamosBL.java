package co.edu.udea.prestamos.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import co.edu.udea.prestamos.dao.UsuarioDAOImpl;
import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dao.interfaces.EstadoPrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.ItemsPrestamoDAO;
import co.edu.udea.prestamos.dao.interfaces.PrestamoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.dto.EstadoPrestamo;
import co.edu.udea.prestamos.dto.ItemsPrestamo;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.dto.Usuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;


/**
* Clase para realizar las solicitudes de prestamo,confirmar la entrega y las devoluciones de los dispositivos
* @author César Muñoz Roldan
* @version 1.0 - 03/05/2017
*/
public class PrestamosBL {
		
	private PrestamoDAO prestamoDAO;
	private UsuarioDAOImpl usuarioDAOImpl;
	private EstadoPrestamoDAO estadoPrestamoDAO;
	private ItemsPrestamoDAO itemsPrestamoDAO;
	private EjemplarDispositivoDAO ejemplarDispositivoDAO;
		
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

	public boolean solicitud(int idPrestamo,int idUsuario,List<Integer>listIdEjemplares) throws ExcepcionPrestamos{
		Prestamo prestamo=new Prestamo();
		EstadoPrestamo estado=new EstadoPrestamo();
		Usuario usuario=new Usuario();				
		List<EjemplarDispositivo> listEjemplaresDispositivos =new ArrayList<EjemplarDispositivo>();
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
			
			
			//Se valida que hayan mas de un dispositivo para solicitar el prestamo
			if(listIdEjemplares.size()<=0){
				throw new ExcepcionPrestamos("No se seleccionaron dispositivos a prestar");
			}
			
			//Se trae la informacion del estado pendiente
			estado=estadoPrestamoDAO.obtener(1);
			
			//Se ingresan los datos de la solicitud
			prestamo.setIdPrestamo(idPrestamo);
			prestamo.setFechaSolicitud(new Date());
			prestamo.setUsuarioSolicita(usuario);
			prestamo.setUsuarioAprueba(null);
			prestamo.setFechaPrestamo(null);
			prestamo.setFechaEntrega(null);
			prestamo.setEstado(estado);
			
			//Se inserta la solicitud
			prestamoDAO.insertar(prestamo);						
			
			int tamLista=listIdEjemplares.size();
			ListIterator<Integer> iter=listIdEjemplares.listIterator(tamLista);
			
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
				itemsPrestamo[cont].setPrestamo(prestamo);
				
				//Le seteo el ejemplear correspondiente
				itemsPrestamo[cont].setEjemplar(ejemplarDispositivo[cont]);
				
				//Inserto el item al prestamo correspondiente
				itemsPrestamoDAO.insertar(itemsPrestamo[cont]);
			    
			    cont++;
			}			
			
			return true;
		}
		catch(ExcepcionPrestamos e){
			throw new ExcepcionPrestamos("Error registrando la solicitud por favor vuelve a intentar",e);
		}
		
	}
	
	public boolean comprobarEntrega(int id) throws ExcepcionPrestamos{
		try{
			Prestamo prestamo=new Prestamo();
			EstadoPrestamo estadoPrestamo=new EstadoPrestamo();
			
			//Obtengo el prestamo por su id
			prestamo=prestamoDAO.obtener(id);
			
			//Obtengo el estado prestado parametrizado en la base de datos
			estadoPrestamo=estadoPrestamoDAO.obtener(5);

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
	
	public boolean comprobarDevolucion(int id) throws ExcepcionPrestamos{
		try{
			Prestamo prestamo=new Prestamo();
			EstadoPrestamo estadoPrestamo=new EstadoPrestamo();
			
			//Obtengo el prestamo por su id
			prestamo=prestamoDAO.obtener(id);
			
			//Establesco la fecha de entrega del prestamo
			prestamo.setFechaEntrega(new Date());
						
			//se actualiza el prestamo con su nuevo estado
			prestamoDAO.actualizar(prestamo);
			
			return true;
		}
		catch (ExcepcionPrestamos e) {
			throw new ExcepcionPrestamos("No fue posible comprobar la devolucion de lo dispositivo");
		}
	}
	

}
