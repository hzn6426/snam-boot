FROM fabric8/java-alpine-openjdk8-jdk
VOLUME /tmp
ADD snam-gateway.jar snam-gateway.jar
ENV TimeZone=Asia/Shanghai
ENV JAVA_OPTIONS="-Xss256k -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+UseCGroupMemoryLimitForHeap -XX:+HeapDumpOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/snam-gateway.jar"]
