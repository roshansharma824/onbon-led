package com.stackfusion.onbon;

import onbon.y2.Y2Env;
import org.apache.log4j.Level;
import java.util.Timer;
import java.util.TimerTask;
import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.MainController.loopErrorMessage;
import static com.stackfusion.onbon.Utils.checkInternet;
import static com.stackfusion.onbon.Utils.printLog;

public class Controller {
    static Timer timer;

    static Timer logInEveryDayTimer;

    static Timer exceptionRestartTimer;

    static Timer checkInternet;
    public static void start()
    {
        timer = new Timer();
        TimerTask reset = new Reset();

        timer.schedule(reset, 120000);

        TimerTask loopError = new LoopError();

        timer.schedule(loopError,1000L,3000L);

    }

    public static void dayTimerStart(){
        logInEveryDayTimer = new Timer();
        exceptionRestartTimer = new Timer();
        checkInternet = new Timer();
        TimerTask exceptionRestart = new ExceptionRestart();
        //Convert 5 minutes to Milliseconds 300000L
        exceptionRestartTimer.schedule(exceptionRestart,1000,300000L);
        TimerTask logInEveryDay = new  LogInEveryDay();
         //Convert 24 Hours to Milliseconds 86,400,000
        logInEveryDayTimer.schedule(logInEveryDay,10000,50400000L);

        TimerTask checkInterNetConnection = new CheckInterNetConnection();

        checkInternet.schedule(checkInterNetConnection,1000L,10000L);
    }

}

class Reset extends TimerTask
{
    public static int i = 0;
    public void run()
    {
        LOOP1 = "low";
        CHECK_LOOP = "low";
        dashboardDatamessage = null;
        notFoundmessage = null;
        welComeMessageResponsemessage = null;
//        exitGateDatamessage = null;
        MainController.siteLogo();
        printLog("Timer ran " + ++i);

    }
}

class LogInEveryDay extends TimerTask{

    @Override
    public void run() {
        printLog("Logout EveryDay ");
        MainController.screen.logout();

        Y2Env.initial();

        MainController.pz_initial_setup();

        MainController.siteLogo();
    }
}

class ExceptionRestart extends TimerTask{
    @Override
    public void run() {

        if (exception_count >3) {
            //timer function when exception occurred more than 3 times in 5 minutes
            // logout than
            // re init again
            printLog("Exception occurred Logout ");
            MainController.screen.logout();

            Y2Env.initial();

            MainController.pz_initial_setup();

            MainController.siteLogo();
        }else {
            exception_count = 0;
        }
    }
}

class LoopError extends TimerTask {
    @Override
    public void run() {
        if (Loop_Fluctuating_Count >3){
            loopErrorMessage();
        }
        Loop_Fluctuating_Count = 0;
    }
}

class CheckInterNetConnection extends TimerTask{

    @Override
    public void run() {
        if (checkInternet()){
            printLog(Level.INFO,String.valueOf("Connection Successful "+checkInternet()));
        }
        else {
            printLog(Level.ERROR,String.valueOf("Internet Not Connected "+checkInternet()));
        }
    }
}