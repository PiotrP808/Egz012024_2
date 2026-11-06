package com.example.egz012024_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etOwner, etPurpose;
    private ListView listViewSpecies;
    private TextView tvAgeLabel, tvResult;
    private SeekBar seekBarAge;
    private TimePicker timePickerVisit;
    private Button btnOk;

    private String selectedSpecies = "Pies";
    private int currentAge = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etOwner = findViewById(R.id.etOwner);
        etPurpose = findViewById(R.id.etPurpose);
        listViewSpecies = findViewById(R.id.listViewSpecies);
        tvAgeLabel = findViewById(R.id.tvAgeLabel);
        tvResult = findViewById(R.id.tvResult);
        seekBarAge = findViewById(R.id.seekBarAge);
        timePickerVisit = findViewById(R.id.timePickerVisit);
        btnOk = findViewById(R.id.btnOk);

        timePickerVisit.setIs24HourView(true);

        String[] speciesList = {"Pies", "Kot", "Świnka morska"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                speciesList
        );
        listViewSpecies.setAdapter(adapter);
        listViewSpecies.setItemChecked(0, true);

        listViewSpecies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSpecies = (String) listViewSpecies.getItemAtPosition(position);

                if (selectedSpecies.equals("Pies")) {
                    seekBarAge.setMax(18);
                } else if (selectedSpecies.equals("Kot")) {
                    seekBarAge.setMax(20);
                } else if (selectedSpecies.equals("Świnka morska")) {
                    seekBarAge.setMax(9);
                }

                if (seekBarAge.getProgress() > seekBarAge.getMax()) {
                    seekBarAge.setProgress(seekBarAge.getMax());
                }
            }
        });

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentAge = progress;
                tvAgeLabel.setText("Ile ma lat? " + currentAge);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner = etOwner.getText().toString().trim();
                String purpose = etPurpose.getText().toString().trim();

                int hour = timePickerVisit.getHour();
                int minute = timePickerVisit.getMinute();
                String timeFormatted = String.format("%02d:%02d", hour, minute);

                String resultText = owner + ", " + selectedSpecies + ", " + currentAge + ", " + purpose + ", " + timeFormatted;
                tvResult.setText(resultText);
            }
        });
    }
}