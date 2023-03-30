package com.project.springFileManager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class FormatoFechaTest {

    private FormatoFecha formatoFecha;

    @BeforeEach
    void setUp() {
        formatoFecha = new FormatoFecha();
    }
    @Test
    void deberiaRetornarFechaFormatoCadena(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Assertions.assertEquals(formato.format(new Date()), formatoFecha.RetornarCadenaFecha(new Date()));
    }

}