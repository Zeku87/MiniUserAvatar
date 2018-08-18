package com.demo.joseezequielgallardo.miniuseravatar;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.joseezequielgallardo.miniuseravatar.databinding.ActivityNewUserBinding;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_user);
    }
}
