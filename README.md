# BlogPoint - Backend

## About

This is a repo for the server application of BlogPoint, written in Java using [Spring Boot](https://spring.io/projects/spring-boot/)

## Built using 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

## 🛫 Quick Setup

```sh
# Clone the repo
git clone https://github.com/code-sparsh/blog-java-backend.git

# Navigate to the project directory
cd blog-java-backend

# Build the jar file
mvn clean package

# Execute the jar file
java -jar tar- GET/welcome-0.0.1-SNAPSHOT.jar

# (Optional) if skip test cases
java -jar tar- GET/welcome-0.0.1-SNAPSHOT.jar -DskipTests

```
## 📕 API Documentation

<br> 
<details>
<summary><b>/api/auth:   
Authentication endpoints</b></summary>

<br>

**- POST /login**

Request: 
 ```json
{

    "email": "sparshsethi@example.com",
    "password": "1234@Abcd"
}
```
Response: 
  ```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFyc2hzZXRoaUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNDY0Nzk0MiwiZXhwIjoxNzA0NzM0MzQyfQ.LuOkmhsOeYO4N754-v_Y1FEtXj5EwjGTjtG6SVeyGpg"
}
```
<br>

**- POST /register**

Request: 
 ```json
{
    "email": "sparshsethi@example.com",
    "password": "1234@Abcd",
    "name": "Sparsh Sethi"
}
```
Response: 
  ```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFyc2hzZXRoaUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNDY0ODkwMCwiZXhwIjoxNzA0NzM1MzAwfQ.DAsBnh46abOB_dh3-_iqGaDo2G6a8aYa4ap_yvSSghs"
}
```

</details>



<details>
<summary><b>/api/blog:   
Blog endpoints</b></summary>

<br>

**- GET / (get all Blogs)**

Request Header:
 ```json
{
      "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFyc2hzZXRoaUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNDY0Nzk0MiwiZXhwIjoxNzA0NzM0MzQyfQ.LuOkmhsOeYO4N754-v_Y1FEtXj5EwjGTjtG6SVeyGpg"
}
```
Response: 
  ```json
[
    {
        "id": "93f9d8a1-c612-44b5-8bee-9085cc076fee",
        "title": "Spring Boot Tutorial",
        "data": "Hi this is me, I am going to teach your Spring Boot today",
        "author": "Sparsh Sethi"
    },

    {
        "id": "fdc71a88-cd10-4fd5-8390-2c8d4f50001c",
        "title": "A test Blog",
        "data": "Welcome to the blog /n I am testing something here",
        "author": "Sparsh Sethi"
    }
]
```
<br>

**- GET /{id} (get Blog by ID)**

Request Header:
 ```json
{
      "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFyc2hzZXRoaUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNDY0Nzk0MiwiZXhwIjoxNzA0NzM0MzQyfQ.LuOkmhsOeYO4N754-v_Y1FEtXj5EwjGTjtG6SVeyGpg"
}
```
Response: 
  ```json
{
    "id": "5f4c543e-817d-407d-af41-3567a36dc54d",
    "title": "A test Blog",
    "data": "Welcome to the blog /n I am testing something here",
    "author": "Sparsh Sethi"
}
```

<br> 

**- POST /create (Create a blog)**

Request Header:
 ```json
{
      "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzcGFyc2hzZXRoaUBleGFtcGxlLmNvbSIsImlhdCI6MTcwNDY0Nzk0MiwiZXhwIjoxNzA0NzM0MzQyfQ.LuOkmhsOeYO4N754-v_Y1FEtXj5EwjGTjtG6SVeyGpg"
}
```

Request Body:
 ```json
{
    "title": "A test Blog",
    "data": "Welcome to the blog /n I am testing something here",
    "author": "Sparsh Sethi"
}
```
Response: 
  ```json
{
    "id": "299f230e-ee60-4d02-b440-9b83e993289d",
    "title": "A test Blog",
    "data": "Welcome to the blog /n I amhttps://icons8.com/icon/f34HG4w9RjQk/mysql testing something here",
    "author": "Sparsh Sethi"
}
```


</details>


