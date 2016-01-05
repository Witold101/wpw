package pl.pionwit.wpw;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import pl.pionwit.wpw.library.ConvertDate;
import pl.pionwit.wpw.logic.contragents.Client;
import pl.pionwit.wpw.logic.db.DBwpw;

/**
 * Created by Witold on 11.12.2015.
 */
public class AddeditActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DBwpw dbWPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addedit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbAddEditContr);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddEditContragent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbWPW = new DBwpw(AddeditActivity.this);
                db=dbWPW.getWritableDatabase();
                EditText contragentName=(EditText)findViewById(R.id.etAddEditName);
                EditText contragentFName=(EditText)findViewById(R.id.etAddEditFullName);
                addContragentToDB(contragentName.getText().toString(),
                        contragentFName.getText().toString(), new Date());
                dbWPW.close();
                Intent intent = new Intent(AddeditActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private long addContragentToDB(String name, String fName, Date date) {
        long id=0;
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("name_full",fName);
        cv.put("date_changes", ConvertDate.dateToString(date));
        id = db.insert(DBwpw.TABLE_CLIENT,null,cv);
        return id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
