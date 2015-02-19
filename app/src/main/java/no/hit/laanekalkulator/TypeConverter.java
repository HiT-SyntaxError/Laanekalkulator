package no.hit.laanekalkulator;

import android.widget.EditText;

/**
 * Created by jorgen on 19.02.15.
 */
public class TypeConverter {

    public static int editTextToInt(EditText editText) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(String.valueOf(editText.getText()));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return intValue;
    }

    public static float editTextToFloat(EditText editText) {
        float floatValue = 0;
        try {
            floatValue = Float.parseFloat(String.valueOf(editText.getText()));
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return floatValue;
    }
}
