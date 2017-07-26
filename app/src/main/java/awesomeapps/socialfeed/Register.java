package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import awesomeapps.socialfeed.databaseHandlers.handleGuestRegister;

public class Register extends AppCompatActivity {
    private static RadioGroup radioGroup;
    private static RadioButton cRadio;
    private static EditText txtusername;
    private static EditText txtpassword;
    private static EditText txtname;
    private static EditText txtdob;
    private static EditText txtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        txtname = (EditText) findViewById(R.id.txtName);
        txtdob = (EditText) findViewById(R.id.txtdob);
        txtemail = (EditText) findViewById(R.id.txtEmail);
        setTitle("Sign Up!");
    }

    public void onRegister (View view){
        int selected_id = radioGroup.getCheckedRadioButtonId();
        cRadio = (RadioButton) findViewById(selected_id);

        String gender = cRadio.getText().toString();
        String username = txtusername.getText().toString();
        String password = txtpassword.getText().toString();
        String name = txtname.getText().toString();
        String email = txtemail.getText().toString();
        String dob = txtdob.getText().toString();

        handleGuestRegister backgroundWorker = new handleGuestRegister(this);
        backgroundWorker.execute(username,password,name,email,gender,dob);

    }
}
