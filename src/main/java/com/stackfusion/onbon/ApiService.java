package com.stackfusion.onbon;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.stackfusion.onbon.data.AdList;
import com.stackfusion.onbon.data.JetsonData;
import okhttp3.*;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.stackfusion.onbon.Constants.*;
import static com.stackfusion.onbon.MainController.init;
import static com.stackfusion.onbon.MainController.siteLogo;
import static com.stackfusion.onbon.Utils.printLog;

public class ApiService {
    static void getJetsonDataFromServer() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("mac_address", jetson_mac);
        printLog("Requesting... "+json.toString());

        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(GET_JETSON_DATA)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                printLog(Level.ERROR,e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        assert response.body() != null;
                        jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());

                        Gson gson = new Gson();
                        JetsonData jetsonData = gson.fromJson(jsonObject.toString(), JetsonData.class);

                        printLog(GET_JETSON_DATA +"\n  response ---->"+jetsonData.toString());
                        Constants.jetsonData = jetsonData;
                        //for testing comment Constants.url and Constants.jetson_ip
                        url = "http://"+jetsonData.getLed_tv_ip();
                        jetson_ip = jetsonData.getJetson_ip_address();
                        textSize = jetsonData.getFont_size();
                        display_brightness = jetsonData.getDisplay_brightness();
                        gateId = String.valueOf(Constants.jetsonData.getGate_id());
                        gateType = Constants.jetsonData.getGate_type();
                        payment_at_entry = Constants.jetsonData.getPayment_at_entry();
                        aoi_site_name = Constants.jetsonData.getAoi_site_name();
                        init();


                    } catch (JsonParseException e) {
                        printLog(Level.ERROR,"JsonParseException JetsonData" + e);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        printLog(Level.ERROR,"JSONException JetsonData" + e);
                        e.printStackTrace();
                    } catch (Exception e) {
                        printLog(Level.ERROR,"Exception" + e);
                        e.printStackTrace();
                    }
                }else {
                    printLog(Level.ERROR,response.message());
                }
            }
        });
    }

    static void getAdvertisementsList(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("gate_id",Integer.parseInt(gateId));
        printLog("Requesting... "+json.toString());

        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(ADVERTISEMENTS_LIST)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback(){

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    JSONArray jsonObject = null;
                    try {
                        assert response.body() != null;
                        jsonObject = new JSONArray(Objects.requireNonNull(response.body()).string());
                        ArrayList<AdList> listdata = new ArrayList<AdList>();
                        Gson gson = new Gson();
                        for(int i=0; i<jsonObject.length();i++){
                            AdList adList = gson.fromJson(String.valueOf(jsonObject.get(i)), AdList.class);
                            listdata.add(adList);
                        }


                        printLog(ADVERTISEMENTS_LIST +"\n  response ---->"+Arrays.toString(listdata.toArray()));
                        Constants.adsList = listdata;

                        siteLogo();

                    }catch (JsonParseException e) {
                        printLog(Level.ERROR,"JsonParseException AdList" + e);
                        e.printStackTrace();
                    } catch (JSONException e) {
                        printLog(Level.ERROR,"JSONException AdList" + e);
                        e.printStackTrace();
                    } catch (Exception e) {
                        printLog(Level.ERROR,"Exception" + e);
                        e.printStackTrace();
                    }
                }else {
                    printLog(Level.ERROR,response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                printLog(Level.ERROR,e.getMessage());
            }
        });
    }
}
