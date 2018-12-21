# base image : openjdk-8
FROM openjdk:8

# copy jar file to /test directory
COPY test_rest-0.0.1.jar /test/

# set working directory to /test
WORKDIR /test

# run jar file
ENTRYPOINT ["java", "-jar", "test_rest-0.0.1.jar"]
