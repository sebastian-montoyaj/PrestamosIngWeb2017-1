package co.edu.udea.prestamos.dao;

//Importes necesarios para la clase
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import co.edu.udea.prestamos.dao.interfaces.RolDAO;
import co.edu.udea.prestamos.dto.Rol;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Rol
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
public class RolDAOImpl implements RolDAO
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
	public List<Rol> obtenerTodo() throws ExcepcionPrestamos
	{
		List<Rol> listaRoles =  new ArrayList<Rol>(); // Variable con la que vamos a recibir la lista de roles definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(Rol.class); // Se crea un criterio en donde traeremos todos los roles
			listaRoles = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los roles del sistema!", e);
		}
		
		return listaRoles; // Por ultimo, retornamos la lista vacia o de roles que recuperamos de la BD
	}

	@Override
	public Rol obtener(int codigo) throws ExcepcionPrestamos
	{
		Rol rol = new Rol(); // Aqui creamos un objeto Rol para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			rol = (Rol) session.load(Rol.class, codigo); // Y se realiza la consulta del rol, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el rol:" + codigo + "!", e);
		}
		
		return rol; // Por ultimo, retornamos el rol encontrado
	}

	@Override
	public void insertar(Rol rol) throws ExcepcionPrestamos
	{
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(rol); // Se guarda el rol en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo rol!", e);
		}
	}

	@Override
	public void actualizar(Rol rol) throws ExcepcionPrestamos
	{
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.update(rol); // Se actualiza el rol en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo rol!", e);
		}
	}

	@Override
	public void eliminar(Rol rol) throws ExcepcionPrestamos
	{
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(rol); // Se elimina el rol en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo rol!", e);
		}
	}

}
