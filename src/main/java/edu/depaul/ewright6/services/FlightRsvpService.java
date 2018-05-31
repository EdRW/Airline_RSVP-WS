package edu.depaul.ewright6.services;

import com.sun.net.httpserver.HttpServer;
import edu.depaul.ewright6.entities.Reservation;
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

@Path("/rsvp")
@Singleton
public class FlightRsvpService {
    private int idIndex;
    private ArrayList<Reservation> reservations;

    public FlightRsvpService() {
        this.reservations = new ArrayList<Reservation>();

        this.idIndex = 0;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservations() {
        return Response.ok().entity(this.reservations).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addReservation(Reservation reservation){
        reservation.setId(++this.idIndex);
        this.reservations.add(reservation);

        return reservation.getId();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservation(@PathParam("id") Integer id){
        for (Reservation reservation : this.reservations){
            if (reservation.getId().equals(id)){
                return Response.ok().entity(reservation).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReservation(@PathParam("id") Integer id, Reservation reservation){
        for (int i = 0; i < this.reservations.size(); i++){
            if (this.reservations.get(i).getId().equals(id)){
                this.reservations.set(i, reservation);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReservation(@PathParam("id") Integer id){
        for (int i = 0; i < this.reservations.size(); i++){
            if (this.reservations.get(i).getId().equals(id)){
                this.reservations.remove(i);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping(){
        return Response.ok().entity("Reservation Service online").build();
    }
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        ResourceConfig conf = new ResourceConfig(FlightRsvpService.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9997/"), conf);


        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9997/rsvp");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
