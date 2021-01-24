package com.example.x_smartcity_1.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/22  15:43
 */
public class GetAllStationById {

    /**
     *             "stationIndex": 1,
     *             "stationname": "巴沟",
     *             "distance": 1400
     */

    private String stationIndex,stationname,distance;

    public String getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(String stationIndex) {
        this.stationIndex = stationIndex;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
