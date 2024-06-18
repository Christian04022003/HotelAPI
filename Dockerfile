# Usar la imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en el directorio de destino
COPY target/hotel-reservation-0.0.1-SNAPSHOT.jar hotel-reservation.jar

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "hotel-reservation.jar"]
