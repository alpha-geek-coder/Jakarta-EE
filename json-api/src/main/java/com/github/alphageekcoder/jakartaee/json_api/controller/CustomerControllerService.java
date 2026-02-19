package com.github.alphageekcoder.jakartaee.json_api.controller;

import com.github.alphageekcoder.jakartaee.json_api.entity.Customer;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("customers")
public class CustomerControllerService {

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public String createCustomer(String jsonString) {
        // Implementation to create a customer goes here
        Jsonb jsonb = JsonbBuilder.create();
        Customer customer = jsonb.fromJson(jsonString, Customer.class);

        return customer.toString();
    }

    @GET
    @Path("{id}/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCustomer(@PathParam("id") Long id) {
        // Implementation to get a customer by ID goes here
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(customer);

        return jsonString;
    }

}
