package com.example.kemussit.Secgen;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.CardAda;
import com.example.kemussit.Kitendawili.CardMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.PrintF;
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

public class Smarta extends AppCompatActivity {
    CardAda cardAda;
    CardMode cardMode;
    ArrayList<CardMode> cardModeArrayList = new ArrayList<>();
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
    EditText fees, descrip;
    Spinner spinner;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SmartCard");
        setContentView(R.layout.activity_smarta);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        button = findViewById(R.id.btnPrint);
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Add SmartCard");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.wakati, null);
            spinner = viewer.findViewById(R.id.mySpin);
            fees = viewer.findViewById(R.id.myFee);
            descrip = viewer.findViewById(R.id.myDesc);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Card, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String mySpi = spinner.getSelectedItem().toString().trim();
                    if (mySpi.equals("Select Category")) {
                        fees.setText("");
                        descrip.setText("");
                    } else if (mySpi.equals("TRIMESTER RENEWAL")) {
                        fees.setText("50");
                        descrip.setText("FULL MEMBERSHIP card Renewal. There will be a renewal fee of Ksh. 50 per trimester (Ksh. 150 per annum)");
                    } else if (mySpi.equals("FULL MEMBERSHIP")) {
                        fees.setText("250");
                        descrip.setText("non-refundable registration fee of Ksh. 250. There will be a renewal fee of Ksh. 50 per trimester (Ksh. 150 per annum)");
                    } else if (mySpi.equals("ALUMNI MEMBERSHIP")) {
                        fees.setText("500");
                        descrip.setText("non-refundable registration fee of Ksh. 500. There will be an annual fee of Ksh. 500");
                    } else if (mySpi.equals("HONORARY MEMBERSHIP")) {
                        fees.setText("1000");
                        descrip.setText("non-refundable registration fee of Ksh. 1000. There will be an annual fee of Ksh. 1000");
                    } else if (mySpi.equals("ASSOCIATE MEMBERSHIP")) {
                        fees.setText("1000");
                        descrip.setText("non-refundable registration fee of Ksh. 1000. There will be an annual fee of Ksh. 1000");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Add", (dialogInterface, i) -> {
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String spin = spinner.getSelectedItem().toString().trim();
                if (spin.equals("Select Category")) {
                    Toast.makeText(this, "Please select Category", Toast.LENGTH_SHORT).show();
                } else {
                    final String mFee = fees.getText().toString().trim();
                    final String mDesc = descrip.getText().toString().trim();
                    if (mFee.isEmpty()) {
                        fees.setError("This field was not empty");
                        fees.requestFocus();
                    } else if (Float.parseFloat(mFee) == 0) {
                        fees.setError("Stop Joking");
                        fees.requestFocus();
                    } else if (mDesc.isEmpty()) {
                        descrip.setError("This field was not empty");
                        descrip.requestFocus();
                    } else {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.card,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            startActivity(new Intent(getApplicationContext(), Smarta.class));
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
                                para.put("category", spin);
                                para.put("fees", mFee);
                                para.put("description", mDesc);
                                para.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                return para;
                            }
                        });
                    }
                }
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (CardMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle(cardMode.getCategory() + " SmartCard");
            builder.setMessage("RegisteredOn: " + cardMode.getDate());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.wakati, null);
            spinner = viewer.findViewById(R.id.mySpin);
            fees = viewer.findViewById(R.id.myFee);
            descrip = viewer.findViewById(R.id.myDesc);
            spinner.setVisibility(View.GONE);
            fees.setText(cardMode.getFees());
            descrip.setText(cardMode.getDescription());
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Update", (dialogInterface, ii) -> {
            });
            builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'><b>DROP!</b></font>"), (dialogInterface, ii) -> {
            });
            builder.setNegativeButton("Cancel", (dialogInterface, ii) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String mFee = fees.getText().toString().trim();
                final String mDesc = descrip.getText().toString().trim();
                if (mFee.isEmpty()) {
                    fees.setError("This field was not empty");
                    fees.requestFocus();
                } else if (Float.parseFloat(mFee) == 0) {
                    fees.setError("Stop Joking");
                    fees.requestFocus();
                } else if (mDesc.isEmpty()) {
                    descrip.setError("This field was not empty");
                    descrip.requestFocus();
                } else {
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.editCa,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        startActivity(new Intent(getApplicationContext(), Smarta.class));
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
                            para.put("id", cardMode.getId());
                            para.put("fees", mFee);
                            para.put("description", mDesc);
                            return para;
                        }
                    });
                }
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.drp,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), Smarta.class));
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
                        para.put("id", cardMode.getId());
                        return para;
                    }
                });
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.court,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new CardMode(jsonObject.getString("id"), jsonObject.getString("category"), jsonObject.getString("fees"), jsonObject.getString("description"), jsonObject.getString("date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new CardAda(Smarta.this, R.layout.fees, cardModeArrayList);
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
                            button.setVisibility(View.VISIBLE);
                            button.setOnClickListener(view -> {
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