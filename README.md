# Assignment Book - Jason Li

## Background
This is an assignment project to provide CRUD API for Book operations.
THis project mainly provides 3 functions, they are:
```
1. create Book
2. get Book data by optional parameters author and published
3. delete a Book by id
```


## Pre-requirement
| Software      | Version       |
| ------------- |:-------------:|
| Java          | 17            |
| Maven         | 3.8.8         |
| MySQL         | 8.4.1         |

Can use docker to start a MySQL on local computer to speed up to process.

```
docker pull mysql:8.4.1
docker run -itd --name assignment-book -p 3306:3306 -e MYSQL_ROOT_PASSWORD=P@ssw0rd mysql:8.4.1
```

## DB setup
Login as root user and create a new Database **Library** and table **Book**
Also to create a new user **librarian** and grant Database **Library** access to the user

```
mysql -uroot -pP@ssw0rd

CREATE DATABASE Library;

USE Library;

CREATE TABLE book (
  id int(10) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  author varchar(50) DEFAULT NULL,
  published boolean DEFAULT false,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE USER 'librarian'@'%' IDENTIFIED BY 'key1357';

GRANT ALL PRIVILEGES ON Library.* TO 'librarian'@'%';

FLUSH PRIVILEGES;
```

Optional to set up initial data by SQL or by post API calls in Postman collection down-below
```
USE Library;

INSERT INTO book (id, title, author, published) 
VALUES 
(1, 'AndroidDreams', 'Sam', true), 
(2, 'SomethingWicked', 'Sam', false), 
(3, 'AStoryOfYesterday', 'Sally', true), 
(4, 'AClockworkOrange', 'Joe', false);

```

## Start the project
```
mvn clean install
mvn sprint-boot:run
```

## Test with Postman
There is a file called **book_collection.json** under resources folder, can import this file to Postman application and get the testing collection.

sample links and results would be:

[Get all books](http://localhost:8080/book)  
![all books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result1.png "all books result image")

[Get books by author](http://localhost:8080/book?author=sam)  
![author books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result2.png "author books result image")

[Get books by published](http://localhost:8080/book?published=true)  
![published books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result3.png "published books result image")

[Get books by non-published](http://localhost:8080/book?published=false)  
![non-published books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result4.png "non-published books result image")

[Get books by author published](http://localhost:8080/book?author=sam&published=true)  
![author published books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result5.png "author published books result image")

[Get books by author non-published](http://localhost:8080/book?author=sam&published=false)  
![author non-published books result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result6.png "author non-published books result image")

[delete a book](http://localhost:8080/book/1)  
![delete book by id result image](https://raw.githubusercontent.com/jasonlilab/assignmentBook/master/src/main/resources/API_result/result7.png "delete book by id result image")
