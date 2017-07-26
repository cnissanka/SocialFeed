package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GroupManagement extends AppCompatActivity {
    private ListView groupSettingList;
    private String [] optionsArray = {
      "Group Members",
      "Group Requests",
      "Edit Group Details"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_management);

        groupSettingList = (ListView) findViewById(R.id.lv_groupsettings);
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsArray);

        groupSettingList.setAdapter(arrayAdapter);

        setTitle("Manage your Social Group Here");
        groupSettingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent("awesomeapps.socialfeed.GroupMembers");
                    startActivity(intent);
                }else if (position == 1){
                    Intent intent = new Intent("awesomeapps.socialfeed.GroupRequests");
                    startActivity(intent);
                }else if (position == 2){
                    Intent intent = new Intent("awesomeapps.socialfeed.GroupSettings");
                    startActivity(intent);
                }
            }
        });
    }
}
