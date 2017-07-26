package awesomeapps.socialfeed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.net.URI;

import awesomeapps.socialfeed.databaseHandlers.handleSaveSocialGroup;

public class SocialGroupCreate extends AppCompatActivity {
    private EditText txtsocialGroupName;
    private EditText txtSocialGroupStatus;
    Bitmap bitmapImage;
    private RadioGroup radioGroup;
    private static final int CAPTURE_ACTIVITY_REQUEST_CODE = 100;
    private File imageFile;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_group_create);
        txtsocialGroupName = (EditText) findViewById(R.id.txtSocialGroupName);
        txtSocialGroupStatus = (EditText) findViewById(R.id.txtSocialGroupStatus);
        radioGroup = (RadioGroup) findViewById(R.id.radioSocialGroup);

        imageView = (ImageView) findViewById(R.id.imgView_socialPic);

        setTitle("Create a new Social Group");
    }

    public void saveSocialGroup (View view){
        int radiobtn_id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radiobtn_id);

        String socialgroupType = radioButton.getText().toString();
        Log.i("data", socialgroupType);
        String socialGroupName = txtsocialGroupName.getText().toString();

        handleSaveSocialGroup backgroundWorker = new handleSaveSocialGroup(this,bitmapImage);

        String socialGroupStatus = txtSocialGroupStatus.getText().toString();
        backgroundWorker.execute(socialGroupName,socialgroupType, socialGroupStatus);
    }
/*
    public void takeSProfilePic (View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"profilepic.jpg");
        Uri tempURi = Uri.fromFile(imageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempURi);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(intent, 0);
    }*/

    public void browserImageGallery (View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Uri targetURL = data.getData();
            try {
                bitmapImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetURL));
                imageView.setImageBitmap(bitmapImage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
