package com.example.kemussit.Stud;

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
import com.example.kemussit.Kitendawili.EventAda;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.Orggen.OlderReg;
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

public class Events extends AppCompatActivity {
    EventAda eventAda;
    EvenMdel evenMdel;
    ArrayList<EvenMdel> evenMdelArrayList = new ArrayList<>();
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
    TextView majorGenrali, termedMem, termmo;
    Model model;
    StuSess stuSess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Terminal Events");
        setContentView(R.layout.activity_events);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            evenMdel = (EvenMdel) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Event Details\nEntry: " + evenMdel.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.simon, null);
            majorGenrali = viewer.findViewById(R.id.txtDetails);
            termmo = viewer.findViewById(R.id.PesaPAp);
            termedMem = viewer.findViewById(R.id.tellMem);
            termedMem.setText(Html.fromHtml("<font>" + evenMdel.getMember() + "&nbsp;<small><b>out Of</b></small>&nbsp;" + evenMdel.getOpened()));
            termmo.setText(evenMdel.getMoney());
            majorGenrali.setText(Html.fromHtml("<font><b><strong>Term</strong></b>: " + evenMdel.getTerm() + "<br><b><strong>Event</strong></b>: " + evenMdel.getTheme() + "<br><b><strong>Venue</strong></b>: " + evenMdel.getSite() + " - " + evenMdel.getLand() + " - " + evenMdel.getVenue() + "<br><b><strong>eventDate</strong></b>: " + evenMdel.getDate() + " " + evenMdel.getTime() + "</font>"));
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Register", (dialogInterface, iu) -> {
            });
            builder.setNegativeButton("Pdf", (dialogInterface, iu) -> {
            });
            builder.setNeutralButton("Cancel", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                AlertDialog.Builder theme = new AlertDialog.Builder(this);
                theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Registry!!</b></font>"));
                theme.setMessage("Term: " + evenMdel.getTerm() + "\neventTheme: " + evenMdel.getTheme() + "\nVenue: " + evenMdel.getSite() + "/" + evenMdel.getLand() + "/" + evenMdel.getVenue() + "\neventDate: " + evenMdel.getDate() + "\neventTime: " + evenMdel.getTime() + "\nmaxMembership: " + evenMdel.getMember() + "\ncontribution: KES" + evenMdel.getMoney());
                theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Load_Profile!!</b>"), (dialogInterface, ii) -> {
                });
                theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                });
                AlertDialog why = theme.create();
                why.show();
                why.setCancelable(false);
                why.setCanceledOnTouchOutside(false);
                why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Profile!!</b></font>"));
                    alert.setMessage("RegNo: " + model.getStud_id() + "\nfullname: " + model.getFname() + " " + model.getLname() + "\nphone: " + model.getPhone() + "\n\nDo wish to continue??");
                    alert.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Yes_Register!!</b>"), (dialogInterface, ii) -> {
                    });
                    alert.setNeutralButton("Quit", (dialogInterface, ii) -> {
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.reg_e,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                                        if (st == 1) {
                                            startActivity(new Intent(getApplicationContext(), Events.class));
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
                                para.put("theme", evenMdel.getTheme());
                                para.put("venue", evenMdel.getVenue());
                                para.put("term", evenMdel.getTerm());
                                para.put("ses", evenMdel.getSes());
                                para.put("land", evenMdel.getLand());
                                para.put("site", evenMdel.getSite());
                                para.put("money", evenMdel.getMoney());
                                para.put("time", evenMdel.getTime());
                                para.put("date", evenMdel.getDate());
                                para.put("evt", evenMdel.getEvt());
                                para.put("reg_no", model.getStud_id());
                                para.put("fullname", model.getFname() + " " + model.getLname());
                                para.put("phone", model.getPhone());
                                para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                return para;
                            }
                        });
                    });
                    dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view3 -> {
                        dialog.cancel();
                    });
                });
                why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                    why.cancel();
                });
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                viewer.findViewById(R.id.circle_center).clearAnimation();
                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.mumu)), null);
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.live,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                evenMdel = new EvenMdel(jsonObject.getString("evt"), jsonObject.getString("ses"),
                                        jsonObject.getString("term"), jsonObject.getString("theme"),
                                        jsonObject.getString("venue"), jsonObject.getString("land"),
                                        jsonObject.getString("site"), jsonObject.getString("date"),
                                        jsonObject.getString("member"), jsonObject.getString("opened"), jsonObject.getString("money"),
                                        jsonObject.getString("time"), jsonObject.getString("status"), jsonObject.getString("comment"), jsonObject.getString("approval"),
                                        jsonObject.getString("closure"), jsonObject.getString("entry_date"));
                                evenMdelArrayList.add(evenMdel);
                            }

                            eventAda = new EventAda(Events.this, R.layout.some_lies, evenMdelArrayList);
                            listView.setAdapter(eventAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    eventAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            findViewById(R.id.btnPrint).setVisibility(View.VISIBLE);
                            findViewById(R.id.btnPrint).setOnClickListener(view -> {
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
                para.put("status", "1");
                para.put("approval", "1");
                para.put("closure", "0");
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
                startActivity(new Intent(getApplicationContext(), PastReg.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}