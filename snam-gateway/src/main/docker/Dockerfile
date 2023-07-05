FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD snam-gateway.jar snam-gateway.jar
ENV TimeZone=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/snam-gateway.jar"]
