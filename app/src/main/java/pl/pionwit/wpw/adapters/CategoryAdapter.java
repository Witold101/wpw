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
import pl.pionwit.wpw.logic.db.DBwpw;

public class CategoryAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Category> categorys;
    LayoutInflater linflater;

    public CategoryAdapter(Context ctx) {
        this.ctx = ctx;
        categorys= new ArrayList<>();
        getCategorys();
        linflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categorys.size();
    }

    @Override
    public Category getItem(int position) {
        return categorys.get(position);
    }

    @Override
    public long getItemId(int position) {

        return ((Category)getItem(position)).get_id() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.item_category, parent, false);
        }
        Category p = categorys.get(position);
        ((TextView) view.findViewById(R.id.tvItemCategory)).setText(p.getName());
        return view;
    }

    private void getCategorys(){
        categorys.clear();
        DBwpw dBwpw=new DBwpw(ctx);
        SQLiteDatabase db = dBwpw.getWritableDatabase();
        Cursor c = db.query(DBwpw.TABLE_CATEGORY, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int nameColIndex = c.getColumnIndex("name");
            int date_changesColIndex=c.getColumnIndex("date_changes");
            int idColIndex = c.getColumnIndex("_id");
            Date date;
            do {
                try {
                    date= ConvertDate.stringToDate(c.getString(date_changesColIndex));
                } catch (ParseException e) {
                    date=new Date();
                }
                categorys.add(new Category(c.getString(nameColIndex), date, c.getLong(idColIndex)));
            } while (c.moveToNext());
        } else
            c.close();
        dBwpw.close();
    }

    public void addCategoryToDB(Context context, String s, Date date){

        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", s);
        cv.put("date_changes", ConvertDate.dateToString(date));
        long id = db.insert(DBwpw.TABLE_CATEGORY, null, cv);
        categorys.add(new Category(s,date,id));
        dbWPW.close();
    }

    public void editCategoryToDb(Context context, Category category, int position){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", category.getName());
        cv.put("date_changes", ConvertDate.dateToString(category.getDate_changes()));
        db.update(DBwpw.TABLE_CATEGORY, cv, "_id=" + category.get_id(), null);
        dbWPW.close();
        categorys.set(position, category);
    }

    public void dellCategoryToDb(Context context,int position){
        DBwpw dbWPW = new DBwpw(context);
        SQLiteDatabase db = dbWPW.getWritableDatabase();
        db.delete(DBwpw.TABLE_CATEGORY, "_id=" + getItem(position).get_id(), null);
        dbWPW.close();
        categorys.remove(position);
    }
}
