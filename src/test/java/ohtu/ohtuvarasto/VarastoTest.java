package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void stringMuotoToimii() {
        String stringi = varasto.toString();
        assertEquals("saldo = 0.0, vielä tilaa 10.0", stringi);
    }
    
    @Test
    public void huonoLyhytKonstruktoriToimii() {
        varasto = new Varasto(-2);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void pitkäKonstruktoriToimii() {
        //normaali toiminta
        varasto = new Varasto(10,0);
        assertEquals(10.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
        //saldo yli tilasta
        varasto = new Varasto(6,10);
        assertEquals(6.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(6.0, varasto.getSaldo(), vertailuTarkkuus);
        
        //negatiivinen tila ja saldo
        varasto = new Varasto(-2,-2);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylitäyttöTäyttääMaksimin() {
        varasto.lisaaVarastoon(20);
        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus); 
    }
    
    @Test
    public void negatiivinenLisäysEiTeeMitään() {
        varasto.lisaaVarastoon(-20);
        assertEquals(10.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus); 
    }
    
    @Test
    public void negatiivinenOttoEiTeeMitään() {
        varasto.lisaaVarastoon(5);
        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus); 
        varasto.otaVarastosta(-20);
        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus); 
    }
    
    @Test
    public void kunOttoYlittääSaldonAnnetaanKaikki() {
        varasto.lisaaVarastoon(5);
        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus); 
       
        assertEquals(5.0, varasto.otaVarastosta(7), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
}