package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.content.Intent;
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
 * Created by codeguy on 2/9/16.
 */
public class handleGuestRegister extends AsyncTask<String, Void, String>{
    Context context;
    AlertDialog alertDialog;

    public handleGuestRegister(Context ctx){
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleGuestRegistration";
        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
            post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8") + "&" +
                               URLEncoder.encode("password","UTF-8") + "=" + URLEncoder.encode(params[1],"UTF-8") +  "&"+
                                URLEncoder.encode("name","UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                               URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                               URLEncoder.encode("gender","UTF-8") + "=" + URLEncoder.encode(params[4],"UTF-8") + "&" +
                              URLEncoder.encode("dob","UTF-8") + "=" + URLEncoder.encode(params[5],"UTF-8");

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
        alertDialog = new AlertDialog.Builder(this.context).create();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("true")){
            alertDialog.setTitle("Info");
            alertDialog.setMessage("Account created! Please Login.");
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
