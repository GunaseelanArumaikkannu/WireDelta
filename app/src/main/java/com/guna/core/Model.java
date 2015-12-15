package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Simple base model which will be implemented by all Models those who all doing network operations.
 */
public abstract class Model implements IModel,Listener {

    private ILoaderFragment context;

    protected Model(ILoaderFragment context)
    {
        this.context=context;
        this.context.setOnLoadDataListener(this);
    }

    public ILoaderFragment getContext()
    {
        return this.context;
    }

    public void onLoadFinished(int id, String jsonData) {
        this.onLoadDataCompleted(id,jsonData);
    };
}
