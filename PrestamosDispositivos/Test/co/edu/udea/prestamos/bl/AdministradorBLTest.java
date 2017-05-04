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
 * Clase para realizar pruebas sobre la clase de la logica del negocio de los administradores
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 03/05/2017
 */
@RunWith(SpringJUnit4ClassRunner.class) // Con esto le estamos diciendo al Junit que se usara otro runner para las pruebas
@Transactional // Con esto indico que la clase sera transaccional
@ContextConfiguration(locations = "classpath:co/edu/udea/prestamos/config/SpringConf.xml") // Esto le dice a Spring donde esta el archivo de configuracion
public class AdministradorBLTest
{
	@Autowired // Esta anotacion permite la inyeccion de datos desde la BD a esta variable
	AdministradorBL adminBL; // Creo un objeto AdministradorBL para realizar las pruebas
	
	@Test
	public void testCambiarEstadoPrestamo()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo cambiarEstadoPrestamo de la clase AdministradorBL
			adminBL.cambiarEstadoPrestamo(1, 3); // El prestamo 1 se aprueba(3)
			adminBL.cambiarEstadoPrestamo(1, 1); // Se revierte el cambio
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRegistrarNuevoDispositivo()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo registrarNuevoDispositivo de la clase AdministradorBL
			adminBL.registrarNuevoDispositivo("Camara fotografica", "NIKON L340 Semiprofesional + Estuche + 8GB Negra", 1); // Se registra un nuevo elemento en la categoria de tecnologia(1) (se asume que tenga como pk el 7)
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRegistrarNuevoEjemplarDispositivo()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo registrarNuevoEjemplarDispositivo de la clase AdministradorBL
			adminBL.registrarNuevoEjemplarDispositivo(7, 1); // Se registra un ejemplar para elemento con id 7 y su estado inicial es disponible
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testRemoverEjemplarDispositivo()
	{
		try
		{
			// El resultado de la prueba dependera del resultado del metodo removerEjemplarDispositivo de la clase AdministradorBL
			adminBL.removerEjemplarDispositivo(1); // Se "Da de baja" el primer ejemplar de la tabla
		}
		catch(ExcepcionPrestamos e) // En caso de error
		{
			// Recupero el mensaje y la prueba falla
			//e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
