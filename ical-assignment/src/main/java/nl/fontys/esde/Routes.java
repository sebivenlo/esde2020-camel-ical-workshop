package nl.fontys.esde;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("platform-http:/calendar?httpMethodRestrict=GET")
                // reset headers so we can properly get a remote resource
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .setHeader(Exchange.HTTP_PATH, constant(""))
                .setHeader(Exchange.HTTP_URI, simple(""))
                .setHeader(Exchange.TO_ENDPOINT, simple(""))
                .setHeader(Exchange.HTTP_URL, simple(""))
                .to("https://xedule.j0dev.nl/47_INF_INF4.ics?bridgeEndpoint=true")
                // parse our response body to ical objects
                .unmarshal().ical(true)
                // return in json format
                .marshal().json();

        // TODO 1. Expand this call and retrieve / load in a ICalendar, then transform it into our nice calendar format and output that (in json format)
        // You should use the Ical2Obj processor class for this
        // TODO 2. Add another processor to filter out events that are already done
        // You may use the EventFilter processor class for this
        from("platform-http:/niceCalendar?httpMethodRestrict=GET");


        from("platform-http:/event")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // TODO create a base calendar and an event to publish

                    }
                })
                // output as ICal
                .marshal().ical(true);
    }
}