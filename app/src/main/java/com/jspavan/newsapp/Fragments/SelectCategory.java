package com.jspavan.newsapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.jspavan.newsapp.Adapters.CategoryListAdapter;
import com.jspavan.newsapp.R;
import com.jspavan.newsapp.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class SelectCategory extends Fragment {


    private String mParam1;
    private String mParam2;
    private RecyclerView categoryRecyclerView;
    private View v;
    private NewsPresenter.NewsCallback context;
    private NewsPresenter newsPresenter;
    private ProgressBar pbLogin;
    private TextView wishes_tv;


    public SelectCategory() {
        // Required empty public constructor
    }

    public static SelectCategory newInstance(String param1, String param2) {
        SelectCategory fragment = new SelectCategory();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_select_category, container, false);

        initViews();

        return v;
    }

    private void initViews() {
        newsPresenter =  new NewsPresenter(context);
        categoryRecyclerView = v.findViewById(R.id.categoryRecyclerView);
        pbLogin = v.findViewById(R.id.pbLogin);
        wishes_tv = v.findViewById(R.id.wishes_tv);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        
        String wishes = "";

        if(timeOfDay >= 0 && timeOfDay < 12){
            wishes = "Good Morning !";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            wishes = "Good Afternoon !";
            
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            wishes = "Good Evening !";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            wishes = "Good Night !";
        }

        wishes_tv.setText(wishes);
        
        
        
        
        ArrayList images = new ArrayList<String>(Arrays.asList("https://cdn.stocksnap.io/img-thumbs/960w/3H21KUXVT4.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/LBUMRQJ0LA.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/OJXHDWMWV9.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/RAW1RLRTM7.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/1Q2U0J72YG.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/UEDSDSAWBQ.jpg", "https://cdn.stocksnap.io/img-thumbs/960w/NW5UMULXAE.jpg"));
        ArrayList category = new ArrayList<String>(Arrays.asList("Business","Entertainment","General","Health","Sports","Technology","Science"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        categoryRecyclerView.setLayoutManager(gridLayoutManager);
        CategoryListAdapter categoryAdapter = new CategoryListAdapter(getActivity(),images,category,SelectCategory.this);
        categoryRecyclerView.setAdapter(categoryAdapter);
       // newsPresenter.getNewsDetails("us","entertainment","7061e798937642779d9dcc6588f13992");
    }

    public void gotoDashBoard(String selectedCategory) {

        Bundle bundle = new Bundle();
        bundle.putString("category",selectedCategory);
        Fragment fragment = new NewsDashBoard();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
