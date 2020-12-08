package com.definesys.rt.dto;

public class UploadResponseDTO {
    private String dDocName;
    private String dId;

    public UploadResponseDTO() {
    }

    public UploadResponseDTO(String dDocName, String dId) {
        this.dDocName = dDocName;
        this.dId = dId;
    }

    public String getdDocName() {
        return dDocName;
    }

    public void setdDocName(String dDocName) {
        this.dDocName = dDocName;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }
}
