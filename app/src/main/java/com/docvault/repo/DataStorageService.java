package com.docvault.repo;

import com.docvault.pojo.PrescriptionDetailsPojo;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataStorageService {
    public void storeDocument(PrescriptionDetailsPojo prescriptionDetailsPojo) {
        Set<String> getAllImages = retrieveDocument(prescriptionDetailsPojo);
        if(getAllImages.isEmpty()) {
            Set<String> imageDocDetailsSet = new HashSet<>();
            imageDocDetailsSet.add(prescriptionDetailsPojo.getData());
            Prefs.putOrderedStringSet(prescriptionDetailsPojo.getKey(), imageDocDetailsSet);
        }
        else {
            getAllImages.add(prescriptionDetailsPojo.getData());
            Prefs.putOrderedStringSet(prescriptionDetailsPojo.getKey(), getAllImages);
        }

    }

    public Set<String> retrieveDocument(PrescriptionDetailsPojo prescriptionDetailsPojo) {
        Set<String> allImagesByDocHospital = Prefs.getStringSet(prescriptionDetailsPojo.getKey(), Collections.<String>emptySet());
        return allImagesByDocHospital;
    }
}
