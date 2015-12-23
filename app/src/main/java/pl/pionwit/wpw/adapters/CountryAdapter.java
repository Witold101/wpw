package pl.pionwit.wpw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.logic.contragents.Country;

/**
 * Created by Witold on 21.12.2015.
 */
public class CountryAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Country> countries;
    LayoutInflater linflater;

    public CountryAdapter(Context ctx, ArrayList<Country> countries) {
        this.ctx = ctx;
        this.countries = countries;
        linflater = (LayoutInflater) this.ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        Country rez=(Country)getItem(position);
        return rez.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = linflater.inflate(R.layout.item_country, parent, false);
        }
        Country p = countries.get(position);
        ((TextView) view.findViewById(R.id.tvCountryCod)).setText(Integer.toString(p.getKod()));
        ((TextView) view.findViewById(R.id.tvCountryName)).setText(p.getName());
        ((TextView) view.findViewById(R.id.tvCountryLitlName)).setText(p.getLitlCod());
        return view;
    }

    public void notifyDataSetChanged(ArrayList<Country> countries) {
        this.countries=countries;
        super.notifyDataSetChanged();
    }
}
