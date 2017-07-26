package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import awesomeapps.socialfeed.databaseHandlers.handleSearchSocialGroup;

public class SearchSocialGroup extends AppCompatActivity {
    public static ListView listSocialGroupSearch;
    private EditText txtSearchString;
    public static String groupNameSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_social_group);
        listSocialGroupSearch = (ListView) findViewById(R.id.lst_socialGroupSearchRes);
        txtSearchString = (EditText) findViewById(R.id.txtSearch);

        setTitle("Search For Social Groups");

        listSocialGroupSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                groupNameSelected = listSocialGroupSearch.getItemAtPosition(position).toString();
                Intent intent = new Intent("awesomeapps.socialfeed.SocialGroupDetail");
                startActivity(intent);
            }
        });
    }

    public void onSearch2 (View view){
        handleSearchSocialGroup backgroundWorker = new handleSearchSocialGroup(this);

        String search =  txtSearchString.getText().toString();
        backgroundWorker.execute(search);
    }




}
