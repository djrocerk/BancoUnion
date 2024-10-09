package com.sa.gaei.service;

import com.sa.gaei.entity.dto.ClienteDTO;

public interface IClientService {
    ClienteDTO guardarCliente(ClienteDTO client);
    ClienteDTO obtenerCliente(String tipoDocumento, String numeroDocumento);
    ClienteDTO actualizarCliente(ClienteDTO cliente);

    boolean clienteExiste(String tipoDocumento, String numeroDocumento);
}
