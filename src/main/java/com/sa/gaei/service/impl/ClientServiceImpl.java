package com.sa.gaei.service.impl;

import com.sa.gaei.entity.Cliente;
import com.sa.gaei.entity.dto.ClienteDTO;
import com.sa.gaei.repository.ClientRepository;
import com.sa.gaei.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ModelMapper modelMapper, ClientRepository clientRepository) {
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClienteDTO guardarCliente(ClienteDTO client) {
        Cliente cliente = clientRepository.save(modelMapper.map(client, Cliente.class));
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public ClienteDTO obtenerCliente(String tipoDocumento, String numeroDocumento) {
        Cliente cliente = clientRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento , numeroDocumento);
        if(cliente == null){
            return null;
        }
        return modelMapper.map(cliente, ClienteDTO.class);

    }

    @Override
    public ClienteDTO actualizarCliente(ClienteDTO cliente) {
        Cliente client = clientRepository.save(modelMapper.map(cliente, Cliente.class));
        return modelMapper.map(client, ClienteDTO.class);
    }

    @Override
    public boolean clienteExiste(String tipoDocumento, String numeroDocumento) {
        return clientRepository.existsByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
    }
}
