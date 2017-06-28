package com.sadmi.project.util;

import android.util.Log;

import com.sadmi.project.model.KeyValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by s on 17/06/17.
 */

public class JSONParser {

    public JSONParser() {
    }


    public JSONObject makeHttpRequest(String url, String method, List<KeyValue> params){
        String json="";




        try{
            HttpURLConnection httpURLConnection = null;

            if(method.equals("POST")){
                URL _url = new URL(url);

                httpURLConnection = (HttpURLConnection)_url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String post_data = createPostData(params);
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


            }else if(method.equals("GET")){
                URL _url = new URL(url+"?"+createPostData(params));
                httpURLConnection = (HttpURLConnection)_url.openConnection();

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                //TODO
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

            }

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String line;
            StringBuilder result = new StringBuilder();
            while((line=bufferedReader.readLine())!=null){
                result.append(line);
            }
            bufferedReader.close();
            inputStream.close();

            httpURLConnection.disconnect();

            json=result.toString();
            Log.d("json....","............"+json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject JsonObj=null;
        try {
            JsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JsonObj;
    }


    public String createPostData(List<KeyValue> params){
        String post_data="";
        int i=0;
        for (KeyValue elem : params){
            i++;
            try {
                if(i!=params.size())
                    post_data += URLEncoder.encode(elem.getKey(),"UTF-8")+"="+URLEncoder.encode(elem.getValue(),"UTF-8")+"&";
                else
                    post_data += URLEncoder.encode(elem.getKey(),"UTF-8")+"="+URLEncoder.encode(elem.getValue(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return post_data;
    }
}
