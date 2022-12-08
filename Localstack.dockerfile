FROM maven:3.8.4-openjdk-17 as builder
COPY . ./

RUN mvn clean package -DskipTests -DskipITs -Plocalstack

FROM azul/zulu-openjdk:17

COPY --from=builder target/*.jar app.jar

EXPOSE 4215

ENTRYPOINT ["java", "-jar", "/app.jar"]
