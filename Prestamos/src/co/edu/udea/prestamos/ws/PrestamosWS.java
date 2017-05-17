package co.edu.udea.prestamos.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.prestamos.bl.PrestamosBL;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

@Path("Prestamo")
@Component
public class PrestamosWS {
	
	@Autowired
	PrestamosBL prestamoBL;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String solicitud(@QueryParam("idUser")int idUser,@QueryParam("listaEjemplares")String strListaEjemplares) throws RemoteException
	{
		try{
			//int idUsuario, List<Integer>listIdEjemplares
			List<Integer>listIdEjemplares=new ArrayList<Integer>();
			listIdEjemplares.add(1);
			//listIdEjemplares.add(2);
			//String ejemplares=strListaEjemplares.split(",");
			
			prestamoBL.solicitud(1, listIdEjemplares,new Date());
			
			return "GET Buenas tardes "+idUser+" lista: "+strListaEjemplares;
		}
		catch (ExcepcionPrestamos e) {
			throw new RemoteException("error guardando el usuario",e);
		}
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String prestamo(@QueryParam("cedula")String cedula)
	{
		return "POST Buenas tardes "+cedula;
	}
	

}
