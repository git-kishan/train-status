package com.shubham.srikanth.kishan;

public class DataModel {
    private String stationName;
    private String stationCode;


    public DataModel(){

    }
    public DataModel(String stationName,String stationCode){
        this.stationName=stationName;
        this.stationCode=stationCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
