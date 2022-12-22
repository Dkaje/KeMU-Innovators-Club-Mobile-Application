package com.example.kemussit.Secgen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.SecSess;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecLog extends AppCompatActivity {
    Model model;
    SecSess stuSess;
    CircleImageView circleImageView;
    TextView forgot, create, errorchecker, ohioStates, templeOne;
    EditText user, pass, reset, fname, lname, phone, email, regno;
    Button bt_log;
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
        stuSess = new SecSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sec. Gen Login");
        setContentView(R.layout.activity_sec_log);
        circleImageView = findViewById(R.id.circle_center);
        circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anti));
        forgot = findViewById(R.id.txtForgot);
        create = findViewById(R.id.txtAccount);
        user = findViewById(R.id.myUser);
        pass = findViewById(R.id.myPass);
        bt_log = findViewById(R.id.btnLog);
        errorchecker = findViewById(R.id.metaReg);
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
                } else if (!mess.matches(getString(R.string.Agr)) & !mess.matches(getString(R.string.Bit)) & !mess.matches(getString(R.string.Mac)) & !mess.matches(getString(R.string.Cis)) & !mess.matches(getString(R.string.Bis)) & !mess.matches(getString(R.string.Isk)) & !mess.matches(getString(R.string.Bio)) & !mess.matches(getString(R.string.Dah))) {
                    errorchecker.setText("Your regNo is inappropriate!");
                    errorchecker.setAnimation(AnimationUtils.loadAnimation(SecLog.this, R.anim.blink));
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
            final String mUser = user.getText().toString().trim();
            final String mpass = pass.getText().toString().trim();
            if (mUser.isEmpty()) {
                user.setError("required");
                user.requestFocus();
            } else if (!mUser.matches(getString(R.string.Agr)) & !mUser.matches(getString(R.string.Bit)) & !mUser.matches(getString(R.string.Mac)) & !mUser.matches(getString(R.string.Cis)) & !mUser.matches(getString(R.string.Bis)) & !mUser.matches(getString(R.string.Isk)) & !mUser.matches(getString(R.string.Bio)) & !mUser.matches(getString(R.string.Dah))) {
                user.setError("your regNo is inappropriate");
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
                                        startActivity(new Intent(getApplicationContext(), SecHome.class));
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
                        par.put("reg_no", mUser);
                        par.put("password", mpass);
                        par.put("theme", "sec");
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
            layer = layoutInflater.inflate(R.layout.password, null);
            layer.setMinimumWidth((int) (reco.width() * 0.9f));
            layer.setMinimumHeight((int) (reco.height() * 0.05f));
            user = layer.findViewById(R.id.myUser);
            pass = layer.findViewById(R.id.myPass);
            reset = layer.findViewById(R.id.begger);
            See = layer.findViewById(R.id.see);
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
                    } else if (!mess.matches(getString(R.string.Agr)) & !mess.matches(getString(R.string.Bit)) & !mess.matches(getString(R.string.Mac)) & !mess.matches(getString(R.string.Cis)) & !mess.matches(getString(R.string.Bis)) & !mess.matches(getString(R.string.Isk)) & !mess.matches(getString(R.string.Bio)) & !mess.matches(getString(R.string.Dah))) {
                        ohioStates.setText("Your regNo is inappropriate!");
                        ohioStates.setAnimation(AnimationUtils.loadAnimation(SecLog.this, R.anim.blink));
                        ohioStates.setVisibility(View.VISIBLE);
                    } else {
                        ohioStates.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
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
                } else if (!mUser.matches(getString(R.string.Agr)) & !mUser.matches(getString(R.string.Bit)) & !mUser.matches(getString(R.string.Mac)) & !mUser.matches(getString(R.string.Cis)) & !mUser.matches(getString(R.string.Bis)) & !mUser.matches(getString(R.string.Isk)) & !mUser.matches(getString(R.string.Bio)) & !mUser.matches(getString(R.string.Dah))) {
                    user.setError("your regNo is inappropriate");
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
                    Toast.makeText(SecLog.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.forr,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("status");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(SecLog.this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), SecLog.class));
                                        finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(SecLog.this, "internal server error", Toast.LENGTH_SHORT).show();
                                }
                            }, error -> {
                        Toast.makeText(SecLog.this, "Connection error", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("reg_no", mUser);
                            para.put("password", mPass);
                            para.put("theme", "sec");
                            return para;
                        }
                    });
                }
            });
        });
        create.setOnClickListener(view -> {
            AlertDialog.Builder meme = new AlertDialog.Builder(this);
            meme.setTitle("Sec. Gen Registration");
            meme.setMessage(Html.fromHtml("<font color='#000000'><b><ul><li>&nbsp;&nbsp;Make sure you have a student account.</li><li>&nbsp;&nbsp;Enter your REG_NO in the next Screen.</li><li>&nbsp;&nbsp;We will automatically fill your identities.</li></ul></b></font>"));
            meme.setPositiveButton("Next", (dialogInterface, i) -> {
            });
            meme.setNegativeButton("Exit", (dialogInterface, i) -> {
            });
            AlertDialog yoo = meme.create();
            yoo.show();
            yoo.setCancelable(false);
            yoo.setCanceledOnTouchOutside(false);
            yoo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            yoo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alert = new AlertDialog.Builder(this);
                reco = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(reco);
                layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                layer = layoutInflater.inflate(R.layout.comb, null);
                layer.setMinimumWidth((int) (reco.width() * 0.9f));
                layer.setMinimumHeight((int) (reco.height() * 0.05f));
                regno = layer.findViewById(R.id.myUser);
                templeOne = layer.findViewById(R.id.templeReg);
                layer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                regno.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String mess = regno.getText().toString().trim();
                        if (mess.isEmpty()) {
                            templeOne.setVisibility(View.GONE);
                        } else if (!mess.matches(getString(R.string.Agr)) & !mess.matches(getString(R.string.Bit)) & !mess.matches(getString(R.string.Mac)) & !mess.matches(getString(R.string.Cis)) & !mess.matches(getString(R.string.Bis)) & !mess.matches(getString(R.string.Isk)) & !mess.matches(getString(R.string.Bio)) & !mess.matches(getString(R.string.Dah))) {
                            templeOne.setText("Your regNo is wrong!");
                            templeOne.setAnimation(AnimationUtils.loadAnimation(SecLog.this, R.anim.blink));
                            templeOne.setVisibility(View.VISIBLE);
                        } else {
                            templeOne.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                alert.setTitle("Create Account");
                alert.setView(layer);
                alert.setPositiveButton("Register", (dialogq, idq) -> {
                });
                alert.setNegativeButton("Exit", (dialogq, idq) -> {
                });
                alertDialog = alert.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                    alertDialog.cancel();
                });
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    String mUser = regno.getText().toString().trim();
                    if (mUser.isEmpty()) {
                        regno.setError("required");
                        regno.requestFocus();
                    } else if (!mUser.matches(getString(R.string.Agr)) & !mUser.matches(getString(R.string.Bit)) & !mUser.matches(getString(R.string.Mac)) & !mUser.matches(getString(R.string.Cis)) & !mUser.matches(getString(R.string.Bis)) & !mUser.matches(getString(R.string.Isk)) & !mUser.matches(getString(R.string.Bio)) & !mUser.matches(getString(R.string.Dah))) {
                        regno.setError("Your regNo is inappropriate");
                        regno.requestFocus();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.akili,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        if (st == 1) {
                                            jsonArray = jsonObject.getJSONArray("victory");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                jsonObject = jsonArray.getJSONObject(i);
                                                String stud_id = jsonObject.getString("stud_id");
                                                String Fname = jsonObject.getString("fname");
                                                String Lname = jsonObject.getString("lname");
                                                String Gend = jsonObject.getString("gender");
                                                String Phone = jsonObject.getString("phone");
                                                String Email = jsonObject.getString("email");
                                                String Department = jsonObject.getString("department");
                                                String Classification = jsonObject.getString("classification");
                                                AlertDialog.Builder key = new AlertDialog.Builder(this);
                                                Rect rt = new Rect();
                                                Window wind = this.getWindow();
                                                wind.getDecorView().getWindowVisibleDisplayFrame(rt);
                                                LayoutInflater lay = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                View sema = lay.inflate(R.layout.system, null);
                                                sema.setMinimumWidth((int) (rt.width() * 0.9f));
                                                sema.setMinimumHeight((int) (rt.height() * 0.05f));
                                                regno = sema.findViewById(R.id.myUser);
                                                regno.setText(stud_id);
                                                fname = sema.findViewById(R.id.fname);
                                                fname.setText(Fname);
                                                lname = sema.findViewById(R.id.lname);
                                                lname.setText(Lname);
                                                phone = sema.findViewById(R.id.phone);
                                                phone.setText(Phone);
                                                email = sema.findViewById(R.id.email);
                                                email.setText(Email);
                                                reset = sema.findViewById(R.id.myConfirm);
                                                pass = sema.findViewById(R.id.myPass);
                                                Button btd = sema.findViewById(R.id.btnDepat);
                                                btd.setText(Department);
                                                circleImageView = sema.findViewById(R.id.circle_center);
                                                Button btc = sema.findViewById(R.id.btnClass);
                                                btc.setText(Classification);
                                                RadioButton radioButton = sema.findViewById(R.id.radioButtonMale);
                                                radioButton.setText(Gend);
                                                See = sema.findViewById(R.id.see);
                                                See.setOnClickListener(view4 -> {
                                                    if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                                                        See.setImageResource(R.drawable.hide);
                                                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                                    } else {
                                                        See.setImageResource(R.drawable.see);
                                                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                                    }
                                                });
                                                See4 = sema.findViewById(R.id.see4);
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
                                                key.setTitle("Create Account");
                                                key.setView(sema);
                                                key.setPositiveButton("Register", (dialogq, idq) -> {
                                                });
                                                key.setNegativeButton("Abort_Process", (dialogq, idq) -> {
                                                });
                                                AlertDialog ndio = key.create();
                                                ndio.show();
                                                ndio.setCancelable(false);
                                                ndio.setCanceledOnTouchOutside(false);
                                                ndio.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                ndio.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                                                    startActivity(new Intent(getApplicationContext(), SecLog.class));
                                                    finish();
                                                });
                                                ndio.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                                                    String mPass = pass.getText().toString().trim();
                                                    String mReset = reset.getText().toString().trim();
                                                    if (mPass.isEmpty()) {
                                                        pass.setError("required");
                                                        pass.requestFocus();
                                                    } else if (mPass.length() < 8) {
                                                        pass.setError("weak");
                                                        pass.requestFocus();
                                                    } else if (mReset.isEmpty()) {
                                                        reset.setError("required");
                                                        reset.requestFocus();
                                                    } else if (!mPass.equals(mReset)) {
                                                        Toast.makeText(SecLog.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        String dateToStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.sign,
                                                                respo -> {
                                                                    try {
                                                                        jsonObject = new JSONObject(respo);
                                                                        int meja = jsonObject.getInt("status");
                                                                        String mseg = jsonObject.getString("message");
                                                                        Toast.makeText(SecLog.this, mseg, Toast.LENGTH_SHORT).show();
                                                                        if (meja == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), SecLog.class));
                                                                            finish();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(SecLog.this, "internal server error", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }, error -> {
                                                            Toast.makeText(SecLog.this, "Connection error", Toast.LENGTH_SHORT).show();
                                                        }) {
                                                            @Nullable
                                                            @Override
                                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                                Map<String, String> para = new HashMap<>();
                                                                para.put("reg_no", stud_id);
                                                                para.put("fname", Fname);
                                                                para.put("lname", Lname);
                                                                para.put("email", Email);
                                                                para.put("gender", Gend);
                                                                para.put("phone", Phone);
                                                                para.put("department", Department);
                                                                para.put("password", mPass);
                                                                para.put("classification", Classification);
                                                                para.put("role", "Secretary");
                                                                para.put("date", dateToStr);
                                                                para.put("theme", "off");
                                                                return para;
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        } else if (st == 8) {
                                            AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                            mouse.setTitle(Html.fromHtml("<font color='#ff0000'><b>Oops!!</b></font>"));
                                            mouse.setMessage(msg + "\n\nOur System Allows only two Active Secretary Generals");
                                            AlertDialog based = mouse.create();
                                            based.show();
                                            based.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        } else {
                                            AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                            mouse.setTitle(Html.fromHtml("<font color='#ff0000'><b>Oops!!</b></font>"));
                                            mouse.setMessage(msg);
                                            AlertDialog based = mouse.create();
                                            based.show();
                                            based.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(SecLog.this, "internal server error", Toast.LENGTH_SHORT).show();
                                    }
                                }, error -> {
                            Toast.makeText(SecLog.this, "Connection error", Toast.LENGTH_SHORT).show();
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> para = new HashMap<>();
                                para.put("reg_no", mUser);
                                para.put("role", "Secretary");
                                return para;
                            }
                        });
                    }
                });
            });
            yoo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                yoo.cancel();
            });
        });
    }


}