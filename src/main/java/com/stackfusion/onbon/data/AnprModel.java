package com.stackfusion.onbon.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnprModel {

@SerializedName("image_urls")
@Expose
private String imageUrls;
@SerializedName("clean_all_images")
@Expose
private List<String> cleanAllImages = null;
@SerializedName("vehicle_category")
@Expose
private String vehicleCategory;
@SerializedName("direction")
@Expose
private String direction;
@SerializedName("top")
@Expose
private int top;
@SerializedName("left")
@Expose
private int left;
@SerializedName("right")
@Expose
private int right;
@SerializedName("bottom")
@Expose
private int bottom;
@SerializedName("data_corrected")
@Expose
private String dataCorrected;
@SerializedName("confidence")
@Expose
private String confidence;
@SerializedName("new_vehicle_flag")
@Expose
private Boolean new_vehicle_flag;
@SerializedName("frequency")
@Expose
private int frequency;
@SerializedName("vehicle_id")
@Expose
private String vehicle_id;

public String getVehicle_id() {
        return vehicle_id;
    }

public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

public String getImageUrls() {
return imageUrls;
}

public void setImageUrls(String imageUrls) {
this.imageUrls = imageUrls;
}

public List<String> getCleanAllImages() {
return cleanAllImages;
}

public void setCleanAllImages(List<String> cleanAllImages) {
this.cleanAllImages = cleanAllImages;
}

public String getVehicleCategory() {
return vehicleCategory;
}

public void setVehicleCategory(String vehicleCategory) {
this.vehicleCategory = vehicleCategory;
}

public String getDirection() {
return direction;
}

public void setDirection(String direction) {
this.direction = direction;
}

public int getTop() {
return top;
}

public void setTop(int top) {
this.top = top;
}

public int getLeft() {
return left;
}

public void setLeft(int left) {
this.left = left;
}

public int getRight() {
return right;
}

public void setRight(int right) {
this.right = right;
}

public int getBottom() {
return bottom;
}

public void setBottom(int bottom) {
this.bottom = bottom;
}

public String getDataCorrected() {
return dataCorrected;
}

public void setDataCorrected(String dataCorrected) {
this.dataCorrected = dataCorrected;
}

public String getConfidence() {
return confidence;
}

public void setConfidence(String confidence) {
this.confidence = confidence;
}

public int getFrequency() {
return frequency;
}

public void setFrequency(int frequency) {
this.frequency = frequency;
}

public Boolean getNew_vehicle_flag() {
        return new_vehicle_flag;
}

public void setNew_vehicle_flag(Boolean new_vehicle_flag) {
        this.new_vehicle_flag = new_vehicle_flag;
}
}