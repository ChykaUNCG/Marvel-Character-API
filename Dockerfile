FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

COPY src ./src

RUN ./mvnw clean package -DskipTests

RUN JAR_FILE=$(find target -maxdepth 1 -name "*.jar" ! -name "*.original" | head -n 1) \
    && cp "$JAR_FILE" app.jar

FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]