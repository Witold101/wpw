package pl.pionwit.wpw;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.adapters.ClientGridAdapter;
import pl.pionwit.wpw.adapters.ContragentAdapter;
import pl.pionwit.wpw.logic.contragents.ContactItem;
import pl.pionwit.wpw.logic.contragents.ContragentItem;
import pl.pionwit.wpw.logic.db.DBwpw;

public class MainActivity extends AppCompatActivity {

    ListView lvContragents;
    GridView gvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ContragentAdapter contragentAdapter=new ContragentAdapter(this);
        lvContragents=(ListView)findViewById(R.id.listViewContragent);
        lvContragents.setAdapter(contragentAdapter);

        ClientGridAdapter cgAdapter = new ClientGridAdapter(this,generateContact());
        gvContacts=(GridView)findViewById(R.id.gvClients);
        gvContacts.setAdapter(cgAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddeditActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


    //*********************** DELL **********************************

    ArrayList<ContactItem> generateContact(){
        ArrayList<ContactItem> rez = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            rez.add(new ContactItem(new Date(), "Name " + i,i,"Department "+i,"Note "+i ));
        }
        return rez;
    }

    //***************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_dell) {
            DBwpw dbWPW = new DBwpw(this);
            SQLiteDatabase db = dbWPW.getWritableDatabase();
            db.delete(DBwpw.TABLE_CLIENT, null, null);
            dbWPW.close();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
