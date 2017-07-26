package awesomeapps.socialfeed.databaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by codeguy on 2/9/16.
 */
public class loginDataSession extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "socialfeed_local.db"; //local database
    public static final String TABLE_NAME1 = "login_table";
    public static final String TABLE_col1 = "id";
    public static final String TABLE1_col2 = "username";

    public loginDataSession (Context context){
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY, USERNAME VARCHAR(45))");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public boolean insertLogin (String username){
        SQLiteDatabase db = this.getWritableDatabase();
        int records = db.rawQuery("select * from login_table",null).getCount();
        long res = 0;
        if (records != 1){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_col1,0);
        contentValues.put(TABLE1_col2, username);
         res = db.insert(TABLE_NAME1, null, contentValues);
        }else{
            db.execSQL("update login_table set USERNAME = '"+username+"' where ID=0");
        }


        return res != -1;
    }

    public Cursor getUsername (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from login_table", null);

        return res;
    }
}
