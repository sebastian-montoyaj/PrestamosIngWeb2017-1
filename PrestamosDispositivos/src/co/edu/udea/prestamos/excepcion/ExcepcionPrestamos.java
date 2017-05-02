package co.edu.udea.prestamos.excepcion;

import org.apache.log4j.Logger;

/**
 * Clase personalizada para manejar todos los errores que puedan ocurrir en este proyecto
 * @author Sebastian Montoya Jimenez
 * @version 1.0 - 01/05/2017
 */
public class ExcepcionPrestamos extends Exception
{
	// Objeto Logger el cual manejara/registrara todos los mensajes de error, depuracion y otros asociados a este proyecto
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Constructor basico que inicializa la clase ExcepcionPrestamos
	 */
	public ExcepcionPrestamos()
	{
		super();
	}
	
	/**
	 * Constructor de la clase que crea una excepcion con la informacion detallada del evento
	 * @param message - Detalles del mensaje/evento
	 * @param cause - Causa u origen del mensaje (Si es nulo, se asume que la causa es desconocida o no existente)
	 * @param enableSuppression - Valor booleano que define si la excepcion se puede suprimir o no
	 * @param writableStackTrace - Valor booleano que indica si se puede escribir o no en la pila de seguimiento 
	 */
	public ExcepcionPrestamos(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		
		// En caso de error se registra la info del evento en el Log destinado para ello en el proyecto
		log.error(message, cause);
	}
	
	/**
	 * Constructor de la clase que crea una excepcion con solo los detalles y causa del evento
	 * @param message - Detalles del mensaje/evento
	 * @param cause - Causa u origen del mensaje (Si es nulo, se asume que la causa es desconocida o no existente)
	 */
	public ExcepcionPrestamos(String message, Throwable cause)
	{
		super(message, cause);
		
		// En caso de error se registra la info del evento en el Log destinado para ello en el proyecto
		log.error(message, cause);
	}
	
	/**
	 * Constructor de la clase que crea una excepcion solamente con los detalles de lo sucedido
	 * @param message - Detalles del mensaje/evento
	 */
	public ExcepcionPrestamos(String message)
	{
		super(message);
		
		// En caso de error se registra la info del evento en el Log destinado para ello en el proyecto
		log.error(message);
	}
	
	/**
	 * Constructor de la clase que crea una excepcion a partir del origen del mismo
	 * @param cause - Causa u origen del mensaje (Si es nulo, se asume que la causa es desconocida o no existente)
	 */
	public ExcepcionPrestamos(Throwable cause)
	{
		super(cause);
		
		// En caso de error se registra la info del evento en el Log destinado para ello en el proyecto
		log.error(cause);
	}
	
}
