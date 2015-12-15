package com.guna.wiredelta.controller;


import com.guna.core.Controller;
import com.guna.core.ILoaderFragment;
import com.guna.wiredelta.model.CompanyModel;
import com.guna.wiredelta.view.adapter.Company;

import java.util.ArrayList;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Controller for company activity.
 */
public class CompanyController extends Controller<CompanyModel>
        implements CompanyModel.CompanyListener {

    private CompanyModel.CompanyListener companyListener;

    public CompanyController(ILoaderFragment context) {
        super(new CompanyModel(context));
        this.getModel().setOnCompanyListener(this);
    }

    public void selectCompanies()
    {
        this.getModel().selectCompanies();
    }

    @Override
    public void onGetCompleted(ArrayList<Company> companies) {
        this.companyListener.onGetCompleted(companies);
    }

    public void setOnCompanyListener(
            CompanyModel.CompanyListener companyListener) {
        this.companyListener = companyListener;
    }
}
