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

import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;
import co.edu.udea.prestamos.dto.EjemplarDispositivo;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase EjemplarDispositivoDAOImpl
* @author César Muñoz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class EjemplarDispositivoDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	EjemplarDispositivoDAO ejemplarDispositivoDAO; // Objeto EjemplarDispositivoDAO el cual me permitira invocar los metodos definidos de EjemplarDispositivoDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<EjemplarDispositivo> listaEjemplarDispositivo = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaEjemplarDispositivo = ejemplarDispositivoDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar			
			assertTrue(listaEjemplarDispositivo.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		EjemplarDispositivo ejemplarDispositivo = null; // Creo un objeto EjemplarDispositivo para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			ejemplarDispositivo = ejemplarDispositivoDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(ejemplarDispositivo != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		EjemplarDispositivo ejemplarDispositivo = null; // Creo un objeto Ejemplar Dispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Ejemplar Dispositivo al sistema
			ejemplarDispositivo = new EjemplarDispositivo(); 
			ejemplarDispositivo.setIdEjemplar(3);
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			ejemplarDispositivoDAO.insertar(ejemplarDispositivo);
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
		EjemplarDispositivo ejemplarDispositivo = null; // Creo un objeto EjemplarDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el EjemplarDispositivo ya existente
			ejemplarDispositivo = new EjemplarDispositivo(); 
			ejemplarDispositivo.setIdEjemplar(3);
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			ejemplarDispositivoDAO.actualizar(ejemplarDispositivo);
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
		EjemplarDispositivo ejemplarDispositivo = null; // Creo un objeto EjemplarDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el EjemplarDispositivo ya existente
			ejemplarDispositivo = new EjemplarDispositivo(); 
			ejemplarDispositivo.setIdEjemplar(3);
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			ejemplarDispositivoDAO.eliminar(ejemplarDispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

}
