package com.example.kemussit.Orggen;

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
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.TakeOver;
import com.example.kemussit.Kitendawili.ViewRegistry;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OldageView extends AppCompatActivity {
    String myThme, myVenu, myClub;
    TakeOver cardAda;
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
            myClub = getIntent().getStringExtra("club");
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle(myClub + "-" + myVenu);
        setContentView(R.layout.activity_oldage_view);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (ViewRegistry) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("BASIC Details\nRegDate: " + cardMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.night, null);
            majorGenrali = viewer.findViewById(R.id.hinter);
            termedMem = viewer.findViewById(R.id.txtDetails);
            termedMem.setText(Html.fromHtml("<font><b><strong>MEMBER</strong></b><br><b>regNO</b>: " + cardMode.getReg_no() + "<br><b><strong>name</strong></b>: " + cardMode.getFullname() + "<br><b><strong>Phone</strong></b>: " + cardMode.getPhone() + "<br><br><b><strong>ORGANIZATION_SECRETARY</strong></b><br><b>regNO</b>: " + cardMode.getOrg_no() + "<br><b><strong>name</strong></b>: " + cardMode.getOrg_name() + "<br><b><strong>Phone</strong></b>: " + cardMode.getOrg_phone() + "<br><br><b><strong><big>EVENT</big></strong></b><br><b>CLUB :</b>" + cardMode.getClub() + "<br><b>Venue</b>: " + cardMode.getSite() + " " + cardMode.getLand() + " " + cardMode.getVenue() + "<br><b>financeStatus</b>: " + cardMode.getApproval() + "<br><b>patronStatus</b> :" + cardMode.getPat() + "</font>"));
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
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.status,
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
                            cardAda = new TakeOver(OldageView.this, R.layout.roller, cardModeArrayList);
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