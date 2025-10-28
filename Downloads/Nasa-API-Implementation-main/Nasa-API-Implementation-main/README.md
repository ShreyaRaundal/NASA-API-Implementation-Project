# Nasa API Implementation 🚀

![nasa-api-logo](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/47a63e0c-1268-4527-a1bd-7dd854171c27)

<hr style="width:75%">

## Introduction
Welcome to the Nasa API Implementation project! This **full-stack web application** utilizes Nasa's API key to provide users with captivating Astronomy Picture Of The Day (APOD) and Mars Rover Photos. The project seamlessly integrates various technologies to deliver a robust and user-friendly experience.


Discover the NASA Astronomy picture of the day(apod) and Rover images from the mars explorations. You can choose...
* one of three different mars rovers 
  * Curiosity
  * Opportunity 
  * Spirit
* multiple different cameras, e.g.
  * Navigation Camera
  * Panoramic Camera
  * Front Hazard Avoidance Camera
  * ...
* a specific day (sol = mars days) since the mars landing 

<hr style="width:75%">

#### Technology Stack And Features

Astronomy Picture Of The Day (APOD) and Mars Rover Photos:-
Fetching and displaying stunning images from Nasa's API using **Spring Boot**.

**Backend:-**
Utilizes **MySQL** database for data storage.

**Spring Framework:-**
The project is developed using the robust and efficient **Spring Boot 3** and **Spring Framework 6**, ensuring a scalable and maintainable codebase. The project showcases a sophisticated integration of cutting-edge technologies, emphasizing security, performance, and user experience.

**Restful Web Services:-**
**RESTful web services** have been meticulously crafted to facilitate seamless access to NASA's Astronomy Picture of the Day and Mars Rover Photos, offering a streamlined experience for users and developers. A comprehensive **REST CRUD API** has been implemented for managing Astronomy Pictures of the Day. Leveraging Spring Security, the API restricts access to authorized administrators, ensuring secure and controlled data operations.

**Frontend:-**
The front-end is designed using the **Model-View-Controller (MVC) architecture**, emphasizing modularity and separation of concerns. **Thymeleaf**, a modern server-side Java template engine, is employed for dynamic and elegant server-side rendering of HTML templates. Basic **HTML5, CSS, and Bootstrap** form the core of the front-end technologies. Bootstrap, in particular, enhances the user interface with **responsive design** elements, ensuring a visually appealing and **user-friendly experience**.

**Security:-**
**Spring Security** has been employed to fortify the application, enabling **custom login pages** and safeguarding endpoints. The implementation extends further to incorporate **JWT with OAuth2 resource server** for enhanced user **authentication and authorization**.

**Admin Page:-**
Provides an **Admin Page** for performing CRUD operations for APOD, giving administrators control over the content.

**Swagger Documentation:-**
Utilizes **Swagger** for comprehensive documentation of the REST APIs, making it easy for developers to understand and integrate.

**Exception Handling:-**
The project demonstrates a commitment to reliability by incorporating custom **exception handling for endpoints**. This ensures graceful handling of errors, enhancing the overall resilience of the application.

**Deployment:-**
For deployment, the project leverages **Docker and AWS Elastic Beanstalk**, showcasing an infrastructure that is not only scalable but also ensures ease of management and deployment in the cloud.

**Testing:-**
Conducts **JUnit and Mockito and Integration Tests with Test Containers** on all layers of the project, ensuring reliability and robustness.

<hr style="width:75%">


## Demo
[**Try it out** 🌎](http://nasa-webapp-env.eba-bpm6gg2n.ap-south-1.elasticbeanstalk.com/nasa/home-page)

#### Home-Page
![home](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/65528a81-2f75-40b0-8881-11a1c0e3092d)

#### Index-Page
![index](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/55bff53e-f749-48f7-a85f-00fdb2d5287d)

#### Astronomy Picture Of The Day( A P O D )
![apod](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/fdc5fb92-59f0-487e-8e65-d784121a54d6)

#### Mars Rover Page
![rover](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/15723c32-57bc-4bb1-a2bf-e4344f3b8e55)

#### Admin Page
![admin](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/144c2bf8-4664-411f-9980-39677df0c6c9)

#### Swagger Documentation Page
![swagger](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/572d17a7-a77f-4ab4-acfb-ea84e4bf660e)

#### Logout Page
![logout](https://github.com/varungr-dev/Nasa-API-Implementation/assets/144204974/92e8dc5a-bd8b-4532-9831-131509ed4629)

<hr style="width:75%">

## Getting Started

To get started with the Nasa API Implementation project, follow these steps:--

FOR STEPS CONTACT:- varungrvv@gmail.com :)

<hr style="width:75%">

## SAMPLE Querying Through Endpoints
Mars Rover Endpoint:--
`http://localhost:5000/api/rover/{roverName}/{earthDate}/{roverCamera}`.

This endpoint will fetch the Nasa's Mars Rover Photos. 

Details are given below( have a look ) 

A.) There are 3 Mars Rovers 

   1.) Curiosity 
   
   2.) Spirit 
   
   3.) Opportunity 
   
B.) There are 9 Cameras For These Rovers 

   1.) FHAZ-> Front Hazard Avoidance Camera 
   
   2.) RHAZ-> Rear Hazard Avoidance Camera 
   
   3.) MAST-> Mast Camera 
   
   4.) CHEMCAM-> Chemistry and Camera Complex 
   
   5.) MAHLI-> Mars Hand Lens Imager 
   
   6.) MARDI-> Mars Descent Imager 
   
   7.) NAVCAM-> Navigation Camera
   
   8.) PANCAM-> Panoramic Camera 
   
   9.) MINITES-> Miniature Thermal Emission Spectrometer (Mini-TES) 
   
C.) Earth Date Is In The Form Of **YYYY/MM/DD** 

D.) You can fetch Mars photos based on the rovername, earthdate, rovercamera. 

   **Ex:- `/rover/curiosity/2015-06-03/fhaz`**
   

## Documentation

Explore the Swagger documentation to understand and integrate with the REST APIs. Access the documentation at **`http://localhost:5000/swaggerdoc.html`** .

#### NOTE
**If you access /swaggerdoc.html DIRECTLY without logging in, you can use the bearer token( JWT Authentication ).
To get JWT Bearer token, use the endpoint:--
`/get-token`
Ex:--
`http://localhost:5000/get-token`.
Alternatively you can also use normal credentials to access `http://localhost:5000/swaggerdoc.html`.**

Feel free to contribute, report issues, or provide feedback to make the Nasa API Implementation project even better! Happy coding! 🚀🌌

