package com.example.raviteja.runtime;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE1 = 1;
    private static final int PERMISSION_REQUEST_CODE2 = 2;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        mRef= new Firebase("https://runtime-f93e8.firebaseio.com/");

    }
    private boolean checkPermission1()
    {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkPermission2() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    public void requestPermission2() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE2);
        }
    }



    private void requestPermission1() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET)) {
            Toast.makeText(MainActivity.this, "Internet permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use internet");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
            case PERMISSION_REQUEST_CODE2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use internet");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;

        }
    }
    public void onClick(View v)
    {

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission1())
            {
                Log.e("ans internet", "Permission Granted, Now you can use internet");
                // Code for above or equal 23 API Oriented Device
                // Create a common Method for both
                //Firebase mRefChild = mRef.child("name");
                //mRefChild.setValue("ravi");

            } else {
                requestPermission1();
                Log.e("ans internt", "Permission Granted, Now you can use internet.");
            }
            if (checkPermission2())
            {
                Log.e("ans1 storage", "Permission Granted, Now you can use storage");
                // Code for above or equal 23 API Oriented Device
                // Create a common Method for both
                //Firebase mRefChild = mRef.child("name");
                //mRefChild.setValue("ravi");

            } else {
                requestPermission2();
                Log.e("ans1 storage", "Permission Granted, Now you can use storage.");
            }
        }
        else
        {
            Log.e("non internet", "Permission Granted, Now you can use internet");

            // Code for Below 23 API Oriented Device
            // Create a common Method for both
        }
    }
}
