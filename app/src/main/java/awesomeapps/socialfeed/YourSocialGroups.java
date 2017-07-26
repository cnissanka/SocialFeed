package awesomeapps.socialfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import awesomeapps.socialfeed.databaseHandlers.handleGetUserSocialGroup;

public class YourSocialGroups extends AppCompatActivity {
    public static ListView socialList;
    public static String selectedGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoursocialgroup_layout);
        setTitle("Your Social Groups");
        socialList = (ListView) findViewById(R.id.socialList);

        handleGetUserSocialGroup backgroundWorker = new handleGetUserSocialGroup(this);
        backgroundWorker.execute();

        socialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGroup = socialList.getItemAtPosition(position).toString();
                Intent intent = new Intent("awesomeapps.socialfeed.SocialGroupFeed");
                startActivity(intent);
            }
        });
    }

    public void onAddSocialGroup (View view){
        Intent intent = new Intent("awesomeapps.socialfeed.SearchSocialGroup");
        startActivity(intent);
    }
}
