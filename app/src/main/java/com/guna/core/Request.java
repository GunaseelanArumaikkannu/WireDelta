package com.guna.core;

import java.io.Serializable;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Request entity used to create request for network operation.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -8495460577069487790L;
    public static final String QUERYPARAM = "Request";
    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    private Listener.HTTPVerb verb;

    public Listener.HTTPVerb getVerb() {
        return verb;
    }

    public void setVerb(Listener.HTTPVerb verb) {
        this.verb = verb;
    }

}
