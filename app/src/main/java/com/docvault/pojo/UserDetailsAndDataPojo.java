package com.docvault.pojo;

import java.util.Map;

public class UserDetailsAndDataPojo {
    private String userName;
    private String passWord;
    private Map<String, PrescriptionDetailsPojo> userDocsSet;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Map<String, PrescriptionDetailsPojo> getUserDocsSet() {
        return userDocsSet;
    }

    public void setUserDocsSet(Map<String, PrescriptionDetailsPojo> userDocsSet) {
        this.userDocsSet = userDocsSet;
    }
}
