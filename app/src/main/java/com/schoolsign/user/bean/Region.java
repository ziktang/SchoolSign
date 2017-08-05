package com.schoolsign.user.bean;

/**
 * Created by tctctc on 2017/6/18.
 * Function:
 */

public class Region {
    private String provice;
    private String city;
    private String cityCode;
    private String area;
    private String areaCode;
    private String address;

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Region(String provice, String city, String cityCode, String area, String areaCode, String address) {
        this.provice = provice;
        this.city = city;
        this.cityCode = cityCode;
        this.area = area;
        this.areaCode = areaCode;
        this.address = address;
    }

    public Region() {
    }
}
