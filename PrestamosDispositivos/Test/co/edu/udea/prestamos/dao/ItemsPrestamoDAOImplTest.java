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

import co.edu.udea.prestamos.dao.interfaces.ItemsPrestamoDAO;
import co.edu.udea.prestamos.dto.ItemsPrestamo;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase ItemsPrestamoDAOImpl
* @author César Muñoz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class ItemsPrestamoDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	ItemsPrestamoDAO itemsPrestamoDAO; // Objeto ItemsPrestamoDAO el cual me permitira invocar los metodos definidos de ItemsPrestamoDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<ItemsPrestamo> listaPrestamo = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaPrestamo = itemsPrestamoDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaPrestamo.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		ItemsPrestamo itemsPrestamo = null; // Creo un objeto Prestamo para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			itemsPrestamo = itemsPrestamoDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(itemsPrestamo != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		ItemsPrestamo itemsPrestamo = null; // Creo un objeto Items Prestamo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Estado Usuario al sistema
			itemsPrestamo = new ItemsPrestamo(); 
			itemsPrestamo.setIdItem(4);			
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			itemsPrestamoDAO.insertar(itemsPrestamo);
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
		ItemsPrestamo itemsPrestamo = null; // Creo un objeto Items Prestamo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el Items Prestamo ya existente
			itemsPrestamo = new ItemsPrestamo(); 
			itemsPrestamo.setIdItem(4);	
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			itemsPrestamoDAO.actualizar(itemsPrestamo);
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
		ItemsPrestamo itemsPrestamo = null; // Creo un objeto Items Prestamo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el Items Prestamo ya existente
			itemsPrestamo = new ItemsPrestamo(); 
			itemsPrestamo.setIdItem(4);	
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			itemsPrestamoDAO.eliminar(itemsPrestamo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}
	
	@Test
	public void testObtenerEjemplares()
	{
		List<ItemsPrestamo> listaPrestamo = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaPrestamo = itemsPrestamoDAO.obtenerEjemplares(1); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaPrestamo.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}
	
	


}
