package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import TablonAnuncios.Anuncio;
import TablonAnuncios.IBaseDeDatosDeAnunciantes;
import TablonAnuncios.IBaseDeDatosDePagos;
import TablonAnuncios.TablonDeAnuncios;

public class TestTablonAnuncio {

	private TablonDeAnuncios tablon;
	 
    @Before
    public void init() {
        tablon = new TablonDeAnuncios();
    }
 
    @After
    public void close() {
        tablon = null;
    }
 
    @Test
    public void anuncioInicial() {
 
        assertEquals(1, tablon.anunciosPublicados());
    }
 
    @Test
    public void nuevoAnuncioEmpresa() {
        int nAnuncios = tablon.anunciosPublicados();
        
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablon.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(nAnuncios + 1, tablon.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularNoExiste() {
        int nAnuncios = tablon.anunciosPublicados();
        
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablon.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(nAnuncios, tablon.anunciosPublicados());
 
    }
 
    @Test
    public void nuevoAnuncioParticularExiste() {
        int nAnuncios = tablon.anunciosPublicados();
        
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "Juan");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("Juan")).thenReturn(true);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
        when(bdPagos.anuncianteTieneSaldo("Juan")).thenReturn(true);
 
        tablon.publicarAnuncio(a, bdAnunciantes, bdPagos);
        assertEquals(nAnuncios + 1, tablon.anunciosPublicados());
        verify(bdPagos, times(1)).anuncioPublicado("Juan");
 
    }
 
    @Test
    public void DosAnuncios() {
        int nAnuncios = tablon.anunciosPublicados();
        
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablon.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tablon.publicarAnuncio(b, bdAnunciantes, bdPagos);
        if (tablon.buscarAnuncioPorTitulo(b.getTitulo()).equals(a.getTitulo())) {
            tablon.borrarAnuncio(b.getTitulo(), b.getAnunciante());
        }
 
        assertEquals(nAnuncios + 1, tablon.anunciosPublicados());
 
    }
 
    @Test
    public void AnuncioQuitado() {
        int nAnuncios = tablon.anunciosPublicados();
        
        Anuncio a = new Anuncio("Nuevo", "Nuevo anuncio", "LA EMPRESA");
        Anuncio b = new Anuncio("Nuevo 2", "Nuevo anuncio 2", "LA EMPRESA");
        IBaseDeDatosDeAnunciantes bdAnunciantes = mock(IBaseDeDatosDeAnunciantes.class);
        when(bdAnunciantes.buscarAnunciante("LA EMPRESA")).thenReturn(false);
 
        IBaseDeDatosDePagos bdPagos = mock(IBaseDeDatosDePagos.class);
 
        tablon.publicarAnuncio(a, bdAnunciantes, bdPagos);
        tablon.publicarAnuncio(b, bdAnunciantes, bdPagos);
 
        tablon.borrarAnuncio(a.getTitulo(), a.getAnunciante());
 
        assertEquals(nAnuncios + 1, tablon.anunciosPublicados());
    }

}
