package com.project.springFileManager.utils;

import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormatoPDFTest {
    private FormatoPDF formatoPDF;
    @BeforeEach
    void setUp() {
        formatoPDF = new FormatoPDF();
    }

    @Test
    void deberiaCrearMultiplePDF() throws DocumentException, IOException {
        List<URL> archivo=new ArrayList<>();
        archivo.add(new File("C:\\Users\\brahi\\Downloads\\prueba\\_pdf-prueba_01.pdf").toURI().toURL());
        archivo.add(new File("C:\\Users\\brahi\\Downloads\\prueba\\_pdf-prueba_02.pdf").toURI().toURL());
        archivo.add(new File("C:\\Users\\brahi\\Downloads\\prueba\\_pdf-prueba_03.pdf").toURI().toURL());
        File pdf= formatoPDF.descargaMultiplesPDF(archivo);
        assertTrue(pdf.exists());
    }

}
