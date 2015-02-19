package no.hit.laanekalkulator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {

    private Spinner spinnerLanetype;
    private EditText inputLanebelop;
    private EditText inputLopetid;
    private EditText inputArligeTerminer;
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

        // ----- Regn ut-knapp -----
        this.inputLanebelop = (EditText) findViewById(R.id.inputLaan);

        // ----- Regn ut-knapp -----
        this.inputLopetid = (EditText) findViewById(R.id.inputLoepetid);

        // ----- Regn ut-knapp -----
        this.inputArligeTerminer = (EditText) findViewById(R.id.inputTerminer);

        // ----- Regn ut-knapp -----
        this.inputRentesats = (EditText) findViewById(R.id.inputRente);

        // ----- Regn ut-knapp -----
        Button regnUt = (Button) findViewById(R.id.buttonRegnUt);
        regnUt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regnUt();
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

    private void regnUt() {

        Lan.Lanetype lanetype = Lan.Lanetype.getByValue((String) spinnerLanetype.getSelectedItem());
        int lanebelop = TypeConverter.editTextToInt(inputLanebelop);
        int lopetid = TypeConverter.editTextToInt(inputLopetid);
        int arligeTerminer = TypeConverter.editTextToInt(inputArligeTerminer);
        float rentesats = TypeConverter.editTextToFloat(inputRentesats);

        Lan lan = new Lan(lanebelop, lopetid, arligeTerminer, lanetype, rentesats);

        System.out.println(lan.toString());
    }

    private String[] getNedbetalingsplan(){
        String[] listeTabell = new String[50];
        for(int rad=0;rad<listeTabell.length;rad++) {
            listeTabell[rad] = "Dette er rad nr. " + rad;
        }
        return listeTabell;
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
