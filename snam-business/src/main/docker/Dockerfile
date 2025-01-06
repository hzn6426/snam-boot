FROM docker.rainbond.cc/fabric8/java-alpine-openjdk8-jdk:1.9.0
WORKDIR /deployments
COPY snam-business.jar /deployments/
ENV AB_OFF true
ENV TimeZone=Asia/Shanghai
ENV JAVA_OPTIONS="-Xss256k -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+UseCGroupMemoryLimitForHeap -XX:+HeapDumpOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
#RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
#RUN apk update
#RUN apk add --update ttf-dejavu fontconfig
#COPY ./simsun.ttf /usr/share/fonts/
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ENTRYPOINT ./run-java.sh
