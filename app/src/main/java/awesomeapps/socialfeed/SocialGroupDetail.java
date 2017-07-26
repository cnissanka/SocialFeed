package awesomeapps.socialfeed;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import awesomeapps.socialfeed.databaseHandlers.handleAddToGroup;
import awesomeapps.socialfeed.databaseHandlers.handleGetSocialGroupDetails;

public class SocialGroupDetail extends AppCompatActivity {
    public static TextView tv_socialGroupName;
    public static TextView tv_socialGroupStatus;

    public static ImageView imgView_profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_group_detail);

        tv_socialGroupName = (TextView) findViewById(R.id.tv_groupName);
        tv_socialGroupStatus = (TextView) findViewById(R.id.tv_groupStatus);

        setTitle("Social Group Details");

    //    imgView_profilePic = (ImageView) findViewById(R.id.imv_socialgrouppic);
        imgView_profilePic = null;
        handleGetSocialGroupDetails backgroundWorker = new handleGetSocialGroupDetails(this, imgView_profilePic);
        backgroundWorker.execute(SearchSocialGroup.groupNameSelected);
    }

    public void addToGroup (View view){
        handleAddToGroup backgroundWorker = new handleAddToGroup(this);
        backgroundWorker.execute(SearchSocialGroup.groupNameSelected);
    }
}
