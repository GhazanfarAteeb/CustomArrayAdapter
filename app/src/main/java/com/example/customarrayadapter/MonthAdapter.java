package com.example.customarrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;

public class MonthAdapter extends BaseAdapter implements Filterable {
    ArrayList<Month> months;
    Context context;
    MonthAdapter(Context context, ArrayList<Month> months) {
        this.months = months;
        this.context = context;
    }
    @Override
    public int getCount() {
        return months.size();
    }

    @Override
    public Object getItem(int i) {
        return months.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_card_item,parent,false);

        }
        Month month =(Month) getItem(position);
        TextView monthName = convertView.findViewById(R.id.month_name);
        monthName.setText(month.getMonthName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            ArrayList<Month> filteredMonths = new ArrayList<>();
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String monthName = charSequence.toString();
                ArrayList<Month> filteredMonthList = new ArrayList<>();
                if(!monthName.isEmpty()) {
                    for (Month month : months) {
                        if(month.getMonthName().toLowerCase().contains(monthName.toLowerCase())) {
                            filteredMonthList.add(month);
                        }
                    }
                    months = filteredMonthList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredMonths;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredMonths = (ArrayList<Month>)filterResults.values;
                for (Month month : filteredMonths) {
                    System.out.println(month.getMonthName());
                }
                notifyDataSetChanged();
            }
        };

    }
    public void setDataBack(ArrayList<Month> months) {
        this.months.clear();
        this.months = months;
        notifyDataSetChanged();
    }
}
