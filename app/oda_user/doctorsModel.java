package com.app.oda_user;

public class doctorsModel {

    private String doctor_name;
    private String doctor_province;

    private doctorsModel() {}

    doctorsModel(String doctor_name, String doctor_province) {
        this.doctor_name = doctor_name;
        this.doctor_province = doctor_province;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_province() {
        return doctor_province;
    }

    public void setDoctor_province(String doctor_province) {
        this.doctor_province = doctor_province;
    }
}
