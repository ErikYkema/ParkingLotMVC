Java Dojo Parking Lot
https://github.com/ErikYkema/ParkingLotMVC
Oscar Riezebos & Erik Ykema

The project combines:
 - Spring Autowiring
 - Spring MVC/WebApp/JSP
 - JUnit
 - JDBC (with DerbyDB), Spring JPA
And some minor stuff like:
- SLF/logging
 
  
Instructions:
- run the webapp with 
-- maven clean (to force latest class versions to deployable)
-- maven tomcat7:run-war (includes running all tests...)
- point the browser to http://localhost:8080
- go to the maintenance page
- setup and fill the database with cars/license plates and parking lot properties
- check the list of setup data at the maintenance page.
- go to the home page, go to entrance. Drive into the parking lot with a car's license plate
- leave the parking lot by entering again the license plate
- check the totals and changes in totals in the maintenance pages


