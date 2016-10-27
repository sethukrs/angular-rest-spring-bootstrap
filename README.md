* Assumptions :
*
* Port 8080 MUST be free and being used for executing integration tests / building war with integration-tests
*
* Added a fake implementation for ExclusionService with a static list of users who are black listed. The users blacklisted are below
*		1. DOB : 1985-01-01  & SSN : 999-11-1111
*		2. DOB : 1985-01-02  & SSN : 999-11-1112
*		3. DOB : 1985-01-03  & SSN : 999-11-1113
*		4. DOB : 1985-01-04  & SSN : 999-11-1114
*		5. DOB : 1985-01-05  & SSN : 999-11-1115
*		
* As soon as the application starts up, you will notice there are already 3 registered users 

		