package com.example.owner.weatherinfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.owner.weatherinfo.data.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 11/25/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<City> cityList;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        cityList = new ArrayList<City>();
        this.context = context;
    }

    public void addCity(City city) { cityList.add(city); }

    public void addCity(String cityTitle){
        City newCity = new City(cityTitle);
        cityList.add(0, newCity);
        notifyItemInserted(0);
    }

    public void cityDelete(City city){
        cityList.remove(city);
        notifyDataSetChanged();
    }

    public void cityDelete(int position){
        cityList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cityRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, parent, false);
        return new ViewHolder(cityRow);

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        final City cityData = cityList.get(position);
        holder.tvCity.setText(cityData.getCityTitle());

        holder.tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intentCityDetails = new Intent(context, CityDetails.class);
                intentCityDetails.putExtra(CityDetails.KEY_CITY, cityList.get(position).getCityTitle());
                context.startActivity(intentCityDetails);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCity;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvCity);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
