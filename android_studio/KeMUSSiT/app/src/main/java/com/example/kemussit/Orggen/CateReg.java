package com.example.kemussit.Orggen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.ViewAda;
import com.example.kemussit.Kitendawili.ViewRegistry;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CateReg extends AppCompatActivity {
    String myThme, myVenu;
    ViewAda cardAda;
    ViewRegistry cardMode;
    ArrayList<ViewRegistry> cardModeArrayList = new ArrayList<>();
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
    TextView majorGenrali, termedMem, termmo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            myThme = getIntent().getStringExtra("theme");
            myVenu = getIntent().getStringExtra("venue");
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle(myVenu + " Event");
        setContentView(R.layout.activity_cate_reg);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (ViewRegistry) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("User Details\nRegDate: " + cardMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.night, null);
            majorGenrali = viewer.findViewById(R.id.hinter);
            termedMem = viewer.findViewById(R.id.txtDetails);
            termedMem.setText(Html.fromHtml("<font><b><strong>MEMBER</strong></b><br><b>regNO</b>: " + cardMode.getReg_no() + "<br><b><strong>name</strong></b>: " + cardMode.getFullname() + "<br><b><strong>Phone</strong></b>: " + cardMode.getPhone() + "<br><br><b><strong><big>EVENT</big></strong></b><br><b>Venue</b>: " + cardMode.getSite() + " " + cardMode.getLand() + " " + cardMode.getVenue() + "<br><b>status</b>: " + cardMode.getStatus() + "</font>"));
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
            builder.setNeutralButton("Reject", (dialogInterface, iu) -> {
            });
            builder.setPositiveButton("Approve", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder theme = new AlertDialog.Builder(this);
                theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Approval!!</b></font>"));
                theme.setMessage("You are just above to approve registration of:-\n\n" + cardMode.getReg_no() + "\n" + cardMode.getFullname() + "\n\nfor the " + cardMode.getSite() + "-" + cardMode.getLand() + "-" + cardMode.getVenue() + "\nevent. Do you wish to continue?");
                theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Yes_Approve!!</b>"), (dialogInterface, ii) -> {
                });
                theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                });
                AlertDialog why = theme.create();
                why.show();
                why.setCancelable(false);
                why.setCanceledOnTouchOutside(false);
                why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.updRe,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), EventPart.class));
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
                            para.put("status", "1");
                            para.put("sm", cardMode.getSm());
                            para.put("evt", cardMode.getEvt());
                            para.put("comment", "Your registration was approved.");
                            return para;
                        }
                    });
                });
                why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                    why.cancel();
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                AlertDialog.Builder mbogo = new AlertDialog.Builder(this);
                mbogo.setTitle("Type comment");
                EditText editText = new EditText(this);
                editText.setHint("Type some comments");
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                editText.setLayoutParams(params);
                frameLayout.addView(editText);
                mbogo.setView(frameLayout);
                mbogo.setPositiveButton("Submit", (dialogInterface, i1) -> {
                });
                mbogo.setNegativeButton("Back", (dialogInterface, i1) -> {
                });
                AlertDialog dedde = mbogo.create();
                dedde.show();
                dedde.setCancelable(false);
                dedde.setCanceledOnTouchOutside(false);
                dedde.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                dedde.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    final String mess = editText.getText().toString().trim();
                    if (mess.isEmpty()) {
                        editText.setError("Why do Reject???");
                        editText.requestFocus();
                    } else {
                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Rejection!!</b></font>"));
                        theme.setMessage("You are just above to reject registration of:-\n\n" + cardMode.getReg_no() + "\n" + cardMode.getFullname() + "\n\nfor the " + cardMode.getSite() + "-" + cardMode.getLand() + "-" + cardMode.getVenue() + "\nevent. Do you wish to continue?");
                        theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Yes_Reject!!</b>"), (dialogInterface, ii) -> {
                        });
                        theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                        });
                        AlertDialog why = theme.create();
                        why.show();
                        why.setCancelable(false);
                        why.setCanceledOnTouchOutside(false);
                        why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                        why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.updRe,
                                    response -> {
                                        try {
                                            jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                            if (st == 1) {
                                                startActivity(new Intent(getApplicationContext(), EventPart.class));
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
                                    para.put("status", "2");
                                    para.put("sm", cardMode.getSm());
                                    para.put("evt", cardMode.getEvt());
                                    para.put("comment", mess);
                                    return para;
                                }
                            });
                        });
                        why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view3 -> {
                            why.cancel();
                        });
                    }
                });
                dedde.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view2 -> {
                    dedde.cancel();
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.kubad,
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
                            cardAda = new ViewAda(CateReg.this, R.layout.roller, cardModeArrayList);
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