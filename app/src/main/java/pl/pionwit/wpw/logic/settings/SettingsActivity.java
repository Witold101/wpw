package pl.pionwit.wpw.logic.settings;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CountryAdapter;

/**
 * Created by Witold on 21.12.2015.
 */
public class SettingsActivity extends AppCompatActivity {
    FrgCountry frgCountry;
    android.support.v4.app.FragmentTransaction fTrans;
    LinearLayout llAddCountryDialog;
    Button butonRef;

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

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Добавить код страны");
        // создаем view из dialog.xml
        llAddCountryDialog = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dlg_add_country, null);
        // устанавливаем ее, как содержимое тела диалога
        adb.setView(llAddCountryDialog);
        // находим TexView для отображения кол-ва
        return adb.create();
    }


}
