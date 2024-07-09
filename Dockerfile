FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

# Adicionando Flyway CLI
RUN apt-get install -y wget
RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/8.0.0/flyway-commandline-8.0.0-linux-x64.tar.gz | tar xvz -C /usr/local
ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

# Adicionando driver JDBC do PostgreSQL
RUN apt-get install -y libpostgresql-jdbc-java

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/vitalManager-0.0.1-SNAPSHOT.jar app.jar

# Copiando Flyway CLI e driver JDBC
COPY --from=build /usr/local/flyway-8.0.0 /usr/local/flyway-8.0.0
COPY --from=build /usr/share/java/postgresql-jdbc.jar /usr/share/java/postgresql-jdbc.jar

ENV PATH="/usr/local/flyway-8.0.0:${PATH}"

ENTRYPOINT [ "java", "-jar", "app.jar"]

# Adicionando comando para rodar migrações Flyway (opcional)
# RUN flyway -url=jdbc:postgresql://localhost:5432/mydb -user=myuser -password=mypassword migrate
