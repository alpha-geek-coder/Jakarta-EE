package com.github.alphageekcoder.jakartaee.cdi_named_beans.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
@Premium
public class PremiumCustomer extends Customer {

    private Integer discountCode;

    public PremiumCustomer() {
        super();
        discountCode = 100; // Default discount code for premium customers
    }
    public Integer getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Integer discountCode) {
        this.discountCode = discountCode;
    }

}
