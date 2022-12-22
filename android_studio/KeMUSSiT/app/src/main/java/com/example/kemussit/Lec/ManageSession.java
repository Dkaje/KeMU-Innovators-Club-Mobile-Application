package com.example.kemussit.Lec;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.LecSess;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceAda;
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

public class ManageSession extends AppCompatActivity {
    OnceAda cardAda;
    OnceMode cardMode;
    ArrayList<OnceMode> cardModeArrayList = new ArrayList<>();
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
    Spinner spinner;
    Button fees, ender;
    private DatePickerDialog datePickerDialog, datePickerDial;
    Model model;
    LecSess stuSess;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Manage Session");
        setContentView(R.layout.activity_manage_session);
        stuSess = new LecSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        progressDialog = new ProgressDialog(this);
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Session");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.added_ses, null);
            spinner = viewer.findViewById(R.id.mySpin);
            fees = viewer.findViewById(R.id.myFee);
            ender = viewer.findViewById(R.id.myEnder);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Session, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            fees.setOnClickListener(this::openStartDate);
            initDatePicker();
            ender.setOnClickListener(this::openEndDate);
            initDatePick();
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
                if (spin.equals("Select Trimester")) {
                    Toast.makeText(this, "Please select Trimester", Toast.LENGTH_SHORT).show();
                } else {
                    final String mFee = fees.getText().toString().trim();
                    final String mEnd = ender.getText().toString().trim();
                    if (mFee.equals("Expected Starting")) {
                        Toast.makeText(this, "Expected Starting date not set up to NOW", Toast.LENGTH_SHORT).show();
                    } else if (mEnd.equals("Expected Ending")) {
                        Toast.makeText(this, "Expected session Ending date not set up to NOW", Toast.LENGTH_SHORT).show();
                    } else {
                        String mYr = new SimpleDateFormat("yyyy").format(new Date());
                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Alert!!</b></font>"));
                        theme.setMessage("Session: " + spin + " " + mYr + "\n\nStartDate: " + mFee + "\nEndDate: " + mEnd + "\n\nOnce you create Session, you won't be able to Edit or Delete.");
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
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.sessor,
                                    response -> {
                                        try {
                                            jsonObject = new JSONObject(response);
                                            int st = jsonObject.getInt("success");
                                            String msg = jsonObject.getString("message");
                                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                            if (st == 1) {
                                                startActivity(new Intent(getApplicationContext(), ManageSession.class));
                                                finish();
                                            } else if (st == 7) {
                                                AlertDialog.Builder warn = new AlertDialog.Builder(this);
                                                warn.setTitle(Html.fromHtml("<font color='#073FCA'><b>Not Allowed!!</b></font>"));
                                                warn.setIcon(R.drawable.limb);
                                                warn.setMessage(Html.fromHtml("<font color='#073FCA'><b>" + msg + "</b>"));
                                                AlertDialog focus = warn.create();
                                                focus.show();
                                                focus.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                                focus.getWindow().setGravity(Gravity.TOP);
                                                focus.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                                    para.put("term", spin);
                                    para.put("report", mFee);
                                    para.put("ending", mEnd);
                                    para.put("lec_id", model.getStud_id());
                                    para.put("lec_name", model.getFname() + " " + model.getLname());
                                    para.put("lec_phone", model.getPhone());
                                    para.put("year", mYr);
                                    para.put("entry_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                    return para;
                                }
                            });
                        });
                        why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                            why.cancel();
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
            cardMode = (OnceMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Session Details");
            if (cardMode.getEnded().equals("Active")) {
                builder.setMessage("Session: " + cardMode.getTerm() + " " + cardMode.getYear() + "\n\nSessionStart: " + cardMode.getReport() + "\nSessionEnd: " + cardMode.getEnding() + "\n\nEntryDate: " + cardMode.getEntry_date() + "\nStatus: " + cardMode.getEnded());
                builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Close_Session</b>"), (dialogInterface, i1) -> {
                });
            } else {
                builder.setMessage("Session: " + cardMode.getTerm() + " " + cardMode.getYear() + "\n\nSessionStart: " + cardMode.getReport() + "\nSessionEnd: " + cardMode.getEnding() + "\n\nEntryDate: " + cardMode.getEntry_date() + "\nStatus: " + cardMode.getEnded() + "\nendDate: " + cardMode.getEnd_date());
            }
            builder.setNegativeButton("Close", (dialogInterface, i1) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alertDialog.getWindow().setGravity(Gravity.TOP);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            if (cardMode.getEnded().equals("Active")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Alert!!</b></font>"));
                    theme.setMessage("Session: " + cardMode.getTerm() + " " + cardMode.getYear() + "\n\nStartDate: " + cardMode.getReport() + "\nEndDate: " + cardMode.getEnding() + "\n\nOnce you close this session it will no longer be active.");
                    theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>Next--></b>"), (dialogInterface, ii) -> {
                    });
                    theme.setNeutralButton("Exit", (dialogInterface, ii) -> {
                    });
                    AlertDialog why = theme.create();
                    why.show();
                    why.setCancelable(false);
                    why.setCanceledOnTouchOutside(false);
                    why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    why.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    why.getWindow().setGravity(Gravity.CENTER);
                    why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                        AlertDialog.Builder logs = new AlertDialog.Builder(this);
                        logs.setTitle(Html.fromHtml("<font color='#ff0000'><b>Final Warning Alert!!</b></font>"));
                        logs.setMessage(Html.fromHtml("<font><ul><li><b>Closing a session will <strong><u>DEACTIVATE</u></strong> all users from their <strong><u>ACTIVE SESSIONS</u></strong>.</b></li><li><b>Anyone with a <strong><u>FULL MEMBERSHIP</u></strong> card will also come to <strong><u><i>EXPIRY</i></u></strong>!!</b></li><li><b>You cannot undo this process!!</b></li></ul></font>"));
                        logs.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b></font>"), (dialogInterface, ii) -> {
                        });
                        logs.setNeutralButton("Exit", (dialogInterface, ii) -> {
                        });
                        AlertDialog intel = logs.create();
                        intel.show();
                        intel.setCancelable(false);
                        intel.setCanceledOnTouchOutside(false);
                        intel.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                        intel.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        intel.getWindow().setGravity(Gravity.BOTTOM);
                        intel.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view3 -> {
                            AlertDialog.Builder kemu = new AlertDialog.Builder(this);
                            kemu.setTitle(Html.fromHtml("<font color='#ff0000'><b>Final Warning Alert!!</b></font>"));
                            kemu.setMessage(Html.fromHtml("<font color='#ff0000'><b><strong>YourSerial</strong></b>:" + model.getStud_id() + ".<br><b><strong>YourName</strong></b>:" + model.getFname() + "&nbsp;&nbsp;" + model.getLname() + ".<br><b><strong>YourPhone</strong></b>:" + model.getPhone() + "<br><br><i>I do agree to close the session.</i><br>Is it <strong><big><b><u>TRUE</u></b></big></strong><small>??</small></font>"));
                            kemu.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b><strong><u><big>Just_Proceed</big></u></strong>!!</b></font>"), (dialogInterface, ii) -> {
                            });
                            kemu.setNeutralButton("Exit", (dialogInterface, ii) -> {
                            });
                            AlertDialog nam = kemu.create();
                            nam.show();
                            nam.setCancelable(false);
                            nam.setCanceledOnTouchOutside(false);
                            nam.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                            nam.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            nam.getWindow().setGravity(Gravity.CENTER);
                            nam.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view4 -> {
                                nam.cancel();
                            });
                            nam.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view4 -> {
                                progressDialog.setMessage("Master Reset Loading...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                Runnable progressRunnable = () -> progressDialog.cancel();
                                Handler handler = new Handler();
                                handler.postDelayed(() -> {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                                    rect = new Rect();
                                    window = this.getWindow();
                                    window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                    layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View vew = layoutInflater.inflate(R.layout.loading, null);
                                    vew.setMinimumWidth((int) (rect.width() * 0.9f));
                                    vew.setMinimumHeight((int) (rect.height() * 0.04f));
                                    progressBar = vew.findViewById(R.id.progressor);
                                    Drawable bgDrawable = progressBar.getProgressDrawable();
                                    bgDrawable.setColorFilter(Color.parseColor("#036504"), android.graphics.PorterDuff.Mode.MULTIPLY);
                                    progressBar.setProgressDrawable(bgDrawable);
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();
                                            for (int i = 0; i <= 100; ) {
                                                try {
                                                    sleep(700);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                progressBar.setProgress(i);
                                                i = i + 20;
                                            }
                                        }
                                    };
                                    thread.start();
                                    vew.findViewById(R.id.sawaLy).setAnimation(AnimationUtils.loadAnimation(this, R.anim.blink));
                                    frameLayout = new FrameLayout(this);
                                    params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.leftMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                    params.rightMargin = getResources().getDimensionPixelSize(R.dimen.mini);
                                    vew.setLayoutParams(params);
                                    frameLayout.addView(vew);
                                    alert.setView(frameLayout);
                                    AlertDialog sheep = alert.create();
                                    sheep.setCancelable(false);
                                    sheep.show();
                                    Handler ori = new Handler();
                                    ori.postDelayed(() -> {
                                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.willReme,
                                                response -> {
                                                    try {
                                                        jsonObject = new JSONObject(response);
                                                        int st = jsonObject.getInt("success");
                                                        String msg = jsonObject.getString("message");
                                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                                        if (st == 1) {
                                                            startActivity(new Intent(getApplicationContext(), ManageSession.class));
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
                                                para.put("ended", "1");
                                                para.put("lec_id", model.getStud_id());
                                                para.put("lec_name", model.getFname() + " " + model.getLname());
                                                para.put("lec_phone", model.getPhone());
                                                para.put("ending", cardMode.getEnding());
                                                para.put("categ", "FULL MEMBERSHIP");
                                                para.put("normalcy", "Your Session has expired. Please Report a new session.");
                                                para.put("alrt", "Your CARD has reached expiry. Please Renew your card.");
                                                para.put("end_date", new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date()));
                                                return para;
                                            }
                                        });
                                    }, 4900);
                                    sheep.setCanceledOnTouchOutside(false);
                                    sheep.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                    sheep.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                }, 1500);
                                handler.postDelayed(progressRunnable, 1500);
                            });
                        });
                        intel.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view4 -> {
                            intel.cancel();
                        });
                    });
                    why.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view2 -> {
                        why.cancel();
                    });
                });
            }
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.getter,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                cardMode = new OnceMode(jsonObject.getString("id"), jsonObject.getString("term"),
                                        jsonObject.getString("year"), jsonObject.getString("report"),
                                        jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                        jsonObject.getString("ended"), jsonObject.getString("ending"),
                                        jsonObject.getString("end_date"));
                                cardModeArrayList.add(cardMode);
                            }
                            cardAda = new OnceAda(ManageSession.this, R.layout.ndugu, cardModeArrayList);
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
        }));
    }

    private void openEndDate(View view) {
        datePickerDial.show();
    }

    private void initDatePick() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeStringer(day, month, year);
            ender.setText(date);
        };
        Calendar calendar = Calendar.getInstance();
        Calendar call = Calendar.getInstance();
        Calendar makupa = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = android.app.AlertDialog.THEME_HOLO_LIGHT;
        datePickerDial = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        makupa.add(Calendar.DAY_OF_MONTH, +90);
        call.add(Calendar.MONTH, +4);
        datePickerDial.getDatePicker().setMaxDate(call.getTimeInMillis());
        datePickerDial.getDatePicker().setMinDate(makupa.getTimeInMillis());
    }

    private String makeStringer(int day, int month, int year) {
        return day + "-" + getMonth(month) + "-" + year;
    }

    private String getMonth(int month) {
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

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            fees.setText(date);
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
}