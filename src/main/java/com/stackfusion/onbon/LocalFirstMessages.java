package com.stackfusion.onbon;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.stackfusion.onbon.data.GenericReceivedMessageSample;
import com.stackfusion.onbon.data.LocalFisrtModel;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.Utils.printLog;

public class LocalFirstMessages {

    private static String mParkzapMQTT_ClientID2;
    private static MqttAsyncClient mParkzapMQTT_Client2;
    public static MqttConnectOptions mqttConnectOptions2;


    public static void start() throws Exception {
        MQTTClient();
        Controller.start();
        Controller.dayTimerStart();
    }


    private static void MQTTClient(){
        // Local client
        mParkzapMQTT_ClientID2 = MqttAsyncClient.generateClientId()+String.valueOf(System.currentTimeMillis());

        /*For client 2 Local*/
        try {
            mParkzapMQTT_Client2 = new MqttAsyncClient(
                    Constants.getmLocalURI(Constants.jetson_ip),
                    mParkzapMQTT_ClientID2,new MemoryPersistence()
            );
        } catch (MqttException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e){
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
            printLog("Check MQTT is connected or Not "+mParkzapMQTT_Client2.isConnected());
            if (mParkzapMQTT_Client2.isConnected()){
                checkSubscriptionOnTopic();
            }else {
                printLog("Check MQTT is connected or Not "+mParkzapMQTT_Client2.isConnected());
            }

        }
        @Override
        public void messageArrived(String topic, MqttMessage message){

            JSONObject jsonObject= null;

            try {
                jsonObject = new JSONObject(message.toString());
                Gson gson=new Gson();
                GenericReceivedMessageSample genericReceivedMessageSample=null;
                genericReceivedMessageSample=gson.fromJson(jsonObject.toString(),GenericReceivedMessageSample.class);
                if (genericReceivedMessageSample != null
                        && genericReceivedMessageSample.getMessageTypeCode() != null
                        && genericReceivedMessageSample.getMessageTypeCode().length() > 0
                        && genericReceivedMessageSample.getMessageTypeCode().equalsIgnoreCase("8027"))
                {
                    if (genericReceivedMessageSample.getMessageBody() != null) {

                        LocalFisrtModel localFisrtModel;
                        localFisrtModel = gson.fromJson(genericReceivedMessageSample.getMessageBody(), LocalFisrtModel.class);

                        if (localFisrtModelmessage != null) {
                            if (Objects.equals(localFisrtModelmessage.getSignalState(), HIGH) && Objects.equals(localFisrtModel.getSignalState(), LOW)) {
                                Loop_Fluctuating_Count++;
                                dashboardDatamessage = null;
                                notFoundmessage = null;
                                welComeMessageResponsemessage = null;
                                Controller.timer.cancel();
                                Controller.timer.purge();
                                MainController.siteLogo();

                            }else if(Objects.equals(localFisrtModelmessage.getSignalState(), LOW) && Objects.equals(localFisrtModel.getSignalState(), HIGH)) {
                                Loop_Fluctuating_Count++;
                                Controller.timer.cancel();
                                Controller.timer.purge();
                                Controller.start();
                            }
                        }
                        localFisrtModelmessage = localFisrtModel;
                        printLog("Loop message TOPIC "+gateId+LOCAL_FIRST +" with Time "+localFisrtModel.getTimeStamp()+" "+localFisrtModel.getSignalName()+" "+localFisrtModel.getSignalState());


                    }

                }

            } catch (JsonParseException e) {
                printLog("JsonParseException LocalFisrtModel"+e);
                e.printStackTrace();
            }
            catch (JSONException e) {
                printLog("JSONException LocalFisrtModel"+e);
                e.printStackTrace();
            }catch (Exception e) {
                printLog("Exception"+e);
                e.printStackTrace();
            }
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


        String[] topicList = {Constants.gateId+Constants.LOCAL_FIRST};
        int[] qosList={0};
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



}
