FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT [ "java", "-cp", "app:app/lib/*", "br.com.sabrina.aws_project01.AwsProject01Application" ]