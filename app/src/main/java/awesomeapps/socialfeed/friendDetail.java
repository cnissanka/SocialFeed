package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import awesomeapps.socialfeed.databaseHandlers.handleGetFriendDetail;
import awesomeapps.socialfeed.databaseHandlers.handleUnFriend;

public class friendDetail extends AppCompatActivity {
    public static TextView lbl_username,lbl_dob,lbl_gender,lbl_fullname,lbl_status;
    public static String sUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        setTitle("Account Details");
        lbl_username = (TextView) findViewById(R.id.lbl_username);
        lbl_dob = (TextView) findViewById(R.id.lbl_dob);
        lbl_gender = (TextView) findViewById(R.id.lbl_gender);
        lbl_fullname = (TextView) findViewById(R.id.lbl_fullname);
        lbl_status = (TextView) findViewById(R.id.lbl_status);
        sUsername = FriendList.selectedusername;
        handleGetFriendDetail backgroundWorker = new handleGetFriendDetail(this);
        backgroundWorker.execute(FriendList.selectedusername);
    }

    public void onChat (View view){
        Intent intent = new Intent("awesomeapps.socialfeed.ChatApp");
        startActivity(intent);
    }

    public void onUnFriend (View view){
        handleUnFriend backgroundWorker = new handleUnFriend(this);
        backgroundWorker.execute(lbl_username.getText().toString());
    }
}
