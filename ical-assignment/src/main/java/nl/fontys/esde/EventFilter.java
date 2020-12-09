package nl.fontys.esde;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Date;

public class EventFilter implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO remove events if they are before now
    }
}
