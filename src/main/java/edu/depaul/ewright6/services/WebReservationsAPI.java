package edu.depaul.ewright6.services;

        import com.sun.net.httpserver.HttpServer;
        import edu.depaul.ewright6.entities.BookingInfo;
        import edu.depaul.ewright6.entities.Customer;
        import edu.depaul.ewright6.entities.Flight;
        import edu.depaul.ewright6.entities.Reservation;
        import org.glassfish.jersey.client.JerseyClient;
        import org.glassfish.jersey.client.JerseyClientBuilder;
        import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
        import org.glassfish.jersey.server.ResourceConfig;

        import java.io.IOException;
        import java.net.URI;
        import java.net.URISyntaxException;
        import java.util.ArrayList;
        import java.util.HashMap;

        import javax.inject.Singleton;
        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/webclientapi")
@Singleton
public class WebReservationsAPI {

    private int idIndex;
    // This should actually be located in the Itinerary Service, but that doesn't exist
    private HashMap<Integer, ArrayList<Reservation>> itineraries;

    private static final String CUSTOMER_SERVICE_URI = "http://localhost:9998/customers";
    private static final String FLIGHT_SERVICE_URI = "http://localhost:9999/flights";
    private static final String RSVP_SERVICE_URI = "http://localhost:9997/rsvp";

    private static JerseyClient serviceClient = JerseyClientBuilder.createClient();



    public WebReservationsAPI() {
        this.itineraries = new HashMap<Integer, ArrayList<Reservation>>();

        this.idIndex = 1000;
    }

    @POST
    @Path("bookflight")
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer bookFlight(BookingInfo bookingInfo){

        // Pretend this makes a call to the RSVP Service

        return null;
    }

    @GET
    @Path("generateitinerary/{confirmationcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateItinerary(@PathParam("confirmationcode") Integer confirmationcode){

        if(this.itineraries.containsKey(confirmationcode)){
            StringBuilder itineraryStringBuilder = new StringBuilder();
            Integer customerID = this.itineraries.get(confirmationcode).get(0).getCustomerId();

            Response response = serviceClient.target(CUSTOMER_SERVICE_URI).path(customerID.toString()).request().get();
            Customer customer = response.readEntity(Customer.class);

            itineraryStringBuilder.append("Itinerary for Customer: " + customer.toString() + "\nConfirmation Code: " + confirmationcode + "\n");

            for (Reservation rsvp : this.itineraries.get(confirmationcode)){
                response = serviceClient.target(FLIGHT_SERVICE_URI).path(rsvp.getFlightId().toString()).request().get();
                Flight flight = response.readEntity(Flight.class);

                itineraryStringBuilder.append(flight + "\n");
            }

            return Response.ok().entity(itineraryStringBuilder.toString()).build();
        }


        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping(){
        return Response.ok().entity("Web Reservations API Service online").build();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {



        ResourceConfig conf = new ResourceConfig(WebReservationsAPI.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9995/"), conf);


        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9995/webclientapi");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
