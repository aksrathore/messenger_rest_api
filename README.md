# messenger_rest_api
The project is a simple messenger API using a RESTful web service. There are three resources namely Profiles, Messages, Comments. All the components are interrelated through restful webservice. All the responses are rendered in JSON.

We can perform CRUD Opereations on each resource.

The profile section is entry point in the application. The URI is 
http://localhost:8080/messenger_rest_api/restapi/profiles

It lists all the profiles present in the database. Each profile is uniquely identified by an unique profilename. The sample JSON response for A1 profile is :

{
  "created": "2015-10-05T21:00:18",
  "firstName": "A1F",
  "id": 1,
  "lastName": "A1L",
  "lastUpdated": "2015-10-05T21:00:18",
  "profileName": "A1"
}

If we want to delete the profile we just have to do a DELETE operation on a profile using {profileName}. If we want to update the profile we can only update {firstname} and {lastname} and that can be done by PUT  operation. The sample PUT request is :

{
  "firstName": "A1FNEW",
  "lastName": "A1LNEW",
}

Similarly a new profile can be created by POST operation. The sample post request is :
{
  "firstName": "A2F",
  "lastName": "A2L",
  "profileName": "A2"
}
The profilename should be unique.

We can access message section on each profile. The URI is :
http://localhost:8080/messenger_rest_api/restapi/profiles/{profileName}/messages

Each message can be accesed by the following URI :
http://localhost:8080/messenger_rest_api/restapi/profiles/{profileName}/messages/{messageId}

Sample GET Response :
{
    "author": "A2",
    "created": "2015-10-05T21:21:16",
    "id": 1,
    "lastUpdated": "2015-10-05T22:46:46",
    "message": "A2 updated MESSAGE ON AI",
    "profileName": "A1"
  }


Sample PUT Request for message update :
{
    "message": "A2 newly updated MESSAGE ON AI"
}

Sample POST Request for message creation :
{
    "author": "A2",
    "message": "A2 updated MESSAGE ON AI"
}

Similarly for every message there can be multiple comments by an author.
We can access the comments section on each message by the following URI :
http://localhost:8080/messenger_rest_api/restapi/profiles/{profileName}/messages/{messageId}/comments

Each comment can be accessed by the URI :
http://localhost:8080/messenger_rest_api/restapi/profiles/{profileName}/messages/{messageId}/comments/{commentId}

NOTE : 	Here AUTHOR can be only those who have profile(having profileName).
		The sql scripts for creating table is kept at src/resources/sqlscripts in the project folder. I have not created table with the help of a java program for the sake of simplicity.
