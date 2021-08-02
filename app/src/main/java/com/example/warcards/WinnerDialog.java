package com.example.warcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class WinnerDialog extends AppCompatDialogFragment {
    private TextView winnerName;
    private TextView title;
    private WinnerDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String winnername = winnerName.getText().toString();
                        String WinTitle = title.getText().toString();
                        listener.applyTexts(winnername);
                        listener.reset();
                    }
                });

//      set the Textview on the pop up to the name of the winner
        winnerName = view.findViewById(R.id.winnerText);
        title = view.findViewById(R.id.title);

        winnerName.setText(listener.winner_Name());
        title.setText(listener.Title_Name());
        return  builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (WinnerDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement WinnerDialogLister");
        }
    }

    public interface WinnerDialogListener{
        void applyTexts(String winnerName);
        void reset();
        String winner_Name();
        String Title_Name();
    }
}
