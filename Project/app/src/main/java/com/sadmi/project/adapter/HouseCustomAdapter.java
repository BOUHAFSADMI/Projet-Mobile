package com.sadmi.project.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sadmi.project.R;
import com.sadmi.project.activity.CommentActivity;
import com.sadmi.project.activity.DetailsActivity;
import com.sadmi.project.activity.MeetingActivity;
import com.sadmi.project.model.House;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.DeviceService;
import com.sadmi.project.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s on 14/05/17.
 */

public class HouseCustomAdapter extends RecyclerView.Adapter<HouseCustomAdapter.MyViewHolder> {

    Activity context;
    List<House> HousesList;

    public void refreshList(List<House> houses){

        this.HousesList.clear();
        this.HousesList.addAll(houses);
        this.notifyDataSetChanged();

    }

    public HouseCustomAdapter(Activity context, List<House> housesList) {
        this.context = context;
        HousesList = housesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.house_list_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final House house = HousesList.get(position);
        holder.price.setText(house.getPrice());
        holder.address.setText(house.getAddress());
        DeviceService deviceService = new DeviceService(context);
        String density = deviceService.getScreenDensity();
        Glide.with(context).load(Consts.images_url+"drawable-"+density+"/"+house.getImage()).skipMemoryCache(true).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return HousesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView price;
        TextView address;
        ImageView image;


        public MyViewHolder(final View itemView) {
            super(itemView);

            price = (TextView) itemView.findViewById(R.id.price);
            address = (TextView) itemView.findViewById(R.id.address);
            image = (ImageView) itemView.findViewById(R.id.houseImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isTwoPan()) {

                        if (HousesList.size() == 0)
                            return;

                        TextView typeField = (TextView) itemView.findViewById(R.id._type);
                        typeField.setText(HousesList.get(getAdapterPosition()).getType().toString());

                        TextView addressField = (TextView) itemView.findViewById(R.id._address);
                        addressField.setText(HousesList.get(getAdapterPosition()).getAddress());

                        TextView surfaceField = (TextView) itemView.findViewById(R.id._surface);
                        surfaceField.setText(HousesList.get(getAdapterPosition()).getSurface());


                        TextView priceField = (TextView) itemView.findViewById(R.id._price);
                        priceField.setText(HousesList.get(getAdapterPosition()).getPrice());

                        ImageView imageField = (ImageView) itemView.findViewById(R.id._houseImage);
                        DeviceService deviceService = new DeviceService(context);
                        String density = deviceService.getScreenDensity();
                        Glide.with(context).load(Consts.images_url + density + "drawable-" + density + "/" + HousesList.get(getAdapterPosition()).getImage()).skipMemoryCache(true).into(imageField);


                        /************************************* TO ADD ************************************************/
                        Button button = (Button) itemView.findViewById(R.id.comment_btn);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UserSharedPref userSharedPref = new UserSharedPref(context);
                                if (userSharedPref.isConnected()) {
                                    Intent intent = new Intent(context, CommentActivity.class);
                                    intent.putExtra("house", HousesList.get(getAdapterPosition()));
                                    context.startActivity(intent);
                                } else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                    alertDialog.setTitle("Login..");
                                    alertDialog.setMessage("You have to login first..");
                                    alertDialog.show();
                                }
                            }
                        });

                        Button button2 = (Button) itemView.findViewById(R.id.fixmeeting_btn);
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UserSharedPref userSharedPref = new UserSharedPref(context);
                                if (userSharedPref.isConnected()) {
                                    Intent intent = new Intent(context, MeetingActivity.class);
                                    intent.putExtra("house", HousesList.get(getAdapterPosition()));
                                    context.startActivity(intent);
                                } else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                    alertDialog.setTitle("Login..");
                                    alertDialog.setMessage("You have to login first..");
                                    alertDialog.show();
                                }
                            }
                        });
                        /**********************************************************************************************/
                    } else {
                        Intent intent = new Intent(context, DetailsActivity.class);

                        intent.putExtra("house", HousesList.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            });
        }

        private boolean isTwoPan() {
            View view = itemView.findViewById(R.id.details_fragment);
            return view != null;
        }
    }

}
