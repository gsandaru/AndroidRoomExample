package com.teamphoenix.user.userapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by User on 5/10/2018.
 */

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long userid;
    private String username;
    private String usercontact;
    private String useremail;

    public User() {
    }

    public User(Long userid, String username, String usercontact, String useremail) {
        this.userid = userid;
        this.username = username;
        this.usercontact = usercontact;
        this.useremail = useremail;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercontact() {
        return usercontact;
    }

    public void setUsercontact(String usercontact) {
        this.usercontact = usercontact;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }


    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", usercontact='" + usercontact + '\'' +
                ", useremail='" + useremail + '\'' +
                '}';
    }
}
