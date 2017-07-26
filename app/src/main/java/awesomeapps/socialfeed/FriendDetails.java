package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import awesomeapps.socialfeed.databaseHandlers.handleCreateFriendRequest;
import awesomeapps.socialfeed.databaseHandlers.handleGetFriendDetails;

public class FriendDetails extends AppCompatActivity {
    public static TextView friendUsername;
    public static TextView friendName;
    public static TextView friendDob;
    public static TextView friendGender;
    public static TextView friendStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);
        Log.i("CHIRATH", FriendSearch.selectedUsername);
        setTitle("Account Details");
        friendUsername = (TextView) findViewById(R.id.tv_username);
        friendName = (TextView) findViewById(R.id.tv_name);
        friendDob = (TextView) findViewById(R.id.tv_dob);
        friendGender = (TextView) findViewById(R.id.tv_gender2);
        friendStatus = (TextView) findViewById(R.id.tv_status);

        handleGetFriendDetails backgroundWorker = new handleGetFriendDetails(this);
        backgroundWorker.execute(FriendSearch.selectedUsername);
        friendUsername.setText(FriendSearch.selectedUsername);

    }
    
    public void onFriendRequest (View view){
        handleCreateFriendRequest backgroundWorker2 = new handleCreateFriendRequest(this);
        backgroundWorker2.execute(friendUsername.getText().toString());
    }
}
