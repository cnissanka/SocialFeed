package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import awesomeapps.socialfeed.databaseHandlers.handleGetFriends;

public class FriendList extends AppCompatActivity {
    public static ListView friendList;
    public static String selectedusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        friendList = (ListView) findViewById(R.id.listFriendList);
        setTitle("Your Friend List");
        handleGetFriends backgroundWorker = new handleGetFriends(this);
        backgroundWorker.execute();

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedusername = friendList.getItemAtPosition(position).toString();
                Intent intent = new Intent("awesomeapps.socialfeed.friendDetail");
                startActivity(intent);
            }
        });
    }
}
