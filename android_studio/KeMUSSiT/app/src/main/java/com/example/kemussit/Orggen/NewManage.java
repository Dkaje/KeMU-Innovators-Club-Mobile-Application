package com.example.kemussit.Orggen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Editable;
import android.text.Html;
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
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OrgSess;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.SawaAda;
import com.example.kemussit.Kitendawili.ViewRegistry;
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

public class NewManage extends AppCompatActivity {
    String myThme, myVenu;
    SawaAda cardAda;
    ViewRegistry cardMode;
    ArrayList<ViewRegistry> cardModeArrayList = new ArrayList<>();
    ListView listView, listed;
    SearchView searchView, searched;
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
    TextView majorGenrali, termedMem, termmo;
    ClubAda clubAda;
    ClubFC clubFC;
    ArrayList<ClubFC> clubFCArrayList = new ArrayList<>();
    EditText subjecting;
    TextView textv;
    RelativeLayout relative;
    Model model;
    OrgSess stuSess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            myThme = getIntent().getStringExtra("theme");
            myVenu = getIntent().getStringExtra("venue");
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle(myVenu + " Event");
        setContentView(R.layout.activity_new_manage);
        stuSess = new OrgSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        sender = findViewById(R.id.btnSend);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (ViewRegistry) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("User Details\nentryDate: " + cardMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.night, null);
            majorGenrali = viewer.findViewById(R.id.hinter);
            termedMem = viewer.findViewById(R.id.txtDetails);
            termedMem.setText(Html.fromHtml("<font><b><strong>MEMBER</strong></b><br><b>regNO</b>: " + cardMode.getReg_no() + "<br><b><strong>name</strong></b>: " + cardMode.getFullname() + "<br><b><strong>Phone</strong></b>: " + cardMode.getPhone() + "<br><br><b><strong><big>EVENT</big></strong></b><br><b>Venue</b>: " + cardMode.getSite() + " " + cardMode.getLand() + " " + cardMode.getVenue() + "<br><b>financeStatus</b>: " + cardMode.getApproval() + "</font>"));
            majorGenrali.setText("THEME: " + cardMode.getTheme() + "\nTERM: " + cardMode.getTerm());
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setNegativeButton("Cancel", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
        sender.setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Club Adjustment");
            builder.setMessage(Html.fromHtml("<font color='#000000'><b>Select Club or Add a new Club Below</b></font>"));
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.smart, null);
            listed = viewer.findViewById(R.id.listClub);
            searched = viewer.findViewById(R.id.searchClucb);
            termmo = viewer.findViewById(R.id.txtClub);
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
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
                                listed.setVisibility(View.VISIBLE);
                                searched.setVisibility(View.VISIBLE);
                                clubAda = new ClubAda(NewManage.this, R.layout.roller, clubFCArrayList);
                                listed.setAdapter(clubAda);
                                searched.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                                listed.setOnItemClickListener((adapterView, view1, i, l) -> {
                                    clubFC = (ClubFC) adapterView.getItemAtPosition(i);
                                    AlertDialog.Builder nemis = new AlertDialog.Builder(this);
                                    nemis.setTitle("Confirm Details!");
                                    nemis.setMessage("The Above List of members will be forwarded to the Patron.\n\nCLUB: " + clubFC.getClub_name());
                                    nemis.setPositiveButton("Yes_Forward!", (dialogInterface, iu) -> {
                                    });
                                    nemis.setNegativeButton("Cancel", (dialogInterface, iu) -> {
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
                                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.hello,
                                                respo -> {
                                                    try {
                                                        jsonObject = new JSONObject(respo);
                                                        int sto = jsonObject.getInt("success");
                                                        String msig = jsonObject.getString("message");
                                                        Toast.makeText(this, msig, Toast.LENGTH_LONG).show();
                                                        if (sto == 1) {
                                                            startActivity(new Intent(getApplicationContext(), ManageNew.class));
                                                            finish();
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }, error -> {
                                            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
                                        }) {
                                            @Nullable
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> para = new HashMap<>();
                                                para.put("theme", myThme);
                                                para.put("venue", myVenu);
                                                para.put("org_no", model.getStud_id());
                                                para.put("org_name", model.getFname() + " " + model.getLname());
                                                para.put("org_phone", model.getPhone());
                                                para.put("deter", "3");
                                                para.put("club_name", clubFC.getClub_name());
                                                para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ssa").format(new Date()));
                                                para.put("alrt", "You were a member of the " + clubFC.getClub_name() + " for the event themed " + myThme + ". Venue " + myVenu);
                                                return para;
                                            }
                                        });
                                    });
                                });
                            } else if (success == 0) {
                                String msg = jsonObject.getString("mine");
                                termmo.setText(msg + "\nwas Found.\nClick New Club Below.");
                                termmo.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }));
            builder.setView(frameLayout);
            builder.setPositiveButton("Cancel", (dialogInterface, iu) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'><b><u>New_Club</b></font>"), (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                if (!clubFCArrayList.isEmpty()) {
                    clubFCArrayList.clear();
                    clubAda.notifyDataSetChanged();
                }
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                rect = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View legged = layoutInflater.inflate(R.layout.new_club, null);
                subjecting = legged.findViewById(R.id.mySub);
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
                mouse.setPositiveButton("Next", (dialogInterface, i) -> {
                });
                mouse.setNegativeButton("Cancel", (dialogInterface, i) -> {
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
                    } else {
                        AlertDialog.Builder nemis = new AlertDialog.Builder(this);
                        nemis.setTitle("Confirm Details!");
                        nemis.setMessage("The Above List of members will be forwarded to the Patron.\n\nCLUB: " + mSub);
                        nemis.setPositiveButton("Yes_Forward!", (dialogInterface, iu) -> {
                        });
                        nemis.setNegativeButton("Cancel", (dialogInterface, iu) -> {
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
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.hello,
                                    response -> {
                                        try {
                                            jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                            if (st == 1) {
                                                startActivity(new Intent(getApplicationContext(), ManageNew.class));
                                                finish();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }, error -> {
                                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> para = new HashMap<>();
                                    para.put("theme", myThme);
                                    para.put("venue", myVenu);
                                    para.put("org_no", model.getStud_id());
                                    para.put("org_name", model.getFname() + " " + model.getLname());
                                    para.put("org_phone", model.getPhone());
                                    para.put("deter", "4");
                                    para.put("club_name", mSub);
                                    para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ssa").format(new Date()));
                                    para.put("alrt", "You were a member of the " + mSub + " for the event themed " + myThme + ". Venue " + myVenu);
                                    return para;
                                }
                            });
                        });
                    }
                });
                summary.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                    summary.cancel();
                });
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.kubaya,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new ViewRegistry(jsonObject.getString("sm"), jsonObject.getString("evt"), jsonObject.getString("ses"),
                                        jsonObject.getString("term"), jsonObject.getString("theme"), jsonObject.getString("venue"), jsonObject.getString("land"),
                                        jsonObject.getString("site"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("money"),
                                        jsonObject.getString("reg_no"), jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                        jsonObject.getString("status"), jsonObject.getString("comment"), jsonObject.getString("pay"), jsonObject.getString("approval"),
                                        jsonObject.getString("org"), jsonObject.getString("org_no"), jsonObject.getString("org_phone"),
                                        jsonObject.getString("org_name"), jsonObject.getString("club"), jsonObject.getString("pat"), jsonObject.getString("closure"),
                                        jsonObject.getString("entry_date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new SawaAda(NewManage.this, R.layout.roller, cardModeArrayList);
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
                            btn.setVisibility(View.VISIBLE);
                            btn.setOnClickListener(view -> {
                                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                printManager.print(getString(R.string.app_name), new PrintF(this, findViewById(R.id.Printer)), null);
                            });
                            sender.setVisibility(View.VISIBLE);
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
                para.put("theme", myThme);
                para.put("venue", myVenu);
                return para;
            }
        });
    }
}