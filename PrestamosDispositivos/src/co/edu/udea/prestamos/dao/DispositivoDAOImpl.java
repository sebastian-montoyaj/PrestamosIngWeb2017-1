package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.DispositivoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Dispositivo
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class DispositivoDAOImpl implements DispositivoDAO
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
	public List<Dispositivo> obtenerTodo() throws ExcepcionPrestamos {
		List<Dispositivo> listaDispositivos =  new ArrayList<Dispositivo>(); // Variable con la que vamos a recibir la lista de Dispositivo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(Dispositivo.class); // Se crea un criterio en donde traeremos todos los Dispositivo
			listaDispositivos = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los Estado Dispositivo del sistema!", e);
		}
		
		return listaDispositivos; // Por ultimo, retornamos la lista vacia o de Estado Dispositivo que recuperamos de la BD
	}

	@Override
	public Dispositivo obtener(int idDispositivo) throws ExcepcionPrestamos {
		Dispositivo dispositivo = new Dispositivo(); // Aqui creamos un objeto Estado Dispositivo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			dispositivo = (Dispositivo) session.load(Dispositivo.class, idDispositivo); // Y se realiza la consulta del Estado Dispositivo, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el Dispositivo:" + idDispositivo + "!", e);
		}
		
		return dispositivo; // Por ultimo, retornamos el Estado Dispositivo encontrado
	}

	@Override
	public void insertar(Dispositivo dispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(dispositivo); // Se guarda el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Dispositivo!", e);
		}
		
	}

	@Override
	public void actualizar(Dispositivo dispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(dispositivo); // Se actualiza el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error actualizando el Dispositivo!", e);
		}		
	}

	@Override
	public void eliminar(Dispositivo dispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(dispositivo); // Se elimina el Estado Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error eliminando el Dispositivo!", e);
		}		
	}
}
