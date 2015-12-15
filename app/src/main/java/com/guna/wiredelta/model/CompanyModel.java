package com.guna.wiredelta.model;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guna.core.ILoaderFragment;
import com.guna.core.Model;
import com.guna.core.Request;
import com.guna.core.Utility;
import com.guna.wiredelta.view.adapter.Company;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Model for company activity
 */
public class CompanyModel extends Model {

    public interface CompanyListener {
        void onGetCompleted(ArrayList<Company> companies);
    }

    private CompanyListener companyListener;

    private static final int CONTACTSUMMARY = 1;

    public CompanyModel(ILoaderFragment context) {
        super(context);

    }

    public void selectCompanies() {
        Request request = new Request();
        request.setVerb(HTTPVerb.GET);
        request.setURL(Utility.API_URL);
        Bundle arg = new Bundle();
        arg.putSerializable(Request.QUERYPARAM, request);
        this.getContext().getLoader(CONTACTSUMMARY, arg).forceLoad();
    }



    @Override
    public void onLoadDataCompleted(int id, String jsonData) {
        Gson gson = new Gson();
        switch (id) {
            case CONTACTSUMMARY:
                Type listType = new TypeToken<ArrayList<Company>>() {
                }.getType();

                ArrayList<Company> list = gson.fromJson(jsonData, listType);
                this.companyListener.onGetCompleted(list);
                break;
        }
    }

    public void setOnCompanyListener(
            CompanyListener companyListener) {
        this.companyListener = companyListener;
    }

}
