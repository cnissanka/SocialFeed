package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import awesomeapps.socialfeed.databaseHandlers.handleGroupRequests;

public class GroupRequests extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_requests);

        listView = (ListView) findViewById(R.id.lv_grouprequests);

        handleGroupRequests backgroundWorker = new handleGroupRequests(this, listView);
        backgroundWorker.execute(SocialGroupFeed.selectedSocialGroup);
        setTitle("Social Group Requests");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDataViewer.groupSelected = SocialGroupFeed.selectedSocialGroup;
                UserDataViewer.setUserDataContent("REQUEST", listView.getItemAtPosition(position).toString());
                Intent intent = new Intent("awesomeapps.socialfeed.UserDataViewer");
                startActivity(intent);
            }
        });
    }
}
