package com.example.kemussit.Secgen;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.example.kemussit.Kitendawili.BomoaAda;
import com.example.kemussit.Kitendawili.BomoaMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cards extends AppCompatActivity {
    BomoaAda bomoaAda;
    BomoaMode bomoaMode;
    ArrayList<BomoaMode> bomoaModeArrayList = new ArrayList<>();
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
    CircleImageView circleImageView;
    ImageView imageView;
    TextView basics, card, session, teller;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();
    Button issue, expiry;
    DatePickerDialog datePickerDialog;
    String dater, yea;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cards");
        setContentView(R.layout.activity_cards);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        findViewById(R.id.btnIssued).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), CardIssued.class));
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            bomoaMode = (BomoaMode) adapterView.getItemAtPosition(i);
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.getCurr,
                    response -> {
                        try {
                            jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("trust");
                            if (success == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int ii = 0; ii < jsonArray.length(); ii++) {
                                    jsonObject = jsonArray.getJSONObject(ii);
                                    onceMode = new OnceMode(jsonObject.getString("id"), jsonObject.getString("term"),
                                            jsonObject.getString("year"), jsonObject.getString("report"),
                                            jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                            jsonObject.getString("ended"), jsonObject.getString("ending"),
                                            jsonObject.getString("end_date"));
                                    onceModeArrayList.add(onceMode);
                                }
                                builder = new AlertDialog.Builder(this);
                                if (bomoaMode.getDeter().equals("U")) {
                                    builder.setTitle(Html.fromHtml("<font color='#ff0000'><b><big><strong>Card Upgrade</big></strong></b></font>"));
                                    builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b><strong>Upgrade</strong></b></font>"), (dialogInterface, ii) -> {
                                    });
                                } else if (bomoaMode.getDeter().equals("R")) {
                                    builder.setTitle(Html.fromHtml("<font color='#ff0000'><b><big><strong>Card Renewal</big></strong></b></font>"));
                                    builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b><strong>Renew</strong></b></font>"), (dialogInterface, ii) -> {
                                    });
                                } else {
                                    builder.setTitle(Html.fromHtml("<font color='#ff0000'><b><big><strong>Fresh Card</big></strong></b></font>"));
                                    builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b><strong>Issue</strong></b></font>"), (dialogInterface, ii) -> {
                                    });
                                }
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.holders, null);
                                circleImageView = viewer.findViewById(R.id.confirmed);
                                imageView = viewer.findViewById(R.id.mySign);
                                basics = viewer.findViewById(R.id.myBasic);
                                card = viewer.findViewById(R.id.myCard);
                                session = viewer.findViewById(R.id.mySession);
                                Glide.with(this).load(bomoaMode.getProfile()).into(circleImageView);
                                Glide.with(this).load(bomoaMode.getSignature()).into(imageView);
                                circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.maker));
                                basics.setText(bomoaMode.getReg_no() + "\n" + bomoaMode.getFullname() + "\n" + bomoaMode.getDepartment() + "\n" + bomoaMode.getClassification() + "\n" + bomoaMode.getPhone() + "\n" + bomoaMode.getEmail());
                                card.setText(bomoaMode.getCategory() + "\nfinanceStatus: " + bomoaMode.getFinsta() + "\nissuance: " + bomoaMode.getSecsta());
                                session.setText(onceMode.getTerm() + " " + onceMode.getYear() + "\nstatus: " + onceMode.getEnded() + "\nendDate: " + onceMode.getEnding());
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                builder.setView(frameLayout);
                                builder.setNegativeButton("Quit", (dialogInterface, ii) -> {
                                });
                                alertDialog = builder.create();
                                alertDialog.show();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                if (bomoaMode.getDeter().equals("F")) {
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                        AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                        mouse.setTitle("Card Issuing");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View veve = layoutInflater.inflate(R.layout.issued, null);
                                        issue = veve.findViewById(R.id.myFee);
                                        expiry = veve.findViewById(R.id.myEnder);
                                        teller = veve.findViewById(R.id.mester);
                                        if (bomoaMode.getCategory().equals("FULL MEMBERSHIP")) {
                                            teller.setText("Renewed every Trimester");
                                            expiry.setText(onceMode.getEnding());
                                        } else {
                                            teller.setText("Renewed annually");
                                            issue.addTextChangedListener(new TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                }

                                                @Override
                                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                    String MIss = issue.getText().toString().trim();
                                                    if (!MIss.equals("Issue Date")) {
                                                        String summed = String.format("%.0f", (Float.parseFloat(yea) + 1));
                                                        expiry.setText(dater + "-" + summed);
                                                    }
                                                }

                                                @Override
                                                public void afterTextChanged(Editable editable) {
                                                }
                                            });

                                        }
                                        issue.setOnClickListener(this::openStartDate);
                                        initDatePicker();
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        veve.setLayoutParams(params);
                                        frameLayout.addView(veve);
                                        mouse.setView(frameLayout);
                                        mouse.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                        });
                                        mouse.setNegativeButton("Quit", (dialogInterface, ii) -> {
                                        });
                                        AlertDialog ala = mouse.create();
                                        ala.show();
                                        ala.setCancelable(false);
                                        ala.setCanceledOnTouchOutside(false);
                                        ala.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        ala.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                            final String memeDate = issue.getText().toString().trim();
                                            final String mmExp = expiry.getText().toString().trim();
                                            if (memeDate.equals("Issue Date")) {
                                                Toast.makeText(this, "Issue date not set", Toast.LENGTH_SHORT).show();
                                            } else {
                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, Connect.affected,
                                                        respon -> {
                                                            try {
                                                                jsonObject = new JSONObject(respon);
                                                                int st = jsonObject.getInt("success");
                                                                String msg = jsonObject.getString("message");
                                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                if (st == 1) {
                                                                    startActivity(new Intent(getApplicationContext(), Cards.class));
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
                                                        para.put("issue_date", memeDate);
                                                        para.put("expiry_date", mmExp);
                                                        para.put("reg_no", bomoaMode.getReg_no());
                                                        para.put("fullname", bomoaMode.getFullname());
                                                        para.put("category", bomoaMode.getCategory());
                                                        para.put("idi", bomoaMode.getIdi());
                                                        para.put("department", bomoaMode.getDepartment());
                                                        para.put("classification", bomoaMode.getClassification());
                                                        para.put("phone", bomoaMode.getPhone());
                                                        para.put("ses", bomoaMode.getSes());
                                                        para.put("secsta", "1");
                                                        para.put("alrt", "Your card was processed successfully.");
                                                        para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                                        return para;
                                                    }
                                                });
                                            }
                                        });
                                        ala.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                            ala.cancel();
                                        });
                                    });
                                } else if (bomoaMode.getDeter().equals("R")) {
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                        AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                        mouse.setTitle("Card Renewal");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View veve = layoutInflater.inflate(R.layout.issued, null);
                                        issue = veve.findViewById(R.id.myFee);
                                        expiry = veve.findViewById(R.id.myEnder);
                                        teller = veve.findViewById(R.id.mester);
                                        if (bomoaMode.getCategory().equals("TRIMESTER RENEWAL")) {
                                            teller.setText("Renewed every Trimester");
                                            expiry.setText(onceMode.getEnding());
                                        } else {
                                            teller.setText("Renewed annually");
                                            issue.addTextChangedListener(new TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                }

                                                @Override
                                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                    String MIss = issue.getText().toString().trim();
                                                    if (!MIss.equals("Issue Date")) {
                                                        String summed = String.format("%.0f", (Float.parseFloat(yea) + 1));
                                                        expiry.setText(dater + "-" + summed);
                                                    }
                                                }

                                                @Override
                                                public void afterTextChanged(Editable editable) {
                                                }
                                            });

                                        }
                                        issue.setOnClickListener(this::openStartDate);
                                        initDatePicker();
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        veve.setLayoutParams(params);
                                        frameLayout.addView(veve);
                                        mouse.setView(frameLayout);
                                        mouse.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                        });
                                        mouse.setNegativeButton("Quit", (dialogInterface, ii) -> {
                                        });
                                        AlertDialog ala = mouse.create();
                                        ala.show();
                                        ala.setCancelable(false);
                                        ala.setCanceledOnTouchOutside(false);
                                        ala.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        ala.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                            final String memeDate = issue.getText().toString().trim();
                                            final String mmExp = expiry.getText().toString().trim();
                                            if (memeDate.equals("Issue Date")) {
                                                Toast.makeText(this, "Issue date not set", Toast.LENGTH_SHORT).show();
                                            } else {
                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, Connect.cantAgree,
                                                        respon -> {
                                                            try {
                                                                jsonObject = new JSONObject(respon);
                                                                int st = jsonObject.getInt("success");
                                                                String msg = jsonObject.getString("message");
                                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                if (st == 1) {
                                                                    startActivity(new Intent(getApplicationContext(), Cards.class));
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
                                                        para.put("issue_date", memeDate);
                                                        para.put("expiry_date", mmExp);
                                                        para.put("reg_no", bomoaMode.getReg_no());
                                                        para.put("idi", bomoaMode.getIdi());
                                                        para.put("ses", bomoaMode.getSes());
                                                        para.put("secsta", "1");
                                                        para.put("alrt", "Your card renewal was processed successfully.");
                                                        para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                                        return para;
                                                    }
                                                });
                                            }
                                        });
                                        ala.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                            ala.cancel();
                                        });
                                    });
                                } else {
                                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                        AlertDialog.Builder mouse = new AlertDialog.Builder(this);
                                        mouse.setTitle("Card Upgrade");
                                        rect = new Rect();
                                        window = this.getWindow();
                                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                        layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                        View veve = layoutInflater.inflate(R.layout.issued, null);
                                        issue = veve.findViewById(R.id.myFee);
                                        expiry = veve.findViewById(R.id.myEnder);
                                        teller = veve.findViewById(R.id.mester);
                                        if (bomoaMode.getCategory().equals("FULL MEMBERSHIP")) {
                                            teller.setText("Renewed every Trimester");
                                            expiry.setText(onceMode.getEnding());
                                        } else {
                                            teller.setText("Renewed annually");
                                            issue.addTextChangedListener(new TextWatcher() {
                                                @Override
                                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                }

                                                @Override
                                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                    String MIss = issue.getText().toString().trim();
                                                    if (!MIss.equals("Issue Date")) {
                                                        String summed = String.format("%.0f", (Float.parseFloat(yea) + 1));
                                                        expiry.setText(dater + "-" + summed);
                                                    }
                                                }

                                                @Override
                                                public void afterTextChanged(Editable editable) {
                                                }
                                            });

                                        }
                                        issue.setOnClickListener(this::openStartDate);
                                        initDatePicker();
                                        frameLayout = new FrameLayout(this);
                                        params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                        veve.setLayoutParams(params);
                                        frameLayout.addView(veve);
                                        mouse.setView(frameLayout);
                                        mouse.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                        });
                                        mouse.setNegativeButton("Quit", (dialogInterface, ii) -> {
                                        });
                                        AlertDialog ala = mouse.create();
                                        ala.show();
                                        ala.setCancelable(false);
                                        ala.setCanceledOnTouchOutside(false);
                                        ala.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        ala.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                            final String memeDate = issue.getText().toString().trim();
                                            final String mmExp = expiry.getText().toString().trim();
                                            if (memeDate.equals("Issue Date")) {
                                                Toast.makeText(this, "Issue date not set", Toast.LENGTH_SHORT).show();
                                            } else {
                                                requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                requestQueue.add(new StringRequest(Request.Method.POST, Connect.upgraded,
                                                        respon -> {
                                                            try {
                                                                jsonObject = new JSONObject(respon);
                                                                int st = jsonObject.getInt("success");
                                                                String msg = jsonObject.getString("message");
                                                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                if (st == 1) {
                                                                    startActivity(new Intent(getApplicationContext(), Cards.class));
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
                                                        para.put("category", bomoaMode.getCategory());
                                                        para.put("issue_date", memeDate);
                                                        para.put("expiry_date", mmExp);
                                                        para.put("reg_no", bomoaMode.getReg_no());
                                                        para.put("idi", bomoaMode.getIdi());
                                                        para.put("ses", bomoaMode.getSes());
                                                        para.put("secsta", "1");
                                                        para.put("alrt", "Your card renewal was processed successfully.");
                                                        para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                                        return para;
                                                    }
                                                });
                                            }
                                        });
                                        ala.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view5 -> {
                                            ala.cancel();
                                        });
                                    });
                                }
                                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view4 -> {
                                    alertDialog.cancel();
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
                    para.put("ses", bomoaMode.getSes());
                    return para;
                }
            });
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            issue.setText(date);
        };
        Calendar calendar = Calendar.getInstance();
        Calendar call = Calendar.getInstance();
        Calendar makupa = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = android.app.AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        makupa.add(Calendar.DAY_OF_MONTH, +0);
        call.add(Calendar.MONTH, +1);
        datePickerDialog.getDatePicker().setMaxDate(call.getTimeInMillis());
        datePickerDialog.getDatePicker().setMinDate(makupa.getTimeInMillis());
    }

    private String makeDateString(int day, int month, int year) {
        dater = day + "-" + getMonthFormat(month);
        yea = String.valueOf(year);
        return day + "-" + getMonthFormat(month) + "-" + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return "01";
        }
        if (month == 2) {
            return "02";
        }
        if (month == 3) {
            return "03";
        }
        if (month == 4) {
            return "04";
        }
        if (month == 5) {
            return "05";
        }
        if (month == 6) {
            return "06";
        }
        if (month == 7) {
            return "07";
        }
        if (month == 8) {
            return "08";
        }
        if (month == 9) {
            return "09";
        }
        if (month == 10) {
            return "10";
        }
        if (month == 11) {
            return "11";
        }
        if (month == 12) {
            return "12";
        }
        return "01";
    }

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.poleNishasa,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                bomoaMode = new BomoaMode(jsonObject.getString("idi"), jsonObject.getString("ses"), jsonObject.getString("ended"),
                                        jsonObject.getString("pay"), jsonObject.getString("deter"), jsonObject.getString("category"), jsonObject.getString("reg_no"), jsonObject.getString("fullname"),
                                        jsonObject.getString("phone"), Connect.img + jsonObject.getString("profile"), Connect.img + jsonObject.getString("signature"),
                                        jsonObject.getString("department"), jsonObject.getString("classification"), jsonObject.getString("email"),
                                        jsonObject.getString("finsta"), jsonObject.getString("secsta"), jsonObject.getString("lost"), jsonObject.getString("date"));
                                bomoaModeArrayList.add(bomoaMode);
                            }
                            bomoaAda = new BomoaAda(Cards.this, R.layout.sipangwi, bomoaModeArrayList);
                            listView.setAdapter(bomoaAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    bomoaAda.getFilter().filter(newText);
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
                para.put("finsta", "1");
                para.put("secsta", "0");
                return para;
            }
        });
    }
}