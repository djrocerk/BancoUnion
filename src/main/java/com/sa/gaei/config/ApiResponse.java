package com.sa.gaei.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String idTx;
    private String mensaje;

    public ApiResponse(String idTx, String mensaje) {
        this.idTx = idTx;
        this.mensaje = mensaje;
    }

}
