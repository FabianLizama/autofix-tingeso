# Pasos Docker
## Generar imagen backend:
- Crear imagen
```
docker build -t fabianlizama/spring-image .
```
- Subir imagen a la nube
```
docker push fabianlizama/spring-image
```

## Crear contenedor db:
- Crear contenedor
```
docker run --name mysql-container -p 3307:3306 -e MYSQL_ROOT_PASSWORD=3220mA++ --network ge-network -v mysql-volume:/var/lib/mysql -d mysql
```

```
mysql -u root -p
create database autofix;
```

## Crear contenedor backend:

- Crear network
```
docker network create ge-network
```

- Crear volumen
```
docker volume create mysql-volume
```

```
docker run --name spring-container -p 8080:8080 -e DB_HOST=mysql-container -e MYSQL_PASSWORD=password --network ge-network -d fabianlizama/spring-image
```