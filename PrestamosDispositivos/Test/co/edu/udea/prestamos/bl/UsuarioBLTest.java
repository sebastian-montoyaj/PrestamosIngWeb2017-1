package co.edu.udea.prestamos.bl;

//Importes necesarios para la clase
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Clase para realizar pruebas sobre la clase de la logica del negocio de los usuarios
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 03/05/2017
 */
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion
public class UsuarioBLTest
{
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	UsuarioBL userBL; // Creo un objeto UsuarioBL para realizar las pruebas

	@Test
	public void testEsUsuarioValido()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo esUsuarioValido de la clase UsuarioBL
			assertTrue(userBL.esUsuarioValido("1", "1234567")); // Se verifica que el usuario con dicho pass si exista en el sistema
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCambiarEstadoUsuario()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo esUsuarioValido de la clase UsuarioBL
			userBL.cambiarEstadoUsuario("3", 2); // Se cambia el estado del usuario 3 a 2->Sancionado
			userBL.cambiarEstadoUsuario("3", 1); // Se revierte el cambio
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testObtenerRolUsuario()
	{
		try
		{
			String resul = userBL.obtenerRolUsuario("1");			
			assertTrue(resul.equals("Investigador"));
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
