package com.sadmi.project.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sadmi.project.R;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.Meeting;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s on 14/05/17.
 */

public class MeetingCustomAdapter extends RecyclerView.Adapter<MeetingCustomAdapter.MyViewHolder> {
    private Context context;
    private List<Meeting> MeetingsList;


    public MeetingCustomAdapter(Context context, List<Meeting> meetingsList) {
        this.context = context;
        MeetingsList = meetingsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_row_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        final Meeting meeting = MeetingsList.get(position);
        if (meeting.getStatus().equals("accepted"))
            holder.accept_btn.setVisibility(View.INVISIBLE);
        if (meeting.getStatus().equals("rejected"))
            holder.reject_btn.setVisibility(View.INVISIBLE);
        holder.meeting_name.setText(meeting.getSender());
        holder.meeting_date.setText(meeting.getDate());

        holder.accept_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(!meeting.getStatus().equals("accepted")){
                       remove(meeting);
                       new updateTask().execute(meeting.getId()+"","accepted");
                   }



              }
          });

        holder.reject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                if(!meeting.getStatus().equals("rejected")){
                    remove(meeting);
                    new updateTask().execute(meeting.getId()+"","rejected");
                }


             }
         });
    }

    private void remove(Meeting meeting) {
            int position = MeetingsList.indexOf(meeting);
            MeetingsList.remove(position);
            notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return MeetingsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView meeting_name;
        TextView meeting_date;
        Button accept_btn ;
        Button reject_btn ;

        public MyViewHolder(View itemView) {
            super(itemView);
            meeting_name = (TextView)itemView.findViewById(R.id.meeting_name);
            meeting_date = (TextView)itemView.findViewById(R.id.meeting_date);
            accept_btn = (Button) itemView.findViewById(R.id.accept_btn);
            reject_btn = (Button) itemView.findViewById(R.id.reject_btn);
        }
    }


    class  updateTask extends AsyncTask<String,String,String >{

        @Override
        protected String doInBackground(String... params) {

            ArrayList<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("id",params[0]));
            parameters.add(new KeyValue("status",params[1]));

            JSONParser jsonParser = new JSONParser();
            jsonParser.makeHttpRequest(Consts.update_meeting_url,"POST",parameters);

            return null;
        }


    }
}
