package com.biang.www.po;

public class Enterprise {
    private int enterpriseId;

    public Enterprise(int enterpriseId, String enterpriseName, String information, String contactPerson, int userId, int conditionsOfCertification) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.information = information;
        this.contactPerson = contactPerson;
        this.userId = userId;
        this.conditionsOfCertification = conditionsOfCertification;
    }

    public Enterprise() { }

    private String enterpriseName;
    private String information;
    private String contactPerson;
    private int userId;
    private int conditionsOfCertification;

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConditionsOfCertification() {
        return conditionsOfCertification;
    }

    public void setConditionsOfCertification(int conditionsOfCertification) {
        this.conditionsOfCertification = conditionsOfCertification;
    }
}
