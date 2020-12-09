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

        from("platform-http:/niceCalendar?httpMethodRestrict=GET")
                // reset headers so we can properly get a remote resource
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .setHeader(Exchange.HTTP_PATH, constant(""))
                .setHeader(Exchange.HTTP_URI, simple(""))
                .setHeader(Exchange.TO_ENDPOINT, simple(""))
                .setHeader(Exchange.HTTP_URL, simple(""))
                .to("https://xedule.j0dev.nl/47_INF_INF4.ics?bridgeEndpoint=true")
                // parse our response body to ical objects
                .unmarshal().ical(true)
                // The ical object classes are not realy nice POJO
                .process(new Ical2Obj())
                // filter only events in the future
                .process(new EventFilter())
                // return in json format
                .marshal().json();

        from("platform-http:/event")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // create calendar
                        Calendar calendar = new Calendar();
                        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
                        calendar.getProperties().add(Version.VERSION_2_0);

                        // our event date
                        java.util.Calendar date = java.util.Calendar.getInstance();
                        date.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
                        date.set(java.util.Calendar.DAY_OF_MONTH, 25);

                        // create event from date and name
                        VEvent event = new VEvent(new Date(date.getTime()), "Christmas Day");
                        // generate unique id
                        UidGenerator ug = new UidGenerator("1");
                        event.getProperties().add(ug.generateUid());

                        // add event to calendar
                        calendar.getComponents().add(event);

                        // return our calendar
                        exchange.getOut().setBody(calendar);
                    }
                })
                // output as ICal
                .marshal().ical(true);
    }
}