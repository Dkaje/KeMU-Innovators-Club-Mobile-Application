package com.example.kemussit.Stud;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kemussit.Kitendawili.CardAda;
import com.example.kemussit.Kitendawili.CardMode;
import com.example.kemussit.Kitendawili.CheckMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.IssuedMode;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.SessionAda;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.Kitendawili.TegeaAda;
import com.example.kemussit.Kitendawili.TegeaMode;
import com.example.kemussit.MainActivity;
import com.example.kemussit.R;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class StuHome extends AppCompatActivity {
    CardAda cardAda;
    CardMode cardMode;
    ArrayList<CardMode> cardModeArrayList = new ArrayList<>();
    CardAda cowAda;
    CardMode cardYTim;
    ArrayList<CardMode> cardModeArrayList2 = new ArrayList<>();
    CardMode cardMo2;
    ArrayList<CardMode> cardModeArrayList1 = new ArrayList<>();
    Model model;
    StuSess stuSess;
    RelativeLayout relativeLayout, relo;
    CardView cardView;
    Button profi, logger, helper;
    CircleImageView menuu, exit, duba;
    BottomNavigationView bottomNavigationView;
    ListView listView, lister, listSess, listNof;
    SearchView searchView, searchMan, seacrhSess, searchNof;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    AlertDialog.Builder builder, alert, letting, nextFigure;
    EditText fees, descrip, mpesa;
    Bitmap bitm;
    String imagery;
    String encodedImage;
    Bitmap bitmap;
    Spinner spinner;
    AlertDialog alertDialog, dialog, let, oyaNExt;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ArrayList<CheckMode> checkModeArrayList = new ArrayList<>();
    CheckMode checkMode;
    ArrayList<CheckMode> checkModeArrayList1 = new ArrayList<>();
    CheckMode checker;
    SessionAda sessionAda;
    Button btn, baite;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();
    CircleImageView circleImageView, circular;
    ImageView imageView, imager;
    TextView cardNo, issueDate, expiryDate, issue, nocard, userd, typed, termmo;
    ArrayList<IssuedMode> issuedModeArrayList = new ArrayList<>();
    IssuedMode issuedMode;
    String newSess, newTerm, newYear, memes, mTerm, mSEs;
    ArrayList<TegeaMode> tegeaModeArrayList = new ArrayList<>();
    TegeaMode tegeaMode;
    TegeaAda tegeaAda;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + model.getFname());
        setContentView(R.layout.activity_stu_home);
        relativeLayout = findViewById(R.id.Relative);
        cardView = findViewById(R.id.cardy);
        profi = findViewById(R.id.btnProf);
        helper=findViewById(R.id.btnHelp);
        logger = findViewById(R.id.btnOut);
        menuu = findViewById(R.id.myMenu);
        exit = findViewById(R.id.myExit);
        relo = findViewById(R.id.mejaGen);
        bottomNavigationView = findViewById(R.id.topper);
        bottomNavigationView.setSelectedItemId(R.id.Smart);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnActive);
        baite = findViewById(R.id.btnPast);
        checkRpo();
    }

    private void checkRpo() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.searchRp,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                checkMode = new CheckMode(jsonObject.getString("rep"), jsonObject.getString("ses"),
                                        jsonObject.getString("user_id"), jsonObject.getString("username"), jsonObject.getString("userphone"),
                                        jsonObject.getString("term"), jsonObject.getString("year"), jsonObject.getString("report"),
                                        jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                        jsonObject.getString("ended"), jsonObject.getString("ending"),
                                        jsonObject.getString("end_date"));
                                checkModeArrayList.add(checkMode);
                            }
                            newSess = checkMode.getSes();
                            newTerm = checkMode.getTerm();
                            newYear = checkMode.getYear();
                            mTerm = checkMode.getTerm() + " " + checkMode.getYear();
                            mSEs = checkMode.getSes();
                            getMajors();
                            btn.setText("Active " + newTerm + " " + newYear);
                            btn.setOnClickListener(view -> {
                                alert = new AlertDialog.Builder(this);
                                alert.setTitle(Html.fromHtml("<font color='#ff0000'><b>Active Session</b></font>"));
                                alert.setMessage("Session: " + checkMode.getTerm() + " " + checkMode.getYear() + "\n\nsessionStart: " + checkMode.getReport() + "\nsessionEnd: " + checkMode.getEnding() + "\n\nStatus: " + checkMode.getEnded());
                                alert.setPositiveButton("Close", (dialogInterface, i) -> {
                                });
                                dialog = alert.create();
                                dialog.show();
                                dialog.setCancelable(false);
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                dialog.getWindow().setGravity(Gravity.BOTTOM);
                                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    dialog.cancel();
                                    if (cardView.getVisibility() == View.VISIBLE) {
                                        cardView.setVisibility(View.GONE);
                                    }
                                    if (relo.getVisibility() == View.VISIBLE) {
                                        relo.setVisibility(View.GONE);
                                    }
                                    menuu.setVisibility(View.VISIBLE);
                                });
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                            btn.setText(msg);
                            AlertDialog.Builder demo = new AlertDialog.Builder(this);
                            demo.setTitle(Html.fromHtml("<font color='#ff0000'><b>Alert!!</b></font>"));
                            demo.setMessage("Hello " + model.getFname() + ",\nIt seems you have no Active Session.\nClick REPORT below");
                            demo.setPositiveButton("Exit", (dialogInterface, i) -> {
                            });
                            demo.setNeutralButton(Html.fromHtml("<font color='#ff0000'><b><u><strong><i>REPORT</i></strong></u></b></font>"), (dialogInterface, i) -> {
                            });
                            AlertDialog alex = demo.create();
                            alex.show();
                            alex.setCancelable(false);
                            alex.setCanceledOnTouchOutside(false);
                            alex.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            alex.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            });
                            alex.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view -> {
                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(new StringRequest(Request.Method.POST, Connect.trySess,
                                        respons -> {
                                            try {
                                                jsonObject = new JSONObject(respons);
                                                Log.e("response ", respons);
                                                int succe = jsonObject.getInt("trust");
                                                if (succe == 1) {
                                                    jsonArray = jsonObject.getJSONArray("victory");
                                                    for (int i = 0; i < jsonArray.length(); i++) {
                                                        jsonObject = jsonArray.getJSONObject(i);
                                                        onceMode = new OnceMode(jsonObject.getString("id"), jsonObject.getString("term"),
                                                                jsonObject.getString("year"), jsonObject.getString("report"),
                                                                jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                                                jsonObject.getString("ended"), jsonObject.getString("ending"),
                                                                jsonObject.getString("end_date"));
                                                        onceModeArrayList.add(onceMode);
                                                    }
                                                    builder = new AlertDialog.Builder(this);
                                                    builder.setTitle("Active Session");
                                                    builder.setMessage("Session: " + onceMode.getTerm() + " " + onceMode.getYear() + "\n\nSessionStart: " + onceMode.getReport() + "\nSessionEnd: " + onceMode.getEnding() + "\nStatus: " + onceMode.getEnded());
                                                    builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Report_Session</b>"), (dialogInterface, i1) -> {
                                                    });
                                                    builder.setNegativeButton("Close", (dialogInterface, i1) -> {
                                                    });
                                                    alertDialog = builder.create();
                                                    alertDialog.show();
                                                    alertDialog.setCanceledOnTouchOutside(false);
                                                    alertDialog.setCancelable(false);
                                                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                                                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Alert!!</b></font>"));
                                                        theme.setMessage("By Clicking Report Below you agree that you have reported for the \n" + onceMode.getTerm() + " " + onceMode.getYear() + ".");
                                                        theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Report!!</b>"), (dialogInterface, ii) -> {
                                                        });
                                                        theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                                                        });
                                                        AlertDialog why = theme.create();
                                                        why.show();
                                                        why.setCancelable(false);
                                                        why.setCanceledOnTouchOutside(false);
                                                        why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                        why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                                                            why.cancel();
                                                        });
                                                        why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.apple,
                                                                    respo -> {
                                                                        try {
                                                                            jsonObject = new JSONObject(respo);
                                                                            int st = jsonObject.getInt("success");
                                                                            String mssg = jsonObject.getString("message");
                                                                            Toast.makeText(this, mssg, Toast.LENGTH_SHORT).show();
                                                                            if (st == 1) {
                                                                                startActivity(new Intent(getApplicationContext(), StuHome.class));
                                                                                finish();
                                                                            }
                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }, error -> {
                                                                Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                            }) {
                                                                @Nullable
                                                                @Override
                                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                                    Map<String, String> para = new HashMap<>();
                                                                    para.put("term", onceMode.getTerm());
                                                                    para.put("report", onceMode.getReport());
                                                                    para.put("ending", onceMode.getEnding());
                                                                    para.put("year", onceMode.getYear());
                                                                    para.put("ses", onceMode.getId());
                                                                    para.put("user_id", model.getStud_id());
                                                                    para.put("username", model.getFname() + " " + model.getLname());
                                                                    para.put("userphone", model.getPhone());
                                                                    para.put("status", "1");
                                                                    para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                                                    return para;
                                                                }
                                                            });
                                                        });
                                                    });
                                                    alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                                        alertDialog.cancel();
                                                    });
                                                } else if (succe == 0) {
                                                    String msgg = jsonObject.getString("mine");
                                                    Toast.makeText(this, msgg, Toast.LENGTH_SHORT).show();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                            }

                                        }, error -> {
                                    Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                                }));
                            });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            /*final Handler handler = new Handler();
            Runnable refresh = new Runnable() {
                @Override
                public void run() {
                    checkRpo();
                    handler.postDelayed(this, 5000);
                }
            };
            handler.postDelayed(refresh, 5000);*/
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("user_id", model.getStud_id());
                para.put("status", "1");
                para.put("ended", "0");
                return para;
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    private void getMajors() {
        getActive();
        getCard();
        profi.setOnClickListener(view -> {
            cardView.setVisibility(View.GONE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Profile");
            builder.setMessage("REG_NO: " + model.getStud_id() + "\nFirstname: " + model.getFname() + "\nLastname: " + model.getLname() + "\nGender: " + model.getGender() + "\nEmail: " + model.getEmail() + "\nPhone: " + model.getPhone() + "\nDepartment: " + model.getDepartment() + "\nClassification: " + model.getClassification() + "\nDate: " + model.getDate());
            builder.setNegativeButton("Close", (dd, f) -> {
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setGravity(Gravity.TOP);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(viewe -> {
                alertDialog.cancel();
            });
        });
        helper.setOnClickListener(view -> {
            cardView.setVisibility(View.GONE);
            AlertDialog.Builder help = new AlertDialog.Builder(this);
            help.setTitle("Help Student");
            help.setMessage(getString(R.string.Stud));
            help.setPositiveButton(Html.fromHtml("<font color='#950365'><strong><b>Exit</b></strong></font>"), (dialogInterface, i) -> {
            });
            AlertDialog summer = help.create();
            summer.show();
            summer.setCancelable(false);
            summer.setCanceledOnTouchOutside(false);
            summer.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            summer.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            summer.getWindow().setGravity(Gravity.TOP);
            summer.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                summer.cancel();
            });
        });
        logger.setOnClickListener(view -> {
            cardView.setVisibility(View.GONE);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirm");
            alert.setMessage("Confirm Logout!");
            alert.setPositiveButton("Yes", (dr, r) -> {
            });
            alert.setNegativeButton("No", (dr, r) -> {
            });
            AlertDialog dail = alert.create();
            dail.show();
            dail.setCancelable(false);
            dail.setCanceledOnTouchOutside(false);
            dail.getWindow().setGravity(53);
            dail.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            dail.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dail.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                dail.cancel();
            });
            dail.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                stuSess.outUser();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            });
        });
        relativeLayout.setOnClickListener(view -> {
            if (cardView.getVisibility() == View.VISIBLE) {
                cardView.setVisibility(View.GONE);
            }
        });
        menuu.setOnClickListener(view -> {
            if (cardView.getVisibility() == View.VISIBLE) {
                cardView.setVisibility(View.GONE);
            }
            relo.setVisibility(View.VISIBLE);
            menuu.setVisibility(View.GONE);
        });
        exit.setOnClickListener(view -> {
            if (cardView.getVisibility() == View.VISIBLE) {
                cardView.setVisibility(View.GONE);
            }
            relo.setVisibility(View.GONE);
            menuu.setVisibility(View.VISIBLE);
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Smart:
                    if (cardView.getVisibility() == View.VISIBLE) {
                        cardView.setVisibility(View.GONE);
                    }
                    if (relo.getVisibility() == View.VISIBLE) {
                        relo.setVisibility(View.GONE);
                    }
                    menuu.setVisibility(View.VISIBLE);
                    return true;
                case R.id.Evn:
                    if (cardView.getVisibility() == View.VISIBLE) {
                        cardView.setVisibility(View.GONE);
                    }
                    if (relo.getVisibility() == View.VISIBLE) {
                        relo.setVisibility(View.GONE);
                    }
                    menuu.setVisibility(View.VISIBLE);
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                            respon -> {
                                try {
                                    jsonObject = new JSONObject(respon);
                                    int succe = jsonObject.getInt("trust");
                                    if (succe == 1) {
                                        jsonArray = jsonObject.getJSONArray("victory");
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                            jsonObject = jsonArray.getJSONObject(j);
                                            issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                                    jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                                    jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                                    Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                                    jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                                    jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                                    jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                            issuedModeArrayList.add(issuedMode);
                                        }
                                        if (issuedMode.getExpiry().equals("Active")) {
                                            startActivity(new Intent(getApplicationContext(), Events.class));
                                        } else {
                                            AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                            factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                            rect = new Rect();
                                            window = this.getWindow();
                                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                            View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                            cardNo = vv.findViewById(R.id.realCard);
                                            issueDate = vv.findViewById(R.id.realIssue);
                                            expiryDate = vv.findViewById(R.id.realExp);
                                            expiryDate.setText(issuedMode.getExpiry_date());
                                            cardNo.setText(issuedMode.getIss());
                                            issueDate.setText(issuedMode.getIssue_date());
                                            factor.setView(vv);
                                            factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                            });
                                            factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                            });
                                            factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                            });
                                            AlertDialog ff = factor.create();
                                            ff.show();
                                            ff.setCancelable(false);
                                            ff.setCanceledOnTouchOutside(false);
                                            ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                            ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                                memes = issuedMode.getCategory();
                                                upgradeCard(this);
                                            });
                                            ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                                renewCard(this);
                                            });
                                            ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                                ff.cancel();
                                            });
                                        }
                                    } else if (succe == 0) {
                                        String msg = jsonObject.getString("mine");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                        pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                        pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                        AlertDialog dr = pole.create();
                                        dr.show();
                                        dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }, error -> {
                        Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("reg_no", model.getStud_id());
                            para.put("status", "1");
                            return para;
                        }
                    });
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.Ann:
                    if (cardView.getVisibility() == View.VISIBLE) {
                        cardView.setVisibility(View.GONE);
                    }
                    if (relo.getVisibility() == View.VISIBLE) {
                        relo.setVisibility(View.GONE);
                    }
                    menuu.setVisibility(View.VISIBLE);
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                            respon -> {
                                try {
                                    jsonObject = new JSONObject(respon);
                                    int succe = jsonObject.getInt("trust");
                                    if (succe == 1) {
                                        jsonArray = jsonObject.getJSONArray("victory");
                                        for (int j = 0; j < jsonArray.length(); j++) {
                                            jsonObject = jsonArray.getJSONObject(j);
                                            issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                                    jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                                    jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                                    Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                                    jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                                    jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                                    jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                            issuedModeArrayList.add(issuedMode);
                                        }
                                        if (issuedMode.getExpiry().equals("Active")) {
                                            startActivity(new Intent(getApplicationContext(), Announcements.class));
                                        } else {
                                            AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                            factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                            rect = new Rect();
                                            window = this.getWindow();
                                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                            View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                            cardNo = vv.findViewById(R.id.realCard);
                                            issueDate = vv.findViewById(R.id.realIssue);
                                            expiryDate = vv.findViewById(R.id.realExp);
                                            expiryDate.setText(issuedMode.getExpiry_date());
                                            cardNo.setText(issuedMode.getIss());
                                            issueDate.setText(issuedMode.getIssue_date());
                                            factor.setView(vv);
                                            factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                            });
                                            factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                            });
                                            factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                            });
                                            AlertDialog ff = factor.create();
                                            ff.show();
                                            ff.setCancelable(false);
                                            ff.setCanceledOnTouchOutside(false);
                                            ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                            ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                                memes = issuedMode.getCategory();
                                                upgradeCard(this);
                                            });
                                            ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                                renewCard(this);
                                            });
                                            ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                                ff.cancel();
                                            });
                                        }
                                    } else if (succe == 0) {
                                        String msg = jsonObject.getString("mine");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                        pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                        pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                        AlertDialog dr = pole.create();
                                        dr.show();
                                        dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                }

                            }, error -> {
                        Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> para = new HashMap<>();
                            para.put("reg_no", model.getStud_id());
                            para.put("status", "1");
                            return para;
                        }
                    });
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (CardMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle(cardMode.getCategory() + " SmartCard");
            builder.setMessage("Click Register");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.wakati, null);
            spinner = viewer.findViewById(R.id.mySpin);
            spinner.setVisibility(View.GONE);
            fees = viewer.findViewById(R.id.myFee);
            descrip = viewer.findViewById(R.id.myDesc);
            fees.setText("Ksh. " + cardMode.getFees());
            fees.setEnabled(false);
            descrip.setText(cardMode.getDescription());
            descrip.setEnabled(false);
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Register", (dialogInterface, ii) -> {
            });
            builder.setNegativeButton("Exit", (dialogInterface, ii) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alert = new AlertDialog.Builder(this);
                alert.setTitle("SmartCard Registration");
                alert.setMessage("Add profile Image\nClick Next to enter your signature");
                rect = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                viewer = layoutInflater.inflate(R.layout.rotator, null);
                duba = viewer.findViewById(R.id.circuit);
                duba.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                duba.setOnClickListener(view2 -> {
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            22
                    );
                });
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                viewer.setLayoutParams(params);
                frameLayout.addView(viewer);
                alert.setView(frameLayout);
                alert.setPositiveButton("Next", (dialogInterface, ii) -> {
                });
                alert.setNegativeButton("Exit", (dialogInterface, ii) -> {
                });
                dialog = alert.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                    dialog.cancel();
                });
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    Drawable drawable = duba.getDrawable();
                    if (drawable == null) {
                        Toast.makeText(this, "You did not add an image profile", Toast.LENGTH_SHORT).show();
                    } else {
                        letting = new AlertDialog.Builder(this);
                        letting.setTitle("Signature Required");
                        rect = new Rect();
                        window = this.getWindow();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                        viewer = layoutInflater.inflate(R.layout.coder, null);
                        signaturePad = viewer.findViewById(R.id.signature_view);
                        frameLayout = new FrameLayout(this);
                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                        viewer.setLayoutParams(params);
                        frameLayout.addView(viewer);
                        letting.setView(frameLayout);
                        letting.setPositiveButton("Next", (dialogInterface, ii) -> {
                        });
                        letting.setNeutralButton("Clear", (dialogInterface, ii) -> {
                        });
                        letting.setNegativeButton("Back", (dialogInterface, ii) -> {
                        });
                        let = letting.create();
                        let.show();
                        let.setCancelable(false);
                        let.setCanceledOnTouchOutside(false);
                        let.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                        let.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                            if (!signaturePad.isEmpty()) {
                                bitmap = signaturePad.getSignatureBitmap();
                                encodedSign(bitmap);
                                nextFigure = new AlertDialog.Builder(this);
                                nextFigure.setTitle("Confirm");
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.asmoyan, null);
                                circleImageView = viewer.findViewById(R.id.confirmed);
                                imageView = viewer.findViewById(R.id.mySign);
                                circleImageView.setImageBitmap(bitm);
                                imageView.setImageBitmap(bitmap);
                                circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.maker));
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                nextFigure.setView(frameLayout);
                                nextFigure.setPositiveButton("Next", (dialogInterface, ii) -> {
                                });
                                nextFigure.setNegativeButton("Edit", (dialogInterface, ii) -> {
                                });
                                oyaNExt = nextFigure.create();
                                oyaNExt.show();
                                oyaNExt.setCancelable(false);
                                oyaNExt.setCanceledOnTouchOutside(false);
                                oyaNExt.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                oyaNExt.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                    oyaNExt.cancel();
                                });
                                oyaNExt.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                    AlertDialog.Builder kemu = new AlertDialog.Builder(this);
                                    kemu.setTitle("Fees Payment");
                                    kemu.setMessage("Hello " + model.getFname() + ",\nSmartCard: " + cardMode.getCategory() + "\nFees Ksh. " + cardMode.getFees() + "\n\nMPESA PayBill: 300112\nAcc. " + model.getStud_id() + "\n\nEnter MPESA code in the next screen.");
                                    kemu.setPositiveButton("Next", (dialogInterface, i1) -> {
                                    });
                                    kemu.setNegativeButton("Back", (dialogInterface, i1) -> {
                                    });
                                    AlertDialog kemuso = kemu.create();
                                    kemuso.show();
                                    kemuso.setCancelable(false);
                                    kemuso.setCanceledOnTouchOutside(false);
                                    kemuso.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    kemuso.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                        kemuso.cancel();
                                    });
                                    kemuso.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                        AlertDialog.Builder gateB = new AlertDialog.Builder(this);
                                        gateB.setTitle("Payment");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        viewer = layoutInflater.inflate(R.layout.payer, null);
                                        mpesa = viewer.findViewById(R.id.Tranc);
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        viewer.setLayoutParams(params);
                                        frameLayout.addView(viewer);
                                        gateB.setView(frameLayout);
                                        mpesa.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                            }

                                            @Override
                                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                String mess = mpesa.getText().toString().trim();
                                                if (!mess.isEmpty()) {
                                                    if (mess.length() < 10) {
                                                        mpesa.setError("invalid");
                                                        mpesa.requestFocus();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void afterTextChanged(Editable editable) {

                                            }
                                        });
                                        gateB.setNegativeButton("Edit", (dialogInterface, ii) -> {
                                        });
                                        gateB.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                        });
                                        AlertDialog gateA = gateB.create();
                                        gateA.show();
                                        gateA.setCancelable(false);
                                        gateA.setCanceledOnTouchOutside(false);
                                        gateA.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        gateA.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view6 -> {
                                            gateA.cancel();
                                        });
                                        gateA.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view6 -> {
                                            final String mPe = mpesa.getText().toString().trim();
                                            if (mPe.isEmpty()) {
                                                mpesa.setError("Transaction CODE");
                                                mpesa.requestFocus();
                                            } else if (mPe.length() < 10) {
                                                mpesa.setError("not yet valid");
                                                mpesa.requestFocus();
                                            } else {
                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, Connect.payer,
                                                        response -> {
                                                            try {
                                                                jsonObject = new JSONObject(response);
                                                                int st = jsonObject.getInt("success");
                                                                String msg = jsonObject.getString("message");
                                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                if (st == 1) {
                                                                    startActivity(new Intent(getApplicationContext(), StuHome.class));
                                                                    finish();
                                                                }
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }, error -> {
                                                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                }) {
                                                    @Nullable
                                                    @Override
                                                    protected Map<String, String> getParams() throws AuthFailureError {
                                                        Map<String, String> para = new HashMap<>();
                                                        para.put("category", cardMode.getCategory());
                                                        para.put("term", checkMode.getTerm());
                                                        para.put("year", checkMode.getYear());
                                                        para.put("ses", checkMode.getSes());
                                                        para.put("fees", cardMode.getFees());
                                                        para.put("mpesa", mPe);
                                                        para.put("profile", imagery);
                                                        para.put("signature", encodedImage);
                                                        para.put("fullname", model.getFname() + " " + model.getLname());
                                                        para.put("phone", model.getPhone());
                                                        para.put("email", model.getEmail());
                                                        para.put("reg_no", model.getStud_id());
                                                        para.put("department", model.getDepartment());
                                                        para.put("classification", model.getClassification());
                                                        para.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                                        return para;
                                                    }
                                                });
                                            }
                                        });
                                    });
                                });
                            } else {
                                Toast.makeText(this, "Signature required", Toast.LENGTH_SHORT).show();
                            }
                        });
                        let.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                            let.cancel();
                        });
                        let.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view3 -> {
                            signaturePad.clearView();
                        });
                    }
                });
            });
        });
        findViewById(R.id.btnSmart).setOnClickListener(view -> {
            if (cardView.getVisibility() == View.VISIBLE) {
                cardView.setVisibility(View.GONE);
            }
            if (relo.getVisibility() == View.VISIBLE) {
                relo.setVisibility(View.GONE);
            }
            menuu.setVisibility(View.VISIBLE);
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                if (issuedMode.getExpiry().equals("Active")) {
                                    factor.setTitle("Processed Card");
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.cardyy, null);
                                    circular = vv.findViewById(R.id.userProfile);
                                    imager = vv.findViewById(R.id.sigmaF);
                                    issue = vv.findViewById(R.id.issued);
                                    nocard = vv.findViewById(R.id.cardNo);
                                    userd = vv.findViewById(R.id.userName);
                                    Glide.with(this).load(issuedMode.getProfile()).into(circular);
                                    Glide.with(this).load(issuedMode.getSignature()).into(imager);
                                    typed = vv.findViewById(R.id.cardType);
                                    typed.setText(issuedMode.getCategory());
                                    nocard.setText("CardNO: " + issuedMode.getIss());
                                    userd.setText("Reg: " + issuedMode.getReg_no() + "\nName: " + issuedMode.getFullname() + "\nDep: " + issuedMode.getDepartment());
                                    issue.setText("Issue_Date: " + issuedMode.getIssue_date() + "\nExpiry_Date: " + issuedMode.getExpiry_date());
                                    factor.setView(vv);
                                    factor.setPositiveButton("Pdf", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                        printManager.print(getString(R.string.app_name), new PrintF(this, vv.findViewById(R.id.printing)), null);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                } else {
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(msg);
                                pole.setMessage("Oops!! Your card was not found");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        findViewById(R.id.btnParty).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                if (issuedMode.getExpiry().equals("Active")) {
                                    startActivity(new Intent(getApplicationContext(), EventPay.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        findViewById(R.id.btnWithdrawal).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                if (issuedMode.getExpiry().equals("Active")) {
                                    startActivity(new Intent(getApplicationContext(), Courses.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        findViewById(R.id.btnSystem).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                if (issuedMode.getExpiry().equals("Active")) {
                                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        findViewById(R.id.btnCon).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                if (issuedMode.getExpiry().equals("Active")) {
                                    startActivity(new Intent(getApplicationContext(), PastContri.class).putExtra("term", mTerm).putExtra("ses", mSEs));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        findViewById(R.id.btnEvent).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.testExp,
                    respon -> {
                        try {
                            jsonObject = new JSONObject(respon);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    jsonObject = jsonArray.getJSONObject(j);
                                    issuedMode = new IssuedMode(jsonObject.getString("iss"), jsonObject.getString("idi"),
                                            jsonObject.getString("ses"), jsonObject.getString("reg_no"),
                                            jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                            Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                            jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                            jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("status"),
                                            jsonObject.getString("ended"), jsonObject.getString("expiry"), jsonObject.getString("entry_date"));
                                    issuedModeArrayList.add(issuedMode);
                                }
                                if (issuedMode.getExpiry().equals("Active")) {
                                    startActivity(new Intent(getApplicationContext(), ActivePay.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.oya_kabu, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Upgrade", (dialogInterface1, i1) -> {
                                    });
                                    factor.setPositiveButton("Renew", (dialogInterface1, i1) -> {
                                    });
                                    factor.setNegativeButton("Close", (dialogInterface1, i1) -> {
                                    });
                                    AlertDialog ff = factor.create();
                                    ff.show();
                                    ff.setCancelable(false);
                                    ff.setCanceledOnTouchOutside(false);
                                    ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    ff.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ff.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                        memes = issuedMode.getCategory();
                                        upgradeCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        renewCard(this);
                                    });
                                    ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        ff.cancel();
                                    });
                                }
                            } else if (succe == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                AlertDialog.Builder pole = new AlertDialog.Builder(this);
                                pole.setTitle(Html.fromHtml("<font color='#ff0000'><b>Fatal Error!!</b></font>"));
                                pole.setMessage("Oops!!\nAccess denied. You don't have a card to proceed.");
                                AlertDialog dr = pole.create();
                                dr.show();
                                dr.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("reg_no", model.getStud_id());
                    para.put("status", "1");
                    return para;
                }
            });
        });
        baite.setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.searchRp,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            Log.e("response ", response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    checker = new CheckMode(jsonObject.getString("rep"), jsonObject.getString("ses"),
                                            jsonObject.getString("user_id"), jsonObject.getString("username"), jsonObject.getString("userphone"),
                                            jsonObject.getString("term"), jsonObject.getString("year"), jsonObject.getString("report"),
                                            jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                            jsonObject.getString("ended"), jsonObject.getString("ending"),
                                            jsonObject.getString("end_date"));
                                    checkModeArrayList1.add(checker);
                                }
                                builder = new AlertDialog.Builder(this);
                                builder.setTitle("Past Sessions");
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.monuvre, null);
                                listSess = viewer.findViewById(R.id.listView);
                                listSess.setTextFilterEnabled(true);
                                seacrhSess = viewer.findViewById(R.id.seacrhed);
                                sessionAda = new SessionAda(StuHome.this, R.layout.ndugu, checkModeArrayList1);
                                listSess.setAdapter(sessionAda);
                                seacrhSess.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String text) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        sessionAda.getFilter().filter(newText);
                                        return false;
                                    }
                                });
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                builder.setView(frameLayout);
                                builder.setNegativeButton("Exit", (dialogInterface, ii) -> {
                                });
                                alertDialog = builder.create();
                                alertDialog.show();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                    if (!checkModeArrayList1.isEmpty()) {
                                        checkModeArrayList1.clear();
                                        sessionAda.notifyDataSetChanged();
                                    }
                                    alertDialog.cancel();
                                });
                            } else if (success == 0) {
                                AlertDialog.Builder mirror = new AlertDialog.Builder(this);
                                mirror.setTitle(Html.fromHtml("<font color='#ff0000'><b>No Past Session!</b></font>"));
                                mirror.setMessage("You don't have any past reported Session");
                                mirror.setPositiveButton("Close", (dialogInterface, i) -> {
                                });
                                AlertDialog dam = mirror.create();
                                dam.show();
                                dam.setCancelable(false);
                                dam.setCanceledOnTouchOutside(false);
                                dam.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                dam.getWindow().setGravity(Gravity.BOTTOM);
                                dam.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    dam.cancel();
                                    if (cardView.getVisibility() == View.VISIBLE) {
                                        cardView.setVisibility(View.GONE);
                                    }
                                    if (relo.getVisibility() == View.VISIBLE) {
                                        relo.setVisibility(View.GONE);
                                    }
                                    menuu.setVisibility(View.VISIBLE);
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("user_id", model.getStud_id());
                    para.put("status", "1");
                    para.put("ended", "1");
                    return para;
                }
            });
        });
    }

    private void upgradeCard(StuHome stuHome) {
        AlertDialog.Builder bright = new AlertDialog.Builder(stuHome);
        bright.setTitle(Html.fromHtml("<font color='#ff0000'><b><u>Sharp Advice</u></b></font>"));
        bright.setMessage("Expired Card: " + memes + "\n\nWhile upgrading your card, Please a card category in the next screen.\n\nYou cannot upgrade an expired card with the same card type. For that case go back Click RENEW.\n\nWas this helpful?");
        bright.setPositiveButton(Html.fromHtml("<font color='#063E92'><big><strong><b><u>Yes_Next!!</u></b></strong></big></font>"), (dialogInterface, i) -> {
        });
        bright.setNegativeButton(Html.fromHtml("<font color='#ff0000'><b><u>No!!</u></b></font>"), (dialogInterface, i) -> {
        });
        AlertDialog comb = bright.create();
        comb.show();
        comb.setCanceledOnTouchOutside(false);
        comb.setCancelable(false);
        comb.getWindow().setBackgroundDrawableResource(R.drawable.integral);
        comb.getWindow().setGravity(Gravity.TOP);
        comb.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        comb.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.jetted,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            Log.e("response ", response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    cardYTim = new CardMode(jsonObject.getString("id"), jsonObject.getString("category"), jsonObject.getString("fees"), jsonObject.getString("description"), jsonObject.getString("date"));
                                    cardModeArrayList2.add(cardYTim);
                                }
                                AlertDialog.Builder cupBearer = new AlertDialog.Builder(this);
                                cupBearer.setTitle("Select Favorite Card");
                                cupBearer.setMessage(Html.fromHtml("<font color='#ff0000'>Notice your expired Card type aka(<b><u>" + memes + "</u></b>) is missing from the list."));
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.holiday, null);
                                lister = viewer.findViewById(R.id.truList);
                                lister.setTextFilterEnabled(true);
                                searchMan = viewer.findViewById(R.id.truSerc);
                                cowAda = new CardAda(stuHome, R.layout.fees, cardModeArrayList2);
                                lister.setAdapter(cowAda);
                                searchMan.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                    @Override
                                    public boolean onQueryTextSubmit(String text) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        cowAda.getFilter().filter(newText);
                                        return false;
                                    }
                                });
                                lister.setOnItemClickListener((adapterView, view3, i, l) -> {
                                    cardYTim = (CardMode) adapterView.getItemAtPosition(i);
                                    builder = new AlertDialog.Builder(this);
                                    builder.setTitle(cardYTim.getCategory() + " Upgrade");
                                    builder.setMessage("Click Upgrade");
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    viewer = layoutInflater.inflate(R.layout.wakati, null);
                                    spinner = viewer.findViewById(R.id.mySpin);
                                    spinner.setVisibility(View.GONE);
                                    fees = viewer.findViewById(R.id.myFee);
                                    descrip = viewer.findViewById(R.id.myDesc);
                                    fees.setText("Ksh. " + cardYTim.getFees());
                                    fees.setEnabled(false);
                                    descrip.setText(cardYTim.getDescription());
                                    descrip.setEnabled(false);
                                    viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                    frameLayout = new FrameLayout(this);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    viewer.setLayoutParams(params);
                                    frameLayout.addView(viewer);
                                    builder.setView(frameLayout);
                                    builder.setPositiveButton("Upgrade", (dialogInterface, ii) -> {
                                    });
                                    builder.setNegativeButton("Exit", (dialogInterface, ii) -> {
                                    });
                                    alertDialog = builder.create();
                                    alertDialog.show();
                                    alertDialog.setCancelable(false);
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                                        alertDialog.cancel();
                                    });
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                        alert = new AlertDialog.Builder(this);
                                        alert.setTitle("SmartCard Upgrade");
                                        alert.setMessage("Add profile Image\nClick Next to enter your signature");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        viewer = layoutInflater.inflate(R.layout.rotator, null);
                                        duba = viewer.findViewById(R.id.circuit);
                                        duba.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                        duba.setOnClickListener(view2 -> {
                                            ActivityCompat.requestPermissions(
                                                    this,
                                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                    22
                                            );
                                        });
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        viewer.setLayoutParams(params);
                                        frameLayout.addView(viewer);
                                        alert.setView(frameLayout);
                                        alert.setPositiveButton("Next", (dialogInterface, ii) -> {
                                        });
                                        alert.setNegativeButton("Exit", (dialogInterface, ii) -> {
                                        });
                                        dialog = alert.create();
                                        dialog.show();
                                        dialog.setCancelable(false);
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            dialog.cancel();
                                        });
                                        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            Drawable drawable = duba.getDrawable();
                                            if (drawable == null) {
                                                Toast.makeText(this, "You did not add an image profile", Toast.LENGTH_SHORT).show();
                                            } else {
                                                letting = new AlertDialog.Builder(this);
                                                letting.setTitle("Signature Required");
                                                rect = new Rect();
                                                window = this.getWindow();
                                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                                viewer = layoutInflater.inflate(R.layout.coder, null);
                                                signaturePad = viewer.findViewById(R.id.signature_view);
                                                frameLayout = new FrameLayout(this);
                                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                viewer.setLayoutParams(params);
                                                frameLayout.addView(viewer);
                                                letting.setView(frameLayout);
                                                letting.setPositiveButton("Next", (dialogInterface, ii) -> {
                                                });
                                                letting.setNeutralButton("Clear", (dialogInterface, ii) -> {
                                                });
                                                letting.setNegativeButton("Back", (dialogInterface, ii) -> {
                                                });
                                                let = letting.create();
                                                let.show();
                                                let.setCancelable(false);
                                                let.setCanceledOnTouchOutside(false);
                                                let.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                let.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view7 -> {
                                                    if (!signaturePad.isEmpty()) {
                                                        bitmap = signaturePad.getSignatureBitmap();
                                                        encodedSign(bitmap);
                                                        nextFigure = new AlertDialog.Builder(this);
                                                        nextFigure.setTitle("Confirm");
                                                        rect = new Rect();
                                                        window = this.getWindow();
                                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                                        viewer = layoutInflater.inflate(R.layout.asmoyan, null);
                                                        circleImageView = viewer.findViewById(R.id.confirmed);
                                                        imageView = viewer.findViewById(R.id.mySign);
                                                        circleImageView.setImageBitmap(bitm);
                                                        imageView.setImageBitmap(bitmap);
                                                        circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                                        imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.maker));
                                                        frameLayout = new FrameLayout(this);
                                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                        viewer.setLayoutParams(params);
                                                        frameLayout.addView(viewer);
                                                        nextFigure.setView(frameLayout);
                                                        nextFigure.setPositiveButton("Next", (dialogInterface, ii) -> {
                                                        });
                                                        nextFigure.setNegativeButton("Edit", (dialogInterface, ii) -> {
                                                        });
                                                        oyaNExt = nextFigure.create();
                                                        oyaNExt.show();
                                                        oyaNExt.setCancelable(false);
                                                        oyaNExt.setCanceledOnTouchOutside(false);
                                                        oyaNExt.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                        oyaNExt.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                                            oyaNExt.cancel();
                                                        });
                                                        oyaNExt.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                                            AlertDialog.Builder kemu = new AlertDialog.Builder(this);
                                                            kemu.setTitle("Fees Payment");
                                                            kemu.setMessage("Hello " + model.getFname() + ",\nSmartCard: " + cardYTim.getCategory() + "\nFees Ksh. " + cardYTim.getFees() + "\n\nMPESA PayBill: 300112\nAcc. " + model.getStud_id() + "\n\nEnter MPESA code in the next screen.");
                                                            kemu.setPositiveButton("Next", (dialogInterface, i1) -> {
                                                            });
                                                            kemu.setNegativeButton("Back", (dialogInterface, i1) -> {
                                                            });
                                                            AlertDialog kemuso = kemu.create();
                                                            kemuso.show();
                                                            kemuso.setCancelable(false);
                                                            kemuso.setCanceledOnTouchOutside(false);
                                                            kemuso.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                            kemuso.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                                                kemuso.cancel();
                                                            });
                                                            kemuso.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                                                AlertDialog.Builder gateB = new AlertDialog.Builder(this);
                                                                gateB.setTitle("Payment");
                                                                rect = new Rect();
                                                                window = this.getWindow();
                                                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                                                viewer = layoutInflater.inflate(R.layout.payer, null);
                                                                mpesa = viewer.findViewById(R.id.Tranc);
                                                                frameLayout = new FrameLayout(this);
                                                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                                                viewer.setLayoutParams(params);
                                                                frameLayout.addView(viewer);
                                                                gateB.setView(frameLayout);
                                                                mpesa.addTextChangedListener(new TextWatcher() {
                                                                    @Override
                                                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                                    }

                                                                    @Override
                                                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                                        String mess = mpesa.getText().toString().trim();
                                                                        if (!mess.isEmpty()) {
                                                                            if (mess.length() < 10) {
                                                                                mpesa.setError("invalid");
                                                                                mpesa.requestFocus();
                                                                            }
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void afterTextChanged(Editable editable) {

                                                                    }
                                                                });
                                                                gateB.setNegativeButton("Edit", (dialogInterface, ii) -> {
                                                                });
                                                                gateB.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                                                });
                                                                AlertDialog gateA = gateB.create();
                                                                gateA.show();
                                                                gateA.setCancelable(false);
                                                                gateA.setCanceledOnTouchOutside(false);
                                                                gateA.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                                gateA.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view6 -> {
                                                                    gateA.cancel();
                                                                });
                                                                gateA.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view6 -> {
                                                                    final String mPe = mpesa.getText().toString().trim();
                                                                    if (mPe.isEmpty()) {
                                                                        mpesa.setError("Transaction CODE");
                                                                        mpesa.requestFocus();
                                                                    } else if (mPe.length() < 10) {
                                                                        mpesa.setError("not yet valid");
                                                                        mpesa.requestFocus();
                                                                    } else {
                                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.upper,
                                                                                respo -> {
                                                                                    try {
                                                                                        jsonObject = new JSONObject(respo);
                                                                                        int st = jsonObject.getInt("success");
                                                                                        String msg = jsonObject.getString("message");
                                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                                        if (st == 1) {
                                                                                            startActivity(new Intent(getApplicationContext(), StuHome.class));
                                                                                            finish();
                                                                                        }
                                                                                    } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }, error -> {
                                                                            Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                                                        }) {
                                                                            @Nullable
                                                                            @Override
                                                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                                                Map<String, String> para = new HashMap<>();
                                                                                para.put("category", cardYTim.getCategory());
                                                                                para.put("profile", imagery);
                                                                                para.put("signature", encodedImage);
                                                                                para.put("term", newTerm);
                                                                                para.put("year", newYear);
                                                                                para.put("ses", newSess);
                                                                                para.put("fees", cardYTim.getFees());
                                                                                para.put("mpesa", mPe);
                                                                                para.put("fullname", checkMode.getUsername());
                                                                                para.put("phone", checkMode.getUserphone());
                                                                                para.put("email", model.getEmail());
                                                                                para.put("reg_no", checkMode.getUser_id());
                                                                                para.put("department", model.getDepartment());
                                                                                para.put("classification", model.getClassification());
                                                                                para.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                                                                return para;
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            });
                                                        });
                                                    } else {
                                                        Toast.makeText(this, "Signature required", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                let.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view7 -> {
                                                    let.cancel();
                                                });
                                                let.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view7 -> {
                                                    signaturePad.clearView();
                                                });
                                            }
                                        });
                                    });
                                });
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                cupBearer.setView(frameLayout);
                                cupBearer.setPositiveButton("Need_Help?", (dialogInterface, ii) -> {
                                });
                                cupBearer.setNeutralButton("Rollback", (dialogInterface, ii) -> {
                                });
                                AlertDialog tunda = cupBearer.create();
                                tunda.show();
                                tunda.setCancelable(false);
                                tunda.setCanceledOnTouchOutside(false);
                                tunda.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                tunda.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                tunda.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                                    if (!cardModeArrayList2.isEmpty()) {
                                        cardModeArrayList2.clear();
                                        cowAda.notifyDataSetChanged();
                                    }
                                    tunda.cancel();
                                });
                                tunda.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    Toast.makeText(stuHome, "Just Select a card from the list provided\nNotice your previous card type is omitted.", Toast.LENGTH_LONG).show();
                                });
                            } else if (success == 0) {
                                String msg = jsonObject.getString("mine");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> para = new HashMap<>();
                    para.put("category", memes);
                    return para;
                }
            });
        });
        comb.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view -> {
            comb.cancel();
        });
    }

    private void renewCard(StuHome stuHome) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.mercy,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMo2 = new CardMode(jsonObject.getString("id"), jsonObject.getString("category"), jsonObject.getString("fees"), jsonObject.getString("description"), jsonObject.getString("date"));
                                cardModeArrayList1.add(cardMo2);
                            }
                            AlertDialog.Builder bright = new AlertDialog.Builder(stuHome);
                            bright.setTitle(Html.fromHtml("<font color='#ff0000'><b><u>Card Renewal</u></b></font>"));
                            bright.setMessage(Html.fromHtml("<font color='#063E92'><big><strong><b><u>card</u></b></strong></big> :" + cardMo2.getCategory() + "<br><big><strong><b><u>briefing</u></b></strong></big> :" + cardMo2.getDescription() + "<br><big><strong><b><u>feeCharged</u></b></strong></big> :Kes" + cardMo2.getFees() + "<br><br>Do you want to renew your card??<br><u><i><b>Don't worry. We will help recover your card in full.</b></i></u></font>"));
                            bright.setPositiveButton(Html.fromHtml("<font color='#063E92'><big><strong><b><u>Okay_Help</u></b></strong></big></font>"), (dialogInterface, i) -> {
                            });
                            bright.setNegativeButton(Html.fromHtml("<font color='#ff0000'><b><u>Exit</u></b></font>"), (dialogInterface, i) -> {
                            });
                            AlertDialog comb = bright.create();
                            comb.show();
                            comb.setCanceledOnTouchOutside(false);
                            comb.setCancelable(false);
                            comb.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            comb.getWindow().setGravity(Gravity.TOP);
                            comb.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            comb.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                                AlertDialog.Builder waa = new AlertDialog.Builder(stuHome);
                                waa.setTitle(Html.fromHtml("<font color='#187403'><b><u>Card Renewal</u></b></font>"));
                                waa.setMessage(Html.fromHtml("<font color='#187403'>Hello <strong><b>" + model.getFname() + "</b></strong>,<br>card: <big><strong><b>" + cardMo2.getCategory() + "</b></strong></big><br>Fees: Kes<big><strong><b>" + cardMo2.getFees() + "</b></strong></big><br>MPESA PayBill: <big><strong><b>300112</b></strong></big><br>Acc.<big><strong><b>" + model.getStud_id() + "</big></strong></b><br>Enter MPESA code in the next screen.</font>"));
                                waa.setPositiveButton(Html.fromHtml("<font color='#187403'><big><strong><b><u>Next</u></b></strong></big></font>"), (dialogInterface, i) -> {
                                });
                                waa.setNegativeButton(Html.fromHtml("<font color='#ff0000'><b><u>Exit</u></b></font>"), (dialogInterface, i) -> {
                                });
                                AlertDialog lamba = waa.create();
                                lamba.show();
                                lamba.setCanceledOnTouchOutside(false);
                                lamba.setCancelable(false);
                                lamba.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                lamba.getWindow().setGravity(Gravity.CENTER);
                                lamba.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                lamba.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                    AlertDialog.Builder gateB = new AlertDialog.Builder(stuHome);
                                    gateB.setTitle(Html.fromHtml("<font color='#000000'><b>Fee Payment</b></font>"));
                                    rect = new Rect();
                                    window = stuHome.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) stuHome.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View summer = layoutInflater.inflate(R.layout.payer, null);
                                    mpesa = summer.findViewById(R.id.Tranc);
                                    frameLayout = new FrameLayout(stuHome);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    summer.setLayoutParams(params);
                                    frameLayout.addView(summer);
                                    gateB.setView(frameLayout);
                                    mpesa.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                        }

                                        @Override
                                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                            String mess = mpesa.getText().toString().trim();
                                            if (!mess.isEmpty()) {
                                                if (mess.length() < 10) {
                                                    mpesa.setError("invalid");
                                                    mpesa.requestFocus();
                                                }
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable editable) {

                                        }
                                    });
                                    gateB.setNegativeButton(Html.fromHtml("<font color='#ff0000'><b>Exit</b></font>"), (dialogInterface, ii) -> {
                                    });
                                    gateB.setPositiveButton(Html.fromHtml("<font color='#000000'><b><u>Next</u></b></strong></font>"), (dialogInterface, ii) -> {
                                    });
                                    AlertDialog gateA = gateB.create();
                                    gateA.show();
                                    gateA.setCancelable(false);
                                    gateA.setCanceledOnTouchOutside(false);
                                    gateA.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    gateA.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    gateA.getWindow().setGravity(Gravity.BOTTOM);
                                    gateA.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view6 -> {
                                        gateA.cancel();
                                    });
                                    gateA.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view6 -> {
                                        final String mPe = mpesa.getText().toString().trim();
                                        if (mPe.isEmpty()) {
                                            mpesa.setError("Transaction CODE");
                                            mpesa.requestFocus();
                                        } else if (mPe.length() < 10) {
                                            mpesa.setError("not yet valid");
                                            mpesa.requestFocus();
                                        } else {
                                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.vivolas,
                                                    resp -> {
                                                        try {
                                                            jsonObject = new JSONObject(resp);
                                                            int st = jsonObject.getInt("success");
                                                            String msg = jsonObject.getString("message");
                                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                            if (st == 1) {
                                                                startActivity(new Intent(getApplicationContext(), StuHome.class));
                                                                finish();
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }, error -> {
                                                Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                                            }) {
                                                @Nullable
                                                @Override
                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                    Map<String, String> para = new HashMap<>();
                                                    para.put("category", cardMo2.getCategory());
                                                    para.put("term", newTerm);
                                                    para.put("year", newYear);
                                                    para.put("ses", newSess);
                                                    para.put("fees", cardMo2.getFees());
                                                    para.put("mpesa", mPe);
                                                    para.put("fullname", checkMode.getUsername());
                                                    para.put("phone", checkMode.getUserphone());
                                                    para.put("email", model.getEmail());
                                                    para.put("reg_no", checkMode.getUser_id());
                                                    para.put("department", model.getDepartment());
                                                    para.put("classification", model.getClassification());
                                                    para.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                                    return para;
                                                }
                                            });
                                        }
                                    });
                                });
                                lamba.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                    lamba.cancel();
                                });
                            });
                            comb.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view -> {
                                comb.cancel();
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(stuHome, msg, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(stuHome, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(stuHome, "Failed to connect", Toast.LENGTH_SHORT).show();
        }));
    }

    private void getActive() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.searchRp,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                checkMode = new CheckMode(jsonObject.getString("rep"), jsonObject.getString("ses"),
                                        jsonObject.getString("user_id"), jsonObject.getString("username"), jsonObject.getString("userphone"),
                                        jsonObject.getString("term"), jsonObject.getString("year"), jsonObject.getString("report"),
                                        jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                        jsonObject.getString("ended"), jsonObject.getString("ending"),
                                        jsonObject.getString("end_date"));
                                checkModeArrayList.add(checkMode);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("user_id", model.getStud_id());
                para.put("status", "1");
                para.put("ended", "1");
                return para;
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 22) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 22);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 22 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitm = BitmapFactory.decodeStream(inputStream);
                duba.setImageBitmap(bitm);
                encodedBitmap(bitm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodedBitmap(Bitmap bitm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        imagery = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void encodedSign(Bitmap bitm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesofimages = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(bytesofimages, Base64.DEFAULT);
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.sample,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new CardMode(jsonObject.getString("id"), jsonObject.getString("category"), jsonObject.getString("fees"), jsonObject.getString("description"), jsonObject.getString("date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new CardAda(StuHome.this, R.layout.fees, cardModeArrayList);
                            listView.setAdapter(cardAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    cardAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();

        }));
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Prof:
                if (cardView.getVisibility() == View.VISIBLE) {
                    cardView.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                }
                if (relo.getVisibility() == View.VISIBLE) {
                    relo.setVisibility(View.GONE);
                }
                menuu.setVisibility(View.VISIBLE);
                break;
            case R.id.Noti:
                if (cardView.getVisibility() == View.VISIBLE) {
                    cardView.setVisibility(View.GONE);
                }
                if (relo.getVisibility() == View.VISIBLE) {
                    relo.setVisibility(View.GONE);
                }
                menuu.setVisibility(View.VISIBLE);
                getNot(this);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void getNot(StuHome stuHome) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.finAlrt,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                tegeaMode = new TegeaMode(jsonObject.getString("user"), jsonObject.getString("alrt"), jsonObject.getString("reg_date"));
                                tegeaModeArrayList.add(tegeaMode);
                            }
                            AlertDialog.Builder mouse = new AlertDialog.Builder(stuHome);
                            mouse.setTitle(Html.fromHtml("<font color='#950365'><b><strong>Notifications</strong></b></font>"));
                            mouse.setIcon(R.drawable.club);
                            rect = new Rect();
                            window = stuHome.getWindow();
                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                            layoutInflater = (LayoutInflater) stuHome.getSystemService(LAYOUT_INFLATER_SERVICE);
                            View legged = layoutInflater.inflate(R.layout.notices, null);
                            listNof = legged.findViewById(R.id.listClub);
                            listNof.setTextFilterEnabled(true);
                            searchNof = legged.findViewById(R.id.searchClucb);
                            termmo = legged.findViewById(R.id.txtClub);
                            listNof.setVisibility(View.VISIBLE);
                            searchNof.setVisibility(View.VISIBLE);
                            tegeaAda = new TegeaAda(stuHome, R.layout.clubs, tegeaModeArrayList);
                            listNof.setAdapter(tegeaAda);
                            searchNof.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    tegeaAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            legged.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                            frameLayout = new FrameLayout(stuHome);
                            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                            legged.setLayoutParams(params);
                            frameLayout.addView(legged);
                            mouse.setView(frameLayout);
                            mouse.setNegativeButton("Cancel", (dialogInterface, it) -> {
                            });
                            AlertDialog summary = mouse.create();
                            summary.show();
                            summary.setCancelable(false);
                            summary.setCanceledOnTouchOutside(false);
                            summary.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            summary.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            summary.getWindow().setGravity(Gravity.BOTTOM);
                            summary.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                if (!tegeaModeArrayList.isEmpty()) {
                                    tegeaModeArrayList.clear();
                                    tegeaAda.notifyDataSetChanged();
                                }
                                summary.cancel();
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            AlertDialog.Builder mouse = new AlertDialog.Builder(stuHome);
                            mouse.setTitle(Html.fromHtml("<font color='#950365'><b><strong>Notifications</strong></b></font>"));
                            mouse.setIcon(R.drawable.club);
                            rect = new Rect();
                            window = stuHome.getWindow();
                            window.getDecorView().getWindowVisibleDisplayFrame(rect);
                            layoutInflater = (LayoutInflater) stuHome.getSystemService(LAYOUT_INFLATER_SERVICE);
                            View legged = layoutInflater.inflate(R.layout.notices, null);
                            listNof = legged.findViewById(R.id.listClub);
                            listNof.setTextFilterEnabled(true);
                            searchNof = legged.findViewById(R.id.searchClucb);
                            termmo = legged.findViewById(R.id.txtClub);
                            listNof.setVisibility(View.GONE);
                            searchNof.setVisibility(View.GONE);
                            termmo.setText(msg + "\nwas Found.");
                            termmo.setVisibility(View.VISIBLE);
                            legged.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                            frameLayout = new FrameLayout(stuHome);
                            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                            legged.setLayoutParams(params);
                            frameLayout.addView(legged);
                            mouse.setView(frameLayout);
                            mouse.setNegativeButton("Cancel", (dialogInterface, it) -> {
                            });
                            AlertDialog summary = mouse.create();
                            summary.show();
                            summary.setCancelable(false);
                            summary.setCanceledOnTouchOutside(false);
                            summary.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            summary.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            summary.getWindow().setGravity(Gravity.BOTTOM);
                            summary.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                summary.cancel();
                            });
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(stuHome, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            Toast.makeText(stuHome, "Failed to connect", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("user", model.getStud_id());
                return para;
            }
        });
    }
}