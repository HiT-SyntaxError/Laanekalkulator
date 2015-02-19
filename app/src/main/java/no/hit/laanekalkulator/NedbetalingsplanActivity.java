package no.hit.laanekalkulator;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class NedbetalingsplanActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] listeTabell=null;
        Intent i = getIntent();
        if(i!=null) {
            Bundle bundle = i.getBundleExtra("no.hit.laanekalkulator.nedbetalingsplanBundle");
            listeTabell=bundle.getStringArray("no.hit.laanekalkulator.nedbetalingsplan");
        }
        else {
            listeTabell= new String[1];
        }

        // Adapter som knytter data til GUI
        ArrayAdapter<String> mAdapter =
                new ArrayAdapter<String>(this, R.layout.activity_nedbetalingsplan_rad, listeTabell);

        this.setContentView(R.layout.activity_nedbetalingsplan);

        this.setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id ) {
        TextView raden = (TextView) v;
        Intent resultat = new Intent();
        resultat.putExtra("ValgtText", raden.getText());
        resultat.putExtra("ValgtPosisjon", position);
        setResult(Activity.RESULT_OK, resultat);
        this.finish();
    }
}

