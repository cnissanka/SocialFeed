package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import awesomeapps.socialfeed.FriendDetails;

/**
 * Created by codeguy on 2/14/16.
 */
public class handleGetGroupUser extends AsyncTask<String,Void,String>{
    Context context;
    TextView tv_name, tv_dob, tv_gender;

    public handleGetGroupUser (Context ctx, TextView name, TextView dob, TextView gender){
        context = ctx;
        tv_name = name;
        tv_dob = dob;
        tv_gender = gender;
    }

    @Override
    protected String doInBackground(String... params) {
      String url = "http://192.168.43.59:8080/SocialFeed/handleGetFriendDetails";
      try{
        URL cUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) cUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(params[0],"UTF-8");

        bufferedWriter.write(post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
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
        tv_name.setText(dataset[1]);
        tv_dob.setText(dataset[2]);
        tv_gender.setText(dataset[3]);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
