package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import awesomeapps.socialfeed.FeedMangement.FeedAdapter;
import awesomeapps.socialfeed.databaseHandlers.handleGetSocialFeed;

public class SocialGroupFeed extends AppCompatActivity {
    private ListView feedList;
    public static String selectedSocialGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_group_feed);

        feedList = (ListView) findViewById(R.id.ls_feed);
        selectedSocialGroup = YourSocialGroups.selectedGroup;
        handleGetSocialFeed backgroundWorker = new handleGetSocialFeed(this, feedList);
        backgroundWorker.execute(YourSocialGroups.selectedGroup);
        setTitle("Social Group Feed");
    }

    public void onPostCreate (View view){
        Intent intent = new Intent("awesomeapps.socialfeed.CreatePost");
        startActivity(intent);
    }

    public void onReport (View view){

    }

    public void onSettings (View view){
        Intent intent = new Intent("awesomeapps.socialfeed.GroupManagement");
        startActivity(intent);
    }
}
