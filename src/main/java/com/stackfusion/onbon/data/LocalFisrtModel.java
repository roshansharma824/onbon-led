package com.stackfusion.onbon.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalFisrtModel {



@SerializedName("signal_name")
@Expose
private String signalName;
@SerializedName("signal_state")
@Expose
private String signalState;
@SerializedName("signal_id")
@Expose
private String signalId;
@SerializedName("time_stamp")
@Expose
private String timeStamp;

public String getSignalName() {
return signalName;
}

public void setSignalName(String signalName) {
this.signalName = signalName;
}

public String getSignalState() {
return signalState;
}

public void setSignalState(String signalState) {
this.signalState = signalState;
}

public String getSignalId() {
    return signalId;
}

public void setSignalId(String signalId) {
this.signalId = signalId;
}

public String getTimeStamp() {
return timeStamp;
}

public void setTimeStamp(String timeStamp) {
this.timeStamp = timeStamp;
}

}