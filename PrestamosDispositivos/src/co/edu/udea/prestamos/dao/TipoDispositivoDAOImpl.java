package co.edu.udea.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.edu.udea.prestamos.dao.interfaces.TipoDispositivoDAO;
import co.edu.udea.prestamos.dto.TipoDispositivo;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase que implementa la interfaz DAO de Tipo Dispositivo
 * @author César Muñoz Roldan
 * @version 1.0 - 02/05/2017
 */
public class TipoDispositivoDAOImpl implements TipoDispositivoDAO
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
	public List<TipoDispositivo> obtenerTodo() throws ExcepcionPrestamos {
		List<TipoDispositivo> listaTipoDispositivo =  new ArrayList<TipoDispositivo>(); // Variable con la que vamos a recibir la lista de TipoDispositivo definidos en el sistema
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			Criteria criteria = session.createCriteria(TipoDispositivo.class); // Se crea un criterio en donde traeremos todos los TipoDispositivo
			listaTipoDispositivo = criteria.list(); // Y luego llevamos el resultado de la consulta a la lista creada anteriormente
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando los Tipo Dispositivo del sistema!", e);
		}
		
		return listaTipoDispositivo; // Por ultimo, retornamos la lista vacia o de Tipo Dispositivo que recuperamos de la BD
	}

	@Override
	public TipoDispositivo obtener(int idTipo) throws ExcepcionPrestamos {
		TipoDispositivo tipoDispositivo = new TipoDispositivo(); // Aqui creamos un objeto Tipo Dispositivo para recibir el resultado de la consulta
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia(obtiene) la sesion
			tipoDispositivo = (TipoDispositivo) session.load(TipoDispositivo.class, idTipo); // Y se realiza la consulta del Tipo Dispositivo, donde si este no es encontrado se procede a lanzar una excepcion
		}
		catch(HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error consultando el Tipo Dispositivo:" + idTipo + "!", e);
		}
		
		return tipoDispositivo; // Por ultimo, retornamos el Tipo Dispositivo encontrado
	}

	@Override
	public void insertar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.save(tipoDispositivo); // Se guarda el Tipo Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Tipo Dispositivo!", e);
		}
		
	}

	@Override
	public void actualizar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try{
				session = sessionFactory.getCurrentSession(); // Se inicia la sesion
				session.update(tipoDispositivo); // Se actualiza el Tipo Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
				throw new ExcepcionPrestamos("Error insertando el nuevo Tipo Dispositivo!", e);
		}
		
	}

	@Override
	public void eliminar(TipoDispositivo tipoDispositivo) throws ExcepcionPrestamos {
		Session session = null; // Variable con la que se establecera la conexion con la BD
		
		try
		{
			session = sessionFactory.getCurrentSession(); // Se inicia la sesion
			session.delete(tipoDispositivo); // Se elimina el Tipo Dispositivo en la BD
		}
		catch (HibernateException e) // En caso de error recuperamos el error y lanzamos la excepcion
		{
			throw new ExcepcionPrestamos("Error insertando el nuevo Tipo Dispositivo!", e);
		}
		
	}
}
