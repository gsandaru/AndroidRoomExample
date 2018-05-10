package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    private static final String TAG = "ShowDataActivity";
    UserDatabase userDatabase;
    private static final String DATABASE_NAME = "users_db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        userDatabase = Room.databaseBuilder(getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        List<User> users = userDatabase.userDao().FindAll();
        Log.d(TAG, "users Size: "+ users.size());

        userAdapter = new UserAdapter(this, users);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userAdapter);

    }
}
