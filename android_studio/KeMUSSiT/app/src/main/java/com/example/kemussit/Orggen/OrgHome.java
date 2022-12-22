package com.example.kemussit.Orggen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kemussit.Kitendawili.CheckMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.IssuedMode;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.OrgSess;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.SessionAda;
import com.example.kemussit.MainActivity;
import com.example.kemussit.R;
import com.example.kemussit.Stud.StuLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrgHome extends AppCompatActivity {
    Model model;
    OrgSess stuSess;
    RelativeLayout relativeLayout;
    CardView cardView;
    Button profi, logger, helper;
    ArrayList<CheckMode> checkModeArrayList = new ArrayList<>();
    CheckMode checkMode;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    AlertDialog.Builder builder, alert, letting, nextFigure;
    AlertDialog alertDialog, dialog, let, oyaNExt;
    Button btn, baite;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    CircleImageView circleImageView, circular;
    ImageView imageView, imager;
    TextView basics, card, typed, issue, nocard, userd;
    ArrayList<IssuedMode> issuedModeArrayList = new ArrayList<>();
    IssuedMode issuedMode;
    String mTerm, mId;
    TextView cardNo, issueDate, expiryDate;
    ArrayList<CheckMode> checkModeArrayList1 = new ArrayList<>();
    CheckMode checker;
    SessionAda sessionAda;
    ListView listView, lister, listSess;
    SearchView searchView, searchMan, seacrhSess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stuSess = new OrgSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + model.getFname());
        setContentView(R.layout.activity_org_home);
        relativeLayout = findViewById(R.id.Relative);
        cardView = findViewById(R.id.cardy);
        profi = findViewById(R.id.btnProf);
        helper = findViewById(R.id.btnHelp);
        logger = findViewById(R.id.btnOut);
        btn = findViewById(R.id.btnActive);
        baite = findViewById(R.id.btnGone);
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
                            getMajors();
                            btn.setText("Active " + checkMode.getTerm() + " " + checkMode.getYear());
                            mTerm = checkMode.getTerm() + " " + checkMode.getYear();
                            mId = checkMode.getSes();
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
                                                                                startActivity(new Intent(getApplicationContext(), OrgHome.class));
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

    private void getMajors() {
        getActive();
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
            help.setTitle("Help Organization Secretary");
            help.setMessage(getString(R.string.Org));
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
        findViewById(R.id.btnSmart).setOnClickListener(view -> {
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
                                    View vv = layoutInflater.inflate(R.layout.gif_test, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Renew/Upgrade", (dialogInterface1, i1) -> {
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
                                        AlertDialog.Builder mmm = new AlertDialog.Builder(this);
                                        mmm.setTitle(Html.fromHtml("<font color='#ff0000><b><big>Renew/Upgrade card</big></b></font>"));
                                        mmm.setMessage("Hi " + model.getFname() + ",\nYou will be expected to login as a student and manage you card.\n\nDo wish to go now??");
                                        mmm.setPositiveButton("Yes_Urgently!!", (dialogInterface, i) -> {
                                        });
                                        mmm.setNegativeButton("No_Later!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog demo = mmm.create();
                                        demo.show();
                                        demo.setCancelable(false);
                                        demo.setCanceledOnTouchOutside(false);
                                        demo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        demo.getWindow().setGravity(Gravity.TOP);
                                        demo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        demo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            stuSess.outUser();
                                            startActivity(new Intent(getApplicationContext(), StuLog.class));
                                            finish();
                                        });
                                        demo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            demo.cancel();
                                        });
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
                                    startActivity(new Intent(getApplicationContext(), ManageEvents.class).putExtra("term", mTerm).putExtra("ses", mId));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.gif_test, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Renew/Upgrade", (dialogInterface1, i1) -> {
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
                                        AlertDialog.Builder mmm = new AlertDialog.Builder(this);
                                        mmm.setTitle(Html.fromHtml("<font color='#ff0000><b><big>Renew/Upgrade card</big></b></font>"));
                                        mmm.setMessage("Hi " + model.getFname() + ",\nYou will be expected to login as a student and manage you card.\n\nDo wish to go now??");
                                        mmm.setPositiveButton("Yes_Urgently!!", (dialogInterface, i) -> {
                                        });
                                        mmm.setNegativeButton("No_Later!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog demo = mmm.create();
                                        demo.show();
                                        demo.setCancelable(false);
                                        demo.setCanceledOnTouchOutside(false);
                                        demo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        demo.getWindow().setGravity(Gravity.TOP);
                                        demo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        demo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            stuSess.outUser();
                                            startActivity(new Intent(getApplicationContext(), StuLog.class));
                                            finish();
                                        });
                                        demo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            demo.cancel();
                                        });
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
                                    startActivity(new Intent(getApplicationContext(), EventPart.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.gif_test, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Renew/Upgrade", (dialogInterface1, i1) -> {
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
                                        AlertDialog.Builder mmm = new AlertDialog.Builder(this);
                                        mmm.setTitle(Html.fromHtml("<font color='#ff0000><b><big>Renew/Upgrade card</big></b></font>"));
                                        mmm.setMessage("Hi " + model.getFname() + ",\nYou will be expected to login as a student and manage you card.\n\nDo wish to go now??");
                                        mmm.setPositiveButton("Yes_Urgently!!", (dialogInterface, i) -> {
                                        });
                                        mmm.setNegativeButton("No_Later!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog demo = mmm.create();
                                        demo.show();
                                        demo.setCancelable(false);
                                        demo.setCanceledOnTouchOutside(false);
                                        demo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        demo.getWindow().setGravity(Gravity.TOP);
                                        demo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        demo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            stuSess.outUser();
                                            startActivity(new Intent(getApplicationContext(), StuLog.class));
                                            finish();
                                        });
                                        demo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            demo.cancel();
                                        });
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
        findViewById(R.id.btnTrack).setOnClickListener(view -> {
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
                                    startActivity(new Intent(getApplicationContext(), ManageNew.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.gif_test, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Renew/Upgrade", (dialogInterface1, i1) -> {
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
                                        AlertDialog.Builder mmm = new AlertDialog.Builder(this);
                                        mmm.setTitle(Html.fromHtml("<font color='#ff0000><b><big>Renew/Upgrade card</big></b></font>"));
                                        mmm.setMessage("Hi " + model.getFname() + ",\nYou will be expected to login as a student and manage you card.\n\nDo wish to go now??");
                                        mmm.setPositiveButton("Yes_Urgently!!", (dialogInterface, i) -> {
                                        });
                                        mmm.setNegativeButton("No_Later!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog demo = mmm.create();
                                        demo.show();
                                        demo.setCancelable(false);
                                        demo.setCanceledOnTouchOutside(false);
                                        demo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        demo.getWindow().setGravity(Gravity.TOP);
                                        demo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        demo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            stuSess.outUser();
                                            startActivity(new Intent(getApplicationContext(), StuLog.class));
                                            finish();
                                        });
                                        demo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            demo.cancel();
                                        });
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
        findViewById(R.id.btnClub).setOnClickListener(view -> {
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
                                    startActivity(new Intent(getApplicationContext(), SmartClubs.class));
                                } else {
                                    AlertDialog.Builder factor = new AlertDialog.Builder(this);
                                    factor.setTitle(Html.fromHtml("<font color='#950365'><b><u>Expired Card</u></b></font>"));
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View vv = layoutInflater.inflate(R.layout.gif_test, null);
                                    cardNo = vv.findViewById(R.id.realCard);
                                    issueDate = vv.findViewById(R.id.realIssue);
                                    expiryDate = vv.findViewById(R.id.realExp);
                                    expiryDate.setText(issuedMode.getExpiry_date());
                                    cardNo.setText(issuedMode.getIss());
                                    issueDate.setText(issuedMode.getIssue_date());
                                    factor.setView(vv);
                                    factor.setNeutralButton("Renew/Upgrade", (dialogInterface1, i1) -> {
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
                                        AlertDialog.Builder mmm = new AlertDialog.Builder(this);
                                        mmm.setTitle(Html.fromHtml("<font color='#ff0000><b><big>Renew/Upgrade card</big></b></font>"));
                                        mmm.setMessage("Hi " + model.getFname() + ",\nYou will be expected to login as a student and manage you card.\n\nDo wish to go now??");
                                        mmm.setPositiveButton("Yes_Urgently!!", (dialogInterface, i) -> {
                                        });
                                        mmm.setNegativeButton("No_Later!!", (dialogInterface, i) -> {
                                        });
                                        AlertDialog demo = mmm.create();
                                        demo.show();
                                        demo.setCancelable(false);
                                        demo.setCanceledOnTouchOutside(false);
                                        demo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        demo.getWindow().setGravity(Gravity.TOP);
                                        demo.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        demo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                                            stuSess.outUser();
                                            startActivity(new Intent(getApplicationContext(), StuLog.class));
                                            finish();
                                        });
                                        demo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                                            demo.cancel();
                                        });
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
                                sessionAda = new SessionAda(OrgHome.this, R.layout.ndugu, checkModeArrayList1);
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
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.others, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Prof:
                if (cardView.getVisibility() == View.VISIBLE) {
                    cardView.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}