package com.sadmi.project.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sadmi.project.R;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.UserSharedPref;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    EditText emailField,passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        emailField = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password);
    }

    public void showMain(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void Login(View view) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String type="login";

        new LoginTask().execute(type,email,password);
    }





    public class LoginTask extends AsyncTask<String,String,String> {

        ProgressDialog pDialog;
        AlertDialog aDialog;

        public LoginTask() {  }

        @Override
        protected String doInBackground(String... params) {


            String type=params[0];
            int success;

            List<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("email",params[1]));
            parameters.add(new KeyValue("password",params[2]));


            Log.d("email:           ",params[1]);
            Log.d("password:           ",params[2]);

            if(type.equals("login")){
                JSONParser jsonParser = new JSONParser();
                JSONObject json = jsonParser.makeHttpRequest(Consts.login_url,"POST",parameters);

                try {
                    if(json!=null)
                    {
                        Log.d("login status:", json.toString());
                        success=json.getInt(Consts.TAG_SUCCESS);

                        if(success==1){
                            UserSharedPref userSharedPref = new UserSharedPref(HomeActivity.this);
                            userSharedPref.saveConnectedUser(params[1]);

                            Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                            startActivity(intent);
                            finish();
                        }else
                        {
                            pDialog.dismiss();
                            /*aDialog  = new AlertDialog.Builder(HomeActivity.this).create();
                            aDialog.setTitle("Login Failed...");
                            aDialog.setMessage("Check your Credentials");
                            aDialog.show();
                            Log.d("failed","no email & pass");*/
                        }
                    }else
                    {
                        pDialog.dismiss();
                        /*aDialog = new AlertDialog.Builder(HomeActivity.this).create();
                        aDialog.setTitle("Login Failed...");
                        aDialog.setMessage("Check your Connection");
                        aDialog.show();
                        Log.d("failed","connection");*/
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Login..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }
    }

}
