# Utilizar la imagen oficial de PostgreSQL
FROM postgres:15.4

# Configurar variables de entorno para PostgreSQL
ENV POSTGRES_DB=correspondencia
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234

# Copiar script SQL para inicializar la base de datos
COPY init.sql /docker-entrypoint-initdb.d/

