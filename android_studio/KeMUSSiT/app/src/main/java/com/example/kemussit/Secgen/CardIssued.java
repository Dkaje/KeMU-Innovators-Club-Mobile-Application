package com.example.kemussit.Secgen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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
import com.bumptech.glide.Glide;
import com.example.kemussit.Kitendawili.BomoaAda;
import com.example.kemussit.Kitendawili.BomoaMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.IssuedMode;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardIssued extends AppCompatActivity {
    BomoaAda bomoaAda;
    BomoaMode bomoaMode;
    ArrayList<BomoaMode> bomoaModeArrayList = new ArrayList<>();
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
    ArrayList<IssuedMode> issuedModeArrayList = new ArrayList<>();
    IssuedMode issuedMode;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Issued Cards");
        setContentView(R.layout.activity_card_issued);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        findViewById(R.id.btnIssued).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ExpiredCard.class));
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            bomoaMode = (BomoaMode) adapterView.getItemAtPosition(i);
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.getCurr,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int ii = 0; ii < jsonArray.length(); ii++) {
                                    jsonObject = jsonArray.getJSONObject(ii);
                                    onceMode = new OnceMode(jsonObject.getString("id"), jsonObject.getString("term"),
                                            jsonObject.getString("year"), jsonObject.getString("report"),
                                            jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                            jsonObject.getString("ended"), jsonObject.getString("ending"),
                                            jsonObject.getString("end_date"));
                                    onceModeArrayList.add(onceMode);
                                }
                                builder = new AlertDialog.Builder(this);
                                builder.setTitle("Card Holder Details");
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.holders, null);
                                circleImageView = viewer.findViewById(R.id.confirmed);
                                imageView = viewer.findViewById(R.id.mySign);
                                basics = viewer.findViewById(R.id.myBasic);
                                card = viewer.findViewById(R.id.myCard);
                                session = viewer.findViewById(R.id.mySession);
                                Glide.with(this).load(bomoaMode.getProfile()).into(circleImageView);
                                Glide.with(this).load(bomoaMode.getSignature()).into(imageView);
                                circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.maker));
                                basics.setText(bomoaMode.getReg_no() + "\n" + bomoaMode.getFullname() + "\n" + bomoaMode.getDepartment() + "\n" + bomoaMode.getClassification() + "\n" + bomoaMode.getPhone() + "\n" + bomoaMode.getEmail());
                                card.setText(bomoaMode.getCategory() + "\nfinanceStatus: " + bomoaMode.getFinsta() + "\nissuance: " + bomoaMode.getSecsta() + "\nexpiry: " + bomoaMode.getLost());
                                session.setText(onceMode.getTerm() + " " + onceMode.getYear() + "\nstatus: " + onceMode.getEnded() + "\nendDate: " + onceMode.getEnding());
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                builder.setView(frameLayout);
                                builder.setPositiveButton("Print_Card", (dialogInterface, ii) -> {
                                });
                                builder.setNegativeButton("Quit", (dialogInterface, ii) -> {
                                });
                                alertDialog = builder.create();
                                alertDialog.show();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.elimanated,
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
                                                        factor.setTitle("Issued Card");
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
                                                    } else if (succe == 0) {
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
                                            para.put("idi", bomoaMode.getIdi());
                                            return para;
                                        }
                                    });
                                });
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                    alertDialog.cancel();
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
                    para.put("ses", bomoaMode.getSes());
                    return para;
                }
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.poleNishasa,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                bomoaMode = new BomoaMode(jsonObject.getString("idi"), jsonObject.getString("ses"), jsonObject.getString("ended"),
                                        jsonObject.getString("pay"), jsonObject.getString("deter"), jsonObject.getString("category"), jsonObject.getString("reg_no"), jsonObject.getString("fullname"),
                                        jsonObject.getString("phone"), Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                        jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("email"),
                                        jsonObject.getString("finsta"), jsonObject.getString("secsta"), jsonObject.getString("lost"), jsonObject.getString("date"));
                                bomoaModeArrayList.add(bomoaMode);
                            }
                            bomoaAda = new BomoaAda(CardIssued.this, R.layout.sipangwi, bomoaModeArrayList);
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
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("finsta", "1");
                para.put("secsta", "1");
                return para;
            }
        });
    }
}