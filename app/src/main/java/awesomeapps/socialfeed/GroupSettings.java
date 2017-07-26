package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import awesomeapps.socialfeed.databaseHandlers.handleGroupEdit;

public class GroupSettings extends AppCompatActivity {
    private EditText txtName;
    private EditText txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);

        txtName = (EditText) findViewById(R.id.txtGroupName);
        txtStatus = (EditText) findViewById(R.id.txtGroupStatus);
        setTitle("Social Group Settings Config");

    }

    public void onUpdateGroup (View view){
        handleGroupEdit backgroundWorker = new handleGroupEdit(this);
        backgroundWorker.execute(SocialGroupFeed.selectedSocialGroup,txtName.getText().toString(), txtStatus.getText().toString());
    }
}
