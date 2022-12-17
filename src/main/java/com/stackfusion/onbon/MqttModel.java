package com.stackfusion.onbon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttModel {

@SerializedName("topic_name")
@Expose
private List<String> topicName ;
@SerializedName("topic_type")
@Expose
private List<String> topicType;
@SerializedName("QoS")
@Expose
private List<Integer> qoS;

public String getTopicTypeFromTopicName(String topicName) {
    /*
     * checking the type of topic corresponding to topic name
     * */
    if (getTopicName().contains(topicName))
    {
    int index = getTopicName().indexOf(topicName);
        return this.getTopicType().get(index);
    }
    else
    {
        return "Unknown";
    }

}
public List<String> getTopicName() {
return topicName;
}

public void setTopicName(List<String> topicName) {
this.topicName = topicName;
}

public List<String> getTopicType() {
return topicType;
}

public void setTopicType(List<String> topicType) {
this.topicType = topicType;
}

public List<Integer> getQoS() {
return qoS;
}

public void setQoS(List<Integer> qoS) {
this.qoS = qoS;
}


public enum QOS{

    Qos_Zero(0),
    Qos_One(1),
    Qos_Two(2);

    private  int value;

    QOS(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

}
