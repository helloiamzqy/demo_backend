FROM java:latest

ENV PATH $PATH:$JAVA_HOME/bin
ADD ./target/demo-0.0.0.1.jar /
WORKDIR /
CMD ["java","-jar", "./demo-0.0.0.1.jar", "-Xms256m", "-Xmx512m"]
