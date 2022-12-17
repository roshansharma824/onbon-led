package com.stackfusion.onbon;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.stackfusion.onbon.data.*;

import org.apache.log4j.Level;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.Utils.printLog;

public class MQTTEntry {


    /*
     * For Local mqtt connection
     * */

    private static String mParkzapMQTT_ClientID2;
    private static MqttAsyncClient mParkzapMQTT_Client2;
    private static MqttConnectOptions mqttConnectOptions2;
    static String testTopic = "test"; //test topic for subscription

//    public static void main(String[] args) {
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
        } catch (MqttException e) {
            printLog(Level.ERROR,"local mqtt connection fails: "+e.getMessage());
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            printLog(Level.ERROR,"local mqtt connection fails: "+e.getMessage());
            //occurs when there is not jetson device in gatedetails and prefs gives "" to connect MQTT local client
            e.printStackTrace();
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
        public void messageArrived(String topic, MqttMessage message)  {
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

        String[] topicList = {Constants.gateId+Constants.LOCAL_FASTAG, Constants.gateId+Constants.DASHBOARD_TOPIC, Constants.gateId+Constants.Mqtt_Self_topic, Constants.gateId+Constants.JETSON_TOPIC,"xyz"};
        int[] qosList={0,0,0,0,0};
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
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("6010")
            ){

                if (genericReceivedMessageSample.getMessageBody() != null) {
                    WelComeMessageResponse welComeMessageResponse;
                    welComeMessageResponse = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), WelComeMessageResponse.class);
                    if (welComeMessageResponse.getVehicleNumber() != null && welComeMessageResponse.getMessage() != null){
                        welComeMessageResponsemessage = welComeMessageResponse;
                        MainController.entryGate(welComeMessageResponse);
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
                    && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("8013")) {


                if (genericReceivedMessageSample.getMessageBody() != null) {
                    List<TAGDetails> tagDetails = null;
                    tagDetails = new Gson().fromJson(genericReceivedMessageSample.getMessageBody(), new TypeToken<List<TAGDetails>>() {
                    }.getType());
                    if (tagDetails != null) {
                        for (int i = 0; i < tagDetails.size(); i++) {

                            if (Objects.equals(tagDetails.get(i).getL1_high(), "true")) {
                                if (tagDetails.get(i).getVehicleNumber() !=null) {
//                                        MainController.ticketCreated(tagDetails.get(i).getVehicleNumber());
                                }

                                printLog(tagDetails.get(i).getVehicleNumber());
                            }else {
//                                ticketCreated("false");
                            }
                        }

                    }
                }
            }







        } catch (JsonParseException e) {
            printLog(Level.ERROR,"JsonParseException "+e);
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
