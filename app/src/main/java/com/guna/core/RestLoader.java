package com.guna.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;


/**
 * Created by Gunaseelan on 10-12-2015.
 * Loader class for doing background operations.
 */
public class RestLoader extends AsyncTaskLoader<Response> {

    private Request request;

    public RestLoader(Context context, Bundle args) {
        super(context);
        if (args != null)
            request = (Request) args
                    .getSerializable(Request.QUERYPARAM);
    }

    @Override
    public Response loadInBackground() {
        return this.onLoadData(request.getURL(), request.getVerb());
    }

    private Response onLoadData(String URL, Listener.HTTPVerb verb) {
        Response response;

        switch (verb) {
            case GET:
                response = ApiProxy.get(URL);
                break;
            default:
                response = ApiProxy.get(URL);
                break;
        }

        return response;
    }

}
