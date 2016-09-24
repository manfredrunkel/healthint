# Healthint (Micro services with WSDL/SOAP Integration)

Healthint is a project that demonstrates how to use Micr oservices (using Spring Cloud libraries) while integrating Document with WSDL/SOAP Webservice.

Other libraries:
Netflix Zuul, Eureka, Load Ribbon  

Please take note of where each project correlates to Micro service architecture:

![alt tag](https://github.com/manfredrunkel/healthint/blob/master/micro.png)



To Run it, please checkout this project and execute:<br />

mvn package<br />

On each project:<br />

mvn spring-boot:run<br />

All services will register to Discovery Services and after Proxy executed, it's possible to use following links:<br />

https://server:port/admweb/admin - WEB UI Interface to follow documents.<br />
https://server:port/api/sendintegratidocs/ws - to send document through POST request.<br />
https://server:port/api/adm/getAllDocs - Get all documents integrated in a JSON format.<br />
