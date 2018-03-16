package com.example.nilo.dublinbikeir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nilo on 09/03/2018.
 */

public class CustomAdapter  extends ArrayAdapter<LocationContract> {


    LocationContract locationContract;
    public CustomAdapter(@NonNull Context context,  int resource, @NonNull ArrayList<LocationContract> locationContractArrayList) {
        super(context, R.layout.custom_row, locationContractArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_row, parent, false);
        }

        locationContract = getItem(position);

        TextView location= (TextView) convertView.findViewById(R.id.txtLocation);
        location.setText("Location: "+locationContract.getLocation());


        TextView status= (TextView) convertView.findViewById(R.id.txtStatus);
        status.setText("Status: "+locationContract.getStatus());


        TextView bikeStand= (TextView) convertView.findViewById(R.id.txtBikeStand);
        bikeStand.setText("Bike Stands: "+locationContract.getBikeStand());


        TextView availableBike= (TextView) convertView.findViewById(R.id.txtAvailableBike);
        availableBike.setText("Available Bike: "+locationContract.getAvailableBikes());


        TextView availableBikeStand= (TextView) convertView.findViewById(R.id.txtAvailableBikeStand);
        availableBikeStand.setText("Available Bike Stands: "+locationContract.getAvailableBikeStand());


        return convertView;
    }
}
