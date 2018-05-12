package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/31/2018.
 */

public class wagesmodel {
    private String avgwage_skilled_labour;
    private String avgwage_semiskilled_labours;

    public wagesmodel(String avgwage_skilled_labour, String avgwage_semiskilled_labours) {
        this.avgwage_skilled_labour = avgwage_skilled_labour;
        this.avgwage_semiskilled_labours = avgwage_semiskilled_labours;
    }

    public String getAvgwage_skilled_labour() {
        return avgwage_skilled_labour;
    }

    public String getAvgwage_semiskilled_labours() {
        return avgwage_semiskilled_labours;
    }
}
