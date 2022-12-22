package com.example.kemussit.Lec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.LecSess;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class LecLog extends AppCompatActivity {
    Model model;
    LecSess stuSess;
    CircleImageView circleImageView;
    TextView forgot, create, errorchecker, ohioStates, templeOne, templeTwo, templeThree;
    EditText user, pass, reset, fname, lname, phone, email, regno;
    Button bt_log, button;
    private Spinner department;
    View layer;
    AlertDialog.Builder alert;
    Rect reco;
    Window window;
    LayoutInflater layoutInflater;
    AlertDialog alertDialog;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ImageView See, See4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stuSess = new LecSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Lecturer Login");
        setContentView(R.layout.activity_lec_log);
        circleImageView = findViewById(R.id.circle_center);
        circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anti));
        forgot = findViewById(R.id.txtForgot);
        create = findViewById(R.id.txtAccount);
        user = findViewById(R.id.myUser);
        pass = findViewById(R.id.myPass);
        errorchecker = findViewById(R.id.metaReg);
        bt_log = findViewById(R.id.btnLog);
        See = findViewById(R.id.see);
        See.setOnClickListener(view -> {
            if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                See.setImageResource(R.drawable.hide);
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                See.setImageResource(R.drawable.see);
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String mess = user.getText().toString().trim();
                if (mess.isEmpty()) {
                    errorchecker.setVisibility(View.GONE);
                } else if (!mess.matches(getString(R.string.Serial))) {
                    errorchecker.setText("Your serial is wrong!");
                    errorchecker.setAnimation(AnimationUtils.loadAnimation(LecLog.this, R.anim.blink));
                    errorchecker.setVisibility(View.VISIBLE);
                } else {
                    errorchecker.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        bt_log.setOnClickListener(view -> {
            final String muser = user.getText().toString().trim();
            final String mpass = pass.getText().toString().trim();
            if (muser.isEmpty()) {
                user.setError("required");
                user.requestFocus();
            } else if (!muser.matches(getString(R.string.Serial))) {
                user.setError("invalid serial number");
                user.requestFocus();
            } else if (mpass.isEmpty()) {
                pass.setError("required");
                pass.requestFocus();
            } else {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.log,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                if (st == 1) {
                                    jsonArray = jsonObject.getJSONArray("lion");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        String stud_id = jsonObject.getString("stud_id");
                                        String Fname = jsonObject.getString("fname");
                                        String Lname = jsonObject.getString("lname");
                                        String Gender = jsonObject.getString("gender");
                                        String Phone = jsonObject.getString("phone");
                                        String Email = jsonObject.getString("email");
                                        String Department = jsonObject.getString("department");
                                        String Classification = jsonObject.getString("classification");
                                        String Role = jsonObject.getString("role");
                                        String DatE = jsonObject.getString("date");
                                        stuSess.login(stud_id, Fname, Lname, Gender, Phone, Email, Department, Classification, Role, DatE);
                                        startActivity(new Intent(getApplicationContext(), LecHome.class));
                                        finish();
                                    }
                                } else if (st == 2) {
                                    jsonArray = jsonObject.getJSONArray("lion");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        String remarks = jsonObject.getString("remarks").trim();
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(), remarks, Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an internal server error", Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                    Toast.makeText(this, "check your connection", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> par = new HashMap<>();
                        par.put("reg_no", muser);
                        par.put("password", mpass);
                        par.put("theme", "lec");
                        return par;
                    }
                });
            }
        });
        forgot.setOnClickListener(view -> {
            alert = new AlertDialog.Builder(this);
            reco = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(reco);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.forgo, null);
            layer.setMinimumWidth((int) (reco.width() * 0.9f));
            layer.setMinimumHeight((int) (reco.height() * 0.05f));
            user = layer.findViewById(R.id.myUser);
            pass = layer.findViewById(R.id.myPass);
            reset = layer.findViewById(R.id.begger);
            ohioStates = layer.findViewById(R.id.Ohio);
            user.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mess = user.getText().toString().trim();
                    if (mess.isEmpty()) {
                        ohioStates.setVisibility(View.GONE);
                    } else if (!mess.matches(getString(R.string.Serial))) {
                        ohioStates.setText("Your serial is wrong!");
                        ohioStates.setAnimation(AnimationUtils.loadAnimation(LecLog.this, R.anim.blink));
                        ohioStates.setVisibility(View.VISIBLE);
                    } else {
                        ohioStates.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            See = layer.findViewById(R.id.see);
            See.setOnClickListener(view4 -> {
                if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    See.setImageResource(R.drawable.hide);
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    See.setImageResource(R.drawable.see);
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });
            See4 = layer.findViewById(R.id.see4);
            See4.setOnClickListener(view4 -> {
                if (reset.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    See4.setImageResource(R.drawable.hide);
                    reset.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    See4.setImageResource(R.drawable.see);
                    reset.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });
            circleImageView = layer.findViewById(R.id.circle_center);
            circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            alert.setTitle("Reset Password");
            alert.setView(layer);
            alert.setPositiveButton("Reset", (dialogq, idq) -> {
            });
            alert.setNegativeButton("exit", (dialogq, idq) -> dialogq.cancel());
            alertDialog = alert.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                String mUser = user.getText().toString().trim();
                String mPass = pass.getText().toString().trim();
                String mReset = reset.getText().toString().trim();
                if (mUser.isEmpty()) {
                    user.setError("required");
                    user.requestFocus();
                } else if (!mUser.matches(getString(R.string.Serial))) {
                    user.setError("Your serial is wrong");
                    user.requestFocus();
                } else if (mPass.isEmpty()) {
                    pass.setError("required");
                    pass.requestFocus();
                } else if (mPass.length() < 8) {
                    pass.setError("weak password");
                    pass.requestFocus();
                } else if (mReset.isEmpty()) {
                    reset.setError("repeat password");
                    reset.requestFocus();
                } else if (!mPass.equals(mReset)) {
                    Toast.makeText(LecLog.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.forr,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("status");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(LecLog.this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), LecLog.class));
                                        finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(LecLog.this, "internal server error", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(LecLog.this, "Connection error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("reg_no", mUser);
                            para.put("password", mPass);
                            para.put("theme", "lec");
                            return para;
                        }
                    });
                }
            });
        });
        create.setOnClickListener(view -> {
            alert = new AlertDialog.Builder(this);
            reco = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(reco);
            layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layer = layoutInflater.inflate(R.layout.rege, null);
            layer.setMinimumWidth((int) (reco.width() * 0.9f));
            layer.setMinimumHeight((int) (reco.height() * 0.05f));
            regno = layer.findViewById(R.id.myUser);
            regno.setVisibility(View.GONE);
            fname = layer.findViewById(R.id.fname);
            lname = layer.findViewById(R.id.lname);
            phone = layer.findViewById(R.id.phone);
            email = layer.findViewById(R.id.email);
            reset = layer.findViewById(R.id.myConfirm);
            pass = layer.findViewById(R.id.myPass);
            department = layer.findViewById(R.id.department);
            circleImageView = layer.findViewById(R.id.circle_center);
            See = layer.findViewById(R.id.see);
            templeOne = layer.findViewById(R.id.templeReg);
            templeTwo = layer.findViewById(R.id.templePhone);
            templeThree = layer.findViewById(R.id.templeEmail);
            /*regno.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mess = regno.getText().toString().trim();
                    if (mess.isEmpty()) {
                        templeOne.setVisibility(View.GONE);
                    } else if (mess.length() < 8) {
                        templeOne.setText("Your serial is wrong!");
                        templeOne.setAnimation(AnimationUtils.loadAnimation(LecLog.this, R.anim.blink));
                        templeOne.setVisibility(View.VISIBLE);
                    } else {
                        templeOne.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });*/
            See.setOnClickListener(view4 -> {
                if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    See.setImageResource(R.drawable.hide);
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    See.setImageResource(R.drawable.see);
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });
            See4 = layer.findViewById(R.id.see4);
            See4.setOnClickListener(view4 -> {
                if (reset.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    See4.setImageResource(R.drawable.hide);
                    reset.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    See4.setImageResource(R.drawable.see);
                    reset.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            });
            circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Department, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            department.setAdapter(adapter);
            circleImageView = layer.findViewById(R.id.circle_center);
            alert.setTitle("Create Account");
            alert.setView(layer);
            alert.setPositiveButton("Register", (dialogq, idq) -> {
            });
            alert.setNegativeButton("exit", (dialogq, idq) -> dialogq.cancel());
            alertDialog = alert.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                //String mUser = regno.getText().toString().trim();
                String mFna = fname.getText().toString().trim();
                String mLna = lname.getText().toString().trim();
                String mPass = pass.getText().toString().trim();
                String mPhone = phone.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mDepa = department.getSelectedItem().toString().trim();
                String mReset = reset.getText().toString().trim();
                /*if (mUser.isEmpty()) {
                    regno.setError("required");
                    regno.requestFocus();
                } else if (mUser.length() < 8) {
                    regno.setError("Your serial number is inappropriate");
                    regno.requestFocus();
                } else */
                if (mFna.isEmpty()) {
                    fname.setError("required");
                    fname.requestFocus();
                } else if (mLna.isEmpty()) {
                    lname.setError("required");
                    lname.requestFocus();
                } else if (mPhone.isEmpty()) {
                    phone.setError("required");
                    phone.requestFocus();
                } else if (mPhone.length() < 10) {
                    phone.setError("invalid mobile number");
                    phone.requestFocus();
                } else if (!mPhone.matches(getString(R.string.phone7)) & !mPhone.matches(getString(R.string.phone1))) {
                    phone.setError("Only 07...\nor 01...");
                    phone.requestFocus();
                } else if (mEmail.isEmpty()) {
                    email.setError("required");
                    email.requestFocus();
                } else if (!mEmail.matches(getString(R.string.valid_email)) & !mEmail.matches(getString(R.string.valid_email2))) {
                    email.setError("invalid email address");
                    email.requestFocus();
                } else if (mDepa.equals("Select Department")) {
                    Toast.makeText(LecLog.this, "Select Department", Toast.LENGTH_SHORT).show();
                } else if (mPass.isEmpty()) {
                    pass.setError("required");
                    pass.requestFocus();
                } else if (mPass.length() < 8) {
                    pass.setError("weak");
                    pass.requestFocus();
                } else if (mReset.isEmpty()) {
                    reset.setError("required");
                    reset.requestFocus();
                } else if (!mPass.equals(mReset)) {
                    Toast.makeText(LecLog.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                } else {
                    String dateToStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.sign,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("status");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(LecLog.this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        AlertDialog.Builder dell = new AlertDialog.Builder(this);
                                        dell.setTitle("Your Serial");
                                        dell.setMessage("Serial: " + msg + "\n\nYour account was created successfully.\nDo not share your serialNO.\nUse it to login after account approval.");
                                        dell.setPositiveButton("Got_it!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog comb = dell.create();
                                        comb.show();
                                        comb.setCancelable(false);
                                        comb.setCanceledOnTouchOutside(false);
                                        comb.getWindow().setGravity(Gravity.TOP);
                                        comb.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        comb.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            startActivity(new Intent(getApplicationContext(), LecLog.class));
                                            finish();
                                        });
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(LecLog.this, "internal server error", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(LecLog.this, "Connection error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("reg_no", "mUser");
                            para.put("fname", mFna);
                            para.put("lname", mLna);
                            para.put("email", mEmail);
                            para.put("gender", "gender");
                            para.put("phone", mPhone);
                            para.put("department", mDepa);
                            para.put("password", mPass);
                            para.put("classification", "Class");
                            para.put("role", "Role");
                            para.put("date", dateToStr);
                            para.put("theme", "lec");
                            return para;
                        }
                    });
                }
            });
        });
    }

    @Override
    public void onBackPressed() {
    }
}