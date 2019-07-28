package com.jspavan.newsapp.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jspavan.newsapp.Fragments.SelectCategory;
import com.jspavan.newsapp.R;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Fragment frag =  new SelectCategory();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                frag, frag.getClass().getSimpleName()).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

    }
}
