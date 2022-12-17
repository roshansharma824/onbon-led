package com.stackfusion.onbon.data;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WelComeMessageResponse implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
//    @SerializedName("number_plate_image")
//    @Expose
//    private Bitmap numberPlateImage;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("tower")
    @Expose
    private String tower;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bank_code")
    @Expose
    private String bank_code;
//    @SerializedName("footer")
//    @Expose
//    private Footer footer;
    @SerializedName("l1_status")
    @Expose
    private String l1_status;
    @SerializedName("l2_status")
    @Expose
    private String l2_status;
    @SerializedName("fastag_data")
    @Expose
    private JsonElement fastag_data;

    public WelComeMessageResponse(String type, String message, String vehicleNumber/*, Bitmap numberPlateImage*/, String timestamp, String tower/*, Footer footer*/) {
        this.type = type;
        this.message = message;
        this.vehicleNumber = vehicleNumber;
//        this.numberPlateImage = numberPlateImage;
        this.timestamp = timestamp;
        this.tower = tower;
//        this.footer = footer;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

//    public Bitmap getNumberPlateImage() {
//        return numberPlateImage;
//    }
//
//    public void setNumberPlateImage(Bitmap numberPlateImage) {
//        this.numberPlateImage = numberPlateImage;
//    }
//
//    public Footer getFooter() {
//        return footer;
//    }
//
//    public void setFooter(Footer footer) {
//        this.footer = footer;
//    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getL1_status() {
        return l1_status;
    }

    public void setL1_status(String l1_status) {
        this.l1_status = l1_status;
    }

    public String getL2_status() {
        return l2_status;
    }

    public void setL2_status(String l2_status) {
        this.l2_status = l2_status;
    }

    public JsonElement getFastag_data() {
        return fastag_data;
    }

    public void setFastag_data(JsonElement fastag_data) {
        this.fastag_data = fastag_data;
    }
}