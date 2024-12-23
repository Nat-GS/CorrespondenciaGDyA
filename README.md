# CorrespondenciaGDyA
## Especificaciones del sistema
Este sistema fue desarrollado con:
Frontend: NextJs utilizando node v18.18.0
Backend: Spring-boot java
BD: Postgresql

## Funciones del sistema
1. Inicio de sesión con manejo de JWT
2. Manejo de token refresh (7 días)
3. Registro de nuevos usuarios, con envio de correo electrónico
4. Subida de documentos
5. Registro de documentos enviados
6. Monitoreo de documentos
7. Observaciones de documentos
8. Registro de documentos revisados

## Imagenes del sistema
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-44-30.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-44-48.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-44-52.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-45-02.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-45-06.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-45-10.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-45-20.png"> 
<img style="width:200px" src="./documents/Captura desde 2024-12-23 11-45-14.png"> 


## Video muestra del sistema
<video width="100%" controls>
  <source src="./documents/VideoCorrespondencia.webm" type="video/webm">
  Tu navegador no soporta el elemento de video.
</video>

## Base de datos
### Diseño de base de datos
<img src="./documents/CorrespondenciaBD_V1.png"> 

### Inicializar BD

1. Construir una imagen en base al documento docker, ubicado en el directorio "docerAndDataBase"

```
docker build -t img-correspondencia ./docker_and_data_base
```

2. Crear un contenedor en base a la imagen

```
docker run -d -p 5434:5432 --name cont-correspondencia img-correspondencia
```

3. Conectarse a la base de datos

```
docker exec -it cont-correspondencia psql -U postgres -d correspondencia
```

4. Ejecución de la RESTful API con spring-boot

```
mvn spring-boot:run
```

La base de datos es creada autimáticamente una vez construida la imagen, así que la aplicación esta
lista para ser utilizada.
