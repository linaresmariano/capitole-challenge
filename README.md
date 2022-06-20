# Capitole Inditex Interview

## Tests

En la clase **InterviewApplicationTest.java** se prueban los casos pedidos en la consigna

* Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
* Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
* Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
* Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
* Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


Para el escenario planteado, me resultó interesante probar los siguientes casos borde en **PriceRepositoryTest.java**

* antes del 13/6/20 a las 23:59 y despues del 1/1/21 a las 00, no hay precio
* el 14/6 entre las 00 a las 14:59, el precio es 35.50 porque aplica el id 1
* el 14/6 entre las 15 y las 18:30, el precio es 25.45 porque aplica el id 2
* del 14 a las 18:30:01 hasta el 14 a las 23:59, el precio es 35.50 porque aplica el id 1
* del 15 a las 00 hasta el 15 a las 11, el precio es 30.50 porque aplica el id 3
* del 15 a las 11:01 hasta el 15 a las 15:59, el precio es 35.50 porque aplica el id 1
* del 15 a las 16 hasta el 31/12 a las 23:59, el precio es 38.95 porque aplica el id 4



## Pruebas funcionales

[Colección postman](https://www.getpostman.com/collections/681b60d69f842c4dcd05) para probar los endpoints principales

### Checkear status del servicio

* **Home**: `GET /ms-pricing/`
* **Ping**: `GET /ms-pricing/ping`


### Buscar un precio específico

* **Find One** : `GET /ms-pricing/prices/:id`



## Endpoint principal

### Buscar precio vigente

* **Valid Price** : `GET /ms-pricing/prices/valid`

### Parámetros requeridos:

* **applicationDate:** fecha en formate ISO (ej: 2020-06-14T15:00:00)
* **productId:** ID del producto
* **brandId:** ID de la cadena

### Respuestas esperadas:

* **200 - OK**: Cuando se encuentra un Precio vigente
* **204 - No Content**: Cuando NO se encuentra un Precio vigente
* **400 - Bad Request**: Cuando hay parámetros incorrectos
* **500 - Internal Server Error**: Ante cualquier otro error inesperado


	