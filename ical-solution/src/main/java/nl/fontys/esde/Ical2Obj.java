package nl.fontys.esde;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import org.apache.camel.*;

public class Ical2Obj implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // get the ical object
        Calendar calendar = exchange.getIn().getBody(Calendar.class);
        // create our own calendar object
        nl.fontys.esde.Calendar cal = new nl.fontys.esde.Calendar();

        // calender properties
        cal.setName(calendar.getProperty("NAME").getValue());

        // event parsing
        for (Object comp : calendar.getComponents()) {
            Component calev = (Component) comp;
            // create new event
            Event event = new Event();
            // set properties
            event.setId(calev.getProperty("UID").getValue());
            event.setName(calev.getProperty("SUMMARY").getValue());
            event.setDetails(calev.getProperty("DESCRIPTION").getValue());
            event.setLocation(calev.getProperty("LOCATION").getValue());
            event.setStartDate(new DateTime(calev.getProperty("DTSTART").getValue()));
            event.setEndDate(new DateTime(calev.getProperty("DTEND").getValue()));
            // save
            cal.getEvents().add(event);
        }

        // return our transformed objects
        exchange.getOut().setBody(cal);
    }
}
