package no.hit.laanekalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Spinner spinnerLanetype;
    private EditText inputLanebelop;
    private Button buttonLaan10000;
    private Button buttonLaan50000;
    private Button buttonLaan100000;
    private Button buttonLaan500000;
    private TextView textViewLopetid;
    private SeekBar seekbarLopetid;
    private RadioGroup radiogroupTerminer;
    private EditText inputRentesats;
    private Button nedbetalingsKnapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----- Valg av lånetype ----
        this.spinnerLanetype = (Spinner) findViewById(R.id.spinnerLaanetyper);
        ArrayAdapter<String> lanetypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Lan.getLanetyper());
        lanetypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerLanetype.setAdapter(lanetypeAdapter);

        // ----- Input-felt for lånebeløp ----
        this.inputLanebelop = (EditText) findViewById(R.id.inputLaan);

        // ----- Sett-beløp-knapper -----
        this.buttonLaan10000 = (Button) findViewById(R.id.buttonLaan10000);
        this.buttonLaan50000 = (Button) findViewById(R.id.buttonLaan50000);
        this.buttonLaan100000 = (Button) findViewById(R.id.buttonLaan100000);
        this.buttonLaan500000 = (Button) findViewById(R.id.buttonLaan500000);

        // ----- Tekstfelt for løpetid -----
        this.textViewLopetid = (TextView) findViewById(R.id.textViewLoepetid);

        // ----- Seekbar for løpetid -----
        this.seekbarLopetid = (SeekBar) findViewById(R.id.seekBarLoepetid);

        // ----- Input-felt for antall terminer -----
        this.radiogroupTerminer = (RadioGroup) findViewById(R.id.radioGroupTerminer);

        // ----- Input-felt for rente -----
        this.inputRentesats = (EditText) findViewById(R.id.inputRente);

        // ----- 'Lag nedbetalingsplan'-knapp -----
        this.nedbetalingsKnapp = (Button) findViewById(R.id.buttonNedbetalingsplan);


        // ----- Lyttere ------
        buttonLaan10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tillegg = TypeConverter.textViewToInt(buttonLaan10000);
                oppdaterTextView(inputLanebelop, tillegg);
            }
        });

        this.buttonLaan50000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tillegg = TypeConverter.textViewToInt(buttonLaan50000);
                oppdaterTextView(inputLanebelop, tillegg);
            }
        });

        this.buttonLaan100000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tillegg = TypeConverter.textViewToInt(buttonLaan100000);
                oppdaterTextView(inputLanebelop, tillegg);
            }
        });

        this.buttonLaan500000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tillegg = TypeConverter.textViewToInt(buttonLaan500000);
                oppdaterTextView(inputLanebelop, tillegg);
            }
        });

        this.nedbetalingsKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visNedbetalingsplan();
            }
        });

        this.seekbarLopetid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewLopetid.setText("Løpetid " + String.valueOf(progress) + " år");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textViewLopetid.setTextSize(20);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewLopetid.setTextSize(15);
            }
        });
    }

    private void oppdaterTextView(TextView textView, int tillegg) {
        int nyttBelop = TypeConverter.textViewToInt(textView) + tillegg;
        textView.setText("" + nyttBelop);
    }

    private Lan lagLaan() {

        Lan.Lanetype lanetype = Lan.Lanetype.getByValue((String) spinnerLanetype.getSelectedItem());
        int lanebelop = TypeConverter.textViewToInt(inputLanebelop);
        int lopetid = seekbarLopetid.getProgress();

        int checkedId = radiogroupTerminer.getCheckedRadioButtonId();
        RadioButton radiobutton = (RadioButton) findViewById(checkedId);
        int arligeTerminer = Integer.parseInt(String.valueOf(radiobutton.getText()));

        float rentesats = TypeConverter.textViewToFloat(inputRentesats);

        return new Lan(lanebelop, lopetid, arligeTerminer, lanetype, rentesats);
    }

    private void visNedbetalingsplan() {
        Intent i = new Intent(MainActivity.this, NedbetalingsplanActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("no.hit.laanekalkulator.lan", lagLaan());
        i.putExtra("no.hit.laanekalkulator.nedbetalingsplanBundle", b);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
