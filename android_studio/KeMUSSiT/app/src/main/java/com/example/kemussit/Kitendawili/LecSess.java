package com.example.kemussit.Kitendawili;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class LecSess {
    private static final String mStu = "stud_id";
    private static final String mFname = "fname";
    private static final String mLname = "lname";
    private static final String mGender = "gender";
    private static final String mPhone = "phone";
    private static final String mEmail = "email";
    private static final String mDepa = "department";
    private static final String mClass = "classification";
    private static final String mrole = "role";
    private static final String mDate = "date";
    private static final String xx = "";
    private static final String tight = "";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LecSess(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("aibu", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void login(String stud_id, String fname, String lname, String gender, String phone, String email, String department, String classification,
                      String role, String reg_date) {
        editor.putString(mStu, stud_id);
        editor.putString(mFname, fname);
        editor.putString(mLname, lname);
        editor.putString(mGender, gender);
        editor.putString(mPhone, phone);
        editor.putString(mEmail, email);
        editor.putString(mDepa, department);
        editor.putString(mClass, classification);
        editor.putString(mrole, role);
        editor.putString(mDate, reg_date);
        Date dt = new Date();
        long duration = dt.getTime() + (7 * 24 * 60 * 60 * 1000);
        editor.putLong(xx, duration);
        editor.commit();
    }

    public boolean userLog() {
        Date current = new Date();
        long remainder = sharedPreferences.getLong(xx, 0);
        if (remainder == 0) {
            return false;
        }
        Date end = new Date(remainder);
        return current.before(end);
    }

    public Model getUser() {
        if (!userLog()) {
            return null;
        }
        Model cModel = new Model();
        cModel.setStud_id(sharedPreferences.getString(mStu, tight));
        cModel.setFname(sharedPreferences.getString(mFname, tight));
        cModel.setLname(sharedPreferences.getString(mLname, tight));
        cModel.setGender(sharedPreferences.getString(mGender, tight));
        cModel.setPhone(sharedPreferences.getString(mPhone, tight));
        cModel.setEmail(sharedPreferences.getString(mEmail, tight));
        cModel.setDepartment(sharedPreferences.getString(mDepa, tight));
        cModel.setClassification(sharedPreferences.getString(mClass, tight));
        cModel.setRole(sharedPreferences.getString(mrole, tight));
        cModel.setDate(sharedPreferences.getString(mDate, tight));
        return cModel;
    }

    public void outUser() {
        editor.clear();
        editor.commit();
    }
}
