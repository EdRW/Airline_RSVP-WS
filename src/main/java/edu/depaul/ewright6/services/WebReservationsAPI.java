package edu.depaul.ewright6.services;

        import com.sun.net.httpserver.HttpServer;
        import org.glassfish.jersey.client.JerseyClient;
        import org.glassfish.jersey.client.JerseyClientBuilder;
        import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
        import org.glassfish.jersey.server.ResourceConfig;

        import java.io.IOException;
        import java.net.URI;
        import java.net.URISyntaxException;

        import javax.ws.rs.GET;
        import javax.ws.rs.Produces;
        import javax.ws.rs.Path;
        import javax.ws.rs.core.Response;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/webclientapi")
public class WebReservationsAPI {

    private static final String CUSTOMER_SERVICE_URI = "http://localhost:9998/customers";
    private static JerseyClient customerServiceClient = JerseyClientBuilder.createClient();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World from Web Reservations API Service!";
    }

    public Response ping(){
        return Response.ok().entity("Web Reservations API Service online").build();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Response pingResponse = customerServiceClient.target(CUSTOMER_SERVICE_URI).path("ping").request().get();

        System.out.println("Customer Service Status: " + pingResponse.getStatus() + " " + pingResponse.readEntity(String.class));

        ResourceConfig conf = new ResourceConfig(WebReservationsAPI.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9995/"), conf);
        //server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9995/webclientapi");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
