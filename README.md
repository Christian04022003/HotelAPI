---

# Hotel Reservation API

## Descripción

La API de Hotel Reservation es un sistema de gestión de reservas de hotel que permite a los usuarios crear, leer, actualizar y eliminar reservas. La API está construida utilizando Spring Boot y utiliza MongoDB como base de datos NoSQL para almacenar los datos de las reservas.

## Características

- Crear nuevas reservas.
- Obtener todas las reservas.
- Obtener una reserva por su ID.
- Actualizar una reserva existente.
- Eliminar una reserva.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- JUnit 5
- Mockito
- Spring REST Docs

## Instalación

### Prerrequisitos

- Java 11
- Maven
- MongoDB

### Pasos

1. Clona el repositorio:

   ```bash
   git clone https://github.com/Christian04022003/HotelAPI.git
   cd hotel_reservation
   ```

2. Configura MongoDB:
   
   Asegúrate de tener MongoDB ejecutándose en `localhost:27017` o modifica la configuración en `application.properties` según tu entorno.

3. Compila y ejecuta la aplicación:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints

### Obtener todas las reservas

```http
GET /api/reservations
```

#### Respuesta

```json
[
  {
    "id": "1",
    "customerName": "John Doe",
    "customerEmail": "john.doe@example.com",
    "startDate": "2024-06-20T12:00:00Z",
    "endDate": "2024-06-21T12:00:00Z",
    "roomType": "Single"
  }
]
```

### Crear una nueva reserva

```http
POST /api/reservations
```

#### Solicitud

```json
{
  "customerName": "Jane Smith",
  "customerEmail": "jane.smith@example.com",
  "startDate": "2024-06-20T12:00:00Z",
  "endDate": "2024-06-21T12:00:00Z",
  "roomType": "Double"
}
```

#### Respuesta

```json
{
  "id": "2",
  "customerName": "Jane Smith",
  "customerEmail": "jane.smith@example.com",
  "startDate": "2024-06-20T12:00:00Z",
  "endDate": "2024-06-21T12:00:00Z",
  "roomType": "Double"
}
```

### Obtener una reserva por ID

```http
GET /api/reservations/{id}
```

#### Respuesta

```json
{
  "id": "1",
  "customerName": "John Doe",
  "customerEmail": "john.doe@example.com",
  "startDate": "2024-06-20T12:00:00Z",
  "endDate": "2024-06-21T12:00:00Z",
  "roomType": "Single"
}
```

### Actualizar una reserva

```http
PUT /api/reservations/{id}
```

#### Solicitud

```json
{
  "customerName": "Jane Smith",
  "customerEmail": "jane.smith@example.com",
  "startDate": "2024-06-21T12:00:00Z",
  "endDate": "2024-06-22T12:00:00Z",
  "roomType": "Double"
}
```

#### Respuesta

```json
{
  "id": "1",
  "customerName": "Jane Smith",
  "customerEmail": "jane.smith@example.com",
  "startDate": "2024-06-21T12:00:00Z",
  "endDate": "2024-06-22T12:00:00Z",
  "roomType": "Double"
}
```

### Eliminar una reserva

```http
DELETE /api/reservations/{id}
```

## Pruebas

Las pruebas unitarias se han implementado utilizando JUnit 5 y Mockito. Para ejecutar las pruebas, utiliza el siguiente comando:

```bash
mvn test
```

## Documentación

La documentación de la API se ha generado utilizando Spring REST Docs. Para generar la documentación, ejecuta el siguiente comando:

```bash
mvn clean install
```

La documentación generada se encontrará en `target/generated-snippets`.

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y haz commits (`git commit -m 'Añadir nueva funcionalidad'`).
4. Empuja los cambios a tu repositorio (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
