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

import co.edu.udea.prestamos.dao.interfaces.TipoDispositivoDAO;
import co.edu.udea.prestamos.dto.TipoDispositivo;

/**
* Clase para realizar todas las pruebas necesarias sobre los metodos de la clase TipoDispositivoDAOImpl
* @author C�sar Mu�oz Roldan
* @version 1.0 - 02/05/2017
*/
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion

public class TipoDispositivoDAOImplTest {
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	TipoDispositivoDAO tipoDispositivoDAO; // Objeto TipoDispositivoDAO el cual me permitira invocar los metodos definidos de TipoDispositivoDAOImpl
	
	@Test
	public void testObtenerTodo()
	{
		List<TipoDispositivo> listaTipoDispositivo = null; // Creo un objeto lista para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			listaTipoDispositivo = tipoDispositivoDAO.obtenerTodo(); // Ahora, inicializo la lista previa a partir del resultado del metodo que deseo evaluar
			assertTrue(listaTipoDispositivo.size() > 0); // Se considerara correcto el metodo si hay por lo menos un elemento 
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
		TipoDispositivo tipoDispositivo = null; // Creo un objeto TipoDispositivo para recibir el reultado de la consulta (De momento sera nulo)
		
		try
		{
			tipoDispositivo = tipoDispositivoDAO.obtener(2); // Ahora, inicializo el objeto previo a partir del resultado del metodo que deseo evaluar
			assertTrue(tipoDispositivo != null); // Se considerara correcto el metodo si de verdad se obtuvo un elemento
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
		TipoDispositivo tipoDispositivo = null; // Creo un objeto TipoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para poder agregar el nuevo Tipo Dispositivo al sistema
			tipoDispositivo = new TipoDispositivo(); 
			tipoDispositivo.setIdTipoDisp(4);
			tipoDispositivo.setNombre("prueba");
			
			// Ahora, invoco el metodo para insertar y sino se produce error es porque todo salio bien
			tipoDispositivoDAO.insertar(tipoDispositivo);
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
		TipoDispositivo tipoDispositivo = null; // Creo un objeto TipoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para modificar el TipoDispositivo ya existente
			tipoDispositivo = new TipoDispositivo(); 
			tipoDispositivo.setIdTipoDisp(4);
			tipoDispositivo.setNombre("prueba");
			
			// Ahora, invoco el metodo para actualizar y sino se produce error es porque todo salio bien
			tipoDispositivoDAO.actualizar(tipoDispositivo);
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
		TipoDispositivo tipoDispositivo = null; // Creo un objeto TipoDispositivo que de momento sera nulo
		
		try
		{
			// Luego, inicializo y lleno el objeto para eliminar el TipoDispositivo ya existente
			tipoDispositivo = new TipoDispositivo(); 
			tipoDispositivo.setIdTipoDisp(4);
			
			// Ahora, invoco el metodo para eliminar y sino se produce error es porque todo salio bien
			tipoDispositivoDAO.eliminar(tipoDispositivo);
		}
		catch(Exception e)
		{
			fail(e.getMessage()); // En caso de error recupero el mensaje y se considera la prueba como fallida
			//e.printStackTrace(); // En caso que se desee ver la traza completa de error se descomenta esta linea. Aunque no es una buena practica.
		}
	}
}
