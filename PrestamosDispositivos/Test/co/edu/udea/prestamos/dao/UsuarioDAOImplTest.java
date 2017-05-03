package co.edu.udea.prestamos.dao;

//Importes necesarios para la clase
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.prestamos.dao.interfaces.UsuarioDAO;
import co.edu.udea.prestamos.dto.Usuario;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase UsuarioDAOImpl
* @author César Muñoz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class UsuarioDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	UsuarioDAO usuarioDAO; // Objeto UsuarioDAO el cual me permitira invocar los metodos definidos de UsuarioDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<Usuario> listaUsuarios = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaUsuarios = usuarioDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaUsuarios.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		Usuario usuario = null; // Creo un objeto Usuario para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			usuario = usuarioDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(usuario != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		Usuario usuario = null; // Creo un objeto Usuario que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Usuario al sistema
			usuario = new Usuario(); 
			usuario.setId(3);
			usuario.setNombre("prueba");
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			usuarioDAO.insertar(usuario);
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
		Usuario usuario = null; // Creo un objeto Usuario que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el Usuario ya existente
			usuario = new Usuario(); 
			usuario.setId(3);
			usuario.setNombre("prueba");
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			usuarioDAO.actualizar(usuario);
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
		Usuario usuario = null; // Creo un objeto Usuario que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el Usuario ya existente
			usuario = new Usuario(); 
			usuario.setId(3);
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			usuarioDAO.eliminar(usuario);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

}
