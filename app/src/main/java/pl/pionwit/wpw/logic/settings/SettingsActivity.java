package pl.pionwit.wpw.logic.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CountryAdapter;

/**
 * Created by Witold on 21.12.2015.
 */
public class SettingsActivity extends AppCompatActivity {

    LinearLayout llSetingCategory;
    LinearLayout llSetingCountryCod;
    LinearLayout llSetingLevel;
    Context cnt;
    FragmentTransaction fTrans;
    int idFragment;

    FrgCountry frgCountry;
    FrgCategory frgCategory;
    FrgLevel frgLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idFragment=R.id.llSettingCategory;
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        cnt=this;

        llSetingCategory=(LinearLayout)findViewById(R.id.llSettingCategory);
        llSetingCountryCod=(LinearLayout)findViewById(R.id.llSettingCountryCod);
        llSetingLevel=(LinearLayout)findViewById(R.id.llSettingLevel);


        frgCountry=new FrgCountry();
        frgCategory = new FrgCategory();
        frgLevel=new FrgLevel();

        initFragment(R.id.llSettingCountryCod);

        View.OnClickListener ocView=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFragment(v.getId());
            }
        };

        llSetingCategory.setOnClickListener(ocView);
        llSetingCountryCod.setOnClickListener(ocView);
        llSetingLevel.setOnClickListener(ocView);

    }

    private void initFragment(int idFragment) {
        switch (idFragment)
        {
            case R.id.llSettingCountryCod:
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.flSettingsRez,frgCountry);
                fTrans.commit();
                break;
            case R.id.llSettingCategory:
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.flSettingsRez, frgCategory);
                fTrans.commit();
                break;
            case R.id.llSettingLevel:
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.flSettingsRez, frgLevel);
                fTrans.commit();
                break;
            default:
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.flSettingsRez, frgCountry);
                fTrans.commit();
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Добавить код страны");
        // создаем view из dialog.xml
        LinearLayout llAddCountryDialog = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dlg_add_country, null);
        // устанавливаем ее, как содержимое тела диалога
        adb.setView(llAddCountryDialog);
        // находим TexView для отображения кол-ва
        return adb.create();
    }
}
