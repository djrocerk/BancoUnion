package com.sa.gaei.repository;

import com.sa.gaei.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, String> {
    boolean existsByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
    Cliente findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}
