package com.demo.joseezequielgallardo.miniuseravatar;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.joseezequielgallardo.miniuseravatar.data.User;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private List<User> userList;
    private Context context;

    public UserListAdapter(Context context, List<User> userList){
        super();
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.custom_user_list_adapter, null);
        }

        ImageView avatarImageView = view.findViewById(R.id.user_avatar_image_view);
        TextView nameTextView = view.findViewById(R.id.user_name_text_view);
        TextView emailTextView = view.findViewById(R.id.user_email_text_view);

        avatarImageView.setImageBitmap(BitmapFactory.decodeFile(this.userList.get(i).getAvatarUrl()));
        Log.d("ADAPTER", "URL: " + this.userList.get(i).getAvatarUrl());
        nameTextView.setText(this.userList.get(i).getName());
        emailTextView.setText(this.userList.get(i).getEmail());

        return view;
    }
}
