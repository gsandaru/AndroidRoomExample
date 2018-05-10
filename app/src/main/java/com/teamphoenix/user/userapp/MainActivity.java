package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    UserDatabase userDatabase;
    private static final String DATABASE_NAME = "users_db";
    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.txtContact)
    EditText txtContact;

    @BindView(R.id.txtEmail)
    EditText txtEmail;

    @BindView(R.id.textView)
    TextView textView;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void saveuser(View view) {
        Long id =  userDatabase.userDao().SaveUser(new User(null,txtName.getText().toString(),txtContact.getText().toString(),txtEmail.getText().toString()));
        Log.d(TAG, "saveuser: " + id.toString());
    }

    public void viewAll(View view) {
        List<User> users= userDatabase.userDao().FindAll();
        Log.d(TAG, "viewAll: ");
        String strdata = "";
        for (User user: users) {
            Log.d(TAG, "for (User user: users): "  + user.toString());
            strdata = strdata + user.toString();
        }
        textView.setText(strdata);
    }

    public void gotoSecondActivity(View view) {
        startActivityForResult(new Intent(this,ShowDataActivity.class) , 1);
    }

}
