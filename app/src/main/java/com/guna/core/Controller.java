package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Base abstract class will be extended in all controllers.
 */


public abstract class Controller<T>{

    private T model;

    protected Controller(T model) {
        this.setModel(model);
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

}
