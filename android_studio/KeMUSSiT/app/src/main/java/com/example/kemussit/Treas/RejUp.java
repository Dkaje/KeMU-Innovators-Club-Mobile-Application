package com.example.kemussit.Treas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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
import com.example.kemussit.Kitendawili.PayAda;
import com.example.kemussit.Kitendawili.PayMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RejUp extends AppCompatActivity {
    PayAda cardAda;
    PayMode cardMode;
    ArrayList<PayMode> cardModeArrayList = new ArrayList<>();
    ListView listView;
    SearchView searchView;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btn;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Rejected Upgrades");
        setContentView(R.layout.activity_rej_up);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (PayMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Payment Details");
            builder.setMessage("PAYER\nregNO: " + cardMode.getReg_no() + "\nname: " + cardMode.getFullname() + "\nphone: " + cardMode.getPhone() + "\n\nSESSION\n" + cardMode.getTerm() + " " + cardMode.getYear() + "\n\nFEES\ncardCharge Kes " + cardMode.getFees() + "\nmpesa: " + cardMode.getMpesa() + "\n\nSTATUS\npayDate: " + cardMode.getDate() + "\napproval: " + cardMode.getStatus());
            builder.setNeutralButton("Quit", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.gettt,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new PayMode(jsonObject.getString("pay"), jsonObject.getString("deter"), jsonObject.getString("ses"), jsonObject.getString("term"),
                                        jsonObject.getString("year"), jsonObject.getString("mpesa"), jsonObject.getString("fees"), jsonObject.getString("reg_no"),
                                        jsonObject.getString("fullname"), jsonObject.getString("phone"), jsonObject.getString("status"),
                                        jsonObject.getString("expiry"), jsonObject.getString("remarks"), jsonObject.getString("date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new PayAda(RejUp.this, R.layout.roller, cardModeArrayList);
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
                para.put("status", "2");
                return para;
            }
        });
    }
}