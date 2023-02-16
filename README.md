
# Hi, I'm Bianca! 👋

And here you can find the documentation of the Reddit Clone project
## 🚀 About Me
💻(Aspiring) back-end software developer | 👨‍💻Helping companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology | 💼 Actively looking for a job | 4️⃣ personal projects


## 🛠 Skills
Back-end development, Software development, Web development, Java, Spring framework, Spring boot, Data structures, Algorithms, OOP, MySQL, Relational databases, SQL, Git, HTML, CSS, Web services, Rest APIs, Unit Testing

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/BiancaPeter)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/bianca-peter/)


# Reddit Clone

Reddit is a social network with a forum-style discussion structure. Users create posts in topic-based communities — called subreddits — and interact in comment threads. Every thread has an OP (original poster) who started it. Users can also vote content by others “up” or “down” the algorithm.

## Features

As a user, I can:
- view all subreddits
- view a specific subreddit
- add a new post
- view all posts
- view a specific post
- view all posts by a specific subreddit
- view all posts by a specific user
- add a new comment
- view all comments by a specific post
- view all comments by a specific user
- add a vote


As an admin, I can:
- add a new subreddit
- delete subreddit


## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:

(insert link to video demo)


## API Reference

#### Add a new subreddit

```http
  POST/subreddit/add
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `body` | `json` | **Required**. The properties of subreddit to be added |

Request body example:

```json
{
  "name": "string",
  "description": "string"
}
```  


#### Get all subreddit 

```http
  GET /subbreddit/all
```


#### Get a subreddit

```http
  GET /subreddit/${subredditId}
```

| Parameter | Type     | Description                            |
| :-------- | :------- |:---------------------------------------|
| `id`      | `string` | **Required**. Id of subreddit to fetch |

#### Delete a subreddit

```http
  DELETE/subreddit/${subredditId}
```

| Parameter | Type     | Description                             |
| :-------- | :------- |:----------------------------------------|
| `id`      | `string` | **Required**. Id of subreddit to delete |


#### Add a new post

```http
  POST/post/add
```

| Parameter | Type     | Description                                      |
| :-------- | :------- |:-------------------------------------------------|
| `body` | `json` | **Required**. The properties of post to be added |

Request body example:

```json
{
  "postName": "string",
  "description": "string",
  "subredditId": "string"
}
```  


#### Get all post

```http
  GET /post/all
```


#### Get a post

```http
  GET /post/${postId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- |:----------------------------------|
| `id`      | `string` | **Required**. Id of post to fetch |


#### Get posts by subreddit

```http
  GET /post/subreddit/${subredditId}
```

| Parameter | Type     | Description                                  |
| :-------- | :------- |:---------------------------------------------|
| `id`      | `string` | **Required**. Id of subreddit to posts fetch |


#### Get posts by user

```http
  GET /post/user/${userId}
```

| Parameter | Type     | Description                             |
| :-------- | :------- |:----------------------------------------|
| `id`      | `string` | **Required**. Id of user to posts fetch |



#### Delete a post

```http
  DELETE/post/${postId}
```

| Parameter | Type     | Description                        |
| :-------- | :------- |:-----------------------------------|
| `id`      | `string` | **Required**. Id of post to delete |


#### Create a new post

```http
  POST/comment/create
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `body` | `json` | **Required**. The properties of comment to be created |

Request body example:

```json
{
  "text": "string",
  "postId": "string"
}
```  


#### Get all comment by post

```http
  GET /comment/post/${postId}
```

| Parameter | Type     | Description                                |
| :-------- | :------- |:-------------------------------------------|
| `id`      | `string` | **Required**. Id of post to comments fetch |



#### Get posts by user

```http
  GET /comment/user/${userId}
```

| Parameter | Type     | Description                                |
| :-------- | :------- |:-------------------------------------------|
| `id`      | `string` | **Required**. Id of user to comments fetch |



#### Delete a comment

```http
  DELETE/comment/${commentId}
```

| Parameter | Type     | Description                           |
| :-------- | :------- |:--------------------------------------|
| `id`      | `string` | **Required**. Id of comment to delete |


#### Add a vote

```http
  POST/vote/add
```

| Parameter | Type     | Description                                      |
| :-------- | :------- |:-------------------------------------------------|
| `body` | `json` | **Required**. The properties of vote to be added |

Request body example:

```json
{
  "voteType": "string",
  "postId": "string"
}
```  



## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}
```  

#### Register 

```http
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "username": "string",
  "password": "string",
  "email": "string",
  "roleType": "string"
}

```  
After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  
## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For deploying on Heroku you need:
- GIT 
- Heroku CLI

## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/BiancaPeter/reddit-app
```

Go to the project directory

```bash
  cd my-project
```
    
## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Environment Variables

You can deploy this project using Heroku or other providers as well, by specifying the following environment variables:

`PROFILE`

`MYSQL_URL`

`MYSQL_USER`

`MYSQL_PASS`



## Deployment

To deploy this project run

```bash
  git push heroku master
```


## Usage

You cand use the a demo version of the app, using SwaggerUI and following this link:

```javascript
https://obscure-peal.heroku.app/swagger-ui/
```

First, obtain an access token by running the /authenticate endpoint with username "user" and password "pass", which will grant you admin rights in the application.

![App Screenshot](https://i.imgur.com/VTQibfA_d.webp?maxwidth=760&fidelity=grand)

After that, authorize yourself by clicking the authorize button and copy-pasteing the token from the response.

![App Screenshot](https://i.imgur.com/arTX2Ke_d.webp?maxwidth=760&fidelity=grand)

From now on, you can use all other available endpoints, as per swagger documentation.
## Roadmap

In the future, application can be extended with following:

- a reporting functionality - to report a comment for: spam, fraud, threat, etc.

- a functionality to answer questions on the forum with the help of artificial intelligence offered by openAI, through their API


## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)

