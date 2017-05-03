package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.SancionDAO;
import co.edu.udea.prestamos.dto.Sancion;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Sancion
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class SancionDAOImpl implements SancionDAO
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
	public List<Sancion> obtenerTodo() throws ExcepcionPrestamos {
		List<Sancion> listaSancion =  new ArrayList<Sancion>(); // Variable con la que vamos a recibir la lista de Dispositivo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(Sancion.class); // Se crea un criterio en donde traeremos todos los Dispositivo
			listaSancion = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los Sancion del sistema!", e);
		}
		
		return listaSancion; // Por ultimo, retornamos la lista vacia o de Sancion que recuperamos de la BD
	}

	@Override
	public Sancion obtener(int codigo) throws ExcepcionPrestamos {
		Sancion sancion = new Sancion(); // Aqui creamos un objeto Sancion para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			sancion = (Sancion) session.load(Sancion.class, codigo); // Y se realiza la consulta del Sancion, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando la Sancion:" + codigo + "!", e);
		}
		
		return sancion; // Por ultimo, retornamos el Sancion encontrado
	}

	@Override
	public void insertar(Sancion sancion) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(sancion); // Se guarda el Sancion en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando la nuevo Sancion!", e);
		}		
	}

	@Override
	public void actualizar(Sancion sancion) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(sancion); // Se actualiza el Sancion en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error actualizando la Sancion!", e);
		}		
	}

	@Override
	public void eliminar(Sancion sancion) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(sancion); // Se elimina el Sancion en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error eliminando la Sancion!", e);
		}		
	}
	
}
