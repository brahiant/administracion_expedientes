package com.project.springFileManager.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class FormatoPDF {

    public File descargaMultiplesPDF(List<URL> listaDocumentoUrl) throws IOException, DocumentException {
        Document documento = new Document();
        String nombreDocumento="mergeDocuments.pdf";
        File archivo = new File("C:\\Users\\brahi\\OneDrive\\Documentos\\Archivados\\"+nombreDocumento);
        FileOutputStream rutaPDF = new FileOutputStream(archivo);
        PdfCopy anexoPDF = new PdfCopy(documento, rutaPDF);

        documento.open();
        for (URL url : listaDocumentoUrl){
            PdfReader reader = new PdfReader(url);
            anexoPDF.addDocument(reader);
            anexoPDF.freeReader(reader);
            reader.close();
        }
        documento.close();

        return archivo;
    }

}
