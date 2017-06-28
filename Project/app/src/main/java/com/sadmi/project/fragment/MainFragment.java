package com.sadmi.project.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sadmi.project.R;
import com.sadmi.project.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    View view;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(view==null)
            view=inflater.inflate(R.layout.fragment_main, container, false);


        /*************************************************************/
            getFragmentManager().beginTransaction()
                    .replace(R.id.container,
                            new WaitingFragment()).commit();
        /*************************************************************/
        TabLayoutClicked();

        return view;
    }




    private void TabLayoutClicked() {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:
                        getFragmentManager().beginTransaction().addToBackStack(null)
                                .replace(R.id.container,
                                        new WaitingFragment()).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().addToBackStack(null)
                                .replace(R.id.container,
                                        new AcceptedFragment()).commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().addToBackStack(null)
                                .replace(R.id.container,
                                        new RejectedFragment()).commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
