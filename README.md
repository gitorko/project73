# Project 73

Spring Events

[https://gitorko.github.io/spring-events/](https://gitorko.github.io/spring-events/)

### Version

Check version

```bash
$java --version
openjdk 21.0.3 2024-04-16 LTS
```

### Modulith Documentation

```bash
brew install graphviz
```

### Postgres DB

```bash
docker run -p 5432:5432 --name pg-container -e POSTGRES_PASSWORD=password -d postgres:14
docker ps
docker exec -it pg-container psql -U postgres -W postgres
CREATE USER test WITH PASSWORD 'test@123';
CREATE DATABASE "test-db" WITH OWNER "test" ENCODING UTF8 TEMPLATE template0;
grant all PRIVILEGES ON DATABASE "test-db" to test;

docker stop pg-container
docker start pg-container
```

### RabbitMQ

Run the docker command to start a rabbitmq instance

```bash
docker run -d --hostname my-rabbit --name my-rabbit -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest -p 8085:15672 -p 5672:5672 rabbitmq:3-management
```

Open the rabbitmq console

[http://localhost:8085](http://localhost:8085)

```
user:guest
pwd: guest
```

### Dev

To run the code.

```bash
./gradlew clean build
./gradlew bootRun
```
