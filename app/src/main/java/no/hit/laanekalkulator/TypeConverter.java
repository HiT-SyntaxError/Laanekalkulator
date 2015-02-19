package no.hit.laanekalkulator;

import android.widget.TextView;

public class TypeConverter {

    public static int textViewToInt(TextView textView) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(String.valueOf(textView.getText()));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return intValue;
    }

    public static float textViewToFloat(TextView textView) {
        float floatValue = 0;
        try {
            floatValue = Float.parseFloat(String.valueOf(textView.getText()));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return floatValue;
    }
}
