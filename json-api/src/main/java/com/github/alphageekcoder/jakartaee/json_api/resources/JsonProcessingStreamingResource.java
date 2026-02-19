package com.github.alphageekcoder.jakartaee.json_api.resources;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.alphageekcoder.jakartaee.json_api.entity.Customer;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("json-processing-streaming")
public class JsonProcessingStreamingResource {

    @GET
    @Path("build-json-stream")
    @Produces(MediaType.APPLICATION_JSON)
    public String buildJson() {
        StringWriter stringWriter = new StringWriter();
        try( JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)){

            jsonGenerator.writeStartObject()
                .write("firstName", "John")
                .write("lastName", "Doe")
                .write("age", 30)
                .write("email", "jdoe@abc.com")
                .writeEnd();
        }
        return stringWriter.toString();
    }

    @POST
    @Path("parse-json-stream")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String parseJson(String jsonInput) {

        StringReader stringReader = new StringReader(jsonInput);
        Map<String, String> map = new HashMap<>();
        try(JsonParser jsonParser = Json.createParser(stringReader)) {

            while(jsonParser.hasNext()) {
                JsonParser.Event event = jsonParser.next();
                String key = null; String value = null;
                if(event.equals(JsonParser.Event.KEY_NAME)) {
                    key = jsonParser.getString();
                } else if(event.equals(JsonParser.Event.VALUE_STRING)) {
                    value = jsonParser.getString();
                }
                if(key != null && value != null) {
                    map.put(key, value);
                }
            }
        }

        Customer customer = new Customer();
        customer.setFirstName(map.get("firstName"));
        customer.setLastName(map.get("lastName"));
        return customer.toString();
    }
}
