package com.example.kemussit.Treas;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.EvenMdel;
import com.example.kemussit.Kitendawili.KihindoAda;
import com.example.kemussit.Kitendawili.Kihingo;
import com.example.kemussit.Kitendawili.LastAda;
import com.example.kemussit.Kitendawili.LastDay;
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

public class AidedContr extends AppCompatActivity {
    KihindoAda cardAda;
    Kihingo cardMode;
    ArrayList<Kihingo> cardModeArrayList = new ArrayList<>();
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
    AlertDialog.Builder alert;
    AlertDialog dialog;
    TextView majorGenrali, termedMem, termmo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Contributions");
        setContentView(R.layout.activity_aided_contr);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        btn = findViewById(R.id.btnPrint);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            cardMode = (Kihingo) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Contribution Details");
            builder.setMessage("PAYER\nregNO: " + cardMode.getReg_no() + "\nname: " + cardMode.getFullname() + "\nphone: " + cardMode.getPhone() + "\n\nSESSION\n" + cardMode.getTerm() + "\n\nFEES\ncardCharge Kes " + cardMode.getMoney() + "\nmpesa: " + cardMode.getMpesa() + "\n\nSTATUS\npayDate: " + cardMode.getDate() + "\napproval: " + cardMode.getStatus());
            builder.setPositiveButton("Approve", (dialogInterface, i1) -> {
            });
            builder.setNegativeButton("Decline", (dialogInterface, i1) -> {
            });
            builder.setNeutralButton("Exit", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.appPay,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), AidedContr.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                            }

                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pay", cardMode.getPay());
                        params.put("status", "1");
                        params.put("fresh", cardMode.getMoney());
                        params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                        params.put("alrt", "Your contribution was approved.");
                        params.put("user", cardMode.getReg_no());
                        return params;
                    }
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(new StringRequest(Request.Method.POST, Connect.appPay,
                        response -> {
                            try {
                                jsonObject = new JSONObject(response);
                                int st = jsonObject.getInt("success");
                                String msg = jsonObject.getString("message");
                                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                if (st == 1) {
                                    startActivity(new Intent(getApplicationContext(), AidedContr.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(this, "an error occurred", Toast.LENGTH_SHORT).show();
                            }

                        }, error -> {
                    Toast.makeText(this, "connection error", Toast.LENGTH_SHORT).show();
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pay", cardMode.getPay());
                        params.put("status", "2");
                        params.put("fresh", "0");
                        params.put("reg_date", new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(new Date()));
                        params.put("alrt", "Your contribution was rejected.");
                        params.put("user", cardMode.getReg_no());
                        return params;
                    }
                });
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.getterR,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new Kihingo(jsonObject.getString("pay"), jsonObject.getString("ses"), jsonObject.getString("term"),
                                        jsonObject.getString("mpesa"), jsonObject.getString("money"), jsonObject.getString("reg_no"),
                                        jsonObject.getString("fullname"), jsonObject.getString("phone"), jsonObject.getString("status"),
                                        jsonObject.getString("expiry"), jsonObject.getString("remarks"), jsonObject.getString("date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new KihindoAda(AidedContr.this, R.layout.roller, cardModeArrayList);
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
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("status", "0");
                return para;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.fina, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.App:
                startActivity(new Intent(getApplicationContext(), AppAid.class));
                break;
            case R.id.Rej:
                startActivity(new Intent(getApplicationContext(), RejApp.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}