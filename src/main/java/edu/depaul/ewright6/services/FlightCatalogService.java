package edu.depaul.ewright6.services;

import com.sun.net.httpserver.HttpServer;
import edu.depaul.ewright6.entities.Flight;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/flights")
@Singleton
public class FlightCatalogService {

    private int idIndex;
    private ArrayList<Flight> flights;

    public FlightCatalogService() {
        this.flights = new ArrayList<Flight>();

        this.flights.add(new Flight(1, "FA1001", "Chicago", "San Francisco", "2018-06-01 13:30:00 GMT", ""));
        this.flights.add(new Flight(2, "FA1002", "San Francisco", "Chicago", "2018-06-01 17:30:00 GMT", ""));
        this.flights.add(new Flight(3, "FA1003", "Chicago", "New York", "2018-06-02 08:30:00 GMT", ""));
        this.flights.add(new Flight(4, "FA1004", "New York", "Chicago", "2018-06-02 14:30:00 GMT", ""));
        this.flights.add(new Flight(5, "FA1005", "Chicago", "Miami", "2018-06-03 15:30:00 GMT", ""));
        this.flights.add(new Flight(6, "FA1006", "Miami", "Chicago", "2018-06-03 20:30:00 GMT", ""));

        this.idIndex = 6;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlights() {
        return Response.ok().entity(this.flights).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addFlight(Flight flight){
        flight.setId(++this.idIndex);
        this.flights.add(flight);

        return flight.getId();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlight(@PathParam("id") Integer id){
        for (Flight flight : this.flights){
            if (flight.getId().equals(id)){
                return Response.ok().entity(flight).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFlight(@PathParam("id") Integer id, Flight flight){
        for (int i = 0; i < this.flights.size(); i++){
            if (this.flights.get(i).getId().equals(id)){
                this.flights.set(i, flight);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFlight(@PathParam("id") Integer id){
        for (int i = 0; i < this.flights.size(); i++){
            if (this.flights.get(i).getId().equals(id)){
                this.flights.remove(i);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping(){
        return Response.ok().entity("Flight Catalog Service online").build();
    }

    

    public static void main(String[] args) throws IOException, URISyntaxException {
        ResourceConfig conf = new ResourceConfig(FlightCatalogService.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9999/"), conf);


        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9999/flights");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
