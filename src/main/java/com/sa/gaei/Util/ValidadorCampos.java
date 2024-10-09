package com.sa.gaei.Util;

import com.sa.gaei.entity.dto.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCampos {

    public static String validarCampos(ClienteDTO cliente) {
        StringBuilder errores = new StringBuilder();

        if (cliente.getTipoDocumento() == null || cliente.getTipoDocumento().isEmpty()) {
            errores.append("Campos tipoDocumento. Son obligatorios. ");
        }
        if (cliente.getNumeroDocumento() == null || cliente.getNumeroDocumento().isEmpty()) {
            errores.append("Campos numeroDocumento. Son obligatorios. ");
        }

        if (cliente.getCorreoElectronico() == null || cliente.getCorreoElectronico().isEmpty()) {
            errores.append("Campos Correo Electronico. Son obligatorios. ");
        }else{
            if (!cliente.getCorreoElectronico().matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$")) {
                errores.append("Campo correoElectronico no cumple con la estructura de un correo electrónico válido. ");
            }
        }

        return errores.toString();
    }
}
