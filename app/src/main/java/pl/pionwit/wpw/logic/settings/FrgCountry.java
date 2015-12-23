package pl.pionwit.wpw.logic.settings;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.AddeditActivity;
import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CountryAdapter;
import pl.pionwit.wpw.logic.contragents.ContragentItem;
import pl.pionwit.wpw.logic.contragents.Country;
import pl.pionwit.wpw.logic.db.DBwpw;

/**
 * Created by Witold on 21.12.2015.
 */
public class FrgCountry extends android.support.v4.app.Fragment {

    ListView countrysList;
    View v;
    CountryAdapter adapter;
    FloatingActionButton fab;
    Context cnt;
    View vDialod;
    AlertDialog.Builder adb;
    ArrayList<Country> alCountrys;
    long idCountryCode;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frg_settings_country, container, false);
        countrysList = (ListView) v.findViewById(R.id.lvCountry);
        fab = (FloatingActionButton) v.findViewById(R.id.fabAddCountry);
        alCountrys = initCountrys(cnt);
        adapter = new CountryAdapter(cnt, alCountrys);
        adapter.notifyDataSetChanged();
        countrysList.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vDialod = inflater.inflate(R.layout.dlg_add_country, null);
                adb = new AlertDialog.Builder(cnt);
                adb.setTitle(cnt.getString(R.string.title_add_country));
                adb.setView(vDialod);
                adb.setIcon(R.mipmap.ic_wan);
                adb.setPositiveButton(cnt.getString(R.string.btn_submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
                        String country = etCountry.getText().toString();
                        EditText etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);
                        int code = Integer.parseInt(etCode.getText().toString());
                        EditText etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);
                        String litlCode = etLitlCode.getText().toString();
                        addCountryCode(country, code, litlCode, new Date(), cnt);
                        adapter.notifyDataSetChanged(initCountrys(cnt));
                    }
                });

                adb.show();
            }
        });

        countrysList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vDialod = inflater.inflate(R.layout.dlg_add_country, null);
                adb = new AlertDialog.Builder(cnt);
                adb.setTitle(cnt.getString(R.string.title_dell_edit_country));
                adb.setView(vDialod);
                adb.setIcon(R.mipmap.ic_wan);
                idCountryCode=id;

                Country countryItem = (Country) parent.getAdapter().getItem(position);
                EditText etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
                etCountry.setText(countryItem.getName());
                EditText etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);
                etCode.setText(String.valueOf(countryItem.getKod()));
                EditText etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);
                etLitlCode.setText(countryItem.getLitlCod());
                adb.setNegativeButton(cnt.getString(R.string.btn_dell), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dellCountryCode(cnt, idCountryCode);
                        adapter.notifyDataSetChanged(initCountrys(cnt));
                        Toast.makeText(cnt, R.string.toast_dell_country, Toast.LENGTH_SHORT).show();
                    }
                });
                adb.setPositiveButton(cnt.getString(R.string.btn_submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
                        String country = etCountry.getText().toString();
                        EditText etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);
                        int code = Integer.parseInt(etCode.getText().toString());
                        EditText etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);
                        String litlCode = etLitlCode.getText().toString();
                        updateCountryCode(idCountryCode,country,code,litlCode,new Date(),cnt);
                        adapter.notifyDataSetChanged(initCountrys(cnt));
                        Toast.makeText(cnt, R.string.toast_upd_country, Toast.LENGTH_SHORT).show();
                    }
                });

                adb.show();

                return true;
            }
        });

        countrysList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(cnt, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cnt = context;
    }


    private void addCountryCode(String country, int code, String litlCode
            , Date date, Context context) {
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        ContentValues cv = new ContentValues();

        cv.put("name", country);
        cv.put("cod", code);
        cv.put("litl_cod", litlCode);
        cv.put("date_changes", dateFormat.format(date));
        db.insert(DBwpw.TABLE_COUNTRY, null, cv);
        dbWPW.close();
    }

    private void updateCountryCode(long id, String country, int code, String litlCode
            , Date date, Context context){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        ContentValues cv = new ContentValues();

        cv.put("name", country);
        cv.put("cod", code);
        cv.put("litl_cod", litlCode);
        cv.put("date_changes", dateFormat.format(date));
        db.update(DBwpw.TABLE_COUNTRY, cv, "_id=" + id, null);
        dbWPW.close();
    }

    private void dellCountryCode(Context context,long id){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        db.delete(DBwpw.TABLE_COUNTRY, "_id=" + id, null);
        dbWPW.close();
    }

    private ArrayList<Country> initCountrys(Context context) {
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        ArrayList<Country> rez = new ArrayList<>();
        Cursor c = db.query(DBwpw.TABLE_COUNTRY, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date_changes");
            int codColIndex = c.getColumnIndex("cod");
            int litlCodColIndex = c.getColumnIndex("litl_cod");
            int idColIndex = c.getColumnIndex("_id");

            do {
                rez.add(new Country(c.getString(nameColIndex),
                        c.getInt(codColIndex), new Date(), c.getString(litlCodColIndex)
                        , c.getInt(idColIndex)));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            c.close();

        dbWPW.close();
        return rez;
    }
}
