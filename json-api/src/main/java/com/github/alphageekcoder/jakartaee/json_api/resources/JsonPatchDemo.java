package com.github.alphageekcoder.jakartaee.json_api.resources;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonPatch;
import jakarta.json.JsonReader;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("json-patch-demo")
public class JsonPatchDemo {

    private String jsonString;

    @GET
    @Path("apply-patch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyPatch() {
        // Implementation to apply JSON Patch goes here
        initialize();
        JsonArray modifiedArray = null;
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonString))) {
            JsonArray jsonArray = jsonReader.readArray();
            JsonPatch jsonPatch = Json.createPatchBuilder()
                .remove("/0/age")
                .replace("/1/lastName", "Doe-Smith")
                .add("/1/phone", "555-1234")
                .build();

            modifiedArray = jsonPatch.apply(jsonArray);   
        }
            return Response.ok(modifiedArray.toString(), MediaType.APPLICATION_JSON).build();
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
