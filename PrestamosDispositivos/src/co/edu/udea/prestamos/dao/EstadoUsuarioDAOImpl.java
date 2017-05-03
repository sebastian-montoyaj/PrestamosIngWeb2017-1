package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.EstadoUsuarioDAO;
import co.edu.udea.prestamos.dto.EstadoUsuario;
import co.edu.udea.prestamos.dto.Rol;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Estado Usuario
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class EstadoUsuarioDAOImpl implements EstadoUsuarioDAO
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
	public List<EstadoUsuario> obtenerTodo() throws ExcepcionPrestamos {
		List<EstadoUsuario> listaEstadoUsuarios=  new ArrayList<EstadoUsuario>(); // Variable con la que vamos a recibir la lista de esatados de usuarios definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(EstadoUsuario.class); // Se crea un criterio en donde traeremos todos los roles
			listaEstadoUsuarios = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los estados de usuario del sistema!", e);
		}
		
		return listaEstadoUsuarios; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}

	@Override
	public EstadoUsuario obtener(int codigo) throws ExcepcionPrestamos {
		EstadoUsuario estadoUsuario = new EstadoUsuario(); // Aqui creamos un objeto estadoUsuario para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			estadoUsuario = (EstadoUsuario) session.load(EstadoUsuario.class, codigo); // Y se realiza la consulta del estado usuario, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el estado de usuario:" + codigo + "!", e);
		}
		
		return estadoUsuario; // Por ultimo, retornamos de estado usuario encontrado
	}

	@Override
	public void insertar(EstadoUsuario estadoU) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(estadoU); // Se guarda el estado de usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo estado de usuario!", e);
		}
		
	}

	@Override
	public void actualizar(EstadoUsuario estadoU) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.update(estadoU); // Se actualiza el estado de usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo estado de usuario!", e);
		}
		
	}

	@Override
	public void eliminar(EstadoUsuario estadoU) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(estadoU); // Se elimina el estado de usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo estado de usuario!", e);
		}
		
	}

}
