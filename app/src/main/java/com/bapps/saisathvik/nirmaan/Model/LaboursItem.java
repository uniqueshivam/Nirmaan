package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/26/2018.
 */

public class LaboursItem {
    private String aadhar,labour_name,labour_address,labour_phone,labour_skill,labour_skillset,agency_id,gender,blood;

    public LaboursItem(String aadhar, String labour_name, String labour_address, String labour_phone, String labour_skill, String labour_skillset,String gender,String blood) {
        this.aadhar = aadhar;
        this.labour_name = labour_name;
        this.labour_address = labour_address;
        this.labour_phone = labour_phone;
        this.labour_skill = labour_skill;
        this.labour_skillset = labour_skillset;
        this.gender=gender;
        this.blood=blood;
      //  this.agency_id = agency_id;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getLabour_name() {
        return labour_name;
    }

    public void setLabour_name(String labour_name) {
        this.labour_name = labour_name;
    }

    public String getLabour_address() {
        return labour_address;
    }

    public void setLabour_address(String labour_address) {
        this.labour_address = labour_address;
    }

    public String getLabour_phone() {
        return labour_phone;
    }

    public void setLabour_phone(String labour_phone) {
        this.labour_phone = labour_phone;
    }

    public String getLabour_skill() {
        return labour_skill;
    }

    public void setLabour_skill(String labour_skill) {
        this.labour_skill = labour_skill;
    }

    public String getLabour_skillset() {
        return labour_skillset;
    }

    public void setLabour_skillset(String labour_skillset) {
        this.labour_skillset = labour_skillset;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }
    public String getgender()
    {
        return gender;
    }
    public String getBlood()
    {
        return  blood;
    }
}
