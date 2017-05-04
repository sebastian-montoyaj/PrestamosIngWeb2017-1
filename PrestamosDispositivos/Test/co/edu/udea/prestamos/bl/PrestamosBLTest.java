package co.edu.udea.prestamos.bl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.prestamos.dao.interfaces.EjemplarDispositivoDAO;
import co.edu.udea.prestamos.dto.ItemsPrestamo;
import co.edu.udea.prestamos.dto.Prestamo;


/**
* Pruebas para realizar las solicitudes de prestamo,confirmar la entrega y las devoluciones de los dispositivos
* @author César Muñoz Roldan
* @version 1.0 - 03/05/2017
*/
//Para decir que las pruebas se van hacer con Spring
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional //Para especificarle a Spring que la prueba es transaccional,(osea contra la base de datos)
@ContextConfiguration(locations="classpath:co/edu/udea/prestamos/config/SpringConf.xml")//Para decirle a spring donde esta el archivo de configuracion

public class PrestamosBLTest {
	@Autowired
	PrestamosBL prestamoBL;	
	
	/**
	 * Prueba de la solicitud del prestamo
	 */
	@Test
	public void testSolicitar(){
		
 		try{
 			//Lista del id de los ejemplares
 			List<Integer> listIdEjemplares=new ArrayList<Integer>();
 			//Agrego dos ejemplares para registrar en la solicitud
 			listIdEjemplares.add(1);
 			
			assertTrue(prestamoBL.solicitud(3,1, listIdEjemplares));
			
 		}
 		catch(Exception e){
 			e.printStackTrace();
 			fail(e.getMessage());
 		}
	}
	
	/**
	 * Comprobar la entrega de los dispositivos por el "id" del prestamo
	 */
	@Test
	public void testComprobarEntrega(){		
		try{
			assertTrue(prestamoBL.comprobarEntrega(1));			
 		}
 		catch(Exception e){
 			e.printStackTrace();
 			fail(e.getMessage());
 		}
	}
	

	/**
	 * Comprobar la devolucion de los dispositivos por el "id" del prestamo
	 */
	@Test
	public void testComprobarDevolucion(){		
		try{
			assertTrue(prestamoBL.comprobarEntrega(1));			
 		}
 		catch(Exception e){
 			e.printStackTrace();
 			fail(e.getMessage());
 		}
	}
	
}
