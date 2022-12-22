package com.example.kemussit.Orggen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.example.kemussit.Kitendawili.ClubAda;
import com.example.kemussit.Kitendawili.ClubFC;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SmartClubs extends AppCompatActivity {
    TextView termmo;
    ClubAda clubAda;
    ClubFC clubFC;
    ArrayList<ClubFC> clubFCArrayList = new ArrayList<>();
    EditText subjecting;
    TextView textv;
    RelativeLayout relative;
    ListView listView;
    SearchView searchView;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn, sender;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Manage Clubs");
        setContentView(R.layout.activity_smart_clubs);
        listView = findViewById(R.id.listClub);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.searchClucb);
        termmo = findViewById(R.id.txtClub);
        findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
        getClub();
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            clubFC = (ClubFC) adapterView.getItemAtPosition(i);
            AlertDialog.Builder mouse = new AlertDialog.Builder(this);
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View legged = layoutInflater.inflate(R.layout.new_club, null);
            subjecting = legged.findViewById(R.id.mySub);
            subjecting.setText(clubFC.getClub_name());
            textv = legged.findViewById(R.id.kaleshe);
            relative = legged.findViewById(R.id.Mkuu);
            subjecting.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = subjecting.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textv.setText(String.valueOf(30 - (subjecting.getText().toString().length())));
                        relative.setVisibility(View.VISIBLE);
                    } else {
                        textv.setText("30");
                        relative.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            legged.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            legged.setLayoutParams(params);
            frameLayout.addView(legged);
            mouse.setView(frameLayout);
            mouse.setPositiveButton("Edit", (dialogInterface, it) -> {
            });
            mouse.setNeutralButton("Delete", (dialogInterface, it) -> {
            });
            mouse.setNegativeButton("Cancel", (dialogInterface, it) -> {
            });
            AlertDialog summary = mouse.create();
            summary.show();
            summary.setCancelable(false);
            summary.setCanceledOnTouchOutside(false);
            summary.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            summary.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            summary.getWindow().setGravity(Gravity.BOTTOM);
            summary.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                final String mSub = subjecting.getText().toString().trim();
                if (mSub.isEmpty()) {
                    subjecting.setError("Why empty???");
                    subjecting.requestFocus();
                } else if (mSub.equals(clubFC.getClub_name())) {
                    subjecting.setError("Nothing Edited");
                    subjecting.requestFocus();
                } else {
                    AlertDialog.Builder nemis = new AlertDialog.Builder(this);
                    nemis.setTitle("Confirm Edition!");
                    nemis.setMessage("You are just about to change name of the Club from\n" + clubFC.getClub_name() + "\nto\n" + mSub + "\nDo you agree??");
                    nemis.setPositiveButton("Yes_Agreed!", (dialogInterface, iu) -> {
                    });
                    nemis.setNegativeButton("Quit", (dialogInterface, iu) -> {
                    });
                    AlertDialog keybo = nemis.create();
                    keybo.show();
                    keybo.setCancelable(false);
                    keybo.setCanceledOnTouchOutside(false);
                    keybo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    keybo.getWindow().setGravity(Gravity.CENTER);
                    keybo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view8 -> {
                        keybo.cancel();
                    });
                    keybo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view8 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.editClu,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                        if (st == 1) {
                                            startActivity(new Intent(getApplicationContext(), SmartClubs.class));
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
                                para.put("id", clubFC.getId());
                                para.put("club_name", mSub);
                                para.put("variance", "7");
                                return para;
                            }
                        });
                    });
                }
            });
            summary.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                summary.cancel();
            });
            summary.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view4 -> {
                AlertDialog.Builder nemis = new AlertDialog.Builder(this);
                nemis.setTitle("Confirm Deletion!");
                nemis.setMessage("You are just about to permanently delete the Club\n" + clubFC.getClub_name() + "\nDo you agree??");
                nemis.setPositiveButton("Yes_Delete!", (dialogInterface, iu) -> {
                });
                nemis.setNegativeButton("Quit", (dialogInterface, iu) -> {
                });
                AlertDialog keybo = nemis.create();
                keybo.show();
                keybo.setCancelable(false);
                keybo.setCanceledOnTouchOutside(false);
                keybo.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                keybo.getWindow().setGravity(Gravity.CENTER);
                keybo.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view8 -> {
                    keybo.cancel();
                });
                keybo.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view8 -> {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.editClu,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), SmartClubs.class));
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
                            para.put("id", clubFC.getId());
                            para.put("club_name", "mSub");
                            para.put("variance", "3");
                            return para;
                        }
                    });
                });
            });
        });
    }

    private void getClub() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.findClu,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                clubFC = new ClubFC(jsonObject.getString("id"), jsonObject.getString("club_name"), jsonObject.getString("entry_date"));
                                clubFCArrayList.add(clubFC);
                            }
                            listView.setVisibility(View.VISIBLE);
                            searchView.setVisibility(View.VISIBLE);
                            clubAda = new ClubAda(SmartClubs.this, R.layout.roller, clubFCArrayList);
                            listView.setAdapter(clubAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    clubAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                        } else if (success == 0) {
                            String msg = jsonObject.getString("mine");
                            termmo.setText(msg + "\nwas Found.");
                            termmo.setVisibility(View.VISIBLE);
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