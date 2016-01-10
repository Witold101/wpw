package pl.pionwit.wpw.logic.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;

import pl.pionwit.wpw.R;
import pl.pionwit.wpw.adapters.CategoryAdapter;
import pl.pionwit.wpw.adapters.LevelAdapter;
import pl.pionwit.wpw.library.DgOnClickListener;
import pl.pionwit.wpw.logic.contragents.Category;
import pl.pionwit.wpw.logic.contragents.LevelUser;

/**
 * Created by Witold on 10.01.2016.
 */
public class FrgLevel extends Fragment {
    View v;
    LevelAdapter adapter;
    ListView levelList;
    FloatingActionButton fab;
    Context cnt;
    int flagDialog;//0- закрыты диалоговые окна, 1 - открыто окно добавить, 2 - открыто окно редактировать

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        flagDialog = 0;

        v = inflater.inflate(R.layout.frg_settings_level, container, false);
        levelList = (ListView) v.findViewById(R.id.lvLevel);
        fab = (FloatingActionButton) v.findViewById(R.id.fabAddLevel);
        adapter = new LevelAdapter(cnt);
        levelList.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDialog = 1;
                dialogLevel(flagDialog, 0);
            }
        });
        dialogLevel(flagDialog, 0);
        levelList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                flagDialog=2;
                dialogLevel(flagDialog, position);
                return false;
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cnt = context;
    }

    private void dialogLevel(int flag, int position) {
        if (flag!=0) {
            View view = getLayoutInflater(null).inflate(R.layout.dlg_level, null);
            AlertDialog.Builder adb = new AlertDialog.Builder(cnt);
            switch (flag) {
                case 1:
                    adb.setTitle(R.string.level_add);
                    adb.setView(view);
                    adb.setIcon(R.mipmap.ic_account_key);
                    adb.setPositiveButton(R.string.btn_submit, new DgOnClickListener(view) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etLevelNumber = (EditText)getView().findViewById(R.id.etDialogLevelNumber);
                            EditText etLevelNote = (EditText)getView().findViewById(R.id.etDialogLevelNote);
                            adapter.addLevelToDB(cnt, new LevelUser(Integer.parseInt(etLevelNumber.getText().toString())
                                    , etLevelNote.getText().toString(), new Date()));
                            adapter.notifyDataSetChanged();
                            flagDialog=0;
                        }
                    });
                    AlertDialog dialogAdd = adb.create();
                    dialogAdd.show();
                    break;
                case 2:
                    adb.setTitle(R.string.level_edit_dell);
                    adb.setView(view);
                    adb.setIcon(R.mipmap.ic_account_key);
                    EditText etLevelNumber = (EditText)view.findViewById(R.id.etDialogLevelNumber);
                    EditText etLevelNote = (EditText)view.findViewById(R.id.etDialogLevelNote);
                    etLevelNumber.setText(Integer.toString(adapter.getItem(position).getNumber()));
                    etLevelNote.setText(adapter.getItem(position).getNote());
                    adb.setPositiveButton(R.string.btn_submit, new DgOnClickListener(view,position) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etLevelNumber = (EditText)getView().findViewById(R.id.etDialogLevelNumber);
                            EditText etLevelNote = (EditText)getView().findViewById(R.id.etDialogLevelNote);

                            LevelUser item=adapter.getItem(getPosition());
                            item.setNamber(Integer.parseInt(etLevelNumber.getText().toString()));
                            item.setNote(etLevelNote.getText().toString());
                            item.setDateChanges(new Date());
                            adapter.editLevelToDb(cnt, item, getPosition());
                            adapter.notifyDataSetChanged();
                            flagDialog = 0;
                        }
                    });

                    adb.setNegativeButton(R.string.btn_dell, new DgOnClickListener(view, position) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.dellLevelToDb(cnt,getPosition());
                            adapter.notifyDataSetChanged();
                            flagDialog = 0;
                        }
                    });
                    AlertDialog dialogEdit = adb.create();
                    dialogEdit.show();
                    break;
                default:
                    flagDialog=0;
                    break;
            }
        }
    }
}
