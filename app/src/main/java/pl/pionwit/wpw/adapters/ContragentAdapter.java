package pl.pionwit.wpw.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.MainActivity;
import pl.pionwit.wpw.R;
import pl.pionwit.wpw.logic.contragents.Client;
import pl.pionwit.wpw.logic.contragents.ContactItem;
import pl.pionwit.wpw.logic.contragents.ContragentItem;
import pl.pionwit.wpw.logic.db.DBwpw;

/**
 * Created by Witold on 07.12.2015.
 */
public class ContragentAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<ContragentItem> contragentItems;
    LayoutInflater linflater;
    DBwpw dbWPW;
    SQLiteDatabase db;

    public ContragentAdapter(Context context) {
        ctx = context;
        this.contragentItems = generateContragent(context);
        linflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contragentItems.size();
    }

    @Override
    public Object getItem(int position) {
        return contragentItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.item_contragent, parent, false);
        }
        ContragentItem p = contragentItems.get(position);
        ((TextView) view.findViewById(R.id.contragent)).setText(p.getContragent());
        ((TextView) view.findViewById(R.id.contact)).setText(p.getContact());
        ((TextView) view.findViewById(R.id.phone)).setText(p.getPhone());
        if (p.getImgIcon()==1) {
            ((ImageView) view.findViewById(R.id.imageStar)).setImageResource(R.mipmap.ic_star_outline);
        }else {
            ((ImageView) view.findViewById(R.id.imageStar)).setImageResource(R.mipmap.ic_star);
        }
        return view;
    }

    ArrayList<ContragentItem> generateContragent(Context context){
        dbWPW = new DBwpw(context);
        db=dbWPW.getWritableDatabase();
        ArrayList<ContragentItem> rez=new ArrayList<>();
        Cursor c = db.query(DBwpw.TABLE_CLIENT, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int nameColIndex = c.getColumnIndex("name");
            int dateColIndex = c.getColumnIndex("date_changes");
            do {
                rez.add(new ContragentItem(c.getString(nameColIndex),
                        "Contact ","Phone "+c.getString(dateColIndex),10,true));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            c.close();

        dbWPW.close();
        return rez;
    }

}
