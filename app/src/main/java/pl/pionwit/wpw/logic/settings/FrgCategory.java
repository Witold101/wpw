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
import pl.pionwit.wpw.library.DgOnClickListener;
import pl.pionwit.wpw.logic.contragents.Category;

/**
 * Created by Witold on 29.12.2015.
 */
public class FrgCategory extends Fragment {
    View v;
    CategoryAdapter adapter;
    ListView categoryList;
    FloatingActionButton fab;
    Context cnt;
    int flagDialog;//0- закрыты диалоговые окна, 1 - открыто окно добавить, 2 - открыто окно редактировать

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        flagDialog = 0;

        v = inflater.inflate(R.layout.frg_settings_category, container, false);
        categoryList = (ListView) v.findViewById(R.id.lvCategory);
        fab = (FloatingActionButton) v.findViewById(R.id.fabAddCategory);
        adapter = new CategoryAdapter(cnt);
        categoryList.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDialog = 1;
                dialogCategory(flagDialog,0);
            }
        });
        dialogCategory(flagDialog,0);
        categoryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                flagDialog=2;
                dialogCategory(flagDialog,position);
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

    private void dialogCategory(int flag, int position) {
        if (flag!=0) {
            View view = getLayoutInflater(null).inflate(R.layout.dlg_category, null);
            AlertDialog.Builder adb = new AlertDialog.Builder(cnt);

            switch (flag) {
                case 1:
                    adb.setTitle(R.string.category_add);
                    adb.setView(view);
                    adb.setIcon(R.mipmap.ic_checkbox_multiple_marked_outline);
                    adb.setPositiveButton(R.string.btn_submit, new DgOnClickListener(view) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etCategory = (EditText)getView().findViewById(R.id.etDialogCategory);
                            adapter.addCategoryToDB(cnt, etCategory.getText().toString(), new Date());
                            adapter.notifyDataSetChanged();
                            flagDialog=0;
                        }
                    });
                    AlertDialog dialogAdd = adb.create();
                    dialogAdd.show();
                    break;
                case 2:
                    adb.setTitle(R.string.category_edit_dell);
                    adb.setView(view);
                    adb.setIcon(R.mipmap.ic_checkbox_multiple_marked_outline);
                    EditText etCategory = (EditText)view.findViewById(R.id.etDialogCategory);
                    etCategory.setText(adapter.getItem(position).getName());
                    adb.setPositiveButton(R.string.btn_submit, new DgOnClickListener(view,position) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etCategory = (EditText)getView().findViewById(R.id.etDialogCategory);
                            Category item=adapter.getItem(getPosition());
                            item.setName(etCategory.getText().toString());
                            adapter.editCategoryToDb(cnt,item,getPosition());
                            adapter.notifyDataSetChanged();
                            flagDialog = 0;
                        }
                    });

                    adb.setNegativeButton(R.string.btn_dell, new DgOnClickListener(view, position) {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.dellCategoryToDb(cnt,getPosition());
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
