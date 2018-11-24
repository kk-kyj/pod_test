FROM openjdk:8

COPY text_read-0.0.1.jar /test/

#RUN chmod 755 /test/text_read-0.0.1.jar

WORKDIR /test
ENTRYPOINT ["java", "-jar", "text_read-0.0.1.jar"]
