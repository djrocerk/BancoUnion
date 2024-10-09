CREATE TABLE Cliente
(
    idTx              VARCHAR(36) PRIMARY KEY,
    tipoDocumento     VARCHAR(3),
    numeroDocumento   VARCHAR(15),
    primerNombre      VARCHAR(50),
    segundoNombre     VARCHAR(50),
    primerApellido    VARCHAR(50),
    segundoApellido   VARCHAR(50),
    telefono          VARCHAR(15),
    correoElectronico VARCHAR(50)
);
