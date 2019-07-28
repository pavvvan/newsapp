package com.jspavan.newsapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;

import com.jspavan.newsapp.R;


public class SplashActivity extends AppCompatActivity {


    private static final int STORAGE_PERMISSION_CODE =23 ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        this.getSupportActionBar().hide();

        if(isReadStorageAllowed()){
            //If permission is already having then showing the toast
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    Intent toDashBoard = new Intent(SplashActivity.this, BaseActivity.class);
                    startActivity(toDashBoard);
                    finish();
                }
            }, 1000);


            return;
        }


        requestStoragePermission();

    }

    private boolean isReadStorageAllowed() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

        }

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if(requestCode == STORAGE_PERMISSION_CODE){


            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Intent toDashBoard = new Intent(SplashActivity.this, BaseActivity.class);
                        startActivity(toDashBoard);
                        finish();
                    }
                }, 1000);

                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"You just denied the permission caching will not work!",Toast.LENGTH_LONG).show();
            }
        }
    }

}
