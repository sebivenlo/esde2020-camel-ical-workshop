package nl.fontys.esde;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

public class EventFilter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // get our object
        nl.fontys.esde.Calendar calendar = exchange.getIn().getBody(nl.fontys.esde.Calendar.class);
        // remove events if they are before now
        calendar.getEvents().removeIf(event -> event.getStartDate().compareTo(new Date()) < 0);
    }
}
