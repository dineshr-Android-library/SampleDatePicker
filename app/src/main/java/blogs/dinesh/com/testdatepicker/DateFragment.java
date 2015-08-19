package blogs.dinesh.com.testdatepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

public class DateFragment extends android.support.v4.app.DialogFragment{
    int year ;
    int month ;
    int day ;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker


        Context con =  (MainActivity)getActivity();

        SharedPreferences pref =  con.getSharedPreferences("mydata", 0);
        day = pref.getInt("day",0);
        year = pref.getInt("year",0);
        month = pref.getInt("month",0);
        if(day == 0 || month == 0 || year ==0){
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (MainActivity)getActivity(), year, month, day);
    }
}
