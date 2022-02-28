# assignment-6-Data-Access

##Author
Ole Henrik Johansen

##Description
This project was an assignment for Noroff accelerate education program

##How to use
The application have two ways to be used:
1. Routed directly towards the api: write api/v1/customers behind the url that it shows to you in the terminal when started.
!Note use an application that are directed towards apis like postman.
    Behind the api link there are several commands:
   1. /{id}: switch this one out with any number.
   2. /search: this one requests a parameter in the url like name={a name or letters}. This one don't work for some reason.
   3. /limit: this one requests two parameters limit={a number} and offset={a number}.
   4. /add: send a json body to the api to add a customer. Here's the body:
   "customerId": "60",
   "firstName": "test",
   "lastName": "test",
   "country": "test",
   "postalCode": "0000",
   "phone": "0000",
   "email": "test@test.test"
   5. /update: send a json body to api that you want to change, just remember to set the customerId to one that exists in the database.
   6. /countries: Gets the countries from the database, and how many customers that are registered in that country.
   7. /spenders: prints out every customer and how much they have spent in the application.
2. Write the url into a web application. Here you will see the data in generated views. {The generated link the application makes}/customers.
   1. /{id}: Does the same as the one to the api, only now shown in a view.
   2. /name/{name}: It searches up the name and show you the customer. This one don't work.
   3. /limit: this one requests two parameters limit={a number} and offset={a number}.
   4. /add: Shows you a view where you can add a customer. This one won't add one for some reason.
   5. /update: Shows you a view where you can update a customer. This one won't update the customer for some reason.
   6. /countries: Gets the countries from the database, and how many customers that are registered in that country.
   7. /spenders: prints out every customer and how much they have spent in the application.
##Errors
The application for some reason aren't able to get elements from the SQL database when you are using LIKE to look for objects that contains the letters you are looking for. The SQL queries made for this works when used on https://sqliteonline.com/, but not in the application.
The customer add and update views don't work for some reason also.
