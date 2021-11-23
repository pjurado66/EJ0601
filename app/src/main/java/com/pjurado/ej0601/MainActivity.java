package com.pjurado.ej0601;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvDesdeFecha;
    private TextView tvHastaFecha;
    private CheckBox cbHora;
    private TextView tvDesdeHora;
    private TextView tvHastaHora;
    private Button btpersonas;
    private RecyclerView rv;
    private MyAdapter mAdapter;
    private ArrayList itemsRecycler = new ArrayList();
    private ArrayList itemsSeleccionados = new ArrayList();
    private boolean itemSelected[];
    private static final CharSequence[] myDataSet = new CharSequence[4];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataSet[0]="Pepito";
        myDataSet[1]="Juanito";
        myDataSet[2]="Gonzal";
        myDataSet[3]="Paquito";
        itemSelected = new boolean[myDataSet.length];
        tvDesdeFecha = findViewById(R.id.textViewDesdeFecha);
        tvDesdeFecha.setOnClickListener(this);


        tvHastaFecha = findViewById(R.id.textViewHastaFecha);
        tvHastaFecha.setOnClickListener(this);

        tvDesdeHora = findViewById(R.id.textViewDesdeHora);
        tvDesdeHora.setOnClickListener(this);
        tvDesdeHora.setClickable(false);

        tvHastaHora = findViewById(R.id.textViewHastaHora);
        tvHastaHora.setOnClickListener(this);
        tvHastaHora.setClickable(false);

        cbHora = findViewById(R.id.checkBoxHora);
        cbHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHora.isChecked()){
                    tvDesdeHora.setClickable(true);
                    tvHastaHora.setClickable(true);
                    Toast.makeText(MainActivity.this, "a true", Toast.LENGTH_SHORT).show();
                }
                else{
                    tvDesdeHora.setClickable(false);
                    tvHastaHora.setClickable(false);
                }
            }
        });

        btpersonas = findViewById(R.id.button);
        btpersonas.setOnClickListener(this);

        rv = findViewById(R.id.recyclerView);

        // Esta línea mejora el rendimiento, si sabemos que el contenido
        // no va a afectar al tamaño del RecyclerView
        rv.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        // Asociamos un adapter (ver más adelante cómo definirlo)
        mAdapter = new MyAdapter(itemsSeleccionados);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.textViewDesdeFecha):
                pedirFecha(tvDesdeFecha);
                break;
            case (R.id.textViewHastaFecha):
                pedirFecha(tvHastaFecha);
                break;
            case (R.id.textViewDesdeHora):

                pedirHora(tvDesdeHora);
                break;
            case (R.id.textViewHastaHora):
                pedirHora(tvHastaHora);
                break;
            case (R.id.button):

                createMultipleListDialog();
                break;
        }

    }


    private void pedirFecha(final TextView tvFecha){
        FechaDialog newFragment = FechaDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                tvFecha.setText(selectedDate);
            }

        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private  void pedirHora(final TextView tvHora){

        HoraDialog newFragment = HoraDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                final String selectedTime = hourOfDay + ":" + minute;
                tvHora.setText(selectedTime);
            }
        });
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Crea un diálogo con una lista de checkboxes
     * de selección multiple
     *
     * @return Diálogo
     */
    public AlertDialog createMultipleListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Intereses")
                .setMultiChoiceItems(myDataSet, itemSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            // Guardar indice seleccionado
                            itemSelected[which] = true;
                            itemsSeleccionados.add(myDataSet[which]);

                        } else if (itemsSeleccionados.contains(myDataSet[which])) {
                            // Remover indice sin selección

                            itemSelected[which] = false;
                            itemsSeleccionados.remove(itemsSeleccionados.indexOf(myDataSet[which]));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        /*itemsRecycler = new ArrayList();
                        for(int i = 0; i < itemsSeleccionados.size(); i++){
                            itemsRecycler.add(itemsSeleccionados.get(i));
                        }
*/

                    }
                });
/*
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemsSeleccionados = new ArrayList();
                for(int i = 0; i < itemsRecycler.size(); i++){
                    itemsSeleccionados.add(itemsRecycler.get(i));
                }


            }
        });*/
            builder.show();
        return builder.create();
    }


}