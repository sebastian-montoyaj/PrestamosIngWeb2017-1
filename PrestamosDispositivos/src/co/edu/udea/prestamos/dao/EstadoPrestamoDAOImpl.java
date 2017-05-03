package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.EstadoPrestamoDAO;
import co.edu.udea.prestamos.dto.EstadoPrestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Estado Prestamo
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class EstadoPrestamoDAOImpl implements EstadoPrestamoDAO
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
	public List<EstadoPrestamo> obtenerTodo() throws ExcepcionPrestamos {
		List<EstadoPrestamo> listaEstadoPrestamo =  new ArrayList<EstadoPrestamo>(); // Variable con la que vamos a recibir la lista de EstadoPrestamo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(EstadoPrestamo.class); // Se crea un criterio en donde traeremos todos los EstadoPrestamo
			listaEstadoPrestamo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los Estado Prestamo del sistema!", e);
		}
		
		return listaEstadoPrestamo; // Por ultimo, retornamos la lista vacia o de Estado Prestamoado Prestamo que recuperamos de la BD
	}

	@Override
	public EstadoPrestamo obtener(int codigo) throws ExcepcionPrestamos {
		EstadoPrestamo estadoPrestamo = new EstadoPrestamo(); // Aqui creamos un objeto Estado Prestamo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			estadoPrestamo = (EstadoPrestamo) session.load(EstadoPrestamo.class, codigo); // Y se realiza la consulta del Estado Prestamo, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el estado prestamo:" + codigo + "!", e);
		}
		
		return estadoPrestamo; // Por ultimo, retornamos el Estado Prestamo encontrado
	}

	@Override
	public void insertar(EstadoPrestamo estadoP) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(estadoP); // Se guarda el Estado Prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Estado Prestamo!", e);
		}
		
	}

	@Override
	public void actualizar(EstadoPrestamo estadoP) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
			
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(estadoP); // Se actualiza el Estado Prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error insertando el nuevo Estado Prestamo!", e);
		}		
		
	}

	@Override
	public void eliminar(EstadoPrestamo estadoP) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(estadoP); // Se elimina el Estado Prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Estado Prestamo!", e);
		}
		
	}

}
