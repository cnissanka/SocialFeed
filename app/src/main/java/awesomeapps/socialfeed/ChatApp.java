package awesomeapps.socialfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import awesomeapps.socialfeed.databaseHandlers.chatListner;
import awesomeapps.socialfeed.databaseHandlers.handleGetChats;
import awesomeapps.socialfeed.databaseHandlers.handleSaveChatMsg;

public class ChatApp extends AppCompatActivity {
    public static ListView chatList;
    private EditText txtChat;
    public static  String friendname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_app);

        chatList = (ListView) findViewById(R.id.listChat);
        txtChat = (EditText) findViewById(R.id.txtChat);
        friendname = friendDetail.sUsername;
        setTitle("Chat Room");
        handleGetChats backgroundWorker = new handleGetChats(this);
        backgroundWorker.execute(this.friendname);


    }

    public void onChatSend (View view){
        handleSaveChatMsg backgroundWorker2 = new handleSaveChatMsg(this);
        backgroundWorker2.execute(this.friendname,txtChat.getText().toString());


    }
}
