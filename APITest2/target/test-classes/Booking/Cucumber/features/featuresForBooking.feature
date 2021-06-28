Feature: Validating Booking API's
Scenario Outline: Verify if User is successfully login into Booking API
Given Login with payload "< username>" "<password>"
When user login with Post http request
Then the API call got success with status code 200

Examples:
|username 	 | password     |
|admin       |  password123 |
	
	
	