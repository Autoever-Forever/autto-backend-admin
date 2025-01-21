FROM gradle:8.5-jdk17 AS builder
WORKDIR /build
COPY . .

# Gradle 캐시 정리
RUN gradle clean

# Gradle Wrapper를 사용하여 빌드
RUN ./gradlew build --no-daemon -x test

FROM temurin:17-jre
WORKDIR /app

# 타임존 설정
RUN apk add --no-cache tzdata
ENV TZ=Asia/Seoul

# 보안을 위한 비root 유저 생성
RUN addgroup -S spring && adduser -S spring -G spring

# 빌드 결과물 복사
COPY --from=builder /build/build/libs/*.jar app.jar
RUN chown spring:spring app.jar

USER spring

EXPOSE 8080
EXPOSE 8084

ENTRYPOINT ["java", \
            "-jar", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=prod", \
            "app.jar"] 