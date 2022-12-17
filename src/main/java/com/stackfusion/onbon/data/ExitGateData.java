package com.stackfusion.onbon.data;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExitGateData implements Serializable {

    /*{
              "type": "fastag_payment",
             "amount": "500",
               "bank_code": "222222",
           "vehicle_number": "HR23525235",
               "footer": {"color": "", "error_code": "1", "message": "Please present your parking ticket" }
                 }
                 */
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("footer")
    @Expose
    private Footer footer;

    @SerializedName("qr_code")
    @Expose
    private String qrCode;
    @SerializedName("ticket_number")
    @Expose
    private String ticket_number;

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }

    @SerializedName("rfid_tag")
    @Expose
    private String rfid_tag;

    @SerializedName("bank_code")
    @Expose
    private String bank_code;

    @SerializedName("entry_time")
    @Expose
    private String entry_time;
    @SerializedName("exit_time")
    @Expose
    private String exit_time;
    @SerializedName("reverse_counter")
    @Expose
    private String reverse_counter;

    @SerializedName("l1_status")
    @Expose
    private String l1_status;
    @SerializedName("l2_status")
    @Expose
    private String l2_status;
    @SerializedName("fastag_data")
    @Expose
    private JsonElement fastag_data;

    public String getReverse_counter() {
        return reverse_counter;
    }

    public void setReverse_counter(String reverse_counter) {
        this.reverse_counter = reverse_counter;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Footer getFooter() {
        return footer;
    }
//
    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getRfid_tag() {
        return rfid_tag;
    }

    public void setRfid_tag(String rfid_tag) {
        this.rfid_tag = rfid_tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(String entry_time) {
        this.entry_time = entry_time;
    }

    public String getExit_time() {
        return exit_time;
    }

    public void setExit_time(String exit_time) {
        this.exit_time = exit_time;
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
