package lol.go2study.go2study;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import FontysICT.Models.Person;
import Go2Study.Models.User;

/**
 * Created by Todor on 11/19/2015.
 */
public class MyDBHandler extends  SQLiteOpenHelper  {

   // SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_PEOPLE = "people";
    public static  String _ID = "_id";
    public static String PCN = "pcn";
    public static final String GPS_LOCATION = "gps_location";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PEOPLE + "(" +
                "pcn TEXT PRIMARY_KEY," +
                "firstName TEXT," +
                "lastName TEXT," +
                "email TEXT," +
                "office TEXT," +
                "department TEXT," +
                "telephoneNumber INT," +
                "mobileNumber INT," +
                "photo TEXT" +
                "); "+
                "";

        query += "CREATE TABLE " + TABLE_USERS + "(" +
                "pcn TEXT," +
                "firstName TEXT," +
                "lastName TEXT," +
                "email TEXT," +
                "className TEXT," +
                "photo TEXT," +
                ");";

        db.execSQL(query);

    }

    //Change db structure
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.close();
    }

    //Add a new row to the database
    /*public void AddUser(String firstName, String lastName, String email, String pcn, String className, String photo){
        ContentValues values = new ContentValues();
        values.put("pcn", pcn);
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("email", email);
        values.put("className", className);
        values.put("photo", photo);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }*/

    public void AddPerson(JSONObject person) {
        ContentValues values = new ContentValues();
        try {
            values.put("pcn", person.getString("id"));
            values.put("firstName", person.getString("givenName"));
            values.put("lastName", person.getString("surName"));
            values.put("email", person.getString("mail"));
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_PEOPLE, null, values);
            db.close();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Person getPerson(){
        Person p = new Person();
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PEOPLE +";";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do
            {
                p.setId(c.getString(c.getColumnIndex("pcn")));
                p.setGivenName(c.getString(c.getColumnIndex("firstName")));
                p.setSurName(c.getString(c.getColumnIndex("lastName")));
                p.setOffice(c.getString(c.getColumnIndex("office")));
                p.setMail(c.getString(c.getColumnIndex("email")));
                p.setDepartment(c.getString(c.getColumnIndex("department")));
                p.setTelephoneNumber(c.getString(c.getColumnIndex("telephoneNumber")));
                p.setMobileNumber(c.getString(c.getColumnIndex("mobileNumber")));

            }
            while(c.moveToNext());
        }
        c.close();
        db.close();
        return p;
    }
}
