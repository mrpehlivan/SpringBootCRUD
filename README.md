# SpringBootCRUD
A Spring Boot Create , Read , Update and Delete (CRUD) example project. 

To start docker cd /src/main/resources and execute 'docker-compose up' in this path.

To import postman collection cd /src/main/resources choose developer.postman_collection and import to postman.


### Jenkins GitHub configuration

To do this go to Settings -> Integrations & services. The Jenkins Github plugin should be shown on the list of available services as below.

To access the password from the container.

```

docker exec -it jenkins sh

cat /var/jenkins_home/secrets/initialAdminPassword`

```


### Sonarqube configuration

Execute 

```
mvn clean verify sonar:sonar
```

Go to localhost:9000 and enter the username and password

Username : admin <br/>
Password : admin


####

|   Technology |   Version |
| :----------: |  :-------: | 
|   Java           | 1.8 |
|   Spring Boot     | 1.5.8 |
|   Spring Data JPA |  1.11.6 |
|   Hibernate       | 5.2.6 |
|   PostgreSQL      | 9.6 |
|   Hikari          | 2.6.3 |
|   Docker          | |
|   JUnit | 4.12 |
|   Mockito | 1.10.19 |
|   Jenkins | 2.60.3 |
|   Sonarqube | 2.7.1 |
