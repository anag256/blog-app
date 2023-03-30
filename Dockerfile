FROM openjdk:8
EXPOSE 8080
ADD target/blog-app-docker.jar blog-app-docker.jar
ENTRYPOINT [ "java","-jar","blog-app-docker.jar" ]