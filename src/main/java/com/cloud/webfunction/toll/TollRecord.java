package com.cloud.webfunction.toll;


public class TollRecord {

    private String stationId;
    private String licensePlate;
    private String timeStamp;
    

    public TollRecord(String stationId, String licencePlate, String timeStamp) {
        this.stationId = stationId;
        this.licensePlate = licencePlate;
        this.timeStamp = timeStamp;
    }

    public String getStationId() {
        return stationId;
    }
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licencePlate) {
        this.licensePlate = licencePlate;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "TollRecord [licensePlate=" + licensePlate + ", stationId=" + stationId + ", timeStamp=" + timeStamp
                + "]";
    }    
    
}
