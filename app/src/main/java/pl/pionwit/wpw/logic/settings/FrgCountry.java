package pl.pionwit.wpw.logic.settings;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CountryAdapter;
import pl.pionwit.wpw.library.TextWatcherCast;
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
    int posicionCountry;
    EditText etCountry;
    EditText etCode;
    EditText etLitlCode;

    TextWatcherCast watcherCast;
    TextWatcherCast watcherCastLitlCode;
    TextWatcherCast watcherCastCode;

    AlertDialog alertDialog;
    AlertDialog alertDialogEdit;

    Boolean flagAlertDialogShow;
    Boolean flagAlertDialogEditShow;

    Bundle state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_settings_country, container, false);
        countrysList = (ListView) v.findViewById(R.id.lvCountry);
        fab = (FloatingActionButton) v.findViewById(R.id.fabAddCountry);
        flagAlertDialogEditShow = false;
        flagAlertDialogShow = false;
        alCountrys = initCountrys(cnt);
        adapter = new CountryAdapter(cnt, alCountrys);
        adapter.notifyDataSetChanged();
        countrysList.setAdapter(adapter);
        state=savedInstanceState;

        if (state != null) {
            flagAlertDialogShow = state.getBoolean("flagAlertDialogShow");
            flagAlertDialogEditShow = state.getBoolean("flagAlertDialogEditShow");
        }

        if (flagAlertDialogShow) {
            alertDialogShow();
        }

        if (flagAlertDialogEditShow) {
            alertDialogEditShow();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagAlertDialogShow = true;
                alertDialogShow();
            }
        });

        countrysList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                flagAlertDialogEditShow = true;
                idCountryCode = id;
                posicionCountry = position;
                alertDialogEditShow();
                return true;
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cnt = context;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("flagAlertDialogShow", flagAlertDialogShow);
        outState.putBoolean("flagAlertDialogEditShow", flagAlertDialogEditShow);

        if (flagAlertDialogEditShow) {
            outState.putInt("posicionCountry", posicionCountry);
            outState.putLong("idCountryCode", idCountryCode);
            outState.putString("etCountry", etCountry.getText().toString());
            outState.putString("etCode", etCode.getText().toString());
            outState.putString("etLitlCode", etLitlCode.getText().toString());
        }
        if (flagAlertDialogShow) {
            if (etCountry.getText().toString() != null) {
                outState.putString("etCountry", etCountry.getText().toString());
                outState.putString("etCode", etCode.getText().toString());
                outState.putString("etLitlCode", etLitlCode.getText().toString());
            }
        }
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
            , Date date, Context context) {
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

    private void dellCountryCode(Context context, long id) {
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

    private void alertDialogShow() {
        vDialod = getLayoutInflater(null).inflate(R.layout.dlg_add_country, null);
        adb = new AlertDialog.Builder(cnt);
        adb.setTitle(cnt.getString(R.string.title_add_country));
        adb.setView(vDialod);
        adb.setIcon(R.mipmap.ic_wan);

        etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
        etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);
        etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);

//-------------- Валидация текста в окне Добавления кодов стран ----------------
        watcherCast = new TextWatcherCast(etCountry);
        watcherCast.setMinLength(3);
        watcherCast.setMaxLength(50);
        etCountry.addTextChangedListener(watcherCast);

        watcherCastLitlCode = new TextWatcherCast(etLitlCode);
        watcherCastLitlCode.setMinLength(2);
        watcherCastLitlCode.setMaxLength(2);
        watcherCastLitlCode.setUpperCase(true);
        etLitlCode.addTextChangedListener(watcherCastLitlCode);

        watcherCastCode = new TextWatcherCast(etCode);
        watcherCastCode.setMinLength(1);
        watcherCastCode.setCheckStringToInt(true);
        etCode.addTextChangedListener(watcherCastCode);

//***********************************************************************************
        if (state!=null){
            etCountry.setText(state.getString("etCountry"));
            etLitlCode.setText(state.getString("etLitlCode"));
            etCode.setText(state.getString("etCode"));
        }

        adb.setPositiveButton(cnt.getString(R.string.btn_submit), null);
        alertDialog = adb.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (watcherCast.isFlag() & watcherCastLitlCode.isFlag() & watcherCastCode.isFlag()) {
                    EditText etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
                    String country = etCountry.getText().toString();
                    EditText etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);
                    int code = Integer.parseInt(etCode.getText().toString());
                    EditText etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);
                    String litlCode = etLitlCode.getText().toString();
                    addCountryCode(country, code, litlCode, new Date(), cnt);
                    adapter.notifyDataSetChanged(initCountrys(cnt));
                    state=null;
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(cnt, "Не правильно заполнена форма!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                flagAlertDialogShow = false;
            }
        });
    }

    private void alertDialogEditShow() {
        vDialod = getLayoutInflater(null).inflate(R.layout.dlg_add_country, null);
        adb = new AlertDialog.Builder(cnt);
        adb.setTitle(cnt.getString(R.string.title_dell_edit_country));
        adb.setView(vDialod);
        adb.setIcon(R.mipmap.ic_wan);

        etCountry = (EditText) vDialod.findViewById(R.id.etDialogAddCountry);
        etCode = (EditText) vDialod.findViewById(R.id.etDialogAddCountryCode);
        etLitlCode = (EditText) vDialod.findViewById(R.id.etDialogAddLitlCod);

//-------------- Валидация текста в окне Редактирования кодов стран ----------------
        watcherCast = new TextWatcherCast(etCountry);
        watcherCast.setMinLength(3);
        watcherCast.setMaxLength(50);
        etCountry.addTextChangedListener(watcherCast);

        watcherCastLitlCode = new TextWatcherCast(etLitlCode);
        watcherCastLitlCode.setMinLength(2);
        watcherCastLitlCode.setMaxLength(2);
        watcherCastLitlCode.setUpperCase(true);
        etLitlCode.addTextChangedListener(watcherCastLitlCode);

        watcherCastCode = new TextWatcherCast(etCode);
        watcherCastCode.setMinLength(1);
        watcherCastCode.setMaxLength(5);
        watcherCastCode.setUpperCase(false);
        watcherCastCode.setCheckStringToInt(true);
        etCode.addTextChangedListener(watcherCastCode);

//***********************************************************************************

        if (state!=null){
            etCountry.setText(state.getString("etCountry"));
            etLitlCode.setText(state.getString("etLitlCode"));
            etCode.setText(state.getString("etCode"));
            if (state.getInt("posicionCountry")>0
                    & state.getLong("idCountryCode")>0) {
                posicionCountry = state.getInt("posicionCountry");
                idCountryCode=state.getLong("idCountryCode");
            }
        }else {
            Country countryItem = (Country) adapter.getItem(posicionCountry);
            etCountry.setText(countryItem.getName());
            etCode.setText(String.valueOf(countryItem.getKod()));
            etLitlCode.setText(countryItem.getLitlCod());
        }


        adb.setNegativeButton(cnt.getString(R.string.btn_dell), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dellCountryCode(cnt, idCountryCode);
                adapter.notifyDataSetChanged(initCountrys(cnt));
                state=null;
                Toast.makeText(cnt, R.string.toast_dell_country, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setPositiveButton(cnt.getString(R.string.btn_submit), null);
        alertDialogEdit = adb.create();
        alertDialogEdit.show();
        alertDialogEdit.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (watcherCast.isFlag() & watcherCastLitlCode.isFlag() & watcherCastCode.isFlag()) {
                    String country = etCountry.getText().toString();
                    int code = Integer.parseInt(etCode.getText().toString());
                    String litlCode = etLitlCode.getText().toString();
                    updateCountryCode(idCountryCode, country, code, litlCode, new Date(), cnt);
                    adapter.notifyDataSetChanged(alCountrys);
                    Toast.makeText(cnt, R.string.toast_upd_country, Toast.LENGTH_SHORT).show();
                    state=null;
                    alertDialogEdit.dismiss();
                } else {
                    Toast.makeText(cnt, "Не правильно заполнена форма!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialogEdit.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                flagAlertDialogEditShow = false;
            }
        });
    }
}
