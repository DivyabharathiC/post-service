FROM openjdk:8

EXPOSE 3010
ADD target/docker-post.jar docker-post.jar
ENTRYPOINT ["java","-jar","/docker-post.jar"]

