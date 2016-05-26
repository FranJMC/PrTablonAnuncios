package TablonAnuncios;

@SuppressWarnings("serial")
public class AnuncianteNoExisteException extends RuntimeException {
    public AnuncianteNoExisteException() {
        super();
    }
 
    public AnuncianteNoExisteException(String msg) {
        super(msg);
    }
}
