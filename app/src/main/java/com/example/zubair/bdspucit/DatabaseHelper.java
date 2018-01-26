package com.example.zubair.bdspucit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Zubair on 1/17/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int version=2;
    private static final String dbName="BloodBank";
    private static final String tableName="user";
    //Columns
    private static final String id="id";
    private static final String name="name";
    private static final String username="username";
    private static final String email="email";
    private static final String pass="pass";
    private static final String phone="phone";
    private static final String address="address";
    private static final String bloodGroup="bloodGroup";
    private static final String dob="dob";
    private static final String weight="weight";
    private static final String answer="QuestionAnswer";
    //Query
    private static final String qCreatTable="create table "+tableName+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +name+" TEXT,"
            +username+" TEXT,"
            +email+" TEXT,"
            +pass+" TEXT,"
            +phone+" TEXT,"
            +address+" TEXT,"
            +bloodGroup+" TEXT,"
            +dob+" DATETIME,"
            +weight+" INTEGER,"
            +answer+" TEXT)";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(qCreatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(sqLiteDatabase);
    }

    public void addUser(DatabaseAttributes dbAttribut){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(name,dbAttribut.getName());
        cv.put(username,dbAttribut.getUsername());
        cv.put(email,dbAttribut.getEmail());
        cv.put(pass,dbAttribut.getPass());
        cv.put(phone,dbAttribut.getPhone());
        cv.put(address,dbAttribut.getAddress());
        cv.put(bloodGroup,dbAttribut.getBloodGroup());
        cv.put(dob,dbAttribut.getDob());
        cv.put(weight,dbAttribut.getWeight());
        cv.put(answer,dbAttribut.getAnswer());
        long temp=db.insert(tableName,null,cv);
        Log.d("db",""+temp);
    }
    public DatabaseAttributes getUser(String user){
        SQLiteDatabase db=getReadableDatabase();
        String q="select * from "+tableName+" where "+username+"=?";
        Cursor c=db.rawQuery(q,new String[]{user});
        c.moveToFirst();
        DatabaseAttributes dba=new DatabaseAttributes();
        dba.setName(c.getString(c.getColumnIndex(name)));
        dba.setWeight(Integer.parseInt(c.getString(c.getColumnIndex(weight))));
        dba.setBloodGroup(c.getString(c.getColumnIndex(bloodGroup)));
        dba.setAddress(c.getString(c.getColumnIndex(address)));
        dba.setPhone(c.getString(c.getColumnIndex(phone)));
        dba.setPass(null);
        dba.setEmail(c.getString(c.getColumnIndex(email)));
        dba.setUsername(c.getString(c.getColumnIndex(username)));
        dba.setDob(c.getString(c.getColumnIndex(dob)));
        dba.setAnswer(c.getString(c.getColumnIndex(answer)));
        return dba;
    }
    public ArrayList<DatabaseAttributes> getData(){
        SQLiteDatabase db=getReadableDatabase();
        String q="select * from "+tableName;
        Cursor c=db.rawQuery(q,null);
        c.moveToFirst();
        ArrayList<DatabaseAttributes> arr=new ArrayList<DatabaseAttributes>();
        do {
            DatabaseAttributes dba = new DatabaseAttributes();
            dba.setName(c.getString(c.getColumnIndex(name)));
            dba.setWeight(Integer.parseInt(c.getString(c.getColumnIndex(weight))));
            dba.setBloodGroup(c.getString(c.getColumnIndex(bloodGroup)));
            dba.setAddress(c.getString(c.getColumnIndex(address)));
            dba.setPhone(c.getString(c.getColumnIndex(phone)));
            dba.setPass(null);
            dba.setEmail(c.getString(c.getColumnIndex(email)));
            dba.setUsername(c.getString(c.getColumnIndex(username)));
            dba.setDob(c.getString(c.getColumnIndex(dob)));
            dba.setAnswer(c.getString(c.getColumnIndex(answer)));
            arr.add(dba);
        }while (c.moveToNext());
        return arr;
    }


    public ArrayList<DatabaseAttributes> getDataonBG(String bg){
        SQLiteDatabase db=getReadableDatabase();
        String q="select * from "+tableName+" where "+bloodGroup+"=?";
        Cursor c=db.rawQuery(q,new String[]{bg});
        c.moveToFirst();
        ArrayList<DatabaseAttributes> arr=new ArrayList<DatabaseAttributes>();
        if(c.getCount()!=0){
            do {
                DatabaseAttributes dba = new DatabaseAttributes();
                dba.setName(c.getString(c.getColumnIndex(name)));
                dba.setWeight(Integer.parseInt(c.getString(c.getColumnIndex(weight))));
                dba.setBloodGroup(c.getString(c.getColumnIndex(bloodGroup)));
                dba.setAddress(c.getString(c.getColumnIndex(address)));
                dba.setPhone(c.getString(c.getColumnIndex(phone)));
                dba.setPass(null);
                dba.setEmail(c.getString(c.getColumnIndex(email)));
                dba.setUsername(c.getString(c.getColumnIndex(username)));
                dba.setDob(c.getString(c.getColumnIndex(dob)));
                dba.setAnswer(c.getString(c.getColumnIndex(answer)));
                arr.add(dba);
            }while (c.moveToNext());
        }
        return arr;
    }
    public boolean checkUserPass(String user, String password){
        SQLiteDatabase db=getReadableDatabase();
        String q="select * from "+tableName+" where "+username+"= ? AND "+pass+"= ?";
        Cursor c=db.rawQuery(q,new String[]{user,password});
        if (c.getCount()==0){
            return false;
        }else
            return  true;
    }
    public void updateUser(DatabaseAttributes dbAttribut){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(name,dbAttribut.getName());
        cv.put(username,dbAttribut.getUsername());
        cv.put(email,dbAttribut.getEmail());
        cv.put(pass,dbAttribut.getPass());
        cv.put(phone,dbAttribut.getPhone());
        cv.put(address,dbAttribut.getAddress());
        cv.put(bloodGroup,dbAttribut.getBloodGroup());
        cv.put(dob,dbAttribut.getDob());
        cv.put(weight,dbAttribut.getWeight());
        cv.put(answer,dbAttribut.getAnswer());
        db.update(tableName,cv,username+" = ?",new String[]{UserNameClass.logUser});
    }
    public void deleteUser(String usr){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(tableName,username+" = ?",new String[]{usr});
    }
    public boolean updatePass(String usr,String fav,String pss){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(pass,pss);
        int i=db.update(tableName,cv,username+" = ? and "+answer+" = ?",new String[]{String.valueOf(usr),String.valueOf(fav)});
        if(i<=0)
            return false;
        else
            return  true;
    }
}
