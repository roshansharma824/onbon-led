package com.stackfusion.onbon.data;

import java.io.Serializable;

public class DashboardData implements Serializable {




        private String entry_time;

        private String entry_gate_operator;

        private String agent;

        private String transaction_status;

        private String amount;

        private String ticket_num;

        private String exit_epochTime;

        private String vehicle_plate_num;

        private Integer entry_gate;

        private String vehicle_num;

        private String entry_epochTime;

        private String exit_time;

        private String duration;

        private String mode;

        private String total_amount;

        private String vehicleType;

        private String ticket_opening_method;

        private String status;

        public String getEntry_time() {
            return this.entry_time;
        }

        public void setEntry_time(String entry_time) {
            this.entry_time = entry_time;
        }

        public String getEntry_gate_operator() {
            return this.entry_gate_operator;
        }

        public void setEntry_gate_operator(String entry_gate_operator) {
            this.entry_gate_operator = entry_gate_operator;
        }

        public String getAgent() {
            return this.agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getTransaction_status() {
            return this.transaction_status;
        }

        public void setTransaction_status(String transaction_status) {
            this.transaction_status = transaction_status;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTicket_num() {
            return this.ticket_num;
        }

        public void setTicket_num(String ticket_num) {
            this.ticket_num = ticket_num;
        }

        public String getExit_epochTime() {
            return this.exit_epochTime;
        }

        public void setExit_epochTime(String exit_epochTime) {
            this.exit_epochTime = exit_epochTime;
        }

        public String getVehicle_plate_num() {
            return this.vehicle_plate_num;
        }

        public void setVehicle_plate_num(String vehicle_plate_num) {
            this.vehicle_plate_num = vehicle_plate_num;
        }

        public Integer getEntry_gate() {
            return this.entry_gate;
        }

        public void setEntry_gate(Integer entry_gate) {
            this.entry_gate = entry_gate;
        }

        public String getVehicle_num() {
            return this.vehicle_num;
        }

        public void setVehicle_num(String vehicle_num) {
            this.vehicle_num = vehicle_num;
        }

        public String getEntry_epochTime() {
            return this.entry_epochTime;
        }

        public void setEntry_epochTime(String entry_epochTime) {
            this.entry_epochTime = entry_epochTime;
        }

        public String getExit_time() {
            return this.exit_time;
        }

        public void setExit_time(String exit_time) {
            this.exit_time = exit_time;
        }

        public String getDuration() {
            return this.duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getMode() {
            return this.mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public int getTotal_amount() {
            Float f = Float.valueOf(this.total_amount);
            int i = f.intValue();
            return i;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getVehicleType() {
            return this.vehicleType;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public String getTicket_opening_method() {
            return this.ticket_opening_method;
        }

        public void setTicket_opening_method(String ticket_opening_method) {
            this.ticket_opening_method = ticket_opening_method;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


}
