package com.example.kemussit.Orggen;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
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

public class ManageEvents extends AppCompatActivity {
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
    EditText content, landmark, site, member, charge;
    TextView textView;
    RelativeLayout relativeLayout;
    String mTerm, mId;
    Spinner spinner;
    Button date, timer, silent;
    private DatePickerDialog datePickerDialog;
    TextView majorGenrali, termedMem, termmo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Terminal Events");
        setContentView(R.layout.activity_manage_events);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        if (getIntent() != null) {
            mTerm = getIntent().getStringExtra("term");
            mId = getIntent().getStringExtra("ses");
        }
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Event");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.add_e, null);
            content = viewer.findViewById(R.id.myCode);
            spinner = viewer.findViewById(R.id.myDep);
            textView = viewer.findViewById(R.id.remTimer);
            member = viewer.findViewById(R.id.myCounter);
            charge = viewer.findViewById(R.id.myDay);
            landmark = viewer.findViewById(R.id.myTitle);
            site = viewer.findViewById(R.id.myCred);
            date = viewer.findViewById(R.id.myBtnDate);
            timer = viewer.findViewById(R.id.myTime);
            relativeLayout = viewer.findViewById(R.id.relative);
            content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = content.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textView.setText(String.valueOf(50 - (content.getText().toString().length())));
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else {
                        textView.setText("50");
                        relativeLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Current, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            timer.setOnClickListener(view1 -> {
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog.OnTimeSetListener onTimeSetListener = (vview, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    SimpleDateFormat simpleTimer = new SimpleDateFormat("HH:mma");
                    timer.setText(simpleTimer.format(calendar.getTime()));
                };
                new TimePickerDialog(this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            });
            date.setOnClickListener(this::openStartDate);
            initDatePicker();
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Submit", (dialogInterface, i) -> {
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String mVenue = spinner.getSelectedItem().toString().trim();
                final String mVe = content.getText().toString().trim();
                final String mLand = landmark.getText().toString().trim();
                final String mSite = site.getText().toString().trim();
                final String mDate = date.getText().toString().trim();
                final String mTim = timer.getText().toString().trim();
                final String mME = member.getText().toString().trim();
                final String mCha = charge.getText().toString().trim();
                if (mVe.isEmpty()) {
                    content.setError("Why empty???");
                    content.requestFocus();
                } else if (mVenue.equals("Event Venue?")) {
                    Toast.makeText(this, "Event Venue?", Toast.LENGTH_SHORT).show();
                } else if (mLand.isEmpty()) {
                    landmark.setError("Why empty???");
                    landmark.requestFocus();
                } else if (mSite.isEmpty()) {
                    site.setError("Why empty???");
                    site.requestFocus();
                } else if (mDate.equals("Set Date")) {
                    Toast.makeText(this, "Event Date???", Toast.LENGTH_SHORT).show();
                } else if (mTim.equals("Set Time")) {
                    Toast.makeText(this, "Event Timeline???", Toast.LENGTH_SHORT).show();
                } else if (mME.isEmpty()) {
                    member.setError("Why empty???");
                    member.requestFocus();
                } else if (Float.parseFloat(mME) == 0) {
                    member.setError("Why Zero here. Surely???");
                    member.requestFocus();
                } else if (mCha.isEmpty()) {
                    charge.setError("Why empty???");
                    charge.requestFocus();
                } else if (Float.parseFloat(mCha) == 0) {
                    charge.setError("Why Zero here. Surely???");
                    charge.requestFocus();
                } else {
                    String mYr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date());
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Submit!!</b></font>"));
                    theme.setMessage("Term: " + mTerm + "\neventTheme: " + mVe + "\nVenue: " + mSite + "/" + mLand + "/" + mVenue + "\neventDate: " + mDate + "\neventTime: " + mTim + "\nMaxMembers: " + mME + "\ncontribution: KES" + mCha + "\n\npublishDate: " + mYr);
                    theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b>"), (dialogInterface, i) -> {
                    });
                    theme.setNeutralButton("Exit", (dialogInterface, i) -> {
                    });
                    AlertDialog why = theme.create();
                    why.show();
                    why.setCancelable(false);
                    why.setCanceledOnTouchOutside(false);
                    why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.adde,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            if (!evenMdelArrayList.isEmpty()){
                                                evenMdelArrayList.clear();
                                                eventAda.notifyDataSetChanged();
                                            }
                                            why.cancel();
                                            alertDialog.cancel();
                                            getCard();
                                        }
                                    } catch (Exception e) {
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
                                para.put("theme", mVe);
                                para.put("venue", mVenue);
                                para.put("land", mLand);
                                para.put("site", mSite);
                                para.put("term", mTerm);
                                para.put("date", mDate);
                                para.put("member", mME);
                                para.put("money", mCha);
                                para.put("time", mTim);
                                para.put("ses", mId);
                                para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                return para;
                            }
                        });
                    });
                    why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                        why.cancel();
                    });
                }
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            evenMdel = (EvenMdel) adapterView.getItemAtPosition(i);
            String message;
            if (evenMdel.getApproval().equals("Pending")) {
                message = "";
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Event Details\nEntry: " + evenMdel.getEntry_date());
                rect = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                viewer = layoutInflater.inflate(R.layout.add_e, null);
                content = viewer.findViewById(R.id.myCode);
                content.setText(evenMdel.getTheme());
                spinner = viewer.findViewById(R.id.myDep);
                textView = viewer.findViewById(R.id.remTimer);
                landmark = viewer.findViewById(R.id.myTitle);
                landmark.setText(evenMdel.getLand());
                site = viewer.findViewById(R.id.myCred);
                site.setText(evenMdel.getSite());
                date = viewer.findViewById(R.id.myBtnDate);
                date.setText(evenMdel.getDate());
                member = viewer.findViewById(R.id.myCounter);
                member.setText(evenMdel.getMember());
                charge = viewer.findViewById(R.id.myDay);
                charge.setText(evenMdel.getMoney());
                timer = viewer.findViewById(R.id.myTime);
                timer.setText(evenMdel.getTime());
                silent = viewer.findViewById(R.id.spinButton);
                silent.setText(evenMdel.getVenue());
                relativeLayout = viewer.findViewById(R.id.relative);
                content.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String mF = content.getText().toString().trim();
                        if (!mF.isEmpty()) {
                            textView.setText(String.valueOf(50 - (content.getText().toString().length())));
                            relativeLayout.setVisibility(View.VISIBLE);
                        } else {
                            textView.setText("50");
                            relativeLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

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
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Current, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(adapter.getPosition(evenMdel.getVenue()));
                spinner.setVisibility(View.VISIBLE);
                silent.setVisibility(View.GONE);
                content.setEnabled(true);
                site.setEnabled(true);
                landmark.setEnabled(true);
                member.setEnabled(true);
                charge.setEnabled(true);
                timer.setOnClickListener(view1 -> {
                    Calendar calendar = Calendar.getInstance();
                    TimePickerDialog.OnTimeSetListener onTimeSetListener = (vview, hourOfDay, minute) -> {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        SimpleDateFormat simpleTimer = new SimpleDateFormat("HH:mma");
                        timer.setText(simpleTimer.format(calendar.getTime()));
                    };
                    new TimePickerDialog(this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                });
                date.setOnClickListener(this::openStartDate);
                initDatePicker();
                builder.setPositiveButton("Update", (dialogInterface, iu) -> {
                });
                builder.setNeutralButton("Delete", (dialogInterface, iu) -> {
                });
                builder.setNegativeButton("Cancel", (dialogInterface, iu) -> {
                });
                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    final String mVenue = spinner.getSelectedItem().toString().trim();
                    final String mVe = content.getText().toString().trim();
                    final String mLand = landmark.getText().toString().trim();
                    final String mSite = site.getText().toString().trim();
                    final String mDate = date.getText().toString().trim();
                    final String mTim = timer.getText().toString().trim();
                    final String mME = member.getText().toString().trim();
                    final String mCha = charge.getText().toString().trim();
                    if (mVe.isEmpty()) {
                        content.setError("Was this empty???");
                        content.requestFocus();
                    } else if (mVenue.equals("Event Venue?")) {
                        Toast.makeText(this, "You have decided to remove Event Venue?", Toast.LENGTH_SHORT).show();
                    } else if (mLand.isEmpty()) {
                        landmark.setError("Was this empty???");
                        landmark.requestFocus();
                    } else if (mSite.isEmpty()) {
                        site.setError("Was this empty???");
                        site.requestFocus();
                    } else if (mDate.equals("Set Date")) {
                        Toast.makeText(this, "Event Date???", Toast.LENGTH_SHORT).show();
                    } else if (mTim.equals("Set Time")) {
                        Toast.makeText(this, "Event Timeline???", Toast.LENGTH_SHORT).show();
                    } else if (mME.isEmpty()) {
                        member.setError("Why empty???");
                        member.requestFocus();
                    } else if (Float.parseFloat(mME) == 0) {
                        member.setError("Why Zero here. Surely???");
                        member.requestFocus();
                    } else if (mCha.isEmpty()) {
                        charge.setError("Why empty???");
                        charge.requestFocus();
                    } else if (Float.parseFloat(mCha) == 0) {
                        charge.setError("Why Zero here. Surely???");
                        charge.requestFocus();
                    } else {
                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Update!!</b></font>"));
                        theme.setMessage("Term: " + evenMdel.getTerm() + "\neventTheme: " + mVe + "\nVenue: " + mSite + "/" + mLand + "/" + mVenue + "\neventDate: " + mDate + "\neventTime: " + mTim + "\nmaxMembership: " + mME + "\ncontribution: KES" + mCha);
                        theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b>"), (dialogInterface, ii) -> {
                        });
                        theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                        });
                        AlertDialog why = theme.create();
                        why.show();
                        why.setCancelable(false);
                        why.setCanceledOnTouchOutside(false);
                        why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                        why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.edyE,
                                    response -> {
                                        try {
                                            jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            if (st == 1) {
                                                if (!evenMdelArrayList.isEmpty()){
                                                    evenMdelArrayList.clear();
                                                    eventAda.notifyDataSetChanged();
                                                }
                                                why.cancel();
                                                alertDialog.cancel();
                                                getCard();
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
                                    para.put("theme", mVe);
                                    para.put("venue", mVenue);
                                    para.put("land", mLand);
                                    para.put("site", mSite);
                                    para.put("date", mDate);
                                    para.put("member", mME);
                                    para.put("money", mCha);
                                    para.put("time", mTim);
                                    para.put("evt", evenMdel.getEvt());
                                    return para;
                                }
                            });
                        });
                        why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                            why.cancel();
                        });
                    }
                });
                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Delete!!</b></font>"));
                    theme.setMessage("Term: " + evenMdel.getTerm() + "\neventTheme: " + evenMdel.getTheme() + "\nVenue: " + evenMdel.getSite() + "/" + evenMdel.getLand() + "/" + evenMdel.getVenue() + "\neventDate: " + evenMdel.getDate() + "\neventTime: " + evenMdel.getTime() + "\nmaxMembers: " + evenMdel.getMember() + "\ncontribution: KES" + evenMdel.getMoney());
                    theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b>"), (dialogInterface, ii) -> {
                    });
                    theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                    });
                    AlertDialog why = theme.create();
                    why.show();
                    why.setCancelable(false);
                    why.setCanceledOnTouchOutside(false);
                    why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.uongo,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            if (!evenMdelArrayList.isEmpty()){
                                                evenMdelArrayList.clear();
                                                eventAda.notifyDataSetChanged();
                                            }
                                            why.cancel();
                                            alertDialog.cancel();
                                            getCard();
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
                                para.put("evt", evenMdel.getEvt());
                                return para;
                            }
                        });
                    });
                    why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                        why.cancel();
                    });
                });
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                    alertDialog.cancel();
                });
            } else {
                message = evenMdel.getComment();
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Event Details\nEntry: " + evenMdel.getEntry_date());
                builder.setMessage(message);
                rect = new Rect();
                window = this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                viewer = layoutInflater.inflate(R.layout.simon, null);
                majorGenrali = viewer.findViewById(R.id.txtDetails);
                termmo = viewer.findViewById(R.id.PesaPAp);
                termedMem = viewer.findViewById(R.id.tellMem);
                termedMem.setText(evenMdel.getMember());
                termmo.setText(evenMdel.getMoney());
                majorGenrali.setText(Html.fromHtml("<font><b><strong>Term</strong></b>: " + evenMdel.getTerm() + "<br><b><strong>Event</strong></b>: " + evenMdel.getTheme() + "<br><b><strong>Venue</strong></b>: " + evenMdel.getSite() + " - " + evenMdel.getLand() + " - " + evenMdel.getVenue() + "<br><b><strong>eventDate</strong></b>: " + evenMdel.getDate() + " " + evenMdel.getTime() + "<br><b><strong>maxMemberEntry</strong><b> :" + evenMdel.getOpened() + "</font>"));
                viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                frameLayout = new FrameLayout(this);
                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                viewer.setLayoutParams(params);
                frameLayout.addView(viewer);
                builder.setView(frameLayout);
                builder.setNeutralButton(Html.fromHtml("<font color='#ff0000'><b>Status: " + evenMdel.getApproval() + "</b></font>"), (dialogInterface, iu) -> {
                });
                builder.setPositiveButton("Cancel", (dialogInterface, iu) -> {
                });
                builder.setNegativeButton("Pdf", (dialogInterface, iu) -> {
                });
                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                    Toast.makeText(this, "This event record was approved", Toast.LENGTH_SHORT).show();
                });
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                    viewer.findViewById(R.id.circle_center).clearAnimation();
                    PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                    printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.mumu)), null);
                });
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    alertDialog.cancel();
                });
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
                startActivity(new Intent(getApplicationContext(), PastEvents.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String dat = makeDateString(day, month, year);
            date.setText(dat);
        };
        Calendar calendar = Calendar.getInstance();
        Calendar call = Calendar.getInstance();
        Calendar makupa = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = android.app.AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        makupa.add(Calendar.DAY_OF_MONTH, +1);
        call.add(Calendar.MONTH, +3);
        datePickerDialog.getDatePicker().setMaxDate(call.getTimeInMillis());
        datePickerDialog.getDatePicker().setMinDate(makupa.getTimeInMillis());
    }

    private String makeDateString(int day, int month, int year) {
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
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.geto,
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

                            eventAda = new EventAda(ManageEvents.this, R.layout.some_lies, evenMdelArrayList);
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
                para.put("closure", "0");
                return para;
            }
        });
    }
}