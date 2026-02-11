package com.github.alphageekcoder.jakartaee.cdi_named_beans.controller;

import java.util.logging.Logger;

import com.github.alphageekcoder.jakartaee.cdi_named_beans.beans.Customer;
import com.github.alphageekcoder.jakartaee.cdi_named_beans.beans.Premium;
import com.github.alphageekcoder.jakartaee.cdi_named_beans.beans.PremiumCustomer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CustomerController {

    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Inject
    @Premium
    private Customer customer;

    public String saveCustomer() {
        PremiumCustomer premiumCustomer = (PremiumCustomer) customer;
        // logger.info("Saving customer: " + customer.getFirstName() + " " + customer.getLastName() 
        logger.info("Saving customer: " + premiumCustomer.getFirstName() + " " + premiumCustomer.getLastName() +
                    " with discount code: " + premiumCustomer.getDiscountCode());
        return "confirmation";
    }
}
