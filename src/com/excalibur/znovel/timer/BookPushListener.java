package com.excalibur.znovel.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BookPushListener implements ServletContextListener{

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        sce.getServletContext().log("定时器已经启动");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(year,month,day,00,30,00);
        Date date = calendar.getTime();
        int period = 1000 * 60 * 60;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },date,period);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (timer != null) {
            timer.cancel();
            event.getServletContext().log("定时器销毁");
        }
    }
}
