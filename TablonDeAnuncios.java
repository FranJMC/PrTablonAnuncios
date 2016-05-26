package TablonAnuncios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anton_000
 * Date: 11/06/13
 * Time: 8:44
 * To change this template use File | Settings | File Templates.
 */
public class TablonDeAnuncios {
    private List<Anuncio> listaDeAnuncios_;
 
    /**
     * Constructor
     */
    public TablonDeAnuncios() {
        listaDeAnuncios_ = new ArrayList<Anuncio>();
        Anuncio anuncio = new Anuncio("Bienvenidos",
                "Tablon para poner anuncios", "LA EMPRESA");
        listaDeAnuncios_.add(anuncio);
    }
 
    /**
     * Devuelve el numero de anuncios publicados
     * 
     * @return numero de anuncios publicados
     */
    public int anunciosPublicados() {
        return listaDeAnuncios_.size();
    }
 
    /**
     * Publica un anuncio. Si el anunciante es "LA EMPRESA", se asume que es la
     * propietaria del tablon de anuncios, por lo que puede publicar gratis sin
     * restricciones.
     * 
     * @param anuncio
     *            El anuncio
     * @param bdAnunciantes
     *            Base de datos de anunciantes
     * @param bdPagos
     *            Base de datos de pagos.
     */
    public void publicarAnuncio(Anuncio anuncio,
            IBaseDeDatosDeAnunciantes bdAnunciantes, IBaseDeDatosDePagos bdPagos) {
        if (buscarAnuncioPorTitulo(anuncio.getTitulo()) == null) {
            if (anuncio.getAnunciante().equals("LA EMPRESA")) {
                listaDeAnuncios_.add(anuncio);
            } else {
                if ((bdAnunciantes.buscarAnunciante(anuncio.getAnunciante()))
                        && (bdPagos.anuncianteTieneSaldo(anuncio
                                .getAnunciante()))) {
                    listaDeAnuncios_.add(anuncio);
                    bdPagos.anuncioPublicado(anuncio.getAnunciante());
                }
            }
        }
    }
 
    /**
     * Buscar un anuncio por el titulo
     * 
     * @param titulo
     *            titulo del anuncio
     * @return el anuncio si se encuentra, null en caso contrario
     */
    public Anuncio buscarAnuncioPorTitulo(String titulo) {
        Anuncio resultado = null;
        for (Anuncio anuncio : listaDeAnuncios_) {
            if (anuncio.getTitulo().equals(titulo))
                resultado = anuncio;
        }
        return resultado;
    }
 
    /**
     * Borra un anuncio que se ajuste al titulo y anunciante que se pasan como
     * argumentos
     * 
     * @param titulo
     *            titulo del anuncio
     * @param anunciante
     *            nombre del anunciante
     */
    public void borrarAnuncio(String titulo, String anunciante) {
        for (Anuncio anuncio : listaDeAnuncios_) {
            if ((anuncio.getTitulo().equals(titulo) && (anuncio.getAnunciante()
                    .equals(anunciante))))
                listaDeAnuncios_.remove(anuncio);
        }
    }
 
    public boolean anuncioExistente(Anuncio a) {
        return (buscarAnuncioPorTitulo(a.getTitulo()).equals(a.getTitulo()));
    }
}
