package com.sadmi.project.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sadmi.project.R;
import com.sadmi.project.adapter.HouseCustomAdapter;
import com.sadmi.project.model.House;
import com.sadmi.project.model.HouseType;
import com.sadmi.project.model.KeyValue;
import com.sadmi.project.model.Wilaya;
import com.sadmi.project.util.Consts;
import com.sadmi.project.util.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    RecyclerView houseList;
    View view;

    HouseType houseType= HouseType.appartement;

    TabLayout tabLayout;
    HouseCustomAdapter houseCustomAdapter;


    List<House> listToShow = new ArrayList<>();

    List<House> listToShow2 = new ArrayList<>();



    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(view==null){
            view = inflater.inflate(R.layout.fragment_list, container, false);
        }

        houseList =(RecyclerView)view.findViewById(R.id.houseList);

        final Spinner spinner = (Spinner)view.findViewById(R.id.wilaya);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_expandable_list_item_1,Wilaya.values()));



        tabLayout = (TabLayout)view.findViewById(R.id.tab);


        houseCustomAdapter = new HouseCustomAdapter(getActivity(), listToShow);

        /*********************************************** TO ADD ******************************************/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                //listToShow = filterByWilaya(list ,Wilaya.values()[i].toString());
                new loadAnnouncesTask(getActivity(), houseList).execute(Wilaya.values()[i].toString());

                tabLayout.getTabAt(0).select();

                listToShow2 = filterByBuilding(listToShow, houseType);
                houseCustomAdapter.refreshList(listToShow2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition())
                {
                    case 0:
                        houseType = HouseType.appartement;
                        break;
                    case 1:
                        houseType = HouseType.villa;
                        break;
                    case 2:
                        houseType = HouseType.studio;
                        break;
                    case 3:
                        houseType = HouseType.duplex;
                        break;
                    default:
                        houseType = HouseType.appartement;
                }

                listToShow2 = filterByBuilding(listToShow, houseType);
                houseCustomAdapter.refreshList(listToShow2);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /**************************************************************************************************/


        /************************* TO ADD ***********************************************/
        //listToShow = filterByWilaya(list, spinner.getSelectedItem().toString());
        /**********************************************************************************/

        return view;
    }



    /******************************************* TO add ***********************************************/
    private ArrayList<House> filterByWilaya(List<House> list, String wilaya){
        ArrayList<House> result = new ArrayList<House>();
        Wilaya w = Wilaya.valueOf(wilaya.toUpperCase());

        for(House h : list){
            if(h.getWilaya() == w)
                result.add(h);
        }

        return result;
    }

    private ArrayList<House> filterByBuilding(List<House> list, HouseType houseType){
        ArrayList<House> result = new ArrayList<House>();
        for(House h : list){
            if(h.getType() == houseType)
                result.add(h);
        }

        return result;
    }


    /*************************************************************************************************/


    private class loadAnnouncesTask extends AsyncTask<String,String,String>{
        JSONArray Announces = null;
        ProgressDialog pDialog;

        Activity context;
        RecyclerView recyclerView;

        public loadAnnouncesTask(Activity context, RecyclerView recyclerView) {
            this.context = context;
            this.recyclerView = recyclerView;
        }

        @Override
        protected String doInBackground(String... params) {


            JSONParser jsonParser = new JSONParser();
            int success;

            List<KeyValue> parameters = new ArrayList<>();
            parameters.add(new KeyValue("wilaya",params[0]));

            JSONObject jsonObj = jsonParser.makeHttpRequest(Consts.get_announces_url,"GET",parameters);

            try {

                if(jsonObj!=null){
                    Log.d("status:", jsonObj.toString());
                    success= jsonObj.getInt(Consts.TAG_SUCCESS);

                    if(success==1){
                        listToShow.clear();
                        Announces = jsonObj.getJSONArray(Consts.TAG_ANNOUNCES);
                        for(int i=0; i<Announces.length();i++){
                            JSONObject announce = Announces.getJSONObject(i);

                            int id = announce.getInt(Consts.TAG_ID);
                            String price = announce.getString(Consts.TAG_PRICE);
                            String address = announce.getString(Consts.TAG_ADDRESS);
                            String image = announce.getString(Consts.TAG_IMAGE);
                            String surface = announce.getString(Consts.TAG_SURFACE);
                            String type = announce.getString(Consts.TAG_TYPE);
                            String wilaya = announce.getString(Consts.TAG_WILAYA);
                            String image1 = announce.getString(Consts.TAG_IMAGE+"1");
                            String image2 = announce.getString(Consts.TAG_IMAGE+"2");
                            String image3 = announce.getString(Consts.TAG_IMAGE+"3");
                            String userid = announce.getString(Consts.TAG_USERID);
                            Double lat = announce.getDouble(Consts.TAG_LAT);
                            Double lng = announce.getDouble(Consts.TAG_LNG);

                            House house = new House(id, surface,  address,  Wilaya.valueOf(wilaya),  HouseType.valueOf(type),  price,  image,  image1,  image2,  image3,  lat,  lng,userid);


                            listToShow.add(house);
                            Log.d("listToShow........",listToShow.get(i).getAddress());
                        }
                    }else {
                        pDialog.dismiss();
                        Log.d("retrieving failed..","...........................");
                    }
                }else
                {
                    pDialog.dismiss();
                    Log.d("null json obj..",".............................");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Data..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pDialog.dismiss();

            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listToShow2 = filterByBuilding(listToShow,houseType);
                    houseCustomAdapter = new HouseCustomAdapter(context, listToShow2);
                    GridLayoutManager mLayoutManager = new GridLayoutManager(context ,2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);
                    recyclerView.setAdapter(houseCustomAdapter);
                }
            });
        }
    }
}
