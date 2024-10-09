package com.sa.gaei.controller;


import com.sa.gaei.Util.ValidadorCampos;
import com.sa.gaei.config.ApiErrorResponse;
import com.sa.gaei.config.ApiResponse;
import com.sa.gaei.entity.dto.ClienteDTO;
import com.sa.gaei.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final IClientService iClientService;


    public ClientController(IClientService iClientService) {
        this.iClientService = iClientService;
    }

    @PostMapping("/guardarCliente")
    public ResponseEntity<?> guardarCliente(@RequestBody ClienteDTO cliente) {
        String idTx = cliente.getIdTx();

        if (iClientService.clienteExiste(cliente.getTipoDocumento(), cliente.getNumeroDocumento())) {
            String errorMsg = "Cliente " + cliente.getTipoDocumento() + " " + cliente.getNumeroDocumento() + " ya se encuentra registrado.";
            logger.error("Cliente ya registrado: {}", idTx);
            ApiErrorResponse errorResponse = new ApiErrorResponse(idTx, errorMsg);
            return ResponseEntity.status(400).body(errorResponse);
        }

        String errores = ValidadorCampos.validarCampos(cliente);
        if (!errores.isEmpty()) {
            logger.error("Errores de validación: {}", errores);
            ApiErrorResponse errorResponse = new ApiErrorResponse(idTx, errores);
            return ResponseEntity.status(400).body(errorResponse);
        }

        iClientService.guardarCliente(cliente);
        logger.info("Cliente registrado con éxito: {}", cliente.getNumeroDocumento());

        ApiResponse response = new ApiResponse(idTx, "Cliente " + cliente.getNumeroDocumento() + " almacenado de forma exitosa.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultarCliente/{tipoDocumento}_{numeroDocumento}")
    public ResponseEntity<?> consultarCliente(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento) {
        ClienteDTO cliente = iClientService.obtenerCliente(tipoDocumento, numeroDocumento);

        if (cliente == null) {
            logger.error("Cliente no encontrado: {} {}", tipoDocumento, numeroDocumento);
            ApiErrorResponse errorResponse = new ApiErrorResponse(null, "Cliente " + tipoDocumento + " " + numeroDocumento + " no se encuentra registrado.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        logger.info("Cliente encontrado: {} {}", tipoDocumento, numeroDocumento);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/actualizarCliente")
    public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO cliente) {
        String idTx = cliente.getIdTx();

        if (!iClientService.clienteExiste(cliente.getTipoDocumento(), cliente.getNumeroDocumento())) {
            logger.error("Cliente no encontrado: {}", idTx);
            ApiErrorResponse errorResponse = new ApiErrorResponse(idTx, "Cliente " + cliente.getTipoDocumento() + " " + cliente.getNumeroDocumento() + " no se encuentra registrado.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String errores = ValidadorCampos.validarCampos(cliente);
        if (!errores.isEmpty()) {
            logger.error("Errores de validación: {}", errores);
            ApiErrorResponse errorResponse = new ApiErrorResponse(idTx, errores);
            return ResponseEntity.status(400).body(errorResponse);
        }

        iClientService.actualizarCliente(cliente);
        logger.info("Cliente actualizado con éxito: {}", cliente.getNumeroDocumento());

        ApiResponse response = new ApiResponse(idTx, "Cliente " + cliente.getNumeroDocumento() + " actualizado de forma exitosa.");
        return ResponseEntity.ok(response);
    }

}
