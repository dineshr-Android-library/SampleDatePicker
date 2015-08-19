package blogs.dinesh.com.testdatepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
    Button b;
    Calendar calendar;
    int year,month,day;
    Context context = this;
    TextView tv;
    SharedPreferences pref;
    SharedPreferences.Editor edit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b =  (Button)findViewById(R.id.b);
        tv= (TextView)findViewById(R.id.d);
        pref = context.getSharedPreferences("mydata", 0);
        edit = pref.edit();

        day = pref.getInt("day",0);
        year = pref.getInt("year",0);
        month = pref.getInt("month",0);

        if(day != 0 || month != 0 || year !=0){
            tv.setText(year+"."+month+"."+day);
        }else {
            setDate();
            tv.setText(year + "." + month + "." + day);
        }

        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        triggerDate();
                    }
                }
        );

    }

    private void triggerDate() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        DateFragment editNameDialog = new DateFragment();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    private void setDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public void onDateSet(DatePicker view, int yearo, int monthOfYear, int dayOfMonth) {
        year = yearo;
        month = monthOfYear;
        day = dayOfMonth;
        tv.setText(year + "." + monthOfYear + "." + dayOfMonth);
    }

    @Override
    protected void onPause() {
        super.onPause();
        edit.putInt("year", year);
        edit.putInt("month", month);
        edit.putInt("day", day);
        edit.commit();

    }
}

