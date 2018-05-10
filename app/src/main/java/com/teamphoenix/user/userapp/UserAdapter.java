package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by User on 5/10/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    List<User> userList;
    private Context mContext;


    public UserAdapter( Context mContext,List<User> userList) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserAdapter.MyViewHolder holder, final int position) {
        User user = userList.get(position);
        holder.row_username.setText(user.getUsername());
        holder.row_contact.setText(user.getUsercontact());
        holder.row_email.setText(user.getUseremail());
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Update POSITION " + position, Toast.LENGTH_SHORT).show();
                UserDatabase userDatabase;
                String DATABASE_NAME = "users_db";
                userDatabase = Room.databaseBuilder(view.getContext(),UserDatabase.class,DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                userDatabase.userDao().DeleteUserById(Long.valueOf(position));
                userList.remove(position);
                notifyItemChanged(position);
            }
        });
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = userList.get(position);
                Long id = user.getUserid();
                Toast.makeText(mContext, "DELETE POSITION " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.itemView.getContext(),itemUpdateActivity.class);
                intent.putExtra("UserId", id);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView row_username,row_contact,row_email;
        Button btndelete,btnupdate;
        public MyViewHolder(View itemView) {
            super(itemView);
            row_username =( TextView) itemView.findViewById(R.id.row_username);
            row_contact = ( TextView) itemView.findViewById(R.id.row_contact);
            row_email = ( TextView) itemView.findViewById(R.id.row_email);
            btndelete = (Button) itemView.findViewById(R.id.btnRemove);
            btnupdate = (Button) itemView.findViewById(R.id.btnEdit);

        }
    }
}
