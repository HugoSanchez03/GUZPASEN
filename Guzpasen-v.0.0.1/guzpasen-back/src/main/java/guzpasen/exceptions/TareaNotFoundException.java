package guzpasen.exceptions;

public class TareaNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5030665213865363481L;

    public TareaNotFoundException() {
        super();
    }

    public TareaNotFoundException(Long idTarea) {
        super("Tarea no encontrada: " + idTarea);
    }
}
