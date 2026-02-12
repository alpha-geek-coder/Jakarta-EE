package com.github.alphageekcoder.jakartaee.rest_api.resources;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

@ApplicationScoped
@Path("/server-sent-events")
public class ServerSentEventsResource {

     private static final Logger LOG = Logger.getLogger(ServerSentEventsResource.class.getName());

    private SseBroadcaster broadcaster;

    private OutboundSseEvent.Builder eventBuilder;

    private ScheduledExecutorService scheduler;

    private Double stockTickerValue = 100.0;

    
    @Context
    public void setSse(Sse sse) {
        this.broadcaster = sse.newBroadcaster();
        this.eventBuilder = sse.newEventBuilder();
    }

    @PostConstruct
    public void init() {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        sendEvents();
    }

    @PreDestroy
    public void cleanup() {
        scheduler.shutdown();
    }

    @GET
    @Path("subscribe")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void subscribeClient(@Context SseEventSink eventSink) {
        LOG.info(String.format("%s.subscribe() invoked", this.getClass().getName()));
        broadcaster.register(eventSink);
    }


    public void sendEvents() {

        scheduler.scheduleAtFixedRate( () -> {
            final OutboundSseEvent event = eventBuilder
                    .name("ENSD stock ticker value")
                    .data(String.class, String.format("%.2f",stockTickerValue))
                    .build();
            broadcaster.broadcast(event);
            LOG.info("Broadcasted event: " + event.getData());
            stockTickerValue += (Math.random() - 0.5) * 10;

        }, 5, 5, TimeUnit.SECONDS);
    }

}
