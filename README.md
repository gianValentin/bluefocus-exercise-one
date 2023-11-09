## Levantar proyecto

Priemro, importar como proyecto de maven en algunos de los siguientes IDE para ver el codigo fuente:

```bash
Eclipce IDE
# or
Intellij IDE
# or
Visual Studio Code
```

Segundo Importar el archivo `postman_collection` como colleccion  de Postman.

## pruebas funcionales desde Postman

Dentro de la colleccion de postman encontraremos 3 folders `Auth-JWT`, `currency` y `transaction`.

 - Auth-JWT contiene las peticiones de autenticación.
 - Currency contine las peticiones que devuelven los tipos de cambios.
 - Transaction contine las peticiones para realizar cambios de moneda.

 ### Auth-JWT

 En el folder Auth-JWT encontramos las siguientes peticiones:

 - `Register` es la petición para el registro de usuarios, el cual requiere los llenar los siguientes atributos.
 ```json
{
    "username": "nombre de usuario",
    "password": "contraseña",
    "firstname": "primer nombre",
    "lastname": "apellido",
    "email": "correo"
}
```
 - `Login` es la petición para el inicio de sesión de usuario, el cual requiere los llenar los siguientes atributos.
 ```json
{
    "username": "nombre de usaurio",
    "password": "contraseña"
}
```

 ### Currency

 En el folder Currency encontramos las siguientes peticiones:

 - `findAll` es la petición que devuelve todos los tipos de cambios disponible en el sistema.

 ### Transaction

 En el folder Transaction encontramos las siguientes peticiones:

 - `Generate` es la petición para el registro de cambio de moneda, el cual requiere los llenar los siguientes atributos:
    - amount: monto a cambia
    - originCurrency: moneda origen
    - fateCurrency: moneda destino
 ```json
{
    "amount": 3.729,
    "originCurrency": {
        "id": 1,
        "name": "PEN",
        "value": 3.79
    },
    "fateCurrency": {
        "id": 3,
        "name": "USD",
        "value": 1.0
    }
}
```
 - `update` es la petición para el registro de cambio de moneda, el cual requiere los llenar los siguientes atributos:
    - Path Variable `id`: requiere en el path variable el id de la transaccion para actualizar
    - amount: monto a cambia
    - originCurrency: moneda origen
    - fateCurrency: moneda destino
 ```json
{
    "amount": 3.729,
    "originCurrency": {
        "id": 1,
        "name": "PEN",
        "value": 3.79
    },
    "fateCurrency": {
        "id": 3,
        "name": "USD",
        "value": 1.0
    }
}
```
 - `findById` es la petición para buscar la transacción.
 - `audit` es la petición para buscar todas transacción realizadas.