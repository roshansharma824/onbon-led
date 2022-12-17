package com.stackfusion.onbon;
import com.stackfusion.onbon.data.*;

import java.util.ArrayList;

public class Constants {
    // IP address, set using LedshowYQ software


    //testing
//    public static String url = "http://192.168.1.45"; // make connection to onBon-led using this URL
//    public static String jetson_mac = "48:B0:2D:82:0B:19"; // Need jetson mac address to get response from API

    public static String getmLocalURI(String jetsonip) {
        String mLocalUri = "tcp://"+jetsonip+":8883" ;  // local mqtt URL to connect client
        return mLocalUri;

//      return "tcp://172.27.9.1:8883";//for testing
    }

    // production
    public static String url = "";   // make connection to onBon-led using this URL
    public static String jetson_mac = ""; // Need jetson mac address to get response from API


    public static final String BASE_URL = "https://parkzap.com/";
    public static final String GET_JETSON_DATA = BASE_URL+ "parkzap-pos/get_jetson_data/";

    public static final String ADVERTISEMENTS_LIST = BASE_URL+"/parkzap/advertisements/list/";
	public static String jetson_ip;
      // broker uri

    public static String aoi_site_name;
    public static String gateId = ""; // gate id for listening MQTT topic

    public static String gateType; // get gate type exit or entry

    public static Boolean payment_at_entry;


    public static JetsonData jetsonData;

    public static ArrayList<AdList> adsList;
    
    public static float textSize = 14f;  // for set text size

    public static final String currencySymbol = "\u20B9"; // For print rupee symbol: ₹

    public static int display_brightness = 255; // range 1-255

    // topics
    public final static String Mqtt_Self_topic ="_android_tv";
    public final static String topic_logcat ="_android_tv_logcat";
    public final static String LOCAL_FIRST ="_localfirst";

    public final static String LOCAL_FASTAG ="_parkbox_fastag";
    public final static String JETSON_TOPIC ="_jetson";

    public final static String DASHBOARD_TOPIC = "__dashboard";

    public static String LOOP1 = "low";
    public static String CHECK_LOOP = "low";

    public static final String HIGH = "high";
    public static final String LOW = "low";

    public static int exception_count=0; // whenever exception occurred it increase count  // timer function when exception occurred more than 3 times in 5 minutes // logout than // re init again

    public static int Loop_Fluctuating_Count = 0;

    public static LocalFisrtModel localFisrtModelmessage ; // LOOP message response to check loop status

//    public static AnprModel anprModelmessage;

    public static WelComeMessageResponse welComeMessageResponsemessage;

    public static DashboardData dashboardDatamessage;

    public static NotFound notFoundmessage; // when ticket notfound message get from mqtt it display Not Found

    public static ExitGateData exitGateDatamessage;
    


}
