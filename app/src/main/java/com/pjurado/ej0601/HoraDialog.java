package com.pjurado.ej0601;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class HoraDialog extends DialogFragment {
    private TimePickerDialog.OnTimeSetListener listener;
    public static HoraDialog newInstance(TimePickerDialog.OnTimeSetListener listener) {
        Log.d("ABrir", "dialog");
        HoraDialog fragment = new HoraDialog();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Iniciar con el tiempo actual
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        Log.d("ABrir", "dialog");
        // Retornar en nueva instancia del dialogo selector de tiempo
        return new TimePickerDialog(
                getActivity(),
                listener,
                hour,
                minute,
                DateFormat.is24HourFormat(getActivity()));
    }
}
