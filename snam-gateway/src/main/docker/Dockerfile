FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD snam-gateway.jar snam-gateway.jar
ENV TimeZone=Asia/Shanghai
RUN apk update \
    && apk add tzdata \
    && echo "${TimeZone}" > /etc/timezone \
    && ln -sf /usr/share/zoneinfo/${TimeZone} /etc/localtime \
    && rm /var/cache/apk/*
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/snam-gateway.jar"]
