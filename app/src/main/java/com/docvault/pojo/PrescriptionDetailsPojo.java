package com.docvault.pojo;

public class PrescriptionDetailsPojo {
    private String doctorName;
    private String hospitalName;
    private String prescriptionDate;
    private String prescriptionImageFileName;

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

    public String getPrescriptionImageFileName() {
        return prescriptionImageFileName;
    }

    public void setPrescriptionImageFileName(String prescriptionImageFileName) {
        this.prescriptionImageFileName = prescriptionImageFileName;
    }

    public String getKey() {
        return getDoctorName() + "_" + getHospitalName() + "_" + getPrescriptionDate();
    }

    public String getData() {
        return getDoctorName() + "_" + getHospitalName() + "_" + getPrescriptionDate() + "_" + getPrescriptionImageFileName();
    }
}
