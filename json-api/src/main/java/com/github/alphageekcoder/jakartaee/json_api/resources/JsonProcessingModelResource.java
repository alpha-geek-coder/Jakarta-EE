package com.github.alphageekcoder.jakartaee.json_api.resources;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Logger;

import com.github.alphageekcoder.jakartaee.json_api.entity.Customer;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("json-processing-model")
public class JsonProcessingModelResource {

    private static final Logger LOG = Logger.getLogger(JsonProcessingModelResource.class.getName());
    
    @GET
    @Path("build-model")
    @Produces(MediaType.APPLICATION_JSON)
    public String buildJson() {
        LOG.info(String.format("%s.buildJson() invoked", this.getClass().getName()));
        
        // Implementation to build and return a JSON model goes here

        JsonObject jsonObject = Json.createObjectBuilder()
            .add("firstName", "John")
            .add("lastName", "Doe")
            .add("age", 30)
            .add("email", "jdoe@abc.com")
            .build();

        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)){
            jsonWriter.writeObject(jsonObject);
        }
        return stringWriter.toString();
    }

    @POST
    @Path("parse-model")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String parseJson(String jsonInput) {
        LOG.info(String.format("%s.parseJson() invoked", this.getClass().getName()));
        
        // Implementation to parse the input JSON string goes here
        Customer customer = new Customer();
        JsonObject jsonObject = null;
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonInput))){
             jsonObject = jsonReader.readObject();
        } catch (Exception e) {
            LOG.info(String.format("%s.parseJson() exception", e.getMessage()));
            
        }

        customer.setFirstName(jsonObject.getString("firstName"));
        customer.setMiddleName(jsonObject.getString("middleName", ""));
        customer.setLastName(jsonObject.getString("lastName"));
    
        return customer.toString();
    }
}
