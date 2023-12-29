package com.example;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.vertx.core.Vertx;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;





@Path("/labseq")
public class ExampleResource {


    @GET
    @Path("/{index}")
    @Produces(MediaType.TEXT_HTML)
    public CompletionStage<Response> getIndex(@PathParam("index") String index ) {

        Utiles.fillCache();

        return CompletableFuture.supplyAsync(()->{

            BigInteger algResponse = Utiles.getLabSeq(index);

            if (Integer.parseInt(index) > 11000){
                return Response.status(Response.Status.BAD_REQUEST).entity("Very large input:" + "<h1>"+index+"</h1>").build();
            }

            if (algResponse.compareTo(BigInteger.ONE.negate()) != 0){
                return Response.ok("Sequence index inserted:" + "<h1>"+index+"</h1>" +
                        "  Labseq algorithm answer:" + "<h1>"+algResponse+"</h1>").build();
            }

                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid input:" + "<h1>"+index+"</h1>").build();


        });

    }


}
