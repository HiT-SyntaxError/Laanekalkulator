package no.hit.laanekalkulator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class MainActivity extends ActionBarActivity {

    private Spinner spinnerLanetype;
    private EditText inputLanebelop;
    private Button buttonLaan10000;
    private Button buttonLaan50000;
    private Button buttonLaan100000;
    private Button buttonLaan500000;
    private SeekBar seekbarLopetid;
    private RadioGroup radiogroupTerminer;
    private EditText inputRentesats;

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

        // ----- Input-felt for løpetid -----
        this.seekbarLopetid = (SeekBar) findViewById(R.id.seekbarLoepetid);

        // ----- Input-felt for antall terminer -----
        this.radiogroupTerminer = (RadioGroup) findViewById(R.id.radioGroupTerminer);

        // ----- Input-felt for rente -----
        this.inputRentesats = (EditText) findViewById(R.id.inputRente);

        // ----- Regn ut-knapp -----
        findViewById(R.id.buttonRegnUt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lagLaan();
            }
        });

        Button nedbetalingsKnapp   = (Button) findViewById(R.id.buttonNedbetalingsplan);
        nedbetalingsKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visNedbetalingsplan();
            }
        });
    }

    private void updateTextField(EditText textfield) {

    }

    private Lan lagLaan() {

        Lan.Lanetype lanetype = Lan.Lanetype.getByValue((String) spinnerLanetype.getSelectedItem());
        int lanebelop = TypeConverter.editTextToInt(inputLanebelop);
        int lopetid = seekbarLopetid.getProgress();

        int checkedId = radiogroupTerminer.getCheckedRadioButtonId();
        RadioButton radiobutton = (RadioButton) findViewById(checkedId);
        int arligeTerminer = Integer.parseInt(String.valueOf(radiobutton.getText()));

        float rentesats = TypeConverter.editTextToFloat(inputRentesats);

        return new Lan(lanebelop, lopetid, arligeTerminer, lanetype, rentesats);
    }

    private String[] getNedbetalingsplan(){
        Lan lan = lagLaan();
        return lan.getPresentationArray();
    }

    private void visNedbetalingsplan() {
        Intent i = new Intent(MainActivity.this, NedbetalingsplanActivity.class);
        Bundle b = new Bundle();
        b.putStringArray("no.hit.laanekalkulator.nedbetalingsplan", getNedbetalingsplan());
        i.putExtra("no.hit.laanekalkulator.nedbetalingsplanBundle", b);
        startActivityForResult(i, 0);
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
