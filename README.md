# lunchvote
Voting system fro deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day 
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

One admin user already exists in database. Login: 'admin' password: 'admin'. 

This is Spring boot application. It uses embedded HSQL databese and Tomcat application server. 

# API description

POST /v1/users/register - new user registration
{
    "login":"user login",
    "password":"user password"
}

POST /v1/restaurants - add new restaurant  
{
    "name":"restaurant name"
}

GET /v1/restaurants - get all restaurants

GET /v1/restaurants/id - get restaurant with particular id

POST /v1/restaurants/id/menu - add one dish to menu to restaurant with particualar id
{
	"name":"name of new dish",
	"price":"price of new dish"
}

POST /v1/votes/id - add vote for restaurant with particular id

GET /v1/votes - get voting result





