# API Calculadora con Java Spring Boot 3.4.4

Esta es una API RESTful desarrollada con Spring Boot para realizar operaciones matematicas básicas (sumar, restar, multiplicar y dividir).

## Descripción:

La API permite a los usuarios realizar sumas, restas, multiplicaciones y divisiones. También almacena el historial de operaciones y los resultados.

## Endpoints

* **`POST /calculadora/calcular`**: Realiza un cálculo.
    * Cuerpo/Body de la Solicitud (JSON):
        ```json
        {
            "num1": 10,
            "num2": 5,
            "operation": 1  // 1: Suma, 2: Resta, 3: Multiplicación, 4: División
        }
        ```
    * Respuesta: `201 Created` en caso de éxito, `400 Bad Request` en caso de entrada inválida.
* **`GET /calculadora/operaciones`**: Recupera todas las operaciones realizadas desde la base de datos.
    * Respuesta: `200 OK` con una lista JSON.
* **`GET /calculadora/resultados`**: Recupera todos los resultados de cálculo.
    * Respuesta: `200 OK` con una lista de objetos de resultado registrados desde la base de datos.
* **`DELETE /calculadora/operacion/{id}`**: Elimina una operación de la base de datos segun su ID.
    * Respuesta: `204 No Content`.
* **`DELETE /calculadora/resultado/{id}`**: Elimina un resultado de la base de datos segun su ID.
    * Respuesta: `204 No Content`.

### Especificaciones generales

* Java 17 o superior
* Maven
* Base de datos PostgreSQL configurada en archivo `application.properties`
