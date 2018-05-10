package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class itemUpdateActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "users_db";


    EditText UpdatetxtName;


    EditText UpdatetxtContact;


    EditText UpdatetxtEmail;


    Button UpdatebtnSave;

    UserDatabase userDatabase;
    private static final String TAG = "itemUpdateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);



        Intent i = getIntent();
        Long id =i.getLongExtra("UserId",-1L);

        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        UpdatetxtName = (EditText) findViewById(R.id.UpdatetxtName);
        UpdatetxtContact= (EditText) findViewById(R.id.UpdatetxtContact);
        UpdatetxtEmail= (EditText) findViewById(R.id.UpdatetxtEmail);
        UpdatebtnSave = (Button) findViewById(R.id.UpdatebtnSave);
        User user = new User();
        if(id != -1L ){
            user= userDatabase.userDao().FindUserById(id);

                UpdatetxtName.setText(user.getUsername().toString());
                UpdatetxtContact.setText(user.getUsercontact().toString());
                UpdatetxtEmail.setText(user.getUseremail().toString());
            Log.d(TAG, "getid: "+user.getUsername().toString());

        }

        UpdatebtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                Long id =i.getLongExtra("UserId",-1L);
                User user = new User();
                user.setUserid(id);
                user.setUsername(UpdatetxtName.getText().toString());
                user.setUsercontact(UpdatetxtContact.getText().toString());
                user.setUseremail(UpdatetxtEmail.getText().toString());
                int uid = userDatabase.userDao().UpdateUser(user);
                Toast.makeText(itemUpdateActivity.this, "No of Updated Rows "+ uid , Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finishActivity(1);
        startActivity(new Intent(itemUpdateActivity.this, ShowDataActivity.class));
        finish();

    }


}
