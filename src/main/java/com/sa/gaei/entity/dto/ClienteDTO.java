package com.sa.gaei.entity.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String idTx;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Integer telefono;
    private String correoElectronico;
}
