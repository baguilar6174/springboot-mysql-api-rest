# Springboot API Rest

Este repositorio contiene una api rest creada con Spring Boot y Mysql y persistencia de datos con JPA

## Tecnologías

* Spring Boot
* [Spring Tool Suit 4](https://spring.io/tools)
* Mysql en su versión 8

## Características

- La api rest presenta servicios de un CRUD
- La interacción con la BD esta conformada con dos tablas: Product y Presentation, con una relación de muchos a uno y de uno a muchos respectivamente.
- Contiene una estructura bien definida:
  - La capa de entidades (entity) para mapear las clases a tablas de la BD
  - La capa de acceso a datos (dao) con interfaces para definir consultas sql con el repositorio de JpaRepository o consultas personalizadas
  - La capa de servicios (service) con interfaces para implementar los métodos de consulta de datos
  - La capa de controladores (controller) con clases que implementas los métodos de acceso a datos (mapero de solicitudes HTTP) mediante inyección de dependencias de las interfaces de servicios.
- Las solicitudes están correctamente validadas con mensajes personalizados y códigos HTTP.


## Instalación y uso

Paso 1:

Necesita poseer el SDK de java (He usado la versión 8) y el entorno de desarrollo Spring Tool Suit

Paso 2:

Clone o descargue el proyecto con el siguiente comando

```
git clone https://github.com/bryanAguilar001/springboot-mysql-api-rest.git
```

Paso 3:

Abra y ejecute el projecto desde el entorno Spring Tool Suit (la primera vez, descargará las dependencias necesarias de maven)

Paso 4:

Modifique el archivo `src/main/resources/application.properties`

- Debe establecer los parámetros de conexión a la BD Mysql
- Establezca la ip y el puerto de conexión configure el nombre la BD a la cuál accederá (esta BD debe haber sido creada previamente en Mysql)
- Si desea establezca valores de Zona Horaria
- Establezca la contraseña y usuario de su BD Mysql

Paso 5:

Ejecute el servidor local de Spring (por defecto en el puerto 8080).

Paso 6:

Verifique que la conexión a la BD fue exitosa (asegúrese de que cuando se ejecute el servidor de Spring las tablas en la BD se creen de manera automática)

Paso 7:

Para poder ejecutar peticiones a la BD, ejecute estas sentencias SQL en la base de datos creada

```
INSERT INTO `presentation` VALUES (1,NULL,'unidad'),(2,NULL,'docena');
```

```
INSERT INTO `product` VALUES
  (1,NULL,'rezma de papel',3.75,10,1),
  (2,NULL,'cartas',1,10,2),
  (3,NULL,'guitarra de juguete',4.5,5,1),
  (4,NULL,'teclado para computadora',15,5,1),
  (5,NULL,'teclado para laptop',40,5,1),
  (6,NULL,'bocinas bluetooth',15,5,1),
  (7,NULL,'lapices 2b',1.50,4,2),
  (8,NULL,'plumas color azul',2,10,2),
  (9,NULL,'monitor dell 15p',40,5,1),
  (10,NULL,'cargador samsung',10,10,1),
  (11,NULL,'mouse básico',5,5,1);
```

Ya que se ha mapeado una relación de dependencia entre dos tablas, no se podrán agregar, eliminar o actualizar Products sin que existan registros de Presentation previamente.


Paso 8:

Si todo el proceso fue correcto, podrá hacer peticiones a la API Rest con algún cliente como Postman. Encontrará un ejemplo con las peticiones HTTP en el fichero `ProductController.java`


(GET) Obtener todos los productos de la BD

```
http://localhost:8080/products
```

(GET) Obtener todos los productos de la BD con paginación 

```
http://localhost:8080/products?page=0&size=5
```

(GET) Obtener un producto específico (por id)

```
http://localhost:8080/products/1
```

(POST) Agregar un nuevo producto

```
http://localhost:8080/products
```

Body de solicitud

```
{
    "name": "producto",
    "description": "descripcion",
    "price": 69.0,
    "stock": 1,
    "presentation": {
        "id": 1,
        "name": "unidad",
        "description": null
    }
}
```


(PUT) Modificar un producto

```
http://localhost:8080/products/1
```

Body de solicitud

```
{
    "name": "nuevo producto",
    "description": "nueva descripcion",
    "price": 69.0,
    "stock": 1,
    "presentation": {
        "id": 1,
        "name": "unidad",
        "description": null
    }
}
```



## Author

- Website - [bryanaguilar](https://www.bryan-aguilar.com/)
- Medium - [bryanaguilar6174](https://bryanaguilar6174.medium.com/)
- LinkeIn - [bryanaguilar6174](https://www.linkedin.com/in/bryanaguilar6174)

## Estado del proyecto

Actualmente este proyecto muestra una implementación un tanto básica de una API Rest, sin embargo, puede servir como punto de partida para proyectos más grandes.
