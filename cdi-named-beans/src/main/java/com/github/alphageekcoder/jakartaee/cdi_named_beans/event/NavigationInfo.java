package com.github.alphageekcoder.jakartaee.cdi_named_beans.event;

import java.io.Serializable;

import com.github.alphageekcoder.jakartaee.cdi_named_beans.model.CustomerInfo;


public class NavigationInfo implements Serializable {

    private String page;

    private CustomerInfo customerInfo;


    public NavigationInfo() {
    }

    public NavigationInfo(String page, CustomerInfo customerInfo) {
        this.page = page;
        this.customerInfo = customerInfo;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Override
    public String toString() {
        return "NavigationInfo [page=" + page + ", customerInfo=" + customerInfo + "]";
    }


}
