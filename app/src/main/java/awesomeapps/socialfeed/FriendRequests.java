package awesomeapps.socialfeed;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import awesomeapps.socialfeed.databaseHandlers.handleGetFriendRequests;
import awesomeapps.socialfeed.databaseHandlers.loginDataSession;

public class FriendRequests extends AppCompatActivity {
    public static ListView friendRequestList;
    public static String usernameSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);
        friendRequestList = (ListView) findViewById(R.id.listFriendRequest);
        setTitle("Friend Requests");
        handleGetFriendRequests backgroundWorker = new handleGetFriendRequests(this);
        loginDataSession db = new loginDataSession(this);
        Cursor usernameCursor = db.getUsername();
        String username = "";
        while (usernameCursor.moveToNext()){
            username = usernameCursor.getString(1);
        }
        backgroundWorker.execute(username);

        friendRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usernameSelected = friendRequestList.getItemAtPosition(position).toString();
                Intent intent = new Intent("awesomeapps.socialfeed.FriendRequestDetail");
                startActivity(intent);
            }
        });
    }
}
