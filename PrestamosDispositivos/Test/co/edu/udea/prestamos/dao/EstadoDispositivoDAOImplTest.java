package co.edu.udea.prestamos.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.prestamos.dao.interfaces.EstadoDispositivoDAO;
import co.edu.udea.prestamos.dto.EstadoDispositivo;;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase EstadoDispositivoDAOImpl
* @author César Muñoz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class EstadoDispositivoDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	EstadoDispositivoDAO estadoDispositivoDAO; // Objeto EstadoDispositivoDAO el cual me permitira invocar los metodos definidos de EstadoUsuarioDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<EstadoDispositivo> listaEstadoUsuarios = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaEstadoUsuarios = estadoDispositivoDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaEstadoUsuarios.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

	@Test
	public void testObtener()
	{
		EstadoDispositivo estadoDispositivo = null; // Creo un objeto EstadoUsuario para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			estadoDispositivo = estadoDispositivoDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(estadoDispositivo != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

	@Test
	public void testInsertar()
	{
		EstadoDispositivo estadoDispositivo = null; // Creo un objeto EstadoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Estado Usuario al sistema
			estadoDispositivo = new EstadoDispositivo(); 
			estadoDispositivo.setCodEstadoD(4);
			estadoDispositivo.setNombre("prueba");
			estadoDispositivo.setDescripcion("prueba");
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			estadoDispositivoDAO.insertar(estadoDispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

	@Test
	public void testActualizar()
	{
		EstadoDispositivo estadoDispositivo = null; // Creo un objeto EstadoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el EstadoDispositivo ya existente
			estadoDispositivo = new EstadoDispositivo(); 
			estadoDispositivo.setCodEstadoD(4);
			estadoDispositivo.setNombre("prueba2");
			estadoDispositivo.setDescripcion("prueba2");
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			estadoDispositivoDAO.actualizar(estadoDispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

	@Test
	public void testEliminar()
	{
		EstadoDispositivo estadoDispositivo = null; // Creo un objeto EstadoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el EstadoDispositivo ya existente
			estadoDispositivo = new EstadoDispositivo(); 
			estadoDispositivo.setCodEstadoD(4);
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			estadoDispositivoDAO.eliminar(estadoDispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}
}
