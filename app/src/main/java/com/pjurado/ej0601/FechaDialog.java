package com.pjurado.ej0601;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.widget.DatePicker;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.List;

/**
 * Fragmento con un diálogo de elección de fechas
 */

public class FechaDialog extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public static FechaDialog newInstance(DatePickerDialog.OnDateSetListener listener) {
        FechaDialog fragment = new FechaDialog();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Obtener fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Retornar en nueva instancia del dialogo selector de fecha
        return new DatePickerDialog(
                getActivity(),
                listener,
                year,
                month,
                day);
    }



}
