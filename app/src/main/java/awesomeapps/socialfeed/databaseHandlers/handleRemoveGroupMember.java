package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by codeguy on 2/14/16.
 */
public class handleRemoveGroupMember extends AsyncTask<String,Void,String>{
    private Context context;

    public handleRemoveGroupMember (Context ctx){
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleRemoveGroupMember";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
            post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(params[0],"UTF-8") + "&" +
                    URLEncoder.encode("group","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");

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

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
       if (s.equals("success")){
           AlertDialog alertDialog = new AlertDialog.Builder(context).create();
           alertDialog.setMessage("You removed a member!");
           alertDialog.setTitle("Group Msg");

           alertDialog.show();
       }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
