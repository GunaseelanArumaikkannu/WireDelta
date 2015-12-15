package com.guna.wiredelta.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guna.wiredelta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gunaseelan on 11-12-2015.
 * Simple adapter class, used for show all companies in list
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    ArrayList<Company> companies;

    public CompanyAdapter(List<Company> companies) {
        this.companies = new ArrayList<>(companies);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(companies.get(position));
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public void animateTo(List<Company> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Company> newModels) {
        for (int i = companies.size() - 1; i >= 0; i--) {
            final Company model = companies.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Company> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Company model = newModels.get(i);
            if (!companies.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Company> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Company model = newModels.get(toPosition);
            final int fromPosition = companies.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Company removeItem(int position) {
        final Company model = companies.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Company model) {
        companies.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Company model = companies.remove(fromPosition);
        companies.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textName;
        private TextView textOwner;
        private TextView textStartedOn;
        private TextView textDescription;
        private TextView textDepartments;
        private LinearLayout llDescription;
        private LinearLayout llListItem;

        public ViewHolder(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.textName);
            textOwner = (TextView) v.findViewById(R.id.textOwner);
            textStartedOn = (TextView) v.findViewById(R.id.textStartedOn);
            textDescription = (TextView) v.findViewById(R.id.textDescription);
            textDepartments = (TextView) v.findViewById(R.id.textDepartments);

            llDescription = (LinearLayout) v.findViewById(R.id.llDescription);
            llListItem = (LinearLayout) v.findViewById(R.id.llListItem);

            llListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicked();
                }
            });
        }

        public void bindData(Company company) {
            textName.setText(company.comapnyName);
            textOwner.setText(company.companyOwner);
            textStartedOn.setText(company.companyStartDate);
            textDescription.setText(company.companyDescription);
            textDepartments.setText(company.companyDepartments);
        }

        public void onItemClicked() {
            if (llDescription.getVisibility() == View.VISIBLE)
                llDescription.setVisibility(View.GONE);
            else llDescription.setVisibility(View.VISIBLE);
        }
    }

}
