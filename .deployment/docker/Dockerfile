FROM adoptopenjdk/openjdk11

RUN mkdir /opt/app
ADD target/CursosAlternativos.jar /opt/app/CursosAlternativos.jar
EXPOSE 8080
EXPOSE 8000
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-Dspring.profiles.active=docker", "-jar", "/opt/app/CursosAlternativos.jar"]
