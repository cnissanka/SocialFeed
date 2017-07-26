package awesomeapps.socialfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import awesomeapps.socialfeed.databaseHandlers.handleLogin;
import awesomeapps.socialfeed.databaseHandlers.loginDataSession;

public class Login extends AppCompatActivity {
    private EditText txtusername;
    private EditText txtpassword;
    loginDataSession local_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtusername = (EditText) findViewById(R.id.txtUsername);
        txtpassword = (EditText) findViewById(R.id.txtPassword);
        local_db = new loginDataSession(this);
        setTitle("Login to Your Account");
    }

    public void createAccount (View view){
        Intent intent = new Intent("awesomeapps.socialfeed.Register");
        startActivity(intent);
    }

    public void login (View view){
        //Log.i("hello","in the login");

        local_db.insertLogin(txtusername.getText().toString());

        handleLogin backgroundWorker = new handleLogin(this);
        String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();
        backgroundWorker.execute(username,password);
      //  String serverRes = backgroundWorker.getServerResult();

    }
}
