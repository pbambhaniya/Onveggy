package com.multipz.onveggy.Model;

/**
 * Created by Admin on 23-08-2017.
 */

public class AddressModel {

    String user_address_id,user_id,receivername,blockno,street,pincode,city,state,address_type,locality,add_latitude,add_longititude;
    int is_default;

    public String getUser_address_id() {
        return user_address_id;
    }

    public void setUser_address_id(String user_address_id) {
        this.user_address_id = user_address_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getBlockno() {
        return blockno;
    }

    public void setBlockno(String blockno) {
        this.blockno = blockno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdd_latitude() {
        return add_latitude;
    }

    public void setAdd_latitude(String add_latitude) {
        this.add_latitude = add_latitude;
    }

    public String getAdd_longititude() {
        return add_longititude;
    }

    public void setAdd_longititude(String add_longititude) {
        this.add_longititude = add_longititude;
    }

    public boolean is_default() {
        return (is_default == 1) ? true:false;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}
