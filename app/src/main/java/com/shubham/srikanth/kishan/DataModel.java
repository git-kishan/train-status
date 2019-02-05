package com.shubham.srikanth.kishan;

public class DataModel {
    private String stationName;
    private String stationCode;
    private String fromStationName;
    private String fromStationCode;
    private String toStationName;
    private String toStationCode;


    public DataModel(){

    }
    public DataModel(String stationName,String stationCode){
        this.stationName=stationName;
        this.stationCode=stationCode;
    }
    public DataModel(String fromStationCode,String fromStationName,String toStationCode ,String toStationName){
        this.fromStationCode=fromStationCode;
        this.fromStationName=fromStationName;
        this.toStationName=toStationName;
        this.toStationCode=toStationCode;
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

    public String getFromStationCode() {
        return fromStationCode;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public String getToStationCode() {
        return toStationCode;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setFromStationCode(String fromStationCode) {
        this.fromStationCode = fromStationCode;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public void setToStationCode(String toStationCode) {
        this.toStationCode = toStationCode;
    }

    public void setToStationName(String toStationName) {

    }
}
