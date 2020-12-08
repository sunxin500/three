package com.definesys.rt.Test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDTO {
    @JsonProperty(value = "E_TELEPHONE")
    private String phone;
    @JsonProperty(value = "MESSAGE")
    private String message;
    @JsonProperty(value = "STATUS")
    private String  status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
