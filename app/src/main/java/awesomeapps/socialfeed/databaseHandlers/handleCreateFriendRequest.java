package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
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
public class handleCreateFriendRequest extends AsyncTask<String,Void,String>{
    private Context context;

    public handleCreateFriendRequest(Context ctx){
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String register_url = "http://192.168.43.59:8080/SocialFeed/handleCreateFriendRequest";
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
            String userUsername = "";

            //get username form database
            loginDataSession db = new loginDataSession(context);
            Cursor dataset = db.getUsername();
            while (dataset.moveToNext()){
                userUsername = dataset.getString(1);
            }
            db.close();

            post_data = URLEncoder.encode("friend","UTF-8") + "=" + URLEncoder.encode(friendUsername,"UTF-8") + "&" +
                        URLEncoder.encode("user","UTF-8") + "=" + URLEncoder.encode(userUsername, "UTF-8");



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
        if (s.equals("success")){
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Friend Request");
            alertDialog.setMessage("Your Friend Request was sent!");
            alertDialog.show();
        }else if (s.equals("duplicate")){
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Friend Request");
            alertDialog.setMessage("You can't request yourself!");
            alertDialog.show();
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Friend Request");
            alertDialog.setMessage("Something went wrong!");
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
