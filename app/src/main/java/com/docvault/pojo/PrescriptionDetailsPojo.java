package com.docvault.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDetailsPojo implements Serializable{
    private String doctorName;
    private String hospitalName;
    private String prescriptionDate;
    private List<String> prescriptionImageFiles;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getKey() {
        return getDoctorName() + "_" + getHospitalName() + "_" + getPrescriptionDate();
    }

    public PrescriptionDetailsPojo getData() {
        return this;
    }

    public List<String> getPrescriptionImageFiles() {
        if(prescriptionImageFiles == null) prescriptionImageFiles = new ArrayList<>();
        return prescriptionImageFiles;
    }

    public void setPrescriptionImageFiles(List<String> prescriptionImageFiles) {
        this.prescriptionImageFiles = prescriptionImageFiles;
    }
}
