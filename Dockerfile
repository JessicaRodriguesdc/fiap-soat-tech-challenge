FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
WORKDIR build
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21.0.4_7-jre-alpine AS layers
WORKDIR layer
COPY --from=builder /build/target/tech-challenge-0.0.1-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:21.0.4_7-jre-alpine
WORKDIR /opt/app
RUN addgroup --system appuser && adduser -S -s /usr/sbin/nologin -G appuser appuser
COPY --from=layers /layer/dependencies/ ./
COPY --from=layers /layer/spring-boot-loader/ ./
COPY --from=layers /layer/snapshot-dependencies/ ./
COPY --from=layers /layer/application/ ./
RUN chown -R appuser:appuser /opt/app
USER appuser
EXPOSE 8357
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]