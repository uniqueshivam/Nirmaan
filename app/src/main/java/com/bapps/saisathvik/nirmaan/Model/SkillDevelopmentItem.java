package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/28/2018.
 */

public class SkillDevelopmentItem {

    public String name;
    public String imageLink;

    public SkillDevelopmentItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public SkillDevelopmentItem(String name, String imageLink) {

        this.name = name;
        this.imageLink = imageLink;
    }
}
