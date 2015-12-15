package com.guna.core;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.guna.core.Listener;
import com.guna.core.Response;

/**
 * Created by Gunaseelan on 10-12-2015.
 * ILoaderFragment will be implemented in all loader fragment.
 */
public interface ILoaderFragment {
    Loader<Response> getLoader(int id, Bundle args);
    void setOnLoadDataListener(Listener onLoadDataListener);
}
