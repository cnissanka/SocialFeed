package awesomeapps.socialfeed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import awesomeapps.socialfeed.databaseHandlers.handleCreatePost;

public class CreatePost extends AppCompatActivity {

    //private Context context;
    private EditText txtPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        txtPost = (EditText) findViewById(R.id.txtPost);
        setTitle("Create your Post");
    }

    public void onSavePost (View view){
        handleCreatePost backgroundWorker = new handleCreatePost(this);
        backgroundWorker.execute(txtPost.getText().toString(), SocialGroupFeed.selectedSocialGroup);
    }

}
