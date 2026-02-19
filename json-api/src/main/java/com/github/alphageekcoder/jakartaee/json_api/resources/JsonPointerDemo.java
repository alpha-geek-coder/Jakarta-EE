package com.github.alphageekcoder.jakartaee.json_api.resources;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonPointer;
import jakarta.json.JsonReader;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("json-pointer-demo")
public class JsonPointerDemo {

    private String jsonString;

    @GET
    @Path("read-value")
    @Produces(MediaType.TEXT_PLAIN)
    public String readValue() {
        // Implementation to read a value using JSON Pointer goes here
        initialize();
        String lastName = null;
        try(JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
            JsonArray jsonArray = jsonReader.readArray();
            JsonPointer jsonPointer = Json.createPointer("/1/lastName");
            lastName = jsonPointer.getValue(jsonArray).toString();
        }
        return lastName;
    }

    private void initialize() {

        this.jsonString = """
                [
                    {
                        "firstName": "John",
                        "lastName": "Doe",
                        "age": 30,
                        "email": "john.doe@example.com"
                    },
                    {
                        "firstName": "Jane",
                        "lastName": "Smith",
                        "age": 25,
                        "email": "jane.smith@example.com"
                    }
                ]""";

    }
}