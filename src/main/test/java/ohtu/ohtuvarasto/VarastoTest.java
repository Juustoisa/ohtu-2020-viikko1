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
    public void negatiivistaEiVoiLisätä() {
        varasto.lisaaVarastoon(-2);
        varastontilavuus = varasto.paljonkoMahtuu();
        assertEquals(10, varastonstilavuus, vertailuTarkkuus);
    }

    @Test
    public void liikaLisäysTäyttää() {
        varasto.lisaaVarastoon(12);
        varastontilavuus = varasto.paljonkoMahtuu();
        assertEquals(0, varastontilavuus, vertailuTarkkuus);
    }
    
    @Test
    public void stringMuotoToimii() {
        assertEquals("saldo = 0, vielä tilaa 10", varasto.toString());
    }
    
    @Test
    public void pidempiKonstruktoriToimii() {
        varasto2 = new Varasto(10,0);
        varastontilavuus = varasto2.paljonkoMahtuu();
        varastonsaldo = varasto2.getSaldo();
        assertEquals(10, varastontilavuus, vertailuTarkkuus);
        assertEquals(0, varastonsaldo, vertailuTarkkuus);
    }
    
}