
# Proyecto de Microservicio para Gestión de Clientes - GAEI S.A.

Este proyecto es un microservicio desarrollado para la empresa financiera GAEI S.A. que gestiona el registro, consulta y actualización de clientes, siguiendo las mejores prácticas de desarrollo con **Spring Boot**.

## Características del Proyecto

Este microservicio ofrece las siguientes funcionalidades principales:
- **Registrar Clientes**: Almacena nuevos clientes en la base de datos.
- **Consultar Clientes**: Recupera la información de clientes mediante el número y tipo de documento.
- **Actualizar Clientes**: Permite modificar la información de un cliente existente.
- **Validación**: Validación de datos obligatorios y verificación del formato del correo electrónico.

### Tecnologías Utilizadas

- **Java 1.8**
- **Spring Boot 2.7.18**
- **H2 Database** (Base de datos en memoria para simplicidad)
- **Log4j2** para registro de logs en archivos
- **Maven** como herramienta de gestión de dependencias
- **JUnit5** para las pruebas unitarias

### Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener instalado lo siguiente:

- **Java 1.8** o superior
- **Maven 3.6.3** o superior

### Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/nombre-del-repositorio.git
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd nombre-del-repositorio
   ```
3. Compila el proyecto y descarga las dependencias:
   ```bash
   mvn clean install
   ```

### Ejecución del Proyecto

Puedes ejecutar el proyecto de Spring Boot utilizando el siguiente comando:
```bash
mvn spring-boot:run
```

Esto iniciará el microservicio en el puerto **8443**.

Para acceder a la consola H2, visita:
```
http://localhost:8443/h2-console
```

### Endpoints

#### 1. Registrar Cliente (POST `/guardarCliente`)
- **Descripción**: Guarda un nuevo cliente en la base de datos.
- **Body**:
    ```json
    {
        "idTx": "123rfdr232323232",
        "tipoDocumento": "CC",
        "numeroDocumento": "1234567890",
        "primerNombre": "Pepito",
        "segundoNombre": "Ramiro",
        "primerApellido": "Perez",
        "segundoApellido": "Gomez",
        "teléfono": 12345678,
        "correElectronico": "pepito.perez@hotmail.com"
    }
    ```
- **Response de éxito**:
    ```json
    {
        "idTx": "123rfdr232323232",
        "mensaje": "Cliente 1234567890 almacenado de forma exitosa."
    }
    ```
- **Response de error** (Ejemplo: campos faltantes):
    ```json
    {
        "idTx": "123rfdr232323232",
        "error": "Campos tipoDocumento, numeroDocumento son obligatorios."
    }
    ```

#### 2. Consultar Cliente (GET `/consultarCliente/{tipoDocumento}_{numeroDocumento}`)
- **Descripción**: Consulta los datos de un cliente registrado.
- **URL de ejemplo**: `/consultarCliente/CC_1234567890`
- **Response de éxito**:
    ```json
    {
        "tipoDocumento": "CC",
        "numeroDocumento": "1234567890",
        "primerNombre": "Pepito",
        "segundoNombre": "Ramiro",
        "primerApellido": "Perez",
        "segundoApellido": "Gomez",
        "teléfono": 12345678,
        "correElectronico": "pepito.perez@hotmail.com"
    }
    ```
- **Response de error**:
    ```json
    {
        "error": "Cliente CC 1234567890 no se encuentra registrado."
    }
    ```

#### 3. Actualizar Cliente (POST `/actualizarCliente`)
- **Descripción**: Actualiza los datos de un cliente existente.
- **Body**:
    ```json
    {
        "idTx": "123rfdr232323232",
        "tipoDocumento": "CC",
        "numeroDocumento": "1234567890",
        "primerNombre": "Pepito",
        "segundoNombre": "Ramiro",
        "primerApellido": "Perez",
        "segundoApellido": "Gomez",
        "teléfono": 12345678,
        "correElectronico": "pepito.perez@hotmail.com"
    }
    ```
- **Response de éxito**:
    ```json
    {
        "idTx": "123rfdr232323232",
        "mensaje": "Cliente 1234567890 actualizado de forma exitosa."
    }
    ```
- **Response de error** (Ejemplo: cliente no encontrado):
    ```json
    {
        "idTx": "123rfdr232323232",
        "error": "Cliente CC 1234567890 no se encuentra registrado."
    }
    ```

### Manejo de Excepciones

Este proyecto implementa un `GlobalExceptionHandler` que maneja todas las excepciones de forma centralizada y devuelve mensajes de error adecuados con código de estado HTTP 400 o 500 según el caso.

### Pruebas Unitarias

Las pruebas unitarias fueron implementadas usando **JUnit5**. Para ejecutarlas, usa el comando:
```bash
mvn test
```

### Configuración de Logs

Los logs se generan usando **Log4j2** y se guardan en la ruta `D://opt//logs/`. Puedes personalizar esta ruta modificando el archivo de configuración de Log4j2 ubicado en `src/main/resources/log4j2.properties`.
