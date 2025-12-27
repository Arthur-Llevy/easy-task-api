FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean verify -DskipITs

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

RUN addgroup --system appgroup && \
    adduser --system --ingroup appgroup appuser && \
    chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

HEALTHCHECK CMD wget --spider -q http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]