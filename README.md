Camel & ICal Workshop
======
##### Apache Camel
Apache Camel is an open-source Java framework that focuses on making integration between different systems easier and more accessible to developers. Apache Camel does this by implementing most of the Enterprise Integration Patterns.

##### iCalendar
iCalendar is an open standard for transferring calendar information between systems. The iCalendar standard is often used to share and synchronize full calendars and events across the Internet.

# Pre-Requirements
- Java IDE (e.g. netbeans / IntelliJ)
- JDK 1.8+ (properly setup)
- Maven (externally or through your IDE)

# Running the application
1. Open the assignment project (or solution)
2. run `./mvnw compile quarkus:dev` (*nix) or `mvnw compile quarkus:dev` (windows)

# Assignment
In this assignment we will be setting up some HTTP routes through Apache Camel that will process calendars with Camel-iCAL.

### Demo
Before we get into actually programming this ourselves, lets look at a demo of how Camel and the iCAL extension actually work.

After running the application you can go to [http://localhost:8080/calendar](http://localhost:8080/calendar) to get a json representation of a fully filled calendar parsed by the Camel iCAL extension. Here you can also see the internal structure and the properties that we later are going to need in the assignments.

You can view the code of this route in `src/main/java/nl/fontys/esde/Routes.java`. Here you can see how the routes are defined.
