package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.prestamos.dao.interfaces.PrestamoDAO;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;


/**
 * Clase que implementa la interfaz DAO de Estado Usuario
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class PrestamoDAOImpl implements PrestamoDAO
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
	public List<Prestamo> obtenerTodo() throws ExcepcionPrestamos {
		List<Prestamo> listaPrestamo=  new ArrayList<Prestamo>(); // Variable con la que vamos a recibir la lista de esatados de usuarios definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(Prestamo.class); // Se crea un criterio en donde traeremos todos los prestamos
			listaPrestamo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los estados de usuario del sistema!", e);
		}
		
		return listaPrestamo; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}

	@Override
	public Prestamo obtener(int id) throws ExcepcionPrestamos {
		Prestamo prestamo = new Prestamo(); // Aqui creamos un objeto prestamo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			prestamo = (Prestamo) session.load(Prestamo.class, id); // Y se realiza la consulta del estado usuario, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el prestamo:" + id + "!", e);
		}
		
		return prestamo; // Por ultimo, retornamos de estado usuario encontrado
	}

	@Override
	public void insertar(Prestamo prestamo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(prestamo); // Se guarda la solicitud de prestamo en la BD			
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el prestamo!", e);
		}		
	}

	
	public void actualizar(Prestamo prestamo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.update(prestamo); // Se actualiza el estado de usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error actualizando el prestamo!", e);
		}		
	}

	@Override
	public void eliminar(Prestamo prestamo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(prestamo); // Se elimina el estado de usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error eliminando prestamo!", e);
		}		
	}
	
	@Override
	public int ultimoID() throws ExcepcionPrestamos{
		Session session = null; // Variable con la que se establecera la conexion con la BD
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion			
			Criteria criteria=session.createCriteria(Prestamo.class)
					.setProjection(Projections.projectionList()
							.add(Projections.max("idPrestamo"),"idPrestamo"));
			List<Integer> results=criteria.list();
			return (int)results.get(0);		
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el prestamo!", e);
		}
	}
}
