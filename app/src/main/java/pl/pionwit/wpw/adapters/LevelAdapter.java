package pl.pionwit.wpw.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.library.ConvertDate;
import pl.pionwit.wpw.logic.contragents.Category;
import pl.pionwit.wpw.logic.contragents.LevelUser;
import pl.pionwit.wpw.logic.db.DBwpw;

/**
 * Created by Witold on 10.01.2016.
 */
public class LevelAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<LevelUser> levelUsers;
    LayoutInflater linflater;

    public LevelAdapter(Context ctx) {
        this.ctx = ctx;
        this.levelUsers = new ArrayList<>();
        initLevelUsers();
        this.linflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return levelUsers.size();
    }


    @Override
    public LevelUser getItem(int position) {
        return levelUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.item_level, parent, false);
        }
        LevelUser p = levelUsers.get(position);
        ((TextView) view.findViewById(R.id.tvSettingLevelNumber))
                .setText(Integer.toString(p.getNumber()));
        ((TextView) view.findViewById(R.id.tvSettingLevelNote)).setText(p.getNote());
        return view;
    }

    private void initLevelUsers() {
        levelUsers.clear();
        DBwpw dBwpw=new DBwpw(ctx);
        SQLiteDatabase db = dBwpw.getWritableDatabase();
        Cursor c = db.query(DBwpw.TABLE_LEVEL, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int noteColIndex = c.getColumnIndex("note");
            int numberColIndex = c.getColumnIndex("number");
            int date_changesColIndex=c.getColumnIndex("date_changes");
            int idColIndex = c.getColumnIndex("_id");
            Date date;
            do {
                try {
                    date= ConvertDate.stringToDate(c.getString(date_changesColIndex));
                } catch (ParseException e) {
                    date=new Date();
                }
                levelUsers.add(new LevelUser(c.getInt(numberColIndex),c.getString(noteColIndex),
                        date, c.getLong(idColIndex)));
            } while (c.moveToNext());
        } else
            c.close();
        dBwpw.close();
    }

    public void addLevelToDB(Context context, LevelUser levelUser){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", levelUser.getNote());
        cv.put("number",levelUser.getNumber());
        cv.put("date_changes", ConvertDate.dateToString(levelUser.getDateChanges()));
        long id = db.insert(DBwpw.TABLE_LEVEL, null, cv);
        levelUsers.add(new LevelUser(levelUser.getNumber(), levelUser.getNote(),
                levelUser.getDateChanges(), id));
        dbWPW.close();
    }

    public void editLevelToDb(Context context, LevelUser levelUser, int position){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", levelUser.getNote());
        cv.put("number", levelUser.getNumber());
        cv.put("date_changes", ConvertDate.dateToString(levelUser.getDateChanges()));
        db.update(DBwpw.TABLE_LEVEL, cv, "_id=" + levelUser.getId(), null);
        dbWPW.close();
        levelUsers.set(position, levelUser);
    }

    public void dellLevelToDb(Context context,int position){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        db.delete(DBwpw.TABLE_LEVEL, "_id=" + getItem(position).getId(), null);
        dbWPW.close();
        levelUsers.remove(position);
    }
}
