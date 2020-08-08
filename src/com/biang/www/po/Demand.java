package com.biang.www.po;

import java.util.ArrayList;

/**
 * @author dell
 */
public class Demand {
    public Demand(int demandId,
                  String title,
                  String introduction,
                  String specificContent,
                  String demandUnits,
                  String budget,
                  String timeRequirement,
                  ArrayList<String> annexes,
                  int enterpriseId,
                  int conditionOfCertification,
                  int conditionOfDemand) {
        this.demandId = demandId;
        this.title = title;
        this.introduction = introduction;
        this.specificContent = specificContent;
        this.demandUnits = demandUnits;
        this.budget = budget;
        this.timeRequirement = timeRequirement;
        this.annexes = annexes;
        this.enterpriseId = enterpriseId;
        this.conditionOfCertification = conditionOfCertification;
        this.conditionOfDemand=conditionOfDemand;
    }

    public Demand() { }

    private int demandId;
    private String title;
    private String introduction;
    private String specificContent;
    private String demandUnits;
    private String budget;
    private String timeRequirement;
    private ArrayList<String> annexes;
    private int enterpriseId;
    private int conditionOfCertification;


    private int conditionOfDemand;

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSpecificContent() {
        return specificContent;
    }

    public void setSpecificContent(String specificContent) {
        this.specificContent = specificContent;
    }

    public String getDemandUnits() {
        return demandUnits;
    }

    public void setDemandUnits(String demandUnits) {
        this.demandUnits = demandUnits;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getTimeRequirement() {
        return timeRequirement;
    }

    public void setTimeRequirement(String timeRequirement) {
        this.timeRequirement = timeRequirement;
    }

    public ArrayList<String> getAnnexes() {
        return annexes;
    }

    public void setAnnexes(ArrayList<String> annexes) {
        this.annexes = annexes;
    }

    public void addAnnex(String annex) {
        annexes.add(annex);
    }

    public void updateAnnex(int index,String annex) {
        annexes.set(index, annex);
    }

    public void removeAnnex(int index){
        annexes.remove(index);
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getConditionOfCertification() {
        return conditionOfCertification;
    }

    public void setConditionOfCertification(int conditionOfCertification) {
        this.conditionOfCertification = conditionOfCertification;
    }

    public int getConditionOfDemand() {
        return conditionOfDemand;
    }

    public void setConditionOfDemand(int conditionOfDemand) {
        this.conditionOfDemand = conditionOfDemand;
    }



}
