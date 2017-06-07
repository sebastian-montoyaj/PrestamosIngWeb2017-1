package co.edu.udea.prestamos.bl;

//Importes necesarios para la clase
import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.transaction.annotation.Transactional;
import co.edu.udea.prestamos.dao.interfaces.EstadoUsuarioDAO;
import co.edu.udea.prestamos.dao.interfaces.UsuarioDAO;
import co.edu.udea.prestamos.dto.EstadoUsuario;
import co.edu.udea.prestamos.dto.Usuario;
import co.edu.udea.prestamos.excepcion.ExcepcionPrestamos;

/**
 * Logica del negocio para las funciones asociadas a los usuarios del sistema
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 03/05/2017
 */
@Transactional // Esto le indica a la clase que las operaciones que se van a hacer son transaccionales
public class UsuarioBL
{
	// Variable que permitira enlazar/inyectar la informacion de un UsuarioDAO con esta clase
	private UsuarioDAO userDAO;
	
	// Variable que permitira enlazar/inyectar la informacion de un EstadoUsuarioDAO con algun usuario
	private EstadoUsuarioDAO estadoUsuarioDAO;
	
	// Metodos getter/setter para la variable userDAO
	public UsuarioDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsuarioDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	// Metodos getter/setter para la variable estadoUsuarioDAO
	public EstadoUsuarioDAO getEstadoUsuarioDAO() {
		return estadoUsuarioDAO;
	}

	public void setEstadoUsuarioDAO(EstadoUsuarioDAO estadoUsuarioDAO) {
		this.estadoUsuarioDAO = estadoUsuarioDAO;
	}

	/**
	 * RFN1 - Metodo para validar el usuario que esta ingresando su identificador y contraseña.
	 * @param identificador Campo con el identificador del usuario
	 * @param password Campo con la contraseña del usuario
	 * @return Valor logico que confirma o deniega si el usuario hace parte del sistema
	 * @throws ExcepcionPrestamos Ocurre cuando uno o los dos parametros estan vacios
	 */
	public boolean esUsuarioValido(String identificador, String password) throws ExcepcionPrestamos
	{
		// En primer lugar revisamos que los campos si tengan informacion
		if (identificador == null || "".equals(identificador))
		{
			throw new ExcepcionPrestamos("Usuario y contraseña incorrectos!");
		}
		
		if (password == null || "".equals(password))
		{
			throw new ExcepcionPrestamos("Usuario y contraseña incorrectos!");
		}
		
		// Luego, codificamos la clave bajo formato md5
		String passwordCodificada = calcularMD5(password);
				
		// Ahora, creamos un objeto usuario que se inicializa con el resultado de la busqueda de tal usuario
		Usuario user = userDAO.obtener(Integer.parseInt(identificador));
		
		// Luego, si el objeto usuario es nulo entonces este usuario no es valido y retorno falso como resultado
		if (user == null)
		{
			return false;
		}
		
		// Si el usuario dado correponde con uno de la base de datos entonces pasamos a comprobar que la contraseña sea correcta, sino lo es entonces tampoco es valido
		if (!user.getClave().equals(passwordCodificada))
		{
			return false;
		}
		
		// NOTA: No es correcto decir que el usuario es correcto pero la contraseña no, porque eso le facilita a usuarios malintecionados vulnerar el sistema
		
		// Finalmente, si tanto el identificador como el password pasaron las condiciones anteriores entonces retorno que el usuario es valido
		return true;
	}
	
	/**
	 * RFN8 - Metodo para cambiar el estado (inhabilitar/habilitar) del usuario
	 * @param identificador Campo con el identificador del usuario
	 * @param nuevoEstado Campo con el codigo/numero del nuevo estado para el usuario
	 * @throws ExcepcionPrestamos Ocurre cuando se tuvo un problema ya sea obtiendo el usuario, el estado o modificando este en la BD
	 */
	public void cambiarEstadoUsuario(String identificador, int nuevoEstado) throws ExcepcionPrestamos
	{
		try
		{
			// En primer lugar, recuperamos el usuario con el identificador dado
			Usuario user = userDAO.obtener(Integer.parseInt(identificador));
			
			// Luego, obtenemos el estado especifico que se indico en los parametros
			EstadoUsuario estadoHabilitado = estadoUsuarioDAO.obtener(nuevoEstado);
			
			// Y hacemos el cambio de estado al usuario y lo actualizamos en la base de datos
			user.setEstado(estadoHabilitado);
			userDAO.actualizar(user);
		}
		catch (Exception e) // En caso de error recuperamos la excepcion y la guardamos en el log
		{
			throw new ExcepcionPrestamos("Error cambiando el estado de usuario!", e);
		}
	}
	
	/**
     * Metodo para codificar cualquier clave (o hilera de caracteres) en una clave hash usando el algoritmo MD5
     * @param entradaSinCodificar Campo con la clave sin codificar
     * @return Clave codificada que tiene una longitud de 32 caracteres
     */
    public static String calcularMD5(String entradaSinCodificar)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(entradaSinCodificar.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String salidaCodificada = number.toString(16);
            
            // Se rellena con ceros a la izquierda si la clave es menor de 32 digitos
            while (salidaCodificada.length() < 32)
            {
                salidaCodificada = "0" + salidaCodificada;
            }
            
            return salidaCodificada;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Metodo para obtener el rol que desempeña el usuario en el sistema
     * @param identificador Campo con el identificador del usuario
     * @return Valor con el nombre del rol asignado al usuario
     * @throws ExcepcionPrestamos Ocurre cuando el identificador esta vacio o el usuario no existe en la base de datos
     */
    public String obtenerRolUsuario(String identificador) throws ExcepcionPrestamos
    {
    	// En primer lugar se revisa que el campo identificador no este vacio
    	if (identificador == null || "".equals(identificador))
		{
			throw new ExcepcionPrestamos("Usuario y contraseña incorrectos!");
		}
    	
    	// Ahora, creamos un objeto usuario que se inicializa con el resultado de la busqueda de tal usuario
    	Usuario user = userDAO.obtener(Integer.parseInt(identificador));
    	
    	// Si el usuario de la consulta es nulo es porque no existe en la base de datos y entonces se debe abortar el metodo
    	if (user == null)
		{
    		throw new ExcepcionPrestamos("Usuario no existe");
		}
    	
    	// Si el usuario es valido entonces retornamos el nombre de su rol
    	return user.getRol().getNombre();
    }
}
