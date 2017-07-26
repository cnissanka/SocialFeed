package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import awesomeapps.socialfeed.SocialGroupDetail;

/**
 * Created by codeguy on 2/14/16.
 */
public class handleGetSocialGroupDetails extends AsyncTask<String,Void,String>{
    private Context context;
    private ImageView imageView;
    public  handleGetSocialGroupDetails (Context ctx, ImageView imageView1){
        this.context = ctx;
        this.imageView = imageView1;
    }


    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleGetSocialGroupDetails";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";

            String socialGroupName = params[0];
           // String userUsername = "";



            post_data = URLEncoder.encode("socialgroup", "UTF-8") + "=" + URLEncoder.encode(socialGroupName,"UTF-8");




            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";

            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                result += lines;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;


        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
       String dataset [] = s.split(";");

     //  Bitmap bitmapImage = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
    //   SocialGroupDetail.imgView_profilePic.setImageBitmap(bitmapImage);
       SocialGroupDetail.tv_socialGroupName.setText(dataset[0].toString());
       SocialGroupDetail.tv_socialGroupStatus.setText(dataset[1].toString());

      /* try{

           URL myFileUrl = new URL(dataset[2]);
           HttpURLConnection connection = (HttpURLConnection) myFileUrl.openConnection();

           connection.setDoInput(true);
           connection.connect();
           InputStream is = connection.getInputStream();
           imageView.setImageBitmap(BitmapFactory.decodeStream(is));
            Log.i("image","Done!! " + dataset[2]);

       }catch(Exception e){
           e.printStackTrace();
            Log.i("error","error here");
           e.printStackTrace();
       }*/


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
