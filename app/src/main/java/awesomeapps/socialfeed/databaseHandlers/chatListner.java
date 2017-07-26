package awesomeapps.socialfeed.databaseHandlers;

import android.content.Context;

/**
 * Created by codeguy on 2/14/16.
 */
public class chatListner implements Runnable{

    static String friendusername;
    Context context;
    public chatListner ( String fu, Context ctx){
        context = ctx;
        friendusername = fu;
    }

    @Override
    public void run() {
        for (;;){
            handleGetChats backgroundWorker = new handleGetChats(context);
            backgroundWorker.execute(friendusername);
        }
    }
}
