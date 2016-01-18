package lol.go2study.go2study;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public void AddPerson(Person person) {
        ContentValues values = new ContentValues();

            values.put("pcn", person.getId());
            values.put("firstName", person.getGivenName());
            values.put("lastName", person.getSurName());
            values.put("email", person.getMail());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_PEOPLE, null, values);
            db.close();
    }

    public void savePeople(List<Person> people){



        SQLiteDatabase db = getWritableDatabase();

        try
        {
            db.beginTransaction();

            ContentValues values;
            for (Person person: people) {
                values = new ContentValues();
                values.put("pcn", person.getId());
                values.put("firstName", person.getGivenName());
                values.put("lastName", person.getSurName());
                values.put("email", person.getMail());
                db.insert(TABLE_PEOPLE, null, values);
            }

            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
        }
    }

    public List<Person> getPeople(){
        List<Person> people = new ArrayList<Person>();
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PEOPLE +";";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do
            {
                Person p = new Person();
                p.setId(c.getString(c.getColumnIndex("pcn")));
                p.setGivenName(c.getString(c.getColumnIndex("firstName")));
                p.setSurName(c.getString(c.getColumnIndex("lastName")));
                p.setOffice(c.getString(c.getColumnIndex("office")));
                p.setMail(c.getString(c.getColumnIndex("email")));
                p.setDepartment(c.getString(c.getColumnIndex("department")));
                p.setTelephoneNumber(c.getString(c.getColumnIndex("telephoneNumber")));
                p.setMobileNumber(c.getString(c.getColumnIndex("mobileNumber")));
                people.add(p);
            }
            while(c.moveToNext());
        }
        c.close();
        db.close();
        return people;
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
