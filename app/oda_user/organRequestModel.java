package com.app.oda_user;

public class organRequestModel {

    private String recipient_name;
    private String requestedOrgan;

    private organRequestModel() {}

    private organRequestModel(String recipient_name, String requestedOrgan) {
        this.recipient_name = recipient_name;
        this.requestedOrgan = requestedOrgan;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getRequestedOrgan() {
        return requestedOrgan;
    }

    public void setRequestedOrgan(String requestedOrgan) {
        this.requestedOrgan = requestedOrgan;
    }
}
