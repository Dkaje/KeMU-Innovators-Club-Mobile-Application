package com.example.kemussit.Stud;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.AdapterPig;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.StuSess;
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

public class EventPay extends AppCompatActivity {
    String myThme, myVenu;
    AdapterPig cardAda;
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
    Model model;
    StuSess stuSess;
    EditText mpesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Awaiting Payments");
        setContentView(R.layout.activity_contribution);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
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
            termedMem.setText(Html.fromHtml("<font><b><strong>MEMBER</strong></b><br><b>regNO</b>: " + cardMode.getReg_no() + "<br><b><strong>name</strong></b>: " + cardMode.getFullname() + "<br><b><strong>Phone</strong></b>: " + cardMode.getPhone() + "<br><br><b><strong><big>EVENT</big></strong></b><br><b>Venue</b>: " + cardMode.getSite() + " " + cardMode.getLand() + " " + cardMode.getVenue() + "<br><b>status</b>: " + cardMode.getStatus() + "<br><br><b>EventPay</b>: KES" + cardMode.getMoney() + "</font>"));
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
            builder.setPositiveButton("Make_Payment", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                AlertDialog.Builder kemu = new AlertDialog.Builder(this);
                kemu.setTitle("Event EventPay");
                kemu.setMessage("Hello " + model.getFname() + "\nEventPay Ksh. " + cardMode.getMoney() + "\n\nMPESA PayBill: 300112\nAcc. " + model.getStud_id() + "\n\nEnter MPESA code in the next screen.");
                kemu.setPositiveButton("Next", (dialogInterface, i1) -> {
                });
                kemu.setNegativeButton("Back", (dialogInterface, i1) -> {
                });
                AlertDialog kemuso = kemu.create();
                kemuso.show();
                kemuso.setCancelable(false);
                kemuso.setCanceledOnTouchOutside(false);
                kemuso.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                kemuso.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                    kemuso.cancel();
                });
                kemuso.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                    AlertDialog.Builder gateB = new AlertDialog.Builder(this);
                    gateB.setTitle("Payment");
                    rect = new Rect();
                    window = this.getWindow();
                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                    layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    viewer = layoutInflater.inflate(R.layout.payer, null);
                    mpesa = viewer.findViewById(R.id.Tranc);
                    frameLayout = new FrameLayout(this);
                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                    viewer.setLayoutParams(params);
                    frameLayout.addView(viewer);
                    gateB.setView(frameLayout);
                    mpesa.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            String mess = mpesa.getText().toString().trim();
                            if (!mess.isEmpty()) {
                                if (mess.length() < 10) {
                                    mpesa.setError("invalid");
                                    mpesa.requestFocus();
                                }
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                    gateB.setNegativeButton("Exit", (dialogInterface, ii) -> {
                    });
                    gateB.setPositiveButton("Submit", (dialogInterface, ii) -> {
                    });
                    AlertDialog gateA = gateB.create();
                    gateA.show();
                    gateA.setCancelable(false);
                    gateA.setCanceledOnTouchOutside(false);
                    gateA.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    gateA.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view6 -> {
                        gateA.cancel();
                    });
                    gateA.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view6 -> {
                        final String mPe = mpesa.getText().toString().trim();
                        if (mPe.isEmpty()) {
                            mpesa.setError("Transaction CODE");
                            mpesa.requestFocus();
                        } else if (mPe.length() < 10) {
                            mpesa.setError("not yet valid");
                            mpesa.requestFocus();
                        } else {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.newFloat,
                                    response -> {
                                        try {
                                            jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            if (st == 1) {
                                                startActivity(new Intent(getApplicationContext(), EventPay.class));
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
                                    para.put("term", cardMode.getTerm());
                                    para.put("pay", "1");
                                    para.put("ses", cardMode.getSes());
                                    para.put("sm", cardMode.getSm());
                                    para.put("money", cardMode.getMoney());
                                    para.put("mpesa", mPe);
                                    para.put("fullname", cardMode.getFullname());
                                    para.put("phone", cardMode.getPhone());
                                    para.put("reg_no", cardMode.getReg_no());
                                    para.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                    return para;
                                }
                            });
                        }
                    });
                });
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.salome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Memes:
                startActivity(new Intent(getApplicationContext(), PastEventPa.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.viep,
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
                            cardAda = new AdapterPig(EventPay.this, R.layout.roller, cardModeArrayList);
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
                // para.put("venue", myVenu);
                return para;
            }
        });
    }
}