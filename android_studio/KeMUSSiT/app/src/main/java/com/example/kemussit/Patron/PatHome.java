package com.example.kemussit.Patron;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
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
import com.example.kemussit.Kitendawili.CheckMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.PatrSess;
import com.example.kemussit.Kitendawili.SessionAda;
import com.example.kemussit.MainActivity;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PatHome extends AppCompatActivity {
    Model model;
    PatrSess stuSess;
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
    ArrayList<CheckMode> checkModeArrayList1 = new ArrayList<>();
    CheckMode checker;
    SessionAda sessionAda;
    ListView listView, lister, listSess;
    SearchView searchView, searchMan, seacrhSess;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stuSess = new PatrSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + model.getFname());
        setContentView(R.layout.activity_pat_home);
        relativeLayout = findViewById(R.id.Relative);
        cardView = findViewById(R.id.cardy);
        profi = findViewById(R.id.btnProf);
        logger = findViewById(R.id.btnOut);
        helper=findViewById(R.id.btnHelp);
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
                                                                                startActivity(new Intent(getApplicationContext(), PatHome.class));
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
            builder.setMessage("Serial: " + model.getStud_id() + "\nFirstname: " + model.getFname() + "\nLastname: " + model.getLname() + "\nEmail: " + model.getEmail() + "\nPhone: " + model.getPhone() + "\nDate: " + model.getDate());
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
            help.setTitle("Help Patron");
            help.setMessage(getString(R.string.Pat));
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
        findViewById(R.id.btnParty).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), WaitingEvent.class));
        });
        findViewById(R.id.btnEvent).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), UpcomeClubs.class));
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
                                sessionAda = new SessionAda(PatHome.this, R.layout.ndugu, checkModeArrayList1);
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
        findViewById(R.id.btnFunded).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), FundedClubs.class));
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