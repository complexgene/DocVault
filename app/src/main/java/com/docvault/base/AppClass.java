package com.docvault.base;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.docvault.R;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class AppClass extends Application {
    private static AppClass instance;
    private List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;

    public static AppClass getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    private void init() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        // Set Custom Font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Titillium-Regular.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public List<PrescriptionDetailsPojo> getPrescriptionDetailsPojoList() {
        return prescriptionDetailsPojoList;
    }

    public void setPrescriptionDetailsPojoList(List<PrescriptionDetailsPojo> prescriptionDetailsPojoList) {
        this.prescriptionDetailsPojoList = prescriptionDetailsPojoList;
    }
}
