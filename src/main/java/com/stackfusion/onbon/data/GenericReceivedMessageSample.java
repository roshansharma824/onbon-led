package com.stackfusion.onbon.data;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class GenericReceivedMessageSample {
/*{ "sender_mac_add":"aa:bb:cc:dd:ee",
 "reciever_mac":"11:22:33:44:55:66",
 "message_type_code" :"6010",
 "message_body":{
       "type":"show_welcome_message",
       "message":"Welcome To",
       "vehicle_number":"xxxxxx",
        "number_plate_image":"xxxxxxxx", (yeh bitmap image rahegi)
        "timestamp":"101010",(yeh time long me rahega)
        "tower":"xxxx",
        "footer":null,
         "status":0 (0 -Active fastag and 1-blacklisted)
     }
}*/

    /*Empty Constructor*/
    public GenericReceivedMessageSample(){

    }

    public GenericReceivedMessageSample(String senderMacAdd, String recieverMac, String messageTypeCode, JsonElement messageBody, String messageTime) {
        this.senderMacAdd = senderMacAdd;
        this.recieverMac = recieverMac;
        this.messageTypeCode = messageTypeCode;
        this.messageBody = messageBody;
        this.messageTime = messageTime;
    }

    /* For System Check Mqtt Json*/

    public String GenericReceivedMessageSampleForSystemChecks(String senderMacAdd, String recieverMac, String messageTypeCode, String type) {
        JSONObject parentJsonObject = new JSONObject();
        JSONObject messageBodyJsonObject = new JSONObject();
        try {
            parentJsonObject.put("sender_mac_add",senderMacAdd);
            parentJsonObject.put("receiver_mac",recieverMac);
            parentJsonObject.put("message_type_code",messageTypeCode);

            /*Message body json object*/
            messageBodyJsonObject.put("type",type);

            parentJsonObject.put("message_body",messageBodyJsonObject);
            return parentJsonObject.toString();

        } catch (JSONException exception) {
            exception.printStackTrace();
//            MiscUtilities.crashLoggerPutCustomData("Error During Making System Check Json Making",(exception.getLocalizedMessage() !=null) ? exception.getLocalizedMessage() : "Error During Json Making");
//            MiscUtilities.sendErrorReportToCrashAnalytics((exception.getLocalizedMessage() !=null) ? exception.getLocalizedMessage() : "Error During Json Making of System Checks");
            return null;
        }

    }

    @SerializedName("sender_mac_add")
    @Expose
    private String senderMacAdd;
    @SerializedName("receiver_mac")
    @Expose
    private String recieverMac;
    @SerializedName("message_type_code")
    @Expose
    private String messageTypeCode;
    @SerializedName("message_body")
    @Expose
    private JsonElement messageBody;
    @SerializedName("message_time")
    @Expose
    private String messageTime;
    @SerializedName("filter_type")
    @Expose
    private String filterType;
    @SerializedName("filter_tag")
    @Expose
    private String filterTag;
    @SerializedName("event_timestamp")
    @Expose
    private String event_timestamp;
    private String topic;
    private String message;

    /*To Get Respective Message Description*/
    public static String getMessageDescription(String TypeCode)
    {
        if(TypeCode.equalsIgnoreCase("1"))
        {
            return "is_alive";
        }
        else if(TypeCode.equalsIgnoreCase("2"))
        {
            return "get_network_details";
        }
        else if(TypeCode.equalsIgnoreCase("3"))
        {
            return "reset_device";
        }
        else if(TypeCode.equalsIgnoreCase("4"))
        {
            return "get_local_mqtt_server";
        }
        else if(TypeCode.equalsIgnoreCase("6050"))
        {
            return "send_logcat_details";
        }
        else if(TypeCode.equalsIgnoreCase("6051"))
        {
            return "stop_logcat";
        }
        else if (TypeCode.equalsIgnoreCase("6003"))
        {
            return "get_teamviewer_start";
        }
        else if (TypeCode.equalsIgnoreCase("4001"))
        {
            return "screen_increase";
        }
        else if (TypeCode.equalsIgnoreCase("4000"))
        {
            return "screen_decrease";
        }
        else
        {
            return "INVALID";
        }
    }

    /*
     To Get Respective Reply Message Code
     */
    public static String getReplyMessageCode(String TypeCode)
    {
        if(TypeCode.equalsIgnoreCase("1"))
        {
            return "1001";
        }
        else if(TypeCode.equalsIgnoreCase("2"))
        {
            return "1002";
        }
        else if(TypeCode.equalsIgnoreCase("3"))
        {
            return "1003";
        }
        else if(TypeCode.equalsIgnoreCase("4"))
        {
            return "1004";
        }
        else if(TypeCode.equalsIgnoreCase("6022"))
        {
            return "1007";
        }
        else if(TypeCode.equalsIgnoreCase("6023"))
        {
            return "1008";
        }
        else if(TypeCode.equalsIgnoreCase("9"))
        {
            return "1009";
        }
        else if(TypeCode.equalsIgnoreCase("10"))
        {
            return "10010";
        }
        else if(TypeCode.equalsIgnoreCase("11"))
        {
            return "10011";
        }
        else
        {
            return "INVALID";
        }
    }



    public String getSenderMacAdd() {
        return senderMacAdd;
    }

    public void setSenderMacAdd(String senderMacAdd) {
        this.senderMacAdd = senderMacAdd;
    }

    public String getRecieverMac() {
        return recieverMac;
    }

    public void setRecieverMac(String recieverMac) {
        this.recieverMac = recieverMac;
    }

    public String getMessageTypeCode() {
        return messageTypeCode;
    }

    public void setMessageTypeCode(String messageTypeCode) {
        this.messageTypeCode = messageTypeCode;
    }

    public JsonElement getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(JsonElement messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterTag() {
        return filterTag;
    }

    public void setFilterTag(String filterTag) {
        this.filterTag = filterTag;
    }

    public String getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(String event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}