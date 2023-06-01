package co.unicauca.openmarket.server.domain.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import co.unicauca.openmarket.server.infra.Hashing;
/*
 * Pruebas unitarias del servicio de hashing
 */
public class hashingTest {

    
    /*
     * prueba para hashear una frase con minusculas y mayusculas
     */
    @Test
    public void HashTestConMinuMayus(){
        String hashfrase = Hashing.getSHA256Hash("HolaComoEstas");
        String fraseHasheada = "9b01572420f52a4c3ee8973b7abd0eed2b6bee1ad5652f25fac9aae5ed764feb";
        assertEquals(hashfrase,fraseHasheada);
    }

    /*
     * pruebas para hashear una frase con minusculas, mayusculas y numeros
     */
    @Test
    public void hashtTestconMinMayusNums(){
        String hashfrase = Hashing.getSHA256Hash("HolaC0moEst4s");
        String fraseHasheada = "1bbec870f9aeb1595ca6fcf09968cb174fdcfa42120526814601f09718b54af0";
        assertEquals(hashfrase,fraseHasheada);
    }

    /*
     * pruebas para hashear una frase con minusculas, mayusculas, numeros y simbolos
     */
    @Test
    public void hastTestConMinMayusNumsSibols(){
        String hashfrase = Hashing.getSHA256Hash("!HolaC0moEst4s*");
        String fraseHasheada = "931134d6275a8ea21cda129254e5a02d78021e532409b74276e11157a003f6ef";
        assertEquals(hashfrase,fraseHasheada);

    }

    /*
     * pruebas de fallo para hashear una frase con minusculas, mayusculas, numeros y simbolos
     */
    @Test
    public void hastTestfaildConMinMayusNumsSibols(){
        String hashfrase = Hashing.getSHA256Hash("!HolaC0moEst4s*");
        String fraseHasheada = "931134d6275a8ea21cda129254e5a02d78021e5324319b72176e11157a003f6ef";
        assertNotEquals(hashfrase,fraseHasheada);
    }


}
