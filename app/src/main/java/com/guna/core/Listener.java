package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Simple base listener for network operations.
 */
public interface Listener {
    void onLoadFinished(int id ,String jsonData);

    enum HTTPVerb {
        GET,
        /*POST,
        PUT,
        DELETE*/
    }
}
