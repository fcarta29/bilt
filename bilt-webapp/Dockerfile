FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/bilt.war app.war
RUN sh -c 'touch /app.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
