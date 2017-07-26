package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GuestHome extends AppCompatActivity {
    private static ListView list_view;
    private static String [] GUESTOPTIONS = {"Welcome!!",
    "Your Social Groups",
    "Friend List",
    "Create new Social Group",
    "Search for Friends",
    "Friend Requests",
    "Account and Settings"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        listOptions();
        setTitle("Welcome to SocialFeed");
    }

    public void listOptions (){
        list_view = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GUESTOPTIONS);
        list_view.setAdapter(arrayAdapter);


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) list_view.getItemAtPosition(position);
                Toast.makeText(GuestHome.this, position + " : " + value, Toast.LENGTH_SHORT).show();

                Intent optionIntent = null;

                if (position == 0){

                }else if (position == 1){
                    optionIntent = new Intent("awesomeapps.socialfeed.YourSocialGroups");
                    startActivity(optionIntent);
                }else if (position == 2){
                    optionIntent = new Intent("awesomeapps.socialfeed.FriendList");
                    startActivity(optionIntent);
                }else if (position == 3){
                    optionIntent = new Intent("awesomeapps.socialfeed.SocialGroupCreate");
                    startActivity(optionIntent);
                }else if (position == 4){
                    optionIntent = new Intent("awesomeapps.socialfeed.FriendSearch");
                    startActivity(optionIntent);
                }else if (position == 5){
                    optionIntent = new Intent("awesomeapps.socialfeed.FriendRequests");
                    startActivity(optionIntent);
                }
            }
        });
    }


}
