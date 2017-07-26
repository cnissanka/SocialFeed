package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import awesomeapps.socialfeed.YourSocialGroups;

/**
 * Created by codeguy on 2/11/16.
 */
public class handleGetUserSocialGroup extends AsyncTask<String,Void,String>{
    private Context context;

    public  handleGetUserSocialGroup(Context ctx){
        this.context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleGetUserSocialGroups";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";

          //  String friendUsername = params[0];
            String userUsername = "";

            //get username form database
            loginDataSession db = new loginDataSession(context);
            Cursor dataset = db.getUsername();
            while (dataset.moveToNext()){
                userUsername = dataset.getString(1);
            }
            db.close();

            post_data = URLEncoder.encode("user","UTF-8") + "=" + URLEncoder.encode(userUsername, "UTF-8");



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

        if (s.equals("") == false){
        String socialgroups [] = s.split(";");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, socialgroups);
        YourSocialGroups.socialList.setAdapter(arrayAdapter);}
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
