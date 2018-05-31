package edu.depaul.ewright6.clients;

import com.sun.net.httpserver.HttpServer;
import edu.depaul.ewright6.services.CustomerService;
import edu.depaul.ewright6.services.FlightCatalogService;
import edu.depaul.ewright6.services.FlightRsvpService;
import edu.depaul.ewright6.services.WebReservationsAPI;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebReservationsClient {

    private static final String CUSTOMER_SERVICE_URI = "http://localhost:9998/customers";
    private static final String FLIGHT_SERVICE_URI = "http://localhost:9999/flights";
    private static final String RSVP_SERVICE_URI = "http://localhost:9997/rsvp";
    private static final String WEB_RESERVATIONS_API_URI = "http://localhost:9995/webclientapi";

    private static JerseyClient serviceClient;

    public static void main(String[] argv) throws IOException, URISyntaxException {
        serviceClient = JerseyClientBuilder.createClient();

        ResourceConfig conf = new ResourceConfig(CustomerService.class);
        HttpServer costumerServer = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9998/"), conf);

        conf = new ResourceConfig(FlightRsvpService.class);
        HttpServer flightRsvpServer = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9997/"), conf);

        conf = new ResourceConfig(FlightCatalogService.class);
        HttpServer flightServer = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9999/"), conf);

        conf = new ResourceConfig(WebReservationsAPI.class);
        HttpServer webApiServer = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9995/"), conf);

        Response pingResponse = serviceClient.target(CUSTOMER_SERVICE_URI).path("ping").request().get();
        System.out.println("Customer Service Status: " + pingResponse.getStatus() + " " + pingResponse.readEntity(String.class));
        System.out.println("Visit: http://localhost:9998/customers");


        pingResponse = serviceClient.target(FLIGHT_SERVICE_URI).path("ping").request().get();
        System.out.println("Flight Catalog Service Status: " + pingResponse.getStatus() + " " + pingResponse.readEntity(String.class));
        System.out.println("Visit: http://localhost:9999/flights");

        pingResponse = serviceClient.target(RSVP_SERVICE_URI).path("ping").request().get();
        System.out.println("Flight RSVP Service Status: " + pingResponse.getStatus() + " " + pingResponse.readEntity(String.class));
        System.out.println("Visit: http://localhost:9997/rsvp");

        pingResponse = serviceClient.target(WEB_RESERVATIONS_API_URI).path("ping").request().get();
        System.out.println("Web Reservations API Status: " + pingResponse.getStatus() + " " + pingResponse.readEntity(String.class));
        System.out.println("Visit: http://localhost:9995/webclientapi");

        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping servers");
        webApiServer.stop(0);
        costumerServer.stop(0);
        flightRsvpServer.stop(0);
        flightServer.stop(0);
        System.out.println("Servers stopped");
    }
}
