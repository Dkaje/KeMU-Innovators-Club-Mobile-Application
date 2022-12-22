package com.example.kemussit.Treas;

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
import com.example.kemussit.Kitendawili.ClotheAda;
import com.example.kemussit.Kitendawili.ClotheMode;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.MoneyMoney;
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

public class CustWithr extends AppCompatActivity {
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
    MoneyMoney moneyMoney;
    ArrayList<MoneyMoney> moneyMoneyArrayList = new ArrayList<>();
    EditText mpesa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Funds Withdrawal");
        setContentView(R.layout.activity_cust_withr);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (ClotheMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("To Withdraw\nRegDate: " + cardMode.getEntry_date());
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
            builder.setPositiveButton("Click_Next", (dialogInterface, iu) -> {
            });
            builder.setNeutralButton("Print", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                viewer.findViewById(R.id.circle_center).clearAnimation();
                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.mumu)), null);
            });
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.great,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                Log.e("response ", response);
                                int success = jsonObject.getInt("trust");
                                if (success == 1) {
                                    jsonArray = jsonObject.getJSONArray("victory");
                                    for (int i1 = 0; i1 < jsonArray.length(); i1++) {
                                        jsonObject = jsonArray.getJSONObject(i1);
                                        moneyMoney = new MoneyMoney(jsonObject.getString("fresh"), jsonObject.getString("renewal"), jsonObject.getString("upgrade"), jsonObject.getString("contribute"),jsonObject.getString("event_pay"),
                                                jsonObject.getString("withdraw"), jsonObject.getString("whole"), jsonObject.getString("bal"), jsonObject.getString("entry"),
                                                jsonObject.getString("last"));
                                        moneyMoneyArrayList.add(moneyMoney);
                                    }
                                    if (Float.parseFloat(pesa) > Float.parseFloat(moneyMoney.getBal())) {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                        alert.setTitle("Oops!!");
                                        alert.setMessage("currentBalance Kes" + moneyMoney.getBal() + "\n\nEstimatedWithdrawal Kes" + pesa + "\nWhat an insufficiency!!!");
                                        alert.setNeutralButton("Just_Leave", (dialogInterface, i1) -> {
                                        });
                                        AlertDialog dial = alert.create();
                                        dial.show();
                                        dial.setCancelable(false);
                                        dial.setCanceledOnTouchOutside(false);
                                        dial.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        dial.getWindow().setGravity(Gravity.TOP);
                                        dial.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        dial.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view7 -> {
                                            dial.cancel();
                                        });
                                    } else {
                                        String alive = String.format("%.0f", Float.parseFloat(moneyMoney.getBal()) - Float.parseFloat(pesa));
                                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                        alert.setTitle("Account Analysis");
                                        alert.setMessage("currentBalance Kes" + moneyMoney.getBal() + "\n\nestimatedWithdraw Kes" + pesa + "\nbalanceAfter Kes" + alive + "\n\nDo you wish to continue?");
                                        alert.setNeutralButton("Just_Leave", (dialogInterface, i1) -> {
                                        });
                                        alert.setPositiveButton("Yes_Agreed", (dialogInterface, i1) -> {
                                        });
                                        AlertDialog dial = alert.create();
                                        dial.show();
                                        dial.setCancelable(false);
                                        dial.setCanceledOnTouchOutside(false);
                                        dial.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                        dial.getWindow().setGravity(Gravity.TOP);
                                        dial.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        dial.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view7 -> {
                                            dial.cancel();
                                        });
                                        dial.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view7 -> {
                                            AlertDialog.Builder tick = new AlertDialog.Builder(this);
                                            tick.setTitle("Disbursement Analysis");
                                            tick.setMessage(Html.fromHtml("<font color='#000000'><b><strong><u><big>" + cardMode.getPat_name() + "</big></u></strong></b>, is the patron of the<br><b><strong><u><big>" + cardMode.getClub() + "</big></u></strong></b> club with <b><strong><u><big>" + cardMode.getMembers() + "</big></u></strong></b> members.<br><br>Please send Kes<b><u><big><strong>" + pesa + "</strong></big></u></b><br>to <b><u><big><strong>" + cardMode.getPat_phone() + "</b></u></big></strong>&nbsp;&nbsp;<small>[</small><b><u><big><strong>" + cardMode.getPat_name() + "</strong></big></u></b><small>]</small>,&nbsp;&nbsp;patron's name.<br>Enter Transaction CODE next screen"));
                                            tick.setNeutralButton("Well_Exit", (dialogInterface, i1) -> {
                                            });
                                            tick.setPositiveButton("Next_Screen", (dialogInterface, i1) -> {
                                            });
                                            AlertDialog quick = tick.create();
                                            quick.show();
                                            quick.setCancelable(false);
                                            quick.setCanceledOnTouchOutside(false);
                                            quick.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                            quick.getWindow().setGravity(Gravity.CENTER);
                                            quick.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            quick.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view6 -> {
                                                quick.cancel();
                                            });
                                            quick.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view6 -> {
                                                AlertDialog.Builder gateB = new AlertDialog.Builder(this);
                                                gateB.setTitle("Disbursement");
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
                                                gateB.setNeutralButton("Exit", (dialogInterface, ii) -> {
                                                });
                                                gateB.setPositiveButton("Submit", (dialogInterface, ii) -> {
                                                });
                                                AlertDialog gateA = gateB.create();
                                                gateA.show();
                                                gateA.setCancelable(false);
                                                gateA.setCanceledOnTouchOutside(false);
                                                gateA.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                gateA.getWindow().setGravity(Gravity.BOTTOM);
                                                gateA.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view5 -> {
                                                    gateA.cancel();
                                                });
                                                gateA.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view5 -> {
                                                    final String mPe = mpesa.getText().toString().trim();
                                                    if (mPe.isEmpty()) {
                                                        mpesa.setError("Transaction CODE");
                                                        mpesa.requestFocus();
                                                    } else if (mPe.length() < 10) {
                                                        mpesa.setError("not yet valid");
                                                        mpesa.requestFocus();
                                                    } else {
                                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.windowOpen,
                                                                respon -> {
                                                                    try {
                                                                        jsonObject = new JSONObject(respon);
                                                                        int st = jsonObject.getInt("success");
                                                                        String msg = jsonObject.getString("message");
                                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                                        if (st == 1) {
                                                                            startActivity(new Intent(getApplicationContext(), CustWithr.class));
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
                                                                para.put("mv", cardMode.getMv());
                                                                para.put("fund", "Disbursed");
                                                                para.put("ses", cardMode.getSes());
                                                                para.put("term", cardMode.getTerm());
                                                                para.put("mpesa", mPe);
                                                                para.put("club_amt", cardMode.getSumm());
                                                                para.put("utility_token", token);
                                                                para.put("amount", pesa);
                                                                para.put("club", cardMode.getClub());
                                                                para.put("pat_id", cardMode.getPat_id());
                                                                para.put("pat_phone", cardMode.getPat_phone());
                                                                para.put("pat_name", cardMode.getPat_name());
                                                                para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date()));
                                                                return para;
                                                            }
                                                        });
                                                    }
                                                });
                                            });
                                        });
                                    }
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
                }));
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.wastedNo,
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
                            cardAda = new ClotheAda(CustWithr.this, R.layout.roller, cardModeArrayList);
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
                para.put("fund", "Pending");
                return para;
            }
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
                startActivity(new Intent(getApplicationContext(), PastRecord.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}