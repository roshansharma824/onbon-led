package com.stackfusion.onbon;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.stackfusion.onbon.data.*;

import org.apache.log4j.Level;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;

import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.Utils.printLog;

public class MQTTExit {

    /*
     * For Local mqtt connection
     * */

    private static String mParkzapMQTT_ClientID2;
    private static MqttAsyncClient mParkzapMQTT_Client2;
    private static MqttConnectOptions mqttConnectOptions2;
//    public static void main(String[] args) {
//
//        MQTTClient();
//    }







    public static void MQTTClient(){
        // Local client
        mParkzapMQTT_ClientID2 = MqttAsyncClient.generateClientId()+String.valueOf(System.currentTimeMillis());

        /*For client 2 Local*/
        try {
            printLog("connecting local mqtt : "+Constants.getmLocalURI(Constants.jetson_ip));
            mParkzapMQTT_Client2 = new MqttAsyncClient(
                    Constants.getmLocalURI(Constants.jetson_ip),
                    mParkzapMQTT_ClientID2,new MemoryPersistence()
            );
//            printLog("local mqtt connection success: "+Constants.mLocalUri);
            
        } catch (MqttException e) {
            printLog(Level.ERROR,"local mqtt connection fails: "+e.getMessage());
        	e.printStackTrace();
        }catch (IllegalArgumentException e){
            //occurs when there is not jetson device in gatedetails and prefs gives "" to connect MQTT local client
            e.printStackTrace();
            printLog(Level.ERROR,"local mqtt connection success: "+e.getMessage());
        }

        /*For client 2 local*/
        mqttConnectOptions2 = new MqttConnectOptions();
        mqttConnectOptions2.setConnectionTimeout(30);
        mqttConnectOptions2.setKeepAliveInterval(10);
        mqttConnectOptions2.setCleanSession(false);
        mqttConnectOptions2.setAutomaticReconnect(true);

        try {

            // Connect client to server and set callback to mMQttActionListner
            /*For client 2 local*/
            if(mParkzapMQTT_Client2!=null) {
                mParkzapMQTT_Client2.connect(mqttConnectOptions2, null, mMQttActionListner2);
            }
            // Callback to get packets from server side
            /*For client 2 Local*/
            if(mParkzapMQTT_Client2!=null) {
                mParkzapMQTT_Client2.setCallback(ParkzapClientMqttCallback2);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }


    }

    /****  Client 2 Local  MQTT Callback **/
    private static MqttCallback ParkzapClientMqttCallback2 = new MqttCallback() {

        @Override
        public void connectionLost(Throwable cause) {
            checkSubscriptionOnTopic();
            if (mParkzapMQTT_Client2.isConnected()){
                checkSubscriptionOnTopic();
            }else {
//                printLog(mParkzapMQTT_Client2.isConnected());
            }

        }
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            mqttMessageConversion(message);
        }
        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    };

    /*
     *  Client 2 Local mqtt listener
     * */
    private static IMqttActionListener mMQttActionListner2= new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
//            printLog(asyncActionToken.toString());
            /*
             * Checking subscription of topic name
             * */
            checkSubscriptionOnTopic();


        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

        }
    };

    private static void checkSubscriptionOnTopic() {
        /*
        Topic List must be String type and Qos must be Integer type
        */

        String[] topicList = { gateId+DASHBOARD_TOPIC,gateId+Mqtt_Self_topic, "xyz"};
        int[] qosList={0,0,0};
        try {
            mParkzapMQTT_Client2.subscribe(topicList,qosList).setActionCallback(new IMqttActionListener() {
                /*
                 * Check Subscription Action Call back
                 *
                 * */
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private static void mqttMessageConversion(MqttMessage message) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(message.toString());
            Gson gson=new Gson();
            GenericReceivedMessageSample genericReceivedMessageSample=null;
            genericReceivedMessageSample=gson.fromJson(jsonObject.toString(),GenericReceivedMessageSample.class);

            if (genericReceivedMessageSample != null
                    && genericReceivedMessageSample.getMessageTypeCode() != null
                    && genericReceivedMessageSample.getMessageTypeCode().length() > 0
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("6021")
            ){

                if (genericReceivedMessageSample.getMessageBody() != null) {
                    Reading reading;
                    reading = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), Reading.class);
                    if (reading.getType() != null && reading.getEntry_time() != null){
                        MainController.readingMessage("Reading");
                        dashboardDatamessage = null;
                        notFoundmessage = null;
                        welComeMessageResponsemessage = null;
                    }
                }

            }

            if (genericReceivedMessageSample != null
                    && genericReceivedMessageSample.getMessageTypeCode() != null
                    && genericReceivedMessageSample.getMessageTypeCode().length() > 0
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("3006")){

                if (genericReceivedMessageSample.getMessageBody() != null) {
                    DashboardData dashboardData;
                    dashboardData = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), DashboardData.class);
                    if (dashboardData.getAgent() != null && dashboardData.getAmount() != null){
                        dashboardDatamessage = dashboardData;
//                        MainController.exitGate(dashboardData);
                    }
                }

            }




            if (genericReceivedMessageSample != null
                    && genericReceivedMessageSample.getMessageTypeCode() != null
                    && genericReceivedMessageSample.getMessageTypeCode().length() > 0
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("6022")
                    ){

                if (genericReceivedMessageSample.getMessageBody() != null) {
                    NotFound notFound;
                    notFound = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), NotFound.class);
                    if (notFound.getType() != null && notFound.getMessage() != null){
                        notFoundmessage = notFound;
                        MainController.ticketNotFound(notFound);
                        printLog(String.valueOf(notFoundmessage));
                    }
                }

            }
            printLog(String.valueOf(genericReceivedMessageSample.getMessageTypeCode()));
            if (genericReceivedMessageSample != null
                    && genericReceivedMessageSample.getMessageTypeCode() != null
                    && genericReceivedMessageSample.getMessageTypeCode().length() > 0
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("6013")
                    ){

                if (genericReceivedMessageSample.getMessageBody() != null) {
                    printLog(String.valueOf(genericReceivedMessageSample.getMessageTypeCode()));
                    ExitGateData exitGateData;
                    exitGateData = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), ExitGateData.class);
                    printLog(String.valueOf(exitGateData.getFooter()));
                    if (exitGateData.getType() != null ){
                        exitGateDatamessage = exitGateData;
                        MainController.cashPaymentWithErrorCode(exitGateData);
                        printLog(String.valueOf(exitGateDatamessage));
                    }
                    Footer footer = exitGateDatamessage.getFooter();
                    printLog(String.valueOf(footer.getMessage()));
                }

            }


        } catch (JsonParseException e) {
            printLog(Level.ERROR,"JsonParseException"+e);
            e.printStackTrace();
        }
        catch (JSONException e) {
            printLog(Level.ERROR,"JSONException"+e);
            e.printStackTrace();
        }catch (Exception e) {
            printLog(Level.ERROR,"Exception"+e);
            e.printStackTrace();
        }


    }



}
