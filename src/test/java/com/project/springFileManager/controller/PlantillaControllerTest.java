package com.project.springFileManager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springFileManager.utils.TestDatos;
import com.project.springFileManager.domain.DataPlantilla;
import com.project.springFileManager.utils.ConvertirBase64;
import com.project.springFileManager.utils.FormatoFecha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantillaController.class)
class PlantillaControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objetoMapper;

    @MockBean
    private FormatoFecha formatoFecha;

    @MockBean
    private ConvertirBase64 convertirBase64;

    private DataPlantilla dataPlantilla;

    @BeforeEach
    void setUp() {
        dataPlantilla = new DataPlantilla();
    }

    @Test
     void deberiaRetornarPDF() throws Exception {
        dataPlantilla.setNombreCompleto(TestDatos.NOMBRE.getValor());
        dataPlantilla.setFechaFirma(TestDatos.FECHA_FIRMA.getValor());
        dataPlantilla.setDocumentoIdentidad(TestDatos.DOCUMENTO_IDENTIDAD.getValor());
        dataPlantilla.setImagen(TestDatos.IMAGEN.getValor());
        mockMvc.perform(post("/plantillas/consentimientoInformado").contentType(MediaType.APPLICATION_JSON)
                        .content(objetoMapper.writeValueAsString(dataPlantilla)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
