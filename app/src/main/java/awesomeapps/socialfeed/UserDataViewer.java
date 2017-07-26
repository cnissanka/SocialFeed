package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import awesomeapps.socialfeed.databaseHandlers.handleAcceptGroupRequest;
import awesomeapps.socialfeed.databaseHandlers.handleGetGroupUser;
import awesomeapps.socialfeed.databaseHandlers.handleRemoveGroupMember;

public class UserDataViewer extends AppCompatActivity {
    private String userName;
    public static String operationType;
    public static String usernameSelected;
    public static String groupSelected;
    private TextView tv_name;
    private TextView tv_dob;
    private TextView tv_gender;
    private Button btn_acceptRequest;
    private Button btn_removeMember;

    public void setUserData (String username){
        userName = username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("User Details");
        setContentView(R.layout.activity_user_data_viewer);
        setTitle("Account Details");
        tv_name = (TextView) findViewById(R.id.tv_FullName);
        tv_dob = (TextView) findViewById(R.id.tv_DOB);
        tv_gender = (TextView) findViewById(R.id.tv_gender2);

        btn_acceptRequest = (Button) findViewById(R.id.btn_acceptRequest);
        btn_removeMember = (Button) findViewById(R.id.btn_RemoveMember);

        if (operationType.equals("REQUEST")){
            btn_removeMember.setVisibility(Button.INVISIBLE);
        }else if (operationType.equals("MEMBER")){
            btn_acceptRequest.setVisibility(Button.INVISIBLE);
        }

        //get user data
        handleGetGroupUser backgroundWorker = new handleGetGroupUser(this, tv_name, tv_dob, tv_gender);
        backgroundWorker.execute(usernameSelected);
    }

    public void onAcceptRequest (View view){
        //accept code here
        handleAcceptGroupRequest backgroundWorker2 = new handleAcceptGroupRequest(this);
        backgroundWorker2.execute(usernameSelected, groupSelected);
    }

    public void onRemoveMember (View view){
        //remove code here
        handleRemoveGroupMember backgroundWorker3 = new handleRemoveGroupMember(this);
        backgroundWorker3.execute(usernameSelected, groupSelected);
    }

    public static void setUserDataContent (String operationType, String username){
        if (operationType.equals("REQUEST")){
            UserDataViewer.operationType = "REQUEST";
        }else{
            UserDataViewer.operationType = "MEMBER";
        }

        usernameSelected = username;
    }

}
