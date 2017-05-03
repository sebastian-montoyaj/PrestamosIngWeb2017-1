package co.edu.udea.prestamos.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.prestamos.dao.interfaces.DispositivoDAO;
import co.edu.udea.prestamos.dto.Dispositivo;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase DispositivoDAOImpl
* @author César Muñoz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class DispositivoDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	DispositivoDAO dispositivoDAO; // Objeto DispositivoDAO el cual me permitira invocar los metodos definidos de DispositivoDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<Dispositivo> listaDispositivos = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaDispositivos = dispositivoDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaDispositivos.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		Dispositivo dispositivo = null; // Creo un objeto Dispositivo para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			dispositivo = dispositivoDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(dispositivo != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		Dispositivo dispositivo = null; // Creo un objeto Dispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Tipo Dispositivo al sistema
			dispositivo = new Dispositivo(); 
			dispositivo.setIdDispositivo(3);
			dispositivo.setNombre("prueba");
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			dispositivoDAO.insertar(dispositivo);
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
		Dispositivo dispositivo = null; // Creo un objeto Dispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el Dispositivo ya existente
			dispositivo = new Dispositivo(); 
			dispositivo.setIdDispositivo(3);
			dispositivo.setNombre("prueba");
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			dispositivoDAO.actualizar(dispositivo);
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
		Dispositivo dispositivo = null; // Creo un objeto Dispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el Dispositivo ya existente
			dispositivo = new Dispositivo(); 
			dispositivo.setIdDispositivo(3);
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			dispositivoDAO.eliminar(dispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}
}
