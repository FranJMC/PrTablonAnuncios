package TablonAnuncios;

/**
 * Created with IntelliJ IDEA.
 * User: anton_000
 * Date: 11/06/13
 * Time: 8:42
 * To change this template use File | Settings | File Templates.
 */
public class Anuncio {
    private String titulo_;
    private String texto_;
    private String anunciante_;
 
    public Anuncio(String titulo, String texto, String anunciante) {
        titulo_ = titulo;
        texto_ = texto;
        anunciante_ = anunciante;
    }
 
    public String getTitulo() {
        return titulo_;
    }
 
    public String getTexto() {
        return texto_;
    }
 
    public String getAnunciante() {
        return anunciante_;
    }
}