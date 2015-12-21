package pl.pionwit.wpw.logic.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CountryAdapter;
import pl.pionwit.wpw.logic.contragents.Country;

/**
 * Created by Witold on 21.12.2015.
 */
public class FrgCountry extends android.support.v4.app.Fragment {

    ListView countrysList;
    View v;
    CountryAdapter adapter;
    //ContragentAdapter adapter;
    Context cont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frg_settings_country, container,false);
        countrysList=(ListView)v.findViewById(R.id.lvCountry);
        adapter=new CountryAdapter(this.getActivity(),initCountrys());
        countrysList.setAdapter(adapter);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cont=context;
    }

    //********** dell ***********

    private ArrayList<Country> initCountrys(){
        ArrayList<Country> countries =new ArrayList<>();
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));
        countries.add(new Country("Cuba",85,new Date(),5));

        return countries;
    }


    //***************************


}
