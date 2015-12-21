package pl.pionwit.wpw.logic.settings;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pl.pionwit.wpw.R;

/**
 * Created by Witold on 21.12.2015.
 */
public class SettingsActivity extends AppCompatActivity {
    FrgCountry frgCountry;
    android.support.v4.app.FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);

        frgCountry=new FrgCountry();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.flSettingsRez,frgCountry);
        fTrans.commit();
    }
}
