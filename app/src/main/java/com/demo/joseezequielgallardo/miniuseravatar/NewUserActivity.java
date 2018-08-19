package com.demo.joseezequielgallardo.miniuseravatar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.joseezequielgallardo.miniuseravatar.data.DatabaseHandler;
import com.demo.joseezequielgallardo.miniuseravatar.databinding.ActivityNewUserBinding;

public class NewUserActivity extends AppCompatActivity {

    private int RESULT_LOAD_IMAGE = 1;
    private String imagePath;
    private ActivityNewUserBinding binding;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_new_user);

        binding.avatarBrowsingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        binding.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = DatabaseHandler.getInstance(context);
                String name = binding.nameEditText.getText().toString();
                String email = binding.emailEditText.getText().toString();

                if(db.insertUser(name, email, imagePath)){
                    Toast.makeText(context, R.string.success_user_added_toast_msg, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, R.string.error_user_added_toast_msg, Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    /**
     * This method will be called after picking an image from gallery
     * and we will use it for finding out the image name
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();

            String [] metaData = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, metaData, null, null, null);

            if(cursor != null && cursor.moveToFirst())
            {
                int columnIndex = cursor.getColumnIndex(metaData[0]);
                imagePath = cursor.getString(columnIndex);
                binding.avatarBrowsingTextView.setText(imagePath);
            }

            cursor.close();

        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
