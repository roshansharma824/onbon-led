package com.stackfusion.onbon.data;

public class NotFound{
    private String message;
	private String type;
    private String vehicle_number;
    
    public String getMessage() {
    	return this.message;
    }
    
    public String getType() {
    	return this.type;
    }

	public String getVehicle_number() {
		return vehicle_number;
	}

	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}

	public void setType(String type) {
		this.type = type;
	}
}