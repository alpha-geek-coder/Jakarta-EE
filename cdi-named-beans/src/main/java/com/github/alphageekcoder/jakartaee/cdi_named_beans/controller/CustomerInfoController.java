package com.github.alphageekcoder.jakartaee.cdi_named_beans.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import com.github.alphageekcoder.jakartaee.cdi_named_beans.event.NavigationInfo;
import com.github.alphageekcoder.jakartaee.cdi_named_beans.model.CustomerInfo;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CustomerInfoController implements Serializable {
    
    private static final Logger logger = Logger.getLogger(CustomerInfoController.class.getName());

    @Inject
    private Conversation conversation;
    
    @Inject
    private CustomerInfo customer;

    @Inject
    private Event<NavigationInfo> navigationInfoEvent;

    public String customerInfoEntry() {
        conversation.begin();
        logger.info("Conversation ID " + conversation.getId() + " Entry - CustomerInfo: " + customer);
        return navigateToPage1();
    }

    public String navigateToPage1() {
        logger.info("Conversation ID " + conversation.getId() + " On Get:  Page 1 - CustomerInfo: " + customer);
        NavigationInfo navigationInfo = new NavigationInfo("1", customer);
        navigationInfoEvent.fire(navigationInfo);
        return "conversation-customerInfo-page1";
    }

    public String navigateToPage2() {
        logger.info("Conversation ID " + conversation.getId() + " On Get:  Page 2 - CustomerInfo: " + customer);
        NavigationInfo navigationInfo = new NavigationInfo("2", customer);
        navigationInfoEvent.fire(navigationInfo);
        return "conversation-customerInfo-page2";
    }

    public String navigateToPage3() {
        logger.info("Conversation ID " + conversation.getId() + " On Get:  Page 3 - CustomerInfo: " + customer);
        NavigationInfo navigationInfo = new NavigationInfo("3", customer);
        navigationInfoEvent.fire(navigationInfo);
        return "conversation-customerInfo-page3";
    }

    public String navigateToConfirmationPage() {
        logger.info("Conversation ID " + conversation.getId() + " On Get:  Confirmation Page - CustomerInfo: " + customer);
        NavigationInfo navigationInfo = new NavigationInfo("Confirmation", customer);
        navigationInfoEvent.fire(navigationInfo);
        conversation.end();
        return "conversation-customerInfo-confirmation";
    }

}
