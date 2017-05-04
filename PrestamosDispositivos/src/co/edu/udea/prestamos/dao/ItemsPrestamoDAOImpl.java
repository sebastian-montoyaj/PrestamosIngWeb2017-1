package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import co.edu.udea.prestamos.dao.interfaces.ItemsPrestamoDAO;
import co.edu.udea.prestamos.dto.EstadoUsuario;
import co.edu.udea.prestamos.dto.ItemsPrestamo;
import co.edu.udea.prestamos.dto.Prestamo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Estado Usuario
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class ItemsPrestamoDAOImpl implements ItemsPrestamoDAO
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
	public List<ItemsPrestamo> obtenerTodo() throws ExcepcionPrestamos {
		List<ItemsPrestamo> listaItemsPrestamo=  new ArrayList<ItemsPrestamo>(); // Variable con la que vamos a recibir la lista de esatados de usuarios definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(ItemsPrestamo.class); // Se crea un criterio en donde traeremos todos los roles
			listaItemsPrestamo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los items prestamo del sistema!", e);
		}
		
		return listaItemsPrestamo; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}

	@Override
	public ItemsPrestamo obtener(int codigo) throws ExcepcionPrestamos {
		ItemsPrestamo itemsPrestamo = new ItemsPrestamo(); // Aqui creamos un objeto estadoUsuario para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			itemsPrestamo = (ItemsPrestamo) session.load(ItemsPrestamo.class, codigo); // Y se realiza la consulta del estado usuario, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el items prestamo:" + codigo + "!", e);
		}
		
		return itemsPrestamo; // Por ultimo, retornamos de estado usuario encontrado
	}

	@Override
	public void insertar(ItemsPrestamo item) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(item); // Se guarda el items prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo items prestamo!", e);
		}
		
	}

	@Override
	public void actualizar(ItemsPrestamo item) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.update(item); // Se actualiza el items prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo items prestamo!", e);
		}
		
	}

	@Override
	public void eliminar(ItemsPrestamo item) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(item); // Se elimina el items prestamo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo items prestamo!", e);
		}
		
	}

	@Override
	public List<ItemsPrestamo> obtenerEjemplares(int idPrestamo) throws ExcepcionPrestamos {
		List<ItemsPrestamo> listaItemsPrestamo=  new ArrayList<ItemsPrestamo>(); // Variable con la que vamos a recibir la lista de esatados de usuarios definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Prestamo prestamo=new Prestamo();
			prestamo.setIdPrestamo(idPrestamo);					
			Criteria criteria = session.createCriteria(ItemsPrestamo.class)
					.add(Restrictions.eq("prestamo",prestamo)); // Se crea un criterio en donde traeremos todos los ejemplares relacionados al prestamo
			listaItemsPrestamo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los ejemplares referente al prestamo del sistema!", e);
		}
		
		return listaItemsPrestamo; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}	
	
	
}
