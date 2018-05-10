package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by User on 5/10/2018.
 */

@Dao
public interface UserDao {
    @Insert
    Long SaveUser(User user);

    @Update
    int UpdateUser(User user);

    @Query("SELECT * FROM USER WHERE USER.userid = :id")
    User FindUserById(Long id);

    @Query("SELECT * FROM USER")
    List<User> FindAll();

    @Query("DELETE FROM USER WHERE USER.userid = :id")
    void DeleteUserById(Long id);
}
