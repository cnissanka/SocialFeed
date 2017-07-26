package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import awesomeapps.socialfeed.FriendSearch;

/**
 * Created by codeguy on 2/9/16.
 */
public class handleFriendSearch extends AsyncTask<String, Void, String>{
    private Context context;

    public  handleFriendSearch(Context ctx){
        this.context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String url = "http://192.168.43.59:8080/SocialFeed/handleFriendSearch";

       try{
           URL cUrl = new URL(url);
           HttpURLConnection httpURLConnection = (HttpURLConnection) cUrl.openConnection();
           httpURLConnection.setRequestMethod("POST");
           httpURLConnection.setDoInput(true);
           httpURLConnection.setDoOutput(true);

           OutputStream outputStream = httpURLConnection.getOutputStream();
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
           String post_data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(params[0],"UTF-8");

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
       String usernamesList [] = s.split(";");
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, usernamesList);
        FriendSearch.searchedFriends.setAdapter(arrayAdapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
