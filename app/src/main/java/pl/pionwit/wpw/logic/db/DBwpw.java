package pl.pionwit.wpw.logic.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBwpw extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_wpw";
    public static final int DB_VERSION = 2;
    public static final String TABLE_CLIENT = "td_client";
    public static final String TABLE_COUNTRY  = "td_country";
    public static final String TABLE_PHONE  = "td_phone";
    public static final String TABLE_MAIL  = "td_mail";
    public static final String TABLE_CONTACT  = "td_contact";
    public static final String TABLE_NOTE  = "td_note";


    public DBwpw(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_CLIENT + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                name text NOT NULL,\n" +
                "                name_full text,\n" +
                "                create_date text NOT NULL,\n" +
                "                date_changes text NOT NULL \n" +
                "                web text \n" +
                "                curator_id integer NOT NULL \n" +
                "                last_change_id integer NOT NULL \n" +
                "        );");
        db.execSQL(" CREATE TABLE " + TABLE_COUNTRY + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                name text NOT NULL,\n" +
                "                cod integer NOT NULL,\n" +
                "                date_changes text NOT NULL \n" +
                "        );");
        db.execSQL(" CREATE TABLE " + TABLE_PHONE + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                country_id integer NOT NULL,\n" +
                "                name text NOT NULL,\n" +
                "                type_phone_id integer NOT NULL,\n" +
                "                contact_id integer NOT NULL,\n" +
                "                date_changes text NOT NULL \n" +
                "        );");
        db.execSQL(" CREATE TABLE " + TABLE_MAIL + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                mail text NOT NULL,\n" +
                "                contact_id integer NOT NULL,\n" +
                "                date_changes text NOT NULL \n" +
                "        );");
        db.execSQL(" CREATE TABLE " + TABLE_NOTE + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                note text NOT NULL,\n" +
                "                client_id integer NOT NULL,\n" +
                "                date_changes text NOT NULL \n" +
                "        );");
        db.execSQL(" CREATE TABLE " + TABLE_CONTACT + " (\n" +
                "                _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "                create_date text NOT NULL,\n" +
                "                name text NOT NULL,\n" +
                "                client_id integer NOT NULL,\n" +
                "                department text,\n" +
                "                note text,\n" +
                "                date_changes text NOT NULL \n" +
                "        );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion == 1 && newVersion == 2) {

            db.beginTransaction();
            try {
                ContentValues cv = new ContentValues();
                String upgradeQuery = "ALTER TABLE " + TABLE_CLIENT + " ADD COLUMN date_changes TEXT;";
                db.execSQL(upgradeQuery);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }
}
