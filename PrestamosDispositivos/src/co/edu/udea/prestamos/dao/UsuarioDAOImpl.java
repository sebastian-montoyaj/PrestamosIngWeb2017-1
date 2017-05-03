package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.UsuarioDAO;
import co.edu.udea.prestamos.dto.Usuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Estado Usuario
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class UsuarioDAOImpl implements UsuarioDAO
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
	public List<Usuario> obtenerTodo() throws ExcepcionPrestamos {
		List<Usuario> listaUsuario=  new ArrayList<Usuario>(); // Variable con la que vamos a recibir la lista de usuarios definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(Usuario.class); // Se crea un criterio en donde traeremos todos los roles
			listaUsuario = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los usuarios del sistema!", e);
		}
		
		return listaUsuario; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}

	@Override
	public Usuario obtener(int id) throws ExcepcionPrestamos {
		Usuario usuario = new Usuario(); // Aqui creamos un objeto Usuario para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			usuario = (Usuario) session.load(Usuario.class, id); // Y se realiza la consulta del usuario, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el usuario:" + id + "!", e);
		}
		
		return usuario; // Por ultimo, retornamos el usuario encontrado
	}

	@Override
	public void insertar(Usuario usuario) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(usuario); // Se guarda el usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo usuario!", e);
		}
		
	}

	@Override
	public void actualizar(Usuario usuario) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.update(usuario); // Se actualiza el  usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo usuario!", e);
		}
		
	}

	@Override
	public void eliminar(Usuario usuario) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(usuario); // Se elimina el  usuario en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo usuario!", e);
		}
		
	}
	
	
	
}
