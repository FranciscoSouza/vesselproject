# VesselAPI

VesselAPi project has the main focus to provide an webservice that returns the name of the timezone and the current local time for any given lon and lat.
# Requirements considered
- The timezone information needs to be stored in a postgresql/postgis
database. The timezones are stored as polygons and for each time zone
there is some additional information (name, UTC offset etc.)

- Application should run standalone, so no Application server should be
needed to run it. 
Spring Boot
http://projects.spring.io/spring-boot/

Spring Data REST 
http://projects.spring.io/spring-data-rest/
http://projects.spring.io/spring-data/

AngularJS
https://angularjs.org/

PostgreSQL
http://www.postgresql.org/

PostGis - Install with App http://www.bostongis.com/PrinterFriendly.aspx?content_name=postgis_tut01

# Architecture

![architeture](img/vessel.png) 

# Install
Clone the project from gitHub and create a projet on eclipse.
Run the application Boot.java

# How to test
- Get some some latitude and longitude using the site http://mygeoposition.com/
- Hit the URL http://localhost:8080/ on webbrowser and provide the latitude and longitude data. After that, this the button Submit.
- Calling API some postservice (PostMan)

Hit the URL on webbrowser informing the latitude and longitude
http://localhost:8080/timeForLatLng?lat=53.5&lng=10.4

