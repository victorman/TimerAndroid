package se.frand.app.timer;

import android.os.Handler;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by victorfrandsen on 9/26/15.
 */
public class StopwatchTask extends TimerTask {

    private final Handler handler;
    private final Calendar calendar;

    public StopwatchTask(Handler handler, Calendar calendar) {
        this.handler = handler;
        this.calendar = calendar;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                calendar.setTime(new Date(MainActivity.time * 1000L));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                String strDate = simpleDateFormat.format(calendar.getTime());


                Message message = Message.obtain();

                message.obj = strDate;

                handler.sendMessage(message);

                MainActivity.time += 1;
            }
        });
    }
}
