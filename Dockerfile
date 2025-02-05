FROM gradle:8.5-jdk17 AS builder
WORKDIR /build
COPY . .

# gradlew에 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 캐시 정리
RUN gradle clean
# Gradle Wrapper를 사용하여 빌드
RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim
WORKDIR /app

ENV TZ=Asia/Seoul

# 빌드 결과물 복사
COPY --from=builder /build/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", \
            "-jar", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=prod", \
            "app.jar"]