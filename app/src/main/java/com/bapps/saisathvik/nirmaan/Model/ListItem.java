package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/23/2018.
 */

public class ListItem {

    private String agency_name,agency_owner,agency_phone,agency_id,agency_email,agency_service_area;

    public ListItem(String agency_name, String agency_owner, String agency_phone,String agency_id,String agency_email,String agency_service_area) {
        this.agency_name = agency_name;
        this.agency_owner = agency_owner;
        this.agency_phone = agency_phone;
        this.agency_id=agency_id;
        this.agency_email=agency_email;
        this.agency_service_area=agency_service_area;
       // this.numberoflabours=numberoflabours;

    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAgency_owner() {
        return agency_owner;
    }

    public void setAgency_owner(String agency_owner) {
        this.agency_owner = agency_owner;
    }

    public String getAgency_phone() {
        return agency_phone;
    }

    public void setAgency_phone(String agency_phone) {
        this.agency_phone = agency_phone;
    }

    public String getAgency_id() {
        return agency_id;
    }
    public String getAgency_email()
    {
        return agency_email;
    }
    public String getAgency_service_area()
    {
        return agency_service_area;
    }

    public void setAgency_id(String agency_id) {

        this.agency_id = agency_id;
    }

}
