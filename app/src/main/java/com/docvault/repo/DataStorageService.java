package com.docvault.repo;

import com.docvault.pojo.PrescriptionDetailsPojo;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataStorageService {


    public Set<String> retrieveDocument(PrescriptionDetailsPojo prescriptionDetailsPojo) {
        Set<String> allImagesByDocHospital = Prefs.getStringSet(prescriptionDetailsPojo.getKey(), Collections.<String>emptySet());
        return allImagesByDocHospital;
    }
}
