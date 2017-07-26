package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import awesomeapps.socialfeed.friendDetail;

/**
 * Created by codeguy on 2/11/16.
 */
public class handleGetFriendDetail extends AsyncTask<String,Void,String>{
    private Context context;

    public handleGetFriendDetail (Context ctx){
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleGetFriendDetails";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";

            String friendUsername = params[0];
            post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(friendUsername,"UTF-8");

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
        friendDetail.lbl_username.setText(dataset[0]);
        friendDetail.lbl_fullname.setText(dataset[1]);
        friendDetail.lbl_dob.setText(dataset[2]);
        friendDetail.lbl_gender.setText(dataset[3]);
        friendDetail.lbl_status.setText(dataset[4]);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
