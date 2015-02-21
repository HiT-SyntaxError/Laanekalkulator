package no.hit.laanekalkulator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.InputMismatchException;
import java.util.List;


public class NedbetalingsplanActivity extends  Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        if (i != null) {
            Bundle bundle = i.getBundleExtra("no.hit.laanekalkulator.nedbetalingsplanBundle");
            Lan lan = (Lan) bundle.getSerializable("no.hit.laanekalkulator.lan");

            ScrollView sv = new ScrollView(this);
            String[] columns = {Termin.TERMINNR, Termin.AVDRAG, Termin.RENTER, Termin.TERMINBELOP, Termin.RESTGJELD};
            Log.d("TAG", "Defining " + columns.length + " columns");
            TableLayout tableLayout = createTableLayout(lan.getTerminer(), columns);
            HorizontalScrollView hsv = new HorizontalScrollView(this);

            hsv.addView(tableLayout);
            sv.addView(hsv);
            setContentView(sv);
        }
    }

    private String getFieldHeader(String colName) {
        switch (colName) {
            case Termin.TERMINNR:
                return "Termin";
            case Termin.AVDRAG:
                return "Avdrag";
            case Termin.RENTER:
                return "Renter";
            case Termin.TERMINBELOP:
                return "Terminbelop";
            case Termin.RESTGJELD:
                return "Restgjeld";
            default:
                return "";
        }
    }

    private TableLayout createTableLayout(List<Termin> terminer, String[] columns) {
        // 1) Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setBackgroundColor(Color.rgb(63,131,173));

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        TableRow.LayoutParams headerRowParams = new TableRow.LayoutParams();
        headerRowParams.setMargins(8, 3, 8, 3);
        headerRowParams.weight = 1;

        TableRow headerRow = new TableRow(this);
        for(String column : columns) {
            TextView headerTextView = new TextView(this);
            headerTextView.setBackgroundColor(Color.rgb(63,131,173));
            headerTextView.setText(getFieldHeader(column));
            headerRow.addView(headerTextView, headerRowParams);
        }
        tableLayout.addView(headerRow, tableLayoutParams);

        for (Termin termin : terminer) {
            // 3) create tableRow
            TableRow tableRow = new TableRow(this);
            tableRow.setBackgroundColor(Color.BLACK);

            for (int j = 0; j < termin.getFieldCount(); j++) {
                // 4) create textView
                TextView textView = new TextView(this);
                textView.setBackgroundColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);
                textView.setText(Long.toString(getTerminValue(termin, columns[j])));

                // 5) add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }

            // 6) add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }

    private long getTerminValue(Termin termin, String column) {
        switch (column) {
            case Termin.TERMINNR:
                return termin.getTerminNr();
            case Termin.AVDRAG:
                return termin.getAvdrag();
            case Termin.RENTER:
                return termin.getRenter();
            case Termin.TERMINBELOP:
                return termin.getTerminbelop();
            case Termin.RESTGJELD:
                return termin.getRestgjeld();
            default:
                throw new InputMismatchException("Expected a valid column type");
        }
    }

/*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id ) {
        TextView raden = (TextView) v;
        Intent resultat = new Intent();
        resultat.putExtra("ValgtText", raden.getText());
        resultat.putExtra("ValgtPosisjon", position);
        System.out.println("Du klikket på " + raden.getText());
        setResult(Activity.RESULT_OK, resultat);
        this.finish();
    }
    */
}

