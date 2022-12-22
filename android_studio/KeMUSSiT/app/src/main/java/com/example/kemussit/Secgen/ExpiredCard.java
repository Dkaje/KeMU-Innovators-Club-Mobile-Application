package com.example.kemussit.Secgen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.ExeAda;
import com.example.kemussit.Kitendawili.ExpMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExpiredCard extends AppCompatActivity {
    ExeAda bomoaAda;
    ExpMode bomoaMode;
    ArrayList<ExpMode> bomoaModeArrayList = new ArrayList<>();
    ListView listView;
    SearchView searchView;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    CircleImageView circleImageView, circular;
    ImageView imageView, imager;
    TextView basics, card, session, issue, nocard, userd, typed;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Expired Cards");
        setContentView(R.layout.activity_expired_card);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            bomoaMode = (ExpMode) adapterView.getItemAtPosition(i);
            AlertDialog.Builder factor = new AlertDialog.Builder(this);
            factor.setTitle("Expired Card");
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
            Glide.with(this).load(bomoaMode.getProfile()).into(circular);
            Glide.with(this).load(bomoaMode.getSignature()).into(imager);
            typed = vv.findViewById(R.id.cardType);
            typed.setText(bomoaMode.getCategory());
            nocard.setText("CardNO: " + bomoaMode.getIss());
            userd.setText("Reg: " + bomoaMode.getReg_no() + "\nName: " + bomoaMode.getFullname() + "\nDep: " + bomoaMode.getDepartment());
            issue.setText("Issued_On: " + bomoaMode.getIssue_date() + "\nExpired_On: " + bomoaMode.getExpiry_date());
            factor.setView(vv);
            factor.setNegativeButton(Html.fromHtml("<font color='#ff0000'><b>This Card has Expired</b></font>"), (dialogInterface1, i1) -> {
            });
            AlertDialog ff = factor.create();
            ff.show();
            ff.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            ff.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                ff.cancel();
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.expt,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                bomoaMode = new ExpMode(jsonObject.getString("expi"), jsonObject.getString("iss"), jsonObject.getString("idi"),
                                        jsonObject.getString("ses"), jsonObject.getString("reg_no"), jsonObject.getString("fullname"),
                                        jsonObject.getString("phone"), Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                        jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("issue_date"),
                                        jsonObject.getString("expiry_date"), jsonObject.getString("category"), jsonObject.getString("entry_date"));
                                bomoaModeArrayList.add(bomoaMode);
                            }
                            bomoaAda = new ExeAda(ExpiredCard.this, R.layout.sipangwi, bomoaModeArrayList);
                            listView.setAdapter(bomoaAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    bomoaAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            btn.setVisibility(View.VISIBLE);
                            btn.setOnClickListener(view -> {
                                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                printManager.print(getString(R.string.app_name), new PrintF(this, findViewById(R.id.Printer)), null);
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
}