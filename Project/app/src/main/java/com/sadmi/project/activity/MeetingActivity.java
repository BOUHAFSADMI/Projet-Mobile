package com.sadmi.project.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sadmi.project.R;
import com.sadmi.project.model.House;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

    }

    public void cancel(View view) {
        finish();
    }

    public void addMeeting(View view) {
        DatePicker datePicker = (DatePicker)findViewById(R.id.date_picker);
        String date = datePicker.getDayOfMonth() + "/"+ datePicker.getMonth() +"/" + datePicker.getYear();
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        String time;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            time = timePicker.getHour() + ":" + timePicker.getMinute();
        }else {
            time = "10:25";
        }


        Intent intent = getIntent();
        House house = (House)intent.getSerializableExtra("house");
        UserSharedPref userSharedPref = new UserSharedPref(this);
        new addMeetingTask().execute(house.getId()+"",userSharedPref.getConnectedUser(),house.getUserid(),date,time);
        finish();
    }



    public class addMeetingTask extends AsyncTask<String,String,String>{

        ProgressDialog pDialog;
        AlertDialog aDialog;
        int success;
        String message;
        @Override
        protected String doInBackground(String... params) {



            JSONParser jsonParser = new JSONParser();

            List<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("idAnnounce",params[0]));
            parameters.add(new KeyValue("idSender",params[1]));
            parameters.add(new KeyValue("idReciever",params[2]));
            parameters.add(new KeyValue("date",params[3]));
            parameters.add(new KeyValue("time",params[4]));
            parameters.add(new KeyValue("status","waiting"));


            JSONObject jsonObject = jsonParser.makeHttpRequest(Consts.add_meeting_url,"POST",parameters);


            if(jsonObject!=null){
                try {
                    success=jsonObject.getInt(Consts.TAG_SUCCESS);
                    message=jsonObject.getString(Consts.TAG_MESSAGE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);;
        }
    }
}
