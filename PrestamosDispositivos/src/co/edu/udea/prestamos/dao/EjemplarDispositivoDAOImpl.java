package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Dispositivo
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class EjemplarDispositivoDAOImpl implements EjemplarDispositivoDAO
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
	public List<EjemplarDispositivo> obtenerTodo() throws ExcepcionPrestamos {
		List<EjemplarDispositivo> listaEjemplarDispositivo =  new ArrayList<EjemplarDispositivo>(); // Variable con la que vamos a recibir la lista de Dispositivo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(EjemplarDispositivo.class); // Se crea un criterio en donde traeremos todos los Dispositivo
			listaEjemplarDispositivo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los los ejemplares!", e);
		}
		
		return listaEjemplarDispositivo; // Por ultimo, retornamos la lista vacia o de Estado Dispositivo que recuperamos de la BD
	}

	@Override
	public EjemplarDispositivo obtener(int idEjemplar) throws ExcepcionPrestamos {
		EjemplarDispositivo ejemplarDispositivo = new EjemplarDispositivo(); // Aqui creamos un objeto Estado Dispositivo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			ejemplarDispositivo = (EjemplarDispositivo) session.load(EjemplarDispositivo.class, idEjemplar); // Y se realiza la consulta del Estado Dispositivo, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el Ejemplar Dispositivo:" + idEjemplar + "!", e);
		}
		
		return ejemplarDispositivo; // Por ultimo, retornamos el Ejemplar Dispositivo encontrado
	}

	@Override
	public void insertar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(ejemplar); // Se guarda el Ejemplar Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Ejemplar Dispositivo!", e);
		}
		
	}

	@Override
	public void actualizar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(ejemplar); // Se actualiza el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error actualizando el Ejemplar Dispositivo!", e);
		}		
	}

	@Override
	public void eliminar(EjemplarDispositivo ejemplar) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(ejemplar); // Se elimina el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error eliminando el Ejemplar Dispositivo!", e);
		}		
	}
}
