package com.demo.joseezequielgallardo.miniuseravatar;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demo.joseezequielgallardo.miniuseravatar.databinding.ActivityMainBinding;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        EasyPermissions.requestPermissions(this,
                "Access to your gallery is needed", 1, galleryPermissions);

//
//        boolean hasPermission = EasyPermissions.hasPermissions(this, galleryPermissions);
//        Log.d("Permission", "" + hasPermission);

        binding.newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewUserActivity.class);
                startActivity(i);
                finish();
            }
        });

        binding.userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
