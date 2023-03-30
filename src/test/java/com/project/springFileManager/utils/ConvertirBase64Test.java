package com.project.springFileManager.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConvertirBase64Test {

    private ConvertirBase64 mockConvertirBase64;
    private ConvertirBase64 convertirBase64;

    @BeforeEach
    void setUp() {
        mockConvertirBase64 = mock(ConvertirBase64.class);
        convertirBase64 = new ConvertirBase64();
    }

    @Test
     void deberiaRetornarImagenFormatoPDF(){
        String dataImagen= TestDatos.IMAGEN.getValor();
        when(mockConvertirBase64.retornarImagen("")).thenReturn(new File("image.png"));
        assertEquals(convertirBase64.retornarImagen(dataImagen), mockConvertirBase64.retornarImagen(""));
    }

}