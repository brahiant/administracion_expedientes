package com.project.springFileManager.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.DocumentException;
import com.project.springFileManager.domain.DataPlantilla;
import com.project.springFileManager.utils.ConvertirBase64;
import com.project.springFileManager.utils.FormatoFecha;
import com.project.springFileManager.utils.FormatoPDF;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plantillas")
public class PlantillaController {

    @Autowired
    private SpringTemplateEngine motorPlantilla;

    @Autowired
    private ConvertirBase64 convertirBase64;

    @Autowired
    private FormatoFecha formatoFecha;

    @Autowired
    private FormatoPDF formatoPDF;

    @PostMapping("/consentimientoInformado")
    @ResponseBody
    @CrossOrigin( origins = "http://localhost:8080", allowCredentials = "true")
    @ApiOperation("Devuelve el consentimiento informado con firma en formato PDF")
    @ApiResponses({
            @ApiResponse(code=200,message = "APPLICATION_PDF"),
            @ApiResponse(code=404, message = "Producto no encontrado"),
    })
    public ResponseEntity<?> consentimientoInformado(@RequestBody DataPlantilla dataPlantilla) {
        final Context ctx = new Context();
        dataPlantilla.setFechaFirma(formatoFecha.RetornarCadenaFecha(new Date()));
        ctx.setVariable("nombreCompleto", dataPlantilla.getNombreCompleto());
        ctx.setVariable("documentoIdentidad", dataPlantilla.getDocumentoIdentidad());
        ctx.setVariable("fechaFirma", dataPlantilla.getFechaFirma());
        ctx.setVariable("imagen", convertirBase64.retornarImagen(dataPlantilla.getImagen()));
        String html = motorPlantilla.process("consentimientoInformado", ctx);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, target);
        byte[] bytes = target.toByteArray();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @PostMapping("/multiplePDF")
    @ResponseBody
    @CrossOrigin( origins = "http://localhost:8080", allowCredentials = "true")
    @ApiOperation("Recibe rutas de archivos PDF para devolver un único archivo con todos los PDF")
    @ApiResponses({
            @ApiResponse(code=200,message = "APPLICATION_PDF"),
            @ApiResponse(code=404, message = "Producto no encontrado"),
    })
    public ResponseEntity<?> retornarMultiplesPDF(@RequestBody DataPlantilla plantilla) throws IOException, DocumentException {
        List<URL> archivo=new ArrayList<>();
        for (String url:plantilla.getListaUrl()) {
            archivo.add(new File(url).toURI().toURL());
        }
        File pdf=formatoPDF.descargaMultiplesPDF(archivo);
        byte[] bytes = Files.readAllBytes(pdf.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

}
