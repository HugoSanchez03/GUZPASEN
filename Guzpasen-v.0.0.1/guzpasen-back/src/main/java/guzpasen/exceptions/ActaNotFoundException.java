	package guzpasen.exceptions;
	
	public class ActaNotFoundException extends RuntimeException {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 5030665213865363481L;
	
	    public ActaNotFoundException() {
	        super();
	    }
	
	    public ActaNotFoundException(Long idActa) {
	        super("Acta no encontrada: " + idActa);
	    }
	}
