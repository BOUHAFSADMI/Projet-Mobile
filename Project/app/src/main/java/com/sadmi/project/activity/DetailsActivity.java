package com.sadmi.project.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sadmi.project.model.House;
import com.sadmi.project.R;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.DeviceService;

public class DetailsActivity extends AppCompatActivity {

    private House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        house = (House) intent.getSerializableExtra("house");

        TextView type =(TextView)findViewById(R.id._type);
        type.setText(house.getType().toString());

        TextView address =(TextView)findViewById(R.id._address);
        address.setText(house.getAddress());

        TextView surface = (TextView)findViewById(R.id._surface);
        surface.setText(house.getSurface());
        System.out.println(house.getSurface());


        TextView price = (TextView)findViewById(R.id._price);
        price.setText(house.getPrice());

        ImageView image =(ImageView)findViewById(R.id._houseImage);
        DeviceService deviceService = new DeviceService(this);
        String density = deviceService.getScreenDensity();
        Glide.with(this).load(Consts.images_url+"drawable-"+density+"/"+house.getImage()).skipMemoryCache(true).into(image);


        ImageView image1 =(ImageView)findViewById(R.id._houseImage1);
        Glide.with(this).load(Consts.images_url+"drawable-"+density+"/"+house.getImage1()).skipMemoryCache(true).into(image1);
        ImageView image2 =(ImageView)findViewById(R.id._houseImage2);
        Glide.with(this).load(Consts.images_url+"drawable-"+density+"/"+house.getImage2()).skipMemoryCache(true).into(image2);
        ImageView image3 =(ImageView)findViewById(R.id._houseImage3);
        Glide.with(this).load(Consts.images_url+"drawable-"+density+"/"+house.getImage3()).skipMemoryCache(true).into(image3);



        /************************************* TO ADD ************************************************/
        Button button = (Button) findViewById(R.id.comment_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSharedPref userSharedPref = new UserSharedPref(DetailsActivity.this);
                if(userSharedPref.isConnected()){
                    Intent intent = new Intent(DetailsActivity.this, CommentActivity.class);
                    intent.putExtra("house",house);
                    startActivity(intent);
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsActivity.this).create();
                    alertDialog.setTitle("Login..");
                    alertDialog.setMessage("You have to login first..");
                    alertDialog.show();
                }

            }
        });

        Button button2 = (Button) findViewById(R.id.fixmeeting_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSharedPref userSharedPref = new UserSharedPref(DetailsActivity.this);
                if(userSharedPref.isConnected()){
                    Intent intent = new Intent(DetailsActivity.this, MeetingActivity.class);
                    intent.putExtra("house",house);
                    startActivity(intent);
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetailsActivity.this).create();
                    alertDialog.setTitle("Login..");
                    alertDialog.setMessage("You have to login first..");
                    alertDialog.show();
                }

            }
        });
        /**********************************************************************************************/

    }

    public void houseGeocal(View view) {
        Intent intent = new Intent(DetailsActivity.this,MapsActivity.class);
        intent.putExtra("house",house);
        startActivity(intent);
    }
}
