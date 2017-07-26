package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import awesomeapps.socialfeed.databaseHandlers.handleFriendSearch;

public class FriendSearch extends AppCompatActivity {
    public static ListView searchedFriends;
    public static String selectedUsername;
    private static EditText txtFriendUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);
        txtFriendUsername = (EditText) findViewById(R.id.txtFriendName);
        searchedFriends = (ListView) findViewById(R.id.listView2);
        setTitle("Search For Friends");
        searchedFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUsername = searchedFriends.getItemAtPosition(position).toString();
                Intent intent = new Intent("awesomeapps.socialfeed.FriendDetails");
                startActivity(intent);

            }
        });

    }

    public void onSearch (View view){
        handleFriendSearch backgroundWorker = new handleFriendSearch(this);
        backgroundWorker.execute(txtFriendUsername.getText().toString());
    }
}
