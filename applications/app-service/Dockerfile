FROM  artifactory.apps.bancolombia.com/evc/adoptopenjdk/openjdk11-openj9:x86_64-alpine-jre-11.0.12_7_openj9-0.27.0

VOLUME /tmp
# Font packages to be added for java 11
RUN apk update \
 && apk add fontconfig \
 && apk add ttf-dejavu
RUN ln -s /usr/lib/libfontconfig.so.1 /usr/lib/libfontconfig.so && \
    ln -s /lib/libuuid.so.1 /usr/lib/libuuid.so.1 && \
    ln -s /lib/libc.musl-x86_64.so.1 /usr/lib/libc.musl-x86_64.so.1
ENV LD_LIBRARY_PATH /usr/lib

ADD ./src/main/resources/demo.h2.db  /tmp/demo.h2.db

EXPOSE 8080

ADD ./build/libs/poc_servicio_autorizacion.jar /usr/local
ENTRYPOINT ["java","-jar","/usr/local/poc_servicio_autorizacion.jar", "-Dspring.profiles.active=dev", "-g","daemon off","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]