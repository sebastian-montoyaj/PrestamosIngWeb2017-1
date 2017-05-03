package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.EstadoDispositivoDAO;
import co.edu.udea.prestamos.dto.EstadoDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Estado Dispositivo
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class EstadoDispositivoDAOImpl implements EstadoDispositivoDAO
{
	// Variable privada que me ayudara a obtener una sesion con la base de datos
	private SessionFactory sessionFactory;
	
	// Metodos getter y setter para establecer la sesion con la BD
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
		
	@Override
	public List<EstadoDispositivo> obtenerTodo() throws ExcepcionPrestamos {
		List<EstadoDispositivo> listaEstadoDispositivo =  new ArrayList<EstadoDispositivo>(); // Variable con la que vamos a recibir la lista de EstadoDispositivo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(EstadoDispositivo.class); // Se crea un criterio en donde traeremos todos los EstadoDispositivo
			listaEstadoDispositivo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los Estado Dispositivo del sistema!", e);
		}
		
		return listaEstadoDispositivo; // Por ultimo, retornamos la lista vacia o de Estado Dispositivo que recuperamos de la BD
	}

	@Override
	public EstadoDispositivo obtener(int codigo) throws ExcepcionPrestamos {
		EstadoDispositivo estadoDispositivo = new EstadoDispositivo(); // Aqui creamos un objeto Estado Dispositivo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			estadoDispositivo = (EstadoDispositivo) session.load(EstadoDispositivo.class, codigo); // Y se realiza la consulta del Estado Dispositivo, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el Estado Dispositivo:" + codigo + "!", e);
		}
		
		return estadoDispositivo; // Por ultimo, retornamos el Estado Dispositivo encontrado
	}

	@Override
	public void insertar(EstadoDispositivo estadoD) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(estadoD); // Se guarda el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Estado Dispositivo!", e);
		}
		
	}

	@Override
	public void actualizar(EstadoDispositivo estadoD) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(estadoD); // Se actualiza el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error insertando el nuevo Estado Dispositivo!", e);
		}
		
	}

	@Override
	public void eliminar(EstadoDispositivo estadoD) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(estadoD); // Se elimina el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error eliminando el Estado Dispositivo!", e);
		}
		
	}

}
