package se.frand.app.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Timer timer;
    TimerTask timerTask;
    long time;
    Calendar calendar;

    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //startTimer();
    }

    public void startTimer(View v) {
        timer = new Timer();

        initializeTimerTask();
        timer.schedule(timerTask, 0, 1000);
    }

    private void stopTimerTask(View v) {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initializeTimerTask() {

        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.);


        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        calendar.setTime(new Date(time*1000L));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("K:mm:ss");
                        final String strDate = simpleDateFormat.format(calendar.getTime());
                        TextView view = (TextView) findViewById(R.id.time_display_text);

                        view.setText(strDate);

                        time+=1;
                    }
                });
            }
        };
    }

}
