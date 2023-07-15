package com.app.oda_user;

public class organDonorsModel {

    private String donor_name;
    private String donatedOrgan;

    private organDonorsModel() {
    }

    private organDonorsModel(String donor_name, String donatedOrgan) {
        this.donor_name = donor_name;
        this.donatedOrgan = donatedOrgan;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getDonatedOrgan() {
        return donatedOrgan;
    }

    public void setDonatedOrgan(String donatedOrgan) {
        this.donatedOrgan = donatedOrgan;
    }
}
