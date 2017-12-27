This project consists of two microservices and developed using SpringBoot. 

1) WeatherDemoApp => This is the main microservice which provides user registration page where a user can subscribe for weather updates. Currently supports one time update But can be extended for daily, weekly or monthly updates. As soon as user entry is created in MySQL database, the user details will be pushed on to a RabbitMQ.
2) NotificationService => This microservice acts as consumer for RabbitMQ Queue. After getting an item from queue, it will process the request by performing the following activities.
        i) Makes a rest API call to weatherunderground webservice to get the weather details for the given city & state.
       ii) Choses the Freemaker email body & Subject template based on the current weather condition.
      iii) Sends a mail to the user using registered email.
      
 
