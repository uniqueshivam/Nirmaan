package com.bapps.saisathvik.nirmaan.Model;

/**
 * Created by Sai Sathvik on 3/28/2018.
 */

public class SkillDescriptionItem {

    public String categoryId;
    public String imageLink;
    public String momentDesc;
    public String momentSum;
    public String momentTitle;

    public SkillDescriptionItem(){

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getMomentDesc() {
        return momentDesc;
    }

    public void setMomentDesc(String momentDesc) {
        this.momentDesc = momentDesc;
    }

    public String getMomentSum() {
        return momentSum;
    }

    public void setMomentSum(String momentSum) {
        this.momentSum = momentSum;
    }

    public String getMomentTitle() {
        return momentTitle;
    }

    public void setMomentTitle(String momentTitle) {
        this.momentTitle = momentTitle;
    }

    public SkillDescriptionItem(String categoryId, String imageLink, String momentDesc, String momentSum, String momentTitle) {

        this.categoryId = categoryId;
        this.imageLink = imageLink;
        this.momentDesc = momentDesc;
        this.momentSum = momentSum;
        this.momentTitle = momentTitle;
    }
}
