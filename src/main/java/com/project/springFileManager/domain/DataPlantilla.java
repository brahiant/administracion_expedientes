package com.project.springFileManager.domain;

import lombok.Data;

import java.util.List;

@Data
public class DataPlantilla {

    private String nombreCompleto;
    private String documentoIdentidad;
    private String fechaFirma;
    private String imagen;
    private List<String> listaUrl;
}
