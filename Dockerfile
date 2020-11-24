FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAVA_OPTS
ARG IMAGE_VERSION

ENV JAVA_OPTS=$JAVA_OPTS
ENV IMAGE_VERSION=$IMAGE_VERSION

COPY ./entrypoint.sh /usr/local/bin/
COPY ./build/libs/spike-app-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
