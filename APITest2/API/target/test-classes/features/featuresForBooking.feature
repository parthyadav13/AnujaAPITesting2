Feature: Validating Booking API's
Scenario: Get Booking ids
Given API request for Booking
When Get http request
Then Get all booking ids
#get the booking
Then Verify the booking details firstname, lastname, checkin, checkout

Scenario Outline: Verify if User is successfully login into Booking API
Given Login with payload "<username>" "<password>"
When user login with Post http request
Then the API call got success with status code "<code>"



Examples:
|username|password|code|
|admin|password123|200|

#create booking
Scenario Outline: Create the Booking
Given API request for Booking
When Post http request using "<firstname>" "<lastname>" "<totalprice>" "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
Then verify that the booking is created with status code "<code>"

Examples:
|firstname|lastname|totalprice|depositpaid|checkin|checkout|additionalneeds|code|
|Jill|Brown|111|true|2018-01-01|2019-01-01|Lunch|200|

#Update booking
@test
Scenario Outline: Update the Booking
Given API request for Booking
And Get the token"<username>" "<password>"
When Put http request using "<firstname>" "<lastname>" "<totalprice>" "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
Then verify that the booking is updated with status code "<code>"

Examples:
|username|password||firstname|lastname|totalprice|depositpaid|checkin|checkout|additionalneeds|code|
|admin|password123||Anuj|Brown|111|true|2018-01-01|2019-01-01|Breakfast|200|



# delete booking
@test3
Scenario Outline: Update the Booking
Given API request for Booking
And Get the token"<username>" "<password>"
When Delete http request using "<bookingid>"
Then verify that the booking is deleted with status code "<code>"

Examples:
|bookingid|code|username|password|
|28|201|admin|password123|

