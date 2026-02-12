package com.github.alphageekcoder.jakartaee.rest_api.resources;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.alphageekcoder.jakartaee.rest_api.entity.Customer;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    private static final Logger LOG
            = Logger.getLogger(CustomerResource.class.getName());

    private final Customer customer;

    public CustomerResource() {
        customer = new Customer(1L, "David",
                "Raymond", "Heffelfinger");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Customer getCustomers(@PathParam("id") Long id) {
        LOG.log(Level.INFO, "{0}.getCustomer() invoked",
                this.getClass().getCanonicalName());
        LOG.log(Level.INFO, "Received with path param {0}", id);
         // In a real application, you would retrieve the customer by ID from a database
         return new Customer(id, "FirstName" + id, "MiddleName" + id, "LastName" + id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerByID(@QueryParam("id") Long id) {
       LOG.log(Level.INFO, "{0}.getCustomerByID() invoked",
            this.getClass().getCanonicalName());
        LOG.log(Level.INFO, "Received with query param {0}", id);
         // In a real application, you would retrieve the customer by ID from a database
         return new Customer(id, "FirstName" + id, "MiddleName" + id, "LastName" + id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCustomer(Customer customer) {
        LOG.log(Level.INFO, "{0}.putCustomer() invoked",
                this.getClass().getCanonicalName());
        LOG.log(Level.INFO, "customerJson = {0}", customer);
        // Logic to update customer information would go here
        return Response.ok(customer).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCustomer(Customer customer) {
        LOG.log(Level.INFO, "{0}.postCustomer() invoked",
                this.getClass().getCanonicalName());
        LOG.log(Level.INFO, "customerJson = {0}", customer);
        // Logic to create a new customer would go here
        return Response.ok(customer).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCustomer(@QueryParam("id") Long id) {
        LOG.log(Level.INFO, "{0}.deleteCustomer() invoked",
                this.getClass().getCanonicalName());
        LOG.log(Level.INFO, "Received with query param {0}", id);
        LOG.log(Level.INFO, "customerJson = {0}", customer);
        // Logic to delete the customer would go here
        LOG.log(Level.INFO, "Deleting customer {0}", id);
    }

}
