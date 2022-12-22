package com.example.kemussit.Stud;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.kemussit.Kitendawili.LastDay;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.QuickMate;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.Kitendawili.ViewRegistry;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PastEventPa extends AppCompatActivity {
    QuickMate cardAda;
    LastDay cardMode;
    ArrayList<LastDay> cardModeArrayList = new ArrayList<>();
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
    Model model;
    StuSess stuSess;
    ViewRegistry viewRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Past Event Payments");
        setContentView(R.layout.activity_past_contribut);
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (LastDay) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("EventPay Details");
            builder.setMessage("PAYER\nregNO: " + cardMode.getReg_no() + "\nname: " + cardMode.getFullname() + "\nphone: " + cardMode.getPhone() + "\n\nSESSION\n" + cardMode.getTerm() + "\n\nFEES\ncardCharge Kes " + cardMode.getMoney() + "\n\nSTATUS\npayDate: " + cardMode.getDate() + "\napproval: " + cardMode.getStatus());
            builder.setPositiveButton("Quit", (dialogInterface, i1) -> {
            });
            builder.setNeutralButton("More", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.viewSt,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i1 = 0; i1 < jsonArray.length(); i1++) {
                                        jsonObject = jsonArray.getJSONObject(i1);
                                        viewRegistry = new ViewRegistry(jsonObject.getString("sm"), jsonObject.getString("evt"), jsonObject.getString("ses"),
                                                jsonObject.getString("term"), jsonObject.getString("theme"), jsonObject.getString("venue"), jsonObject.getString("land"),
                                                jsonObject.getString("site"), jsonObject.getString("date"), jsonObject.getString("time"), jsonObject.getString("money"),
                                                jsonObject.getString("reg_no"), jsonObject.getString("fullname"), jsonObject.getString("phone"),
                                                jsonObject.getString("status"), jsonObject.getString("comment"), jsonObject.getString("pay"), jsonObject.getString("approval"),
                                                jsonObject.getString("org"), jsonObject.getString("org_no"), jsonObject.getString("org_phone"),
                                                jsonObject.getString("org_name"), jsonObject.getString("club"), jsonObject.getString("pat"), jsonObject.getString("closure"),
                                                jsonObject.getString("entry_date"));
                                    }
                                    AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                    mouse.setTitle("User Details\nRegDate: " + viewRegistry.getEntry_date());
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    viewer = layoutInflater.inflate(R.layout.night, null);
                                    majorGenrali = viewer.findViewById(R.id.hinter);
                                    termedMem = viewer.findViewById(R.id.txtDetails);
                                    termedMem.setText(Html.fromHtml("<font><b><strong>MEMBER</strong></b><br><b>regNO</b>: " + viewRegistry.getReg_no() + "<br><b><strong>name</strong></b>: " + viewRegistry.getFullname() + "<br><b><strong>Phone</strong></b>: " + viewRegistry.getPhone() + "<br><br><b><strong><big>EVENT</big></strong></b><br><b>Venue</b>: " + viewRegistry.getSite() + " " + viewRegistry.getLand() + " " + viewRegistry.getVenue() + "<br><b>status</b>: " + viewRegistry.getPay() + "<br><br><b>EventPay</b>: KES" + viewRegistry.getMoney() + "</font>"));
                                    majorGenrali.setText("THEME: " + viewRegistry.getTheme() + "\nTERM: " + viewRegistry.getTerm());
                                    viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                    frameLayout = new FrameLayout(this);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                    viewer.setLayoutParams(params);
                                    frameLayout.addView(viewer);
                                    mouse.setView(frameLayout);
                                    mouse.setNegativeButton("Cancel", (dialogInterface, iu) -> {
                                    });
                                    AlertDialog key = mouse.create();
                                    key.show();
                                    key.setCancelable(false);
                                    key.setCanceledOnTouchOutside(false);
                                    key.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    key.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view3 -> {
                                        key.cancel();
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
                        para.put("sm", cardMode.getSm());
                        return para;
                    }
                });
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.viewMeme,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new LastDay(jsonObject.getString("pay"), jsonObject.getString("ses"), jsonObject.getString("term"),
                                        jsonObject.getString("sm"), jsonObject.getString("mpesa"), jsonObject.getString("money"), jsonObject.getString("reg_no"),
                                        jsonObject.getString("fullname"), jsonObject.getString("phone"), jsonObject.getString("status"),
                                        jsonObject.getString("expiry"), jsonObject.getString("remarks"), jsonObject.getString("date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new QuickMate(PastEventPa.this, R.layout.roller, cardModeArrayList);
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
                para.put("reg_no", model.getStud_id());
                return para;
            }
        });
    }
}