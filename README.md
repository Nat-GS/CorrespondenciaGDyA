# CorrespondenciaGDyA

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
