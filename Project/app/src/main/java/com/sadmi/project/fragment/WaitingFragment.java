package com.sadmi.project.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.sadmi.project.R;
import com.sadmi.project.adapter.MeetingCustomAdapter;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.Meeting;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    MeetingCustomAdapter meetingCustomAdapter;
    List<Meeting> meetingList =new ArrayList<>();


    public WaitingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view==null)
                view = inflater.inflate(R.layout.fragment_waiting, container, false);

        UserSharedPref userSharedPref = new UserSharedPref(getActivity());
        new getWaitingMeetingsTask().execute(userSharedPref.getConnectedUser());

        //prepareData();


        return view;
    }




    class getWaitingMeetingsTask extends AsyncTask<String,String,String>{

        int success;
        String message;

        @Override
        protected String doInBackground(String... params) {


            ArrayList<KeyValue> parameters = new ArrayList<>();
            UserSharedPref userSharedPref = new UserSharedPref(getActivity());

            parameters.add(new KeyValue("idReciever",userSharedPref.getConnectedUser()));
            parameters.add(new KeyValue("status","waiting"));


            JSONParser jsonParser=new JSONParser();
            JSONObject jsonObject = jsonParser.makeHttpRequest(Consts.get_waiting_meetings_url,"GET",parameters);


            if(jsonObject!=null){
                try {
                    success=jsonObject.getInt(Consts.TAG_SUCCESS);
                    message=jsonObject.getString(Consts.TAG_MESSAGE);
                    if(success==1){
                        JSONArray meetings = jsonObject.getJSONArray(Consts.TAG_MEETINGS);


                        for(int i=0;i<=meetings.length();i++ ){
                            JSONObject jsonObj = meetings.getJSONObject(i);
                            int id = jsonObj.getInt(Consts.TAG_ID);
                            int announce = jsonObj.getInt(Consts.TAG_ANNOUNCE);
                            String idSender = jsonObj.getString(Consts.TAG_SENDER);
                            String idReciever = jsonObj.getString(Consts.TAG_RECIEVER);
                            String date = jsonObj.getString(Consts.TAG_DATE);
                            String time = jsonObj.getString(Consts.TAG_TIME);


                            Meeting meeting = new Meeting(id,announce,idSender,idReciever,date,time,"waiting");
                            meetingList.add(meeting);
                        }
                    }

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
            super.onPostExecute(s);
            recyclerView = (RecyclerView) view.findViewById(R.id.meeting_list);
            meetingCustomAdapter = new MeetingCustomAdapter(getActivity(), meetingList);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            itemAnimator.setAddDuration(1000);
            itemAnimator.setRemoveDuration(1000);
            recyclerView.setItemAnimator(itemAnimator);
            recyclerView.setAdapter(meetingCustomAdapter);
        }
    }



}
