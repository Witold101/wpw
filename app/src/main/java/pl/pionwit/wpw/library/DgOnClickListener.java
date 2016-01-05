package pl.pionwit.wpw.library;

import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Witold on 05.01.2016.
 */
public abstract class DgOnClickListener implements DialogInterface.OnClickListener {
    View view;
    int position;

    public DgOnClickListener(View view) {
        this.view=view;
    }

    public DgOnClickListener(View view, int position) {
        this.view=view;
        this.position=position;
    }

    public View getView(){
        return view;
    }

    public int getPosition(){
        return position;
    }

    @Override
    public abstract void onClick(DialogInterface dialog, int which);
}
