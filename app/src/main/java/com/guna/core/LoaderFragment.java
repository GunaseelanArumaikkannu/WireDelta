package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * LoaderFragment will be act as base fragment for all fragment those all
 * implementing network operations.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

public abstract class LoaderFragment extends Fragment implements
        LoaderCallbacks<Response>, ILoaderFragment {
    private Listener onLoadDataListener;
    private Common common;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.common = new Common(this.getActivity());
        this.initLoader();
    }

    public Loader<Response> getLoader(int id, Bundle args) {
        return getLoaderManager().restartLoader(id, args, this);
    }

    public void initLoader() {
        getLoaderManager().initLoader(0, null, this);
    }

    public void setOnLoadDataListener(Listener onLoadDataListener) {
        this.onLoadDataListener = onLoadDataListener;
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        return new RestLoader(this.getActivity(), args);
    }

    @Override
    public void onLoadFinished(Loader<Response> loader,
                               Response response) {
        hideProgressDialog();
        if (response.isStatus())
            this.onLoadDataListener.onLoadFinished(loader.getId(),
                    response.getResult());
        else {
            this.toast(response.getMessage());
            System.out.println("LoaderListFragment 1 : "
                    + response.getMessage());
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        loader.reset();
    }

    protected void showProgressDialog(String text) {
        this.common.showProgressDialog(text);
    }

    protected void hideProgressDialog() {
        this.common.hideProgressDialog();
    }

    protected void toast(String message) {
        this.common.toast(message);
    }
}
