package com.example.kemussit.Patron;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
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
import com.example.kemussit.Kitendawili.ClotheAda;
import com.example.kemussit.Kitendawili.ClotheMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.PamperedMode;
import com.example.kemussit.Kitendawili.PatrSess;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FundedClubs extends AppCompatActivity {
    ClotheAda cardAda;
    ClotheMode cardMode;
    ArrayList<ClotheMode> cardModeArrayList = new ArrayList<>();
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
    TextView majorGenrali, termedMem;
    PamperedMode pamperedMode;
    ArrayList<PamperedMode> pamperedModeArrayList = new ArrayList<>();
    Model model;
    PatrSess stuSess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Funded Clubs");
        setContentView(R.layout.activity_funded_clubs);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        stuSess = new PatrSess(getApplicationContext());
        model = stuSess.getUser();
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (ClotheMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Amount Withdrawal\nRegDate: " + cardMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.night, null);
            majorGenrali = viewer.findViewById(R.id.hinter);
            termedMem = viewer.findViewById(R.id.txtDetails);
            String token = "1000";
            String pesa = String.format("%.0f", Float.parseFloat(cardMode.getSumm()) + Float.parseFloat(token));
            termedMem.setText(Html.fromHtml("<font><b><strong>CLUB</strong></b><br><b>name</b>: " + cardMode.getClub() + "<br><b><strong>members</strong></b>: " + cardMode.getMembers() + "<br><br><b><strong>PATRON</strong></b><br><b><strong>name</strong></b>: " + cardMode.getPat_name() + "<br><b><strong>phone</strong></b>: " + cardMode.getPat_phone() + "<br><br><b><strong>AMOUNT_To_WITHDRAW</strong></b><br><b>members</b>: " + cardMode.getMembers() + " <strong><b>x</b></strong> Kes<b>" + cardMode.getMoney() + "</b>=Kes<strong><b>" + cardMode.getSumm() + "</b></strong><br><b><strong><big>+</big></strong></b>&nbsp;&nbsp;patronToken: Kes<b><u><strong>" + token + "</strong></u></b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><strong>total</strong></b>:&nbsp;&nbsp;Kes<b><u><big><strong>" + pesa + "</big></strong><b></u><br><b><strong>fundDisbursement</strong></b>: " + cardMode.getFund() + "</font>"));
            majorGenrali.setText("THEME: " + cardMode.getTheme() + "\nTERM: " + cardMode.getTerm());
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setNegativeButton("Cancel", (dialogInterface, iu) -> {
            });
            if (cardMode.getFund().equals("Disbursed")) {
                builder.setPositiveButton("viewDisbursed", (dialogInterface, iu) -> {
                });
            }
            builder.setNeutralButton("Print", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getWindow().setGravity(Gravity.BOTTOM);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                viewer.findViewById(R.id.circle_center).clearAnimation();
                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.mumu)), null);
            });
            if (cardMode.getFund().equals("Disbursed")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.icanSee,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    Log.e("response ", response);
                                    int success = jsonObject.getInt("trust");
                                    if (success == 1) {
                                        jsonArray = jsonObject.getJSONArray("victory");
                                        for (int i1 = 0; i1 < jsonArray.length(); i1++) {
                                            jsonObject = jsonArray.getJSONObject(i1);
                                            pamperedMode = new PamperedMode(jsonObject.getString("pay"), jsonObject.getString("mv"), jsonObject.getString("ses"),
                                                    jsonObject.getString("term"), jsonObject.getString("mpesa"), jsonObject.getString("club_amt"), jsonObject.getString("utility_token"),
                                                    jsonObject.getString("amount"), jsonObject.getString("club"), jsonObject.getString("pat_id"), jsonObject.getString("pat_phone"),
                                                    jsonObject.getString("pat_name"), jsonObject.getString("closure"), jsonObject.getString("entry_date"));
                                            pamperedModeArrayList.add(pamperedMode);
                                        }
                                        AlertDialog.Builder nie = new AlertDialog.Builder(this);
                                        nie.setTitle("Disbursement Details\nRegDate: " + cardMode.getEntry_date());
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        viewer = layoutInflater.inflate(R.layout.night, null);
                                        majorGenrali = viewer.findViewById(R.id.hinter);
                                        termedMem = viewer.findViewById(R.id.txtDetails);
                                        termedMem.setText(Html.fromHtml("<font color='#000000'><big><b><strong>PATRON</strong></b></big><br><b><u><strong>regID</strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getPat_id() + "<br><b><u><strong>name</strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getPat_name() + "<br><b><u><strong>phone</strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getPat_phone() + "<br><b><u><strong>club</strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getClub() + "<br><br><b><big><strong>DISBURSEMENT</strong></big></b><br><b><u><strong>clubExpenditure</strong></u></b>&nbsp;&nbsp;:Kes" + pamperedMode.getClub_amt() + "<br><b><u><strong>patronToken</strong></u></b>&nbsp;&nbsp;:Kes" + pamperedMode.getUtility_token() + "<br><b><u><strong><big>TOTAL</big></strong></u></b>&nbsp;&nbsp;:Kes" + pamperedMode.getAmount() + "<br><b><u><strong><big>MPESA</big></strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getMpesa() + "<br><b><u><strong>entryDate</strong></u></b>&nbsp;&nbsp;:" + pamperedMode.getEntry_date() + "</font>"));
                                        majorGenrali.setText("THEME: " + cardMode.getTheme() + "\nTERM: " + cardMode.getTerm());
                                        viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                        viewer.setLayoutParams(params);
                                        frameLayout.addView(viewer);
                                        nie.setView(frameLayout);
                                        nie.setNegativeButton("Cancel", (dialogInterface, iu) -> {
                                        });
                                        nie.setNeutralButton("Print", (dialogInterface, iu) -> {
                                        });
                                        AlertDialog mouse = nie.create();
                                        mouse.show();
                                        mouse.setCancelable(false);
                                        mouse.setCanceledOnTouchOutside(false);
                                        mouse.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        mouse.getWindow().setGravity(Gravity.TOP);
                                        mouse.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        mouse.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view6 -> {
                                            mouse.cancel();
                                        });
                                        mouse.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view6 -> {
                                            viewer.findViewById(R.id.circle_center).clearAnimation();
                                            PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                                            printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.mumu)), null);
                                        });
                                    } else if (success == 0) {
                                        String msg = jsonObject.getString("mine");
                                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
                            para.put("mv", cardMode.getMv());
                            return para;
                        }
                    });
                });
            }
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.fundedOne,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new ClotheMode(jsonObject.getString("mv"), jsonObject.getString("evt"), jsonObject.getString("ses"), jsonObject.getString("term"),
                                        jsonObject.getString("theme"), jsonObject.getString("venue"), jsonObject.getString("date"), jsonObject.getString("club"),
                                        jsonObject.getString("members"), jsonObject.getString("money"), jsonObject.getString("summ"),
                                        jsonObject.getString("pat_id"), jsonObject.getString("pat_phone"), jsonObject.getString("pat_name"),
                                        jsonObject.getString("fund"), jsonObject.getString("closure"), jsonObject.getString("entry_date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new ClotheAda(FundedClubs.this, R.layout.roller, cardModeArrayList);
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
                para.put("pat_id", model.getStud_id());
                return para;
            }
        });
    }
}