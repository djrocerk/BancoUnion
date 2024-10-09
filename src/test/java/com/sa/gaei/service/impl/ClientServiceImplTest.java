package com.sa.gaei.service.impl;

import com.sa.gaei.entity.Cliente;
import com.sa.gaei.entity.dto.ClienteDTO;
import com.sa.gaei.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private ClientRepository mockClientRepository;

    private ClientServiceImpl clientServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        clientServiceImplUnderTest = new ClientServiceImpl(mockModelMapper, mockClientRepository);
    }

    @Test
    void testGuardarCliente() {

        final ClienteDTO client = new ClienteDTO();
        client.setIdTx("idTx");
        client.setTipoDocumento("tipoDocumento");
        client.setNumeroDocumento("numeroDocumento");
        client.setPrimerNombre("primerNombre");
        client.setSegundoNombre("segundoNombre");

        final ClienteDTO expectedResult = new ClienteDTO();
        expectedResult.setIdTx("idTx");
        expectedResult.setTipoDocumento("tipoDocumento");
        expectedResult.setNumeroDocumento("numeroDocumento");
        expectedResult.setPrimerNombre("primerNombre");
        expectedResult.setSegundoNombre("segundoNombre");

        final Cliente cliente = new Cliente();
        cliente.setIdTx("idTx");
        cliente.setTipoDocumento("tipoDocumento");
        cliente.setNumeroDocumento("numeroDocumento");
        cliente.setPrimerNombre("primerNombre");
        cliente.setSegundoNombre("segundoNombre");

        when(mockModelMapper.map(any(ClienteDTO.class), eq(Cliente.class))).thenReturn(cliente);
        when(mockModelMapper.map(any(Cliente.class), eq(ClienteDTO.class))).thenReturn(expectedResult);

        when(mockClientRepository.save(any(Cliente.class))).thenReturn(cliente);

        final ClienteDTO result = clientServiceImplUnderTest.guardarCliente(client);

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testObtenerCliente() {
        final ClienteDTO expectedResult = new ClienteDTO();
        expectedResult.setIdTx("idTx");
        expectedResult.setTipoDocumento("tipoDocumento");
        expectedResult.setNumeroDocumento("numeroDocumento");
        expectedResult.setPrimerNombre("primerNombre");
        expectedResult.setSegundoNombre("segundoNombre");

        final Cliente cliente = new Cliente();
        cliente.setIdTx("idTx");
        cliente.setTipoDocumento("tipoDocumento");
        cliente.setNumeroDocumento("numeroDocumento");
        cliente.setPrimerNombre("primerNombre");
        cliente.setSegundoNombre("segundoNombre");
        when(mockClientRepository.findByTipoDocumentoAndNumeroDocumento("tipoDocumento", "numeroDocumento"))
                .thenReturn(cliente);

        final ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdTx("idTx");
        clienteDTO.setTipoDocumento("tipoDocumento");
        clienteDTO.setNumeroDocumento("numeroDocumento");
        clienteDTO.setPrimerNombre("primerNombre");
        clienteDTO.setSegundoNombre("segundoNombre");
        final Cliente source = new Cliente();
        source.setIdTx("idTx");
        source.setTipoDocumento("tipoDocumento");
        source.setNumeroDocumento("numeroDocumento");
        source.setPrimerNombre("primerNombre");
        source.setSegundoNombre("segundoNombre");
        when(mockModelMapper.map(source, ClienteDTO.class)).thenReturn(clienteDTO);

        final ClienteDTO result = clientServiceImplUnderTest.obtenerCliente("tipoDocumento", "numeroDocumento");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testObtenerCliente_ClientRepositoryReturnsNull() {
        when(mockClientRepository.findByTipoDocumentoAndNumeroDocumento("tipoDocumento", "numeroDocumento"))
                .thenReturn(null);

        final ClienteDTO result = clientServiceImplUnderTest.obtenerCliente("tipoDocumento", "numeroDocumento");

        assertThat(result).isNull();
    }

    @Test
    void testActualizarCliente() {
        final ClienteDTO cliente = new ClienteDTO();
        cliente.setIdTx("idTx");
        cliente.setTipoDocumento("tipoDocumento");
        cliente.setNumeroDocumento("numeroDocumento");
        cliente.setPrimerNombre("primerNombre");
        cliente.setSegundoNombre("segundoNombre");

        final ClienteDTO expectedResult = new ClienteDTO();
        expectedResult.setIdTx("idTx");
        expectedResult.setTipoDocumento("tipoDocumento");
        expectedResult.setNumeroDocumento("numeroDocumento");
        expectedResult.setPrimerNombre("primerNombre");
        expectedResult.setSegundoNombre("segundoNombre");

        final Cliente cliente1 = new Cliente();
        cliente1.setIdTx("idTx");
        cliente1.setTipoDocumento("tipoDocumento");
        cliente1.setNumeroDocumento("numeroDocumento");
        cliente1.setPrimerNombre("primerNombre");
        cliente1.setSegundoNombre("segundoNombre");
        final ClienteDTO source = new ClienteDTO();
        source.setIdTx("idTx");
        source.setTipoDocumento("tipoDocumento");
        source.setNumeroDocumento("numeroDocumento");
        source.setPrimerNombre("primerNombre");
        source.setSegundoNombre("segundoNombre");
        when(mockModelMapper.map(source, Cliente.class)).thenReturn(cliente1);
        when(mockModelMapper.map(any(Cliente.class), eq(ClienteDTO.class))).thenReturn(expectedResult);

        final Cliente cliente2 = new Cliente();
        cliente2.setIdTx("idTx");
        cliente2.setTipoDocumento("tipoDocumento");
        cliente2.setNumeroDocumento("numeroDocumento");
        cliente2.setPrimerNombre("primerNombre");
        cliente2.setSegundoNombre("segundoNombre");
        final Cliente entity = new Cliente();
        entity.setIdTx("idTx");
        entity.setTipoDocumento("tipoDocumento");
        entity.setNumeroDocumento("numeroDocumento");
        entity.setPrimerNombre("primerNombre");
        entity.setSegundoNombre("segundoNombre");
        when(mockClientRepository.save(entity)).thenReturn(cliente2);

        final ClienteDTO result = clientServiceImplUnderTest.actualizarCliente(cliente);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testClienteExiste() {
        when(mockClientRepository.existsByTipoDocumentoAndNumeroDocumento("tipoDocumento",
                "numeroDocumento")).thenReturn(false);

        final boolean result = clientServiceImplUnderTest.clienteExiste("tipoDocumento", "numeroDocumento");

        assertThat(result).isFalse();
    }

    @Test
    void testClienteExiste_ClientRepositoryReturnsTrue() {
        when(mockClientRepository.existsByTipoDocumentoAndNumeroDocumento("tipoDocumento",
                "numeroDocumento")).thenReturn(true);

        final boolean result = clientServiceImplUnderTest.clienteExiste("tipoDocumento", "numeroDocumento");

        assertThat(result).isTrue();
    }
}
