FROM openjdk:8

ADD target/docker-post.jar docker-post.jar

EXPOSE 3010

ENTRYPOINT ["java","-jar","docker-post.jar"]