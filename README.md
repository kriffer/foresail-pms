# foresail-pms
Property management system (PMS), the application for managing accommodation premises.
It can be freely used as a starter template for any of similar projects.

The used stack - Spring Boot + JPA, Thymeleaf templates, JQuery, PostgresSQL

### DEMO

http://172.105.249.34:8080


### DEPLOYMENT
- clone the project
- in the project folder run `$ mvn package`
  
- in your PostgreSQL server run sql dump (in foresail-pms/src/main/resources/db/) 
  that creates database foresail_pms with some initial data
- launch the application like:
`$ java -jar foresail-pms.jar`
- open http://localhost:8080 in browser
   - login: user1
   - password: test
    
### TODO

- Some code polishing;
- db fixture script for initial test data;
- demo resource;
- unit tests coverage;

### How to contribute
Just fork and make a pull request ;)
