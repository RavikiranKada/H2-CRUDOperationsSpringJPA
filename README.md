### About the project

This project is all about performing CRUD operations using REST API and Spring Data JPA.
We have used H2 in memory database to store the data. You can use any database of your choice. In order to accomodate the other database specific changes, the only changes you have to do is to add the right database connectivity drive dependency in pom.xml and change the connectivity details like url, username, password etc in application.properties.


### Accessing the h2 database

* Hit this url in your chrome browser http://localhost:9006/h2-console/
  The port number will change based on which port you have configured your application.
* The database url should be jdbc:h2:mem:testdb
* In this project since we already have an Employee POJO , the H2 database table will also get created when you run the application.
* Once your application is up and running, go to the above URL, you will find the EMPLOYEE table which is empty. You need to execute the below insert queries to add the data.

Insert Queries
* insert into EMPLOYEE values('1','Pune','Ravikiran','20000')
* insert into EMPLOYEE values('2','Pune','Shikumar','50000')
* insert into EMPLOYEE values('3','Pune','Jayasri','60000')
* insert into EMPLOYEE values('4','Pune','Rajvika','10000')

### Please note
Just take a note that Whenever you restart your application, your table data will go away and you need to reinsert.
This is how H2 database works since its in memory and will be UP when you execute you start your application.


# H2-CRUDOperationsSpringJPA
