package se.frand.app.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Timer timer;
    TimerTask timerTask;
    long time;
    Calendar calendar;
    Button startButton;
    Button stopButton;
    TextView timeView;
    LinearLayout layout;



    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        timeView = new TextView(this);
        timeView.setTextSize(30.0f);

        startButton = new Button(this);
        startButton.setText("start");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopButton = new Button(this);
        stopButton.setText("stop");
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopTimerTask();
            }
        });

        layout.addView(timeView);
        layout.addView(startButton);

        setContentView(layout);

        time = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //startTimer();
    }

    private void startTimer() {
        timer = new Timer();

        initializeTimerTask();
        timer.schedule(timerTask, 0, 1000);

        layout.removeView(startButton);
        layout.addView(stopButton);
    }

    private void stopTimerTask() {
        time = 0;
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        layout.removeView(stopButton);
        layout.addView(startButton);
    }

    private void initializeTimerTask() {

        calendar = Calendar.getInstance();


        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        calendar.setTime(new Date(time*1000L));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                        final String strDate = simpleDateFormat.format(calendar.getTime());


                        timeView.setText(strDate);

                        time+=1;
                    }
                });
            }
        };
    }

}
