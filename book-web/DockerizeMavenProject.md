
### Steps to dockerize tha Maven project

1. Create a simple Spring-Boot application

2. Add the following dependencies to add Spring – Boot dependencies 

```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
    </parent>

    <packaging>jar</packaging>
    <properties>
        <docker.image.prefix>blr</docker.image.prefix>
    </properties>


    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.plugin</groupId>
            <artifactId>spring-plugin-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```
3. Add following build plugin under```<build><plugins>```    Add Plugin for spring-boot-maven-plugin
```
    <plugin>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
```
4. Add Spotify plugin
```
    <plugin>
        <groupId>com.spotify</groupId>
        <artifactId>dockerfile-maven-plugin</artifactId>
        <version>1.3.6</version>
        <configuration>
            <repository>${docker.image.prefix}/${project.artifactId}</repository>
        </configuration>
    </plugin>
```
5. Add maven plugin
```
    <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-dependency-plugin</artifactId>
       <executions>
          <execution>
             <id>unpack</id>
             <phase>package</phase>
             <goals>
                <goal>unpack</goal>
             </goals>
             <configuration>
                <artifactItems>
                   <artifactItem>
                      <groupId>${project.groupId}</groupId>
                      <artifactId>${project.artifactId}</artifactId>
                      <version>${project.version}</version>
                   </artifactItem>
                </artifactItems>
             </configuration>
          </execution>
       </executions>
    </plugin>
```
6. Add Repositories
```
    <repositories>
        <!-- ... possibly other repository elements ... -->
        <repository>
            <id>spring-snapshot</id>
            <name>Spring Snapshot Repository</name>
            <url>http://repo.spring.io/snapshot</url>
        </repository>
        <repository>
            <id>spring-releases</id>
            <url>https://repo.spring.io/libs-release</url>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>spring-releases</id>
            <url>https://repo.spring.io/libs-release</url>
        </pluginRepository>
    </pluginRepositories>
```
    

7. Add Docker File with the following content
```
    FROM openjdk:8-jdk-alpine
    VOLUME /tmp
    ARG DEPENDENCY=target/dependency
    COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
    COPY ${DEPENDENCY}/META-INF /app/META-INF
    COPY ${DEPENDENCY}/BOOT-INF/classes /app
    LABEL version="1.0"
    LABEL description="This is example application for testing docker push"
    LABEL MAINTAINER="sagar"
    ENTRYPOINT ["java","-cp","app:app/lib/*","example.DockerExampleApplication"]
```

8. Create a sample application file
```
    package example;
    
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.context.annotation.ComponentScan;
    
    @SpringBootApplication
    @ComponentScan("com.example")
    public class DockerExampleApplication {
        private static Logger LOGGER = LoggerFactory.getLogger(DockerExampleApplication.class);
        public static void main(String[] args) {
            LOGGER.info("First docker push example ... ");
        }
    }

```        
        
9. Build using following command
```
    mvn  clean install dockerfile:build
```        

10. Open Powershell prompt, run the following command to list the images available with the docker 
```
    docker image ls
```
11. This should list the available image list as given below
```
    REPOSITORY                  TAG                 IMAGE ID            CREATED              SIZE     blr/docker-artifact         latest              3067b08e54ee        About a minute ago   119MB
```        
12. Run the docker image using the following command
```
    docker run 3067b08e54ee        
```        
13. Login into the docker repo from powershell
```
    docker login
    provide valid user name and password
```        
14. Open browser and loging into hub.docker.com

15. Create a repository

16. To create tag for the image use the following 
```
    docker tag 3067b08e54ee <username>/<hub_repository>:<tag name>
```
    
        
17. After creating the tag docker image ls should list something like this, given below

```
    REPOSITORY                  TAG                 IMAGE ID            CREATED              SIZE
    blr/docker-artifact         latest              0382996d53b1        About a minute ago   119MB
    sagarvt/bossy_dockerlearn   dockfirst           0382996d53b1        About a minute ago   119MB
```

18. Push the image to docker image repository

```
    docker push <hubusername>/<hub_repository>
```
19. Perform image pull from the machine where image needs to run

```
    docker pull sagarvt/bossy_dockerlearn:dockfirst
```
