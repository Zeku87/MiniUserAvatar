package com.demo.joseezequielgallardo.miniuseravatar;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.joseezequielgallardo.miniuseravatar.data.DatabaseHandler;
import com.demo.joseezequielgallardo.miniuseravatar.data.User;
import com.demo.joseezequielgallardo.miniuseravatar.databinding.ActivityUserListBinding;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class UserListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserListBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_user_list);

        DatabaseHandler db = DatabaseHandler.getInstance(this);
        List<User> userList = db.selectAllUsers();
        UserListAdapter adapter = new UserListAdapter(this, userList);
        binding.userListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(UserListActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
