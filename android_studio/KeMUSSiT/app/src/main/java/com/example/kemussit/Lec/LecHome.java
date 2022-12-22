package com.example.kemussit.Lec;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.LecSess;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceAda;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.MainActivity;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class LecHome extends AppCompatActivity {
    Model model;
    LecSess stuSess;
    RelativeLayout relativeLayout;
    CardView cardView;
    Button profi, logger, btn, helper;
    OnceAda onceAda;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stuSess = new LecSess(getApplicationContext());
        model = stuSess.getUser();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Welcome " + model.getFname());
        setContentView(R.layout.activity_lec_home);
        relativeLayout = findViewById(R.id.Relative);
        cardView = findViewById(R.id.cardy);
        profi = findViewById(R.id.btnProf);
        helper = findViewById(R.id.btnHelp);
        logger = findViewById(R.id.btnOut);
        btn = findViewById(R.id.btnActive);

        profi.setOnClickListener(view -> {
            cardView.setVisibility(View.GONE);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Profile");
            builder.setMessage("Serial: " + model.getStud_id() + "\nFirstname: " + model.getFname() + "\nLastname: " + model.getLname() + "\nEmail: " + model.getEmail() + "\nPhone: " + model.getPhone() + "\nDepartment: " + model.getDepartment() + "\nDate: " + model.getDate());
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
            help.setTitle("Help Lecturer");
            help.setMessage(getString(R.string.Lec));
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
            startActivity(new Intent(getApplicationContext(), ManageSession.class));
        });
        findViewById(R.id.btnCrs).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ManageCourses.class));
        });
        getActive();
    }

    private void getActive() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.getAc,
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
                            btn.setText("Active " + onceMode.getTerm() + " " + onceMode.getYear());
                            btn.setOnClickListener(view -> {
                                alert = new AlertDialog.Builder(this);
                                alert.setTitle(Html.fromHtml("<font color='#ff0000'><b>Active Session</b></font>"));
                                alert.setMessage("Session: " + onceMode.getTerm() + " " + onceMode.getYear() + "\n\nsessionStart: " + onceMode.getReport() + "\nsessionEnd: " + onceMode.getEnding() + "\n\nStatus: " + onceMode.getEnded());
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
                                });
                            });
                        } else if (succe == 0) {
                            String msgg = jsonObject.getString("mine");
                            btn.setText(msgg);
                            Toast.makeText(this, msgg, Toast.LENGTH_SHORT).show();
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