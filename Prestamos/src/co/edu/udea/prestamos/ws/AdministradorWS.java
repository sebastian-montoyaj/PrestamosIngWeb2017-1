package co.edu.udea.prestamos.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import co.edu.udea.prestamos.bl.AdministradorBL;

@Path("Administrador")
@Component
public class AdministradorWS
{
	AdministradorBL adminBL;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String saludar()
	{
		return "Buenas tardes";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("2")
	public String saludar2()
	{
		return "Desde ing web!!!";
	}
}
