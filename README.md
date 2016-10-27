#User Registration

User Registration is a Web application that allows users to be registered, de-registered. The front end is build using AngularJS, Bootstrap
and the backend is REST services using Spring, no Database - just a in-memory collection of User Entity.


# Rules

Username has to be alphanumeric, no spaces.

Password has to be atleast 4 characters, atleast one number, atleast one uppercase

SSN has to be unique in the format xxx-xx-xxxx 

Unique Constraint on Social Security Number and Only SSN.

Multiple users can have same username.

Tech stack : Java, Spring, AngularJS, Bootstrap, Maven, Mockito, JUnit, rest-assured, REST WebSerivce, Tomcat

# Assumptions :

Port 8080 MUST be free and should not be in use for executing integration tests / building war with integration-tests

Hosted @ http://localhost:8080/

Added a fake implementation for ExclusionService with a static list of users who are black listed. The users blacklisted are below

*		DOB : 1985-01-01  & SSN : 999-11-1111
*		DOB : 1985-01-02  & SSN : 999-11-1112
*		DOB : 1985-01-03  & SSN : 999-11-1113
*		DOB : 1985-01-04  & SSN : 999-11-1114
*		DOB : 1985-01-05  & SSN : 999-11-1115
		
As soon as the application starts up, you will notice there are already 3 registered users 


#Services 
1. REGISTER

```
Request URL: http://localhost:8080/CodingExercise/register/
Request Method: POST  
Request Body : {id: null, username: "USER1", password: "P3ss", dob: "2016-01-01T00:00:00.000Z", ssn: "123-44-8758"}
Response Status Code : 201
```

2. DE-REGISTER Single User
```
Request URL: http://localhost:8080/CodingExercise/register/{id}
Request Method: DELETE  
Response Status Code : 204
```

3. DE-REGISTER All Users
```
Request URL: http://localhost:8080/CodingExercise/register/
Request Method: DELETE  
Response Status Code : 204
```

4. FETCH All Users
```
Request URL: http://localhost:8080/CodingExercise/register/
Request Method: GET  
Response Status Code : 200
Response Body : [{"id":1,"username":"Sam1","password":"aaA1","dob":"1985-10-17T23:00:00.000Z","ssn":"123-11-1234"},{"id":2,"username":"Tomy1","password":"bbB1","dob":"1998-11-17T23:00:00.000Z","ssn":"123-11-1233"}]
```





		