package com.sa.gaei.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiErrorResponse {

    private String idTx;
    private String error;

    public ApiErrorResponse(String idTx, String error) {
        this.idTx = idTx;
        this.error = error;
    }
}
