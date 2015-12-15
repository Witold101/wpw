package pl.pionwit.wpw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.logic.contragents.ContactItem;

/**
 * Created by Witold on 07.12.2015.
 */
public class ClientGridAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<ContactItem> contactItems;
    LayoutInflater linflater;

    public ClientGridAdapter(Context context, ArrayList<ContactItem> cItems) {
        ctx = context;
        this.contactItems = cItems;
        linflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactItems.size();
    }

    @Override
    public Object getItem(int position) {
        return contactItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.item_grid_contact, parent, false);
        }
        ContactItem p = contactItems.get(position);
        ((TextView) view.findViewById(R.id.tvNameGridRez)).setText(p.getName());
        ((TextView) view.findViewById(R.id.tvDepartmentGridRez)).setText(p.getDepartment());
        ((TextView) view.findViewById(R.id.tvPhoneGridRez)).setText(p.getNote());
        ((TextView) view.findViewById(R.id.tvMailGridRez)).setText(p.getNote());
        return view;
    }
}
