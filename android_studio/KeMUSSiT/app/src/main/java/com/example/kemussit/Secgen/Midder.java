package com.example.kemussit.Secgen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.AdaMess;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.MesMode;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.R;
import com.example.kemussit.Stud.Feedback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Midder extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    EditText subjecting;
    TextView textv;
    RelativeLayout relative;
    String total,phone,namer;
    AdaMess adaMess;
    MesMode mesMode;
    ArrayList<AdaMess> mesModeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent()!=null){
            total=getIntent().getStringExtra("stud_id");
            phone=getIntent().getStringExtra("phone");
            namer=getIntent().getStringExtra("name");
        }
        setContentView(R.layout.activity_midder);
        Objects.requireNonNull(getSupportActionBar()).setTitle(namer+"\n "+phone);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        findViewById(R.id.btnFeed).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.typed_feed, null);
            subjecting = viewer.findViewById(R.id.mySub);
            textv = viewer.findViewById(R.id.kaleshe);
            relative = viewer.findViewById(R.id.Mkuu);
            subjecting.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = subjecting.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textv.setText(String.valueOf(100 - (subjecting.getText().toString().length())));
                        relative.setVisibility(View.VISIBLE);
                    } else {
                        textv.setText("100");
                        relative.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Send", (dialogInterface, i) -> {
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String mSub = subjecting.getText().toString().trim();
                if (mSub.isEmpty()) {
                    subjecting.setError("Why empty???");
                    subjecting.requestFocus();
                } else {
                    String mYr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ssa").format(new Date());
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Reply!!</b></font>"));
                    theme.setMessage("feedback: " + mSub + "\n\nsendDate: " + mYr);
                    theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Just_Reply!!</b>"), (dialogInterface, i) -> {
                    });
                    theme.setNeutralButton("Exit", (dialogInterface, i) -> {
                    });
                    AlertDialog why = theme.create();
                    why.show();
                    why.setCancelable(false);
                    why.setCanceledOnTouchOutside(false);
                    why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.tumaMEss,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            startActivity(new Intent(getApplicationContext(), ManageFeed.class));
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
                                para.put("stud_id", total);
                                para.put("phone", phone);
                                para.put("name", namer);
                                para.put("message", mSub);
                                para.put("move", "R");
                                para.put("date", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                                para.put("time", new SimpleDateFormat("hh:mm:ssa").format(new Date()));
                                para.put("current", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                return para;
                            }
                        });
                    });
                    why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                        why.cancel();
                    });
                }
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
        getCard();
    }
    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.mes,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int st = jsonObject.getInt("success");
                        if (st == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                adaMess = new AdaMess(jsonObject.getString("fd"), jsonObject.getString("stud_id"),
                                        jsonObject.getString("phone"), jsonObject.getString("name"),
                                        jsonObject.getString("message"), jsonObject.getString("move"), jsonObject.getString("date"), jsonObject.getString("time"),
                                        jsonObject.getString("current"));
                                mesModeArrayList.add(adaMess);
                            }

                            mesMode = new MesMode(Midder.this, R.layout.revenge, mesModeArrayList);
                            listView.setAdapter(mesMode);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String s) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String s) {
                                    mesMode.getFilter().filter(s);
                                    return false;
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(this, "Network", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("stud_id", total);
                return para;
            }
        });
    }
}