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
import co.edu.udea.prestamos.dao.interfaces.RolDAO;
import co.edu.udea.prestamos.dto.Rol;

/**
 * Clase para realizar todas las pruebas necesarias sobre los metodos de la clase RolDAOImpl
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 02/05/2017
 */
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion
public class RolDAOImplTest
{
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	RolDAO rolDAO; // Objeto RolDAO el cual me permitira invocar los metodos definidos de RolDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<Rol> listaRoles = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaRoles = rolDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaRoles.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		Rol rol = null; // Creo un objeto Rol para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			rol = rolDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(rol != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		Rol rol = null; // Creo un objeto Rol que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo rol al sistema
			rol = new Rol(); 
			rol.setCodRol(3);
			rol.setNombre("Auxiliar");
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			rolDAO.insertar(rol);
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
		Rol rol = null; // Creo un objeto Rol que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el rol ya existente
			rol = new Rol(); 
			rol.setCodRol(3);
			rol.setNombre("Asesor");
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			rolDAO.actualizar(rol);
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
		Rol rol = null; // Creo un objeto Rol que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el rol ya existente
			rol = new Rol(); 
			rol.setCodRol(3);
			rol.setNombre("Asesor");
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			rolDAO.eliminar(rol);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}

}
