package com.guna.wiredelta.view;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.guna.core.LoaderFragment;
import com.guna.wiredelta.view.adapter.Company;
import com.guna.wiredelta.view.adapter.CompanyAdapter;
import com.guna.wiredelta.controller.CompanyController;
import com.guna.wiredelta.R;
import com.guna.wiredelta.model.CompanyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A CompanyFragment fragment containing a simple view.
 */
public class CompanyFragment extends LoaderFragment implements CompanyModel.CompanyListener, SearchView.OnQueryTextListener {

    private CompanyController controller;
    private RecyclerView companyList;
    private CompanyAdapter adapter;

    private ArrayList<Company> companies;

    public CompanyFragment() {
        controller = new CompanyController(this);
        controller.setOnCompanyListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        companyList = (RecyclerView) view.findViewById(R.id.companyList);
        companyList.setLayoutManager(new LinearLayoutManager(getActivity()));
        companyList.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.showProgressDialog("Loading companies...");
        controller.selectCompanies();
    }

    @Override
    public void onGetCompleted(ArrayList<Company> companies) {
        this.companies = companies;
        adapter = new CompanyAdapter(this.companies);
        companyList.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_company, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Company> filteredModelList = filter(companies, newText);
        Log.v("App", newText + ", " + companies.size() + ", " + filteredModelList.size());
        adapter.animateTo(filteredModelList);
        companyList.scrollToPosition(0);
        return true;
    }

    private List<Company> filter(List<Company> companies, String query) {
        query = query.toLowerCase();

        ArrayList<Company> filteredCompanyList = new ArrayList<>();
        for (Company company : companies) {
            final String name = company.comapnyName.toLowerCase();
            final String departments = company.companyDepartments.toLowerCase();
            if (name.contains(query) || departments.contains(query)) {
                filteredCompanyList.add(company);
            }
        }
        //if (filteredCompanyList.size() == 0) filteredCompanyList = (ArrayList<Company>) companies;
        return filteredCompanyList;
    }
}
