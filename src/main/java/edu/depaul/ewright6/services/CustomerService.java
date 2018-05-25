package edu.depaul.ewright6.services;

import com.sun.net.httpserver.HttpServer;
import edu.depaul.ewright6.entities.Customer;
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

@Path("/customers")
@Singleton
public class CustomerService {

    private int idIndex;
    private ArrayList<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<Customer>();

        this.customers.add(new Customer(1, "Han", "Solo", "123 W Endor", ""));
        this.customers.add(new Customer(2, "Leia", "Organa", "234 W Alderaan", ""));
        this.customers.add(new Customer(3, "Luke", "Skywalker", "345 W Tatooine", ""));
        this.customers.add(new Customer(4, "Obiwan", "Kenobi", "456 W Coruscant", ""));
        this.customers.add(new Customer(5, "Chewbacca", "Wookie", "567 W Kashyyyk", ""));

        this.idIndex = 5;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        return Response.ok().entity(this.customers).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer addCustomer(Customer customer){
        customer.setId(++this.idIndex);
        this.customers.add(customer);

        return customer.getId();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Integer id){
        for (Customer customer : this.customers){
            if (customer.getId().equals(id)){
                return Response.ok().entity(customer).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") Integer id, Customer customer){
        for (int i = 0; i < this.customers.size(); i++){
            if (this.customers.get(i).getId().equals(id)){
                this.customers.set(i, customer);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") Integer id){
        for (int i = 0; i < this.customers.size(); i++){
            if (this.customers.get(i).getId().equals(id)){
                this.customers.remove(i);
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping(){
        return Response.ok().entity("Customer Service online").build();
    }



    public static void main(String[] args) throws IOException, URISyntaxException {
        ResourceConfig conf = new ResourceConfig(CustomerService.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer( new URI("http://localhost:9998/"), conf); //HttpServerFactory.create("http://localhost:9998/");

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/customers");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
