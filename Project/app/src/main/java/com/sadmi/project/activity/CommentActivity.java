package com.sadmi.project.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.sadmi.project.R;
import com.sadmi.project.model.Comment;
import com.sadmi.project.model.House;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {


    House house;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent intent = getIntent();
        house = (House)intent.getSerializableExtra("house");


        new getCommentsTask().execute(house.getId()+"");
    }

    public void cancel(View view) {
        finish();
    }

    public void addComment(View view) {



        UserSharedPref userSharedPref = new UserSharedPref(this);

        EditText commentField = (EditText)findViewById(R.id.editText);
        CharSequence comment = commentField.getText();

        new addCommentTask().execute(house.getId()+"",userSharedPref.getConnectedUser(),house.getUserid(),comment.toString());
        finish();
    }



    class addCommentTask extends AsyncTask<String,String,String>{

        int success;
        String message;

        @Override
        protected String doInBackground(String... params) {

            ArrayList<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("idAnnounce",params[0]));
            parameters.add(new KeyValue("idSender",params[1]));
            parameters.add(new KeyValue("idReciever",params[2]));
            parameters.add(new KeyValue("comment",params[3]));


            JSONParser jsonParser=new JSONParser();
            JSONObject jsonObject = jsonParser.makeHttpRequest(Consts.add_comment_url,"POST",parameters);


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
            super.onPostExecute(s);
        }
    }


    class getCommentsTask extends AsyncTask<String,String,String>{

        List<String> listComments;
        int success;
        @Override
        protected String doInBackground(String... params) {
            ArrayList<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("idAnnounce",params[0]));

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = jsonParser.makeHttpRequest(Consts.get_comments,"GET",parameters);
            listComments=new ArrayList<>();
            if(jsonObject!=null){
                try {
                    success=jsonObject.getInt(Consts.TAG_SUCCESS);
                    if(success==1){

                        JSONArray comments = jsonObject.getJSONArray(Consts.TAG_COMMENTS);

                        for(int i=0; i<comments.length();i++){
                            JSONObject comment = comments.getJSONObject(i);
                            String idSender = comment.getString(Consts.TAG_SENDER);
                            String the_comment = comment.getString(Consts.TAG_COMMENT);

                            listComments.add(idSender+" :  "+the_comment);
                        }


                    }else {

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

            ListView listView = (ListView)findViewById(R.id.comments_list);
            ArrayAdapter arrayAdapter = new ArrayAdapter(CommentActivity.this, android.R.layout.simple_list_item_1, listComments);
            listView.setAdapter(arrayAdapter);

        }
    }
}
