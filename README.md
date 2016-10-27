#User Registration

User Registration is a Web application that allows users to be registered, de-registered. The front end is build using AngularJS, Bootstrap
and the backend is REST services using Spring, no Database - just a in-memory collection of User Entity.


# Rules

Username has to be alphanumeric, no spaces.
Password has to be atleast 4 characters, atleast one number, atleast one uppercase
SSN has to be unique in the format xxx-xx-xxxx 

Unique Constraint on Social Security Number is forced.
Multiple users can have same username.



Tech stack : Java, Spring, AngularJS, Bootstrap, Mockito, JUnit, REST WebSerivce, Tomcat

# Assumptions :

Port 8080 MUST be free and should not be in use for executing integration tests / building war with integration-tests

Hosted @ http://localhost:8080/

Added a fake implementation for ExclusionService with a static list of users who are black listed. The users blacklisted are below
		1. DOB : 1985-01-01  & SSN : 999-11-1111
		2. DOB : 1985-01-02  & SSN : 999-11-1112
		3. DOB : 1985-01-03  & SSN : 999-11-1113
		4. DOB : 1985-01-04  & SSN : 999-11-1114
		5. DOB : 1985-01-05  & SSN : 999-11-1115
		
As soon as the application starts up, you will notice there are already 3 registered users 

		