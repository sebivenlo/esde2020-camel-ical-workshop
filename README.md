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

### Assignment 1
In this assignment we are going to try and expose a much nicer (json) representation of our calendar and events.
So in this assignment we are going to implement a processor that will transform our ical objects into nice POJO's that we then can encode nicely in any format we want.

For this and other assignments we already provided a new simple calendar and event class to use.

In this assignment you will need to edit the route in `src/main/java/nl/fontys/esde/Routes.java` and complete it. You can use the `Ical2Obj` class to implement a processor.

See also TODO item 1 inside your IDE.

### Assignment 2
Now that we have a nice representation of a calendar. It might be nice to filter out all events that already happened.

See TODO item 2 and the `EventFilter` class

### Assignment 3
In this assignment we are going to create an event that can be shared with other (for example through email)

See TODO item 3 inside your IDE.


# Resources
[Apache Camel](https://camel.apache.org/)  
[Camel-iCAL extension](https://camel.apache.org/camel-quarkus/latest/reference/extensions/ical.html)  
[iCalendar](https://icalendar.org/)  
[ical4j](https://ical4j.github.io/ical4j-user-guide/) (the system that camel-ical uses to work with the iCalendar standard)  
[ical4j Examples](https://ical4j.github.io/ical4j-user-guide/) (Very useful link with examples of how to work with calendar objects)
