package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by codeguy on 2/9/16.
 */
public class handleLogin extends AsyncTask<String, Void, String>{
    Context context;
    private String username;
    String serverResult;
    public handleLogin(Context ctx){
        this.context = ctx;
        this.serverResult = "";
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleLogin";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            this.username = params[0];
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
            post_data = URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(params[0],"UTF-8") + "&" +
                        URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8");

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

            serverResult = result;
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
        Intent loginIntent = null;
        if (s.equals("GUEST")){
            loginIntent = new Intent("awesomeapps.socialfeed.GuestHome");
            this.context.startActivity(loginIntent);
            try {
                FileOutputStream fileOutputStream = context.openFileOutput("useracc.ac",Context.MODE_PRIVATE);
                fileOutputStream.write(username.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (s.equals("ADMIN")){
            try {
                FileOutputStream fileOutputStream = context.openFileOutput("useracc.ac",Context.MODE_PRIVATE);
                fileOutputStream.write(username.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (s.equals("INVALID")){
            AlertDialog loginAlert = new AlertDialog.Builder(this.context).create();
            loginAlert.setMessage("Login UnSuccessful!");
            loginAlert.setTitle("Login Message");
            loginAlert.show();
        }
    }

    public String getServerResult (){
        return this.serverResult;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
