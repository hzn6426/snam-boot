FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD snam-authority.jar snam-authority.jar
ENV TimeZone=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/snam-authority.jar"]
