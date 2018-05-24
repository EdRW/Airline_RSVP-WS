package edu.depaul.ewright6.services;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

// The Java class will be hosted at the URI path "/rsvp"
@Path("/rsvp")
public class FlightRsvpService {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World from Flight RSVP Service!";
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        ResourceConfig conf = new ResourceConfig(FlightRsvpService.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9997/"), conf);
        //server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9997/rsvp");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
