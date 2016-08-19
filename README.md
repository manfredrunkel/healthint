# Healthint (Microservices with WSDL/SOAP Integration)

Healthint is a project that demonstrates how to use Microservices (using Spring Cloud libraries) while integrating Document with WSDL/SOAP Webservice.

Please take note of where each project correlates to Microservice architecture:

![alt tag](https://github.com/manfredrunkel/healthint/blob/master/micro.png)

To Run it, please checkout this project and execute:

mvn package

On each project:
mvn spring-boot:run

All services will register to Discovery Services and after Proxy executed, it's possible to use following links:

https://server:port/api/adm/ - 
https://server:port/admweb - WEB UI Interface to follow documents.
https://server:port/api/sendintegratidocs/ws - to send document through POST request.
https://server:port/api/adm/getAllDocs - Get all documents integrated in a JSON format.
