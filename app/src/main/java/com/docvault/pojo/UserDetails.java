package com.docvault.pojo;

import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDetails implements Serializable{
    private String userName;
    private String password;
    private List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;

    public UserDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
        prescriptionDetailsPojoList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return getUserName() + "_" + getPassword();
    }

    public List<PrescriptionDetailsPojo> getPrescriptionDetailsPojoList() {
        return prescriptionDetailsPojoList;
    }

    public void setPrescriptionDetailsPojoList(List<PrescriptionDetailsPojo> prescriptionDetailsPojoList) {
        this.prescriptionDetailsPojoList = prescriptionDetailsPojoList;
    }
}
