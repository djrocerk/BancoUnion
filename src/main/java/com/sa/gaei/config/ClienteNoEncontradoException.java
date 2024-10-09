package com.sa.gaei.config;

public class ClienteNoEncontradoException extends RuntimeException{
    private String idTx;

    public ClienteNoEncontradoException(String idTx, String message) {
        super(message);
        this.idTx = idTx;
    }

    public String getIdTx() {
        return idTx;
    }
}
