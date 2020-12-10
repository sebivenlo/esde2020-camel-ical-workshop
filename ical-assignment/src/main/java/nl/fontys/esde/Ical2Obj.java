package nl.fontys.esde;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import org.apache.camel.*;

public class Ical2Obj implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // create our own calendar object
        nl.fontys.esde.Calendar cal = new nl.fontys.esde.Calendar();

        // TODO 1. transform the ical object into our nice calendar and event structure


        // return our transformed objects
        exchange.getOut().setBody(cal);
    }
}
