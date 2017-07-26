package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import awesomeapps.socialfeed.databaseHandlers.handleAcceptFriendRequest;
import awesomeapps.socialfeed.databaseHandlers.handleGetFriendRequestDetails;
import awesomeapps.socialfeed.databaseHandlers.handleRejectFriendRequest;

public class FriendRequestDetail extends AppCompatActivity {
    public static TextView lblFullName,lblUsername,lblDob,lblGender,lblStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request_detail);

        lblFullName = (TextView) findViewById(R.id.lbl_FullName);
        lblUsername = (TextView) findViewById(R.id.lbl_Username);
        lblDob = (TextView) findViewById(R.id.lbl_Dob);
        lblGender = (TextView) findViewById(R.id.lbl_Gender);
        lblStatus = (TextView) findViewById(R.id.lbl_Status);
        setTitle("Account Details");
        lblUsername.setText(FriendRequests.usernameSelected);

        handleGetFriendRequestDetails backgroundWorker = new handleGetFriendRequestDetails(this);
        backgroundWorker.execute(FriendRequests.usernameSelected);
    }

    public void onAcceptFriendRequest (View view){
        handleAcceptFriendRequest backgroundWorker = new handleAcceptFriendRequest(this);
        backgroundWorker.execute(FriendRequests.usernameSelected);
    }

    public void onRejectFriendRequest(View view){
        handleRejectFriendRequest backgroundWorker = new handleRejectFriendRequest(this);
        backgroundWorker.execute(FriendRequests.usernameSelected);
    }
}
