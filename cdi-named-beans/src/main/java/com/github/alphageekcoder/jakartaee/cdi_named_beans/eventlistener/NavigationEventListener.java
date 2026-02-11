package com.github.alphageekcoder.jakartaee.cdi_named_beans.eventlistener;

import java.io.Serializable;
import java.util.logging.Logger;

import com.github.alphageekcoder.jakartaee.cdi_named_beans.event.NavigationInfo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class NavigationEventListener implements Serializable {

    private static final Logger logger = Logger.getLogger(NavigationEventListener.class.getName());

    public void handleNavigationEvent(@Observes NavigationInfo navigationInfo) {
        logger.info("Navigation Event Received: " + navigationInfo);
    }
}
