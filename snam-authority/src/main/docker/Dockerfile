FROM docker.rainbond.cc/fabric8/java-alpine-openjdk8-jdk
WORKDIR /deployments
COPY snam-authority.jar /deployments/
ENV AB_OFF true
ENV TimeZone=Asia/Shanghai
ENV JAVA_OPTIONS="-Xss256k -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+UseCGroupMemoryLimitForHeap -XX:+HeapDumpOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ENTRYPOINT ./run-java.sh
