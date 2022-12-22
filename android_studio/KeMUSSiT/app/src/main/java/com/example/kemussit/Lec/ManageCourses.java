package com.example.kemussit.Lec;

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
import androidx.cardview.widget.CardView;

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
import com.example.kemussit.Kitendawili.RelaxAda;
import com.example.kemussit.Kitendawili.RelaxMode;
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

public class ManageCourses extends AppCompatActivity {
    RelaxAda relaxAda;
    RelaxMode relaxMode;
    ArrayList<RelaxMode> relaxModeArrayList = new ArrayList<>();
    ListView listView;
    SearchView searchView;
    Rect rect;
    Window window;
    View viewer;
    LayoutInflater layoutInflater;
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    EditText content, title, credit, quoted;
    String mTerm, mId;
    Model model;
    LecSess stuSess;
    CardView cardView;
    Button profi, logger, btn;
    OnceAda onceAda;
    OnceMode onceMode;
    ArrayList<OnceMode> onceModeArrayList = new ArrayList<>();
    AlertDialog.Builder builder, alert;
    AlertDialog alertDialog, dialog;
    RequestQueue requestQueue;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Spinner spinner;
    RelativeLayout relativeLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Terminal Courses");
        setContentView(R.layout.activity_manage_courses);
        stuSess = new LecSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(new StringRequest(Request.Method.POST, Connect.getAc,
                    respons -> {
                        try {
                            jsonObject = new JSONObject(respons);
                            Log.e("response ", respons);
                            int succe = jsonObject.getInt("trust");
                            if (succe == 1) {
                                jsonArray = jsonObject.getJSONArray("victory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    onceMode = new OnceMode(jsonObject.getString("id"), jsonObject.getString("term"),
                                            jsonObject.getString("year"), jsonObject.getString("report"),
                                            jsonObject.getString("status"), jsonObject.getString("entry_date"),
                                            jsonObject.getString("ended"), jsonObject.getString("ending"),
                                            jsonObject.getString("end_date"));
                                    onceModeArrayList.add(onceMode);
                                }
                                mTerm = onceMode.getTerm() + " " + onceMode.getYear();
                                mId = onceMode.getId();
                                builder = new AlertDialog.Builder(this);
                                builder.setTitle("Add Course");
                                rect = new Rect();
                                window = this.getWindow();
                                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                                layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                viewer = layoutInflater.inflate(R.layout.sawasawa, null);
                                content = viewer.findViewById(R.id.myCode);
                                title = viewer.findViewById(R.id.myTitle);
                                credit = viewer.findViewById(R.id.myCred);
                                spinner = viewer.findViewById(R.id.myDep);
                                quoted = viewer.findViewById(R.id.myScrip);
                                relativeLayout = viewer.findViewById(R.id.relative);
                                textView = viewer.findViewById(R.id.remTimer);
                                quoted.setText("Note that if you have a Retake you cannot register a course from your portal.");
                                quoted.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                        String mF = quoted.getText().toString().trim();
                                        if (!mF.isEmpty()) {
                                            textView.setText(String.valueOf(250 - (quoted.getText().toString().length())));
                                            relativeLayout.setVisibility(View.VISIBLE);
                                        } else {
                                            textView.setText("250");
                                            relativeLayout.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });
                                ArrayAdapter<CharSequence> com = ArrayAdapter.createFromResource(this, R.array.Comp, android.R.layout.simple_spinner_item);
                                com.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                ArrayAdapter<CharSequence> mac = ArrayAdapter.createFromResource(this, R.array.Mac, android.R.layout.simple_spinner_item);
                                mac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                ArrayAdapter<CharSequence> agr = ArrayAdapter.createFromResource(this, R.array.Agri, android.R.layout.simple_spinner_item);
                                agr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                switch (model.getDepartment()) {
                                    case "COMPUTER SCIENCE":
                                        spinner.setAdapter(com);
                                        break;
                                    case "AGRICULTURE":
                                        spinner.setAdapter(agr);
                                        break;
                                    case "PURE & APPLIED SCIENCE":
                                        spinner.setAdapter(mac);
                                        break;
                                }
                                viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
                                frameLayout = new FrameLayout(this);
                                params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
                                viewer.setLayoutParams(params);
                                frameLayout.addView(viewer);
                                builder.setView(frameLayout);
                                builder.setPositiveButton("Upload", (dialogInterface, i) -> {
                                });
                                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                                });
                                alertDialog = builder.create();
                                alertDialog.show();
                                alertDialog.setCancelable(false);
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                                    final String mDep = spinner.getSelectedItem().toString().trim();
                                    final String mCode = content.getText().toString().trim();
                                    final String mQuote = quoted.getText().toString().trim();
                                    final String mTitle = title.getText().toString().trim();
                                    final String mCred = credit.getText().toString().trim();
                                    if (mDep.equals("Select Department")) {
                                        Toast.makeText(this, "Select Department", Toast.LENGTH_SHORT).show();
                                    } else if (mCode.isEmpty()) {
                                        content.setError("Why empty???");
                                        content.requestFocus();
                                    } else if (mTitle.isEmpty()) {
                                        title.setError("Why empty???");
                                        title.requestFocus();
                                    } else if (mCred.isEmpty()) {
                                        credit.setError("Why empty???");
                                        credit.requestFocus();
                                    } else if (Float.parseFloat(mCred) == 0) {
                                        credit.setError("Which course has\n" + mCred + " here??");
                                        credit.requestFocus();
                                    } else if (mQuote.isEmpty()) {
                                        quoted.setError("This field wasn't empty!!!\nDid you tamper???");
                                        quoted.requestFocus();
                                    } else {
                                        String mYr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date());
                                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Upload!!</b></font>"));
                                        theme.setMessage("Term: " + mTerm + "\ndepartment: " + mDep + "\ncrsCODE: " + mCode + "\ncrsTitle: " + mTitle + "\ncrsCredits: " + mCred + "\n\npublishDate: " + mYr);
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
                                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.addc,
                                                    respo -> {
                                                        try {
                                                            jsonObject = new JSONObject(respo);
                                                            int sta = jsonObject.getInt("success");
                                                            String mseg = jsonObject.getString("message");
                                                            Toast.makeText(this, mseg, Toast.LENGTH_SHORT).show();
                                                            if (sta == 1) {
                                                                if (!relaxModeArrayList.isEmpty()){
                                                                    relaxModeArrayList.clear();
                                                                    relaxAda.notifyDataSetChanged();
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
                                                    para.put("code", mCode);
                                                    para.put("term", mTerm);
                                                    para.put("ses", mId);
                                                    para.put("title", mTitle);
                                                    para.put("quote", mQuote);
                                                    para.put("credits", mCred);
                                                    para.put("department", mDep);
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
                            } else if (succe == 0) {
                                String msgg = jsonObject.getString("mine");
                                AlertDialog.Builder noti = new AlertDialog.Builder(this);
                                noti.setTitle(msgg);
                                noti.setMessage(Html.fromHtml("<font color=#ff0000><b>You cannot add a course if no Active session was found</b></font>"));
                                AlertDialog dial = noti.create();
                                dial.show();
                                dial.getWindow().setGravity(80);
                                dial.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                                dial.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }, error -> {
                Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }));
        });
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            relaxMode = (RelaxMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Uploaded Course\nEntry: " + relaxMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.sawasawa, null);
            content = viewer.findViewById(R.id.myCode);
            content.setText(relaxMode.getCode());
            title = viewer.findViewById(R.id.myTitle);
            title.setText(relaxMode.getTitle());
            credit = viewer.findViewById(R.id.myCred);
            credit.setText(relaxMode.getCredits());
            spinner = viewer.findViewById(R.id.myDep);
            quoted = viewer.findViewById(R.id.myScrip);
            quoted.setText(relaxMode.getQuote());
            relativeLayout = viewer.findViewById(R.id.relative);
            textView = viewer.findViewById(R.id.remTimer);
            quoted.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = quoted.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textView.setText(String.valueOf(250 - (quoted.getText().toString().length())));
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else {
                        textView.setText("250");
                        relativeLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            ArrayAdapter<CharSequence> com = ArrayAdapter.createFromResource(this, R.array.Comp, android.R.layout.simple_spinner_item);
            com.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> mac = ArrayAdapter.createFromResource(this, R.array.Mac, android.R.layout.simple_spinner_item);
            mac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ArrayAdapter<CharSequence> agr = ArrayAdapter.createFromResource(this, R.array.Agri, android.R.layout.simple_spinner_item);
            agr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            switch (model.getDepartment()) {
                case "COMPUTER SCIENCE":
                    spinner.setAdapter(com);
                    spinner.setSelection(com.getPosition(relaxMode.getDepartment()));
                    break;
                case "AGRICULTURE":
                    spinner.setAdapter(agr);
                    spinner.setSelection(agr.getPosition(relaxMode.getDepartment()));
                    break;
                case "PURE & APPLIED SCIENCE":
                    spinner.setAdapter(mac);
                    spinner.setSelection(mac.getPosition(relaxMode.getDepartment()));
                    break;
            }
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            if (relaxMode.getClosure().equals("Active")) {
                spinner.setEnabled(true);
                content.setEnabled(true);
                title.setEnabled(true);
                credit.setEnabled(true);
                quoted.setEnabled(true);
                builder.setPositiveButton("Edit", (dialogInterface, ii) -> {
                });
                builder.setNeutralButton("Delete", (dialogInterface, ii) -> {
                });
            } else {
                spinner.setEnabled(false);
                content.setEnabled(false);
                title.setEnabled(false);
                credit.setEnabled(false);
                quoted.setEnabled(false);
            }
            builder.setNegativeButton("Cancel", (dialogInterface, ii) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            if (relaxMode.getClosure().equals("Active")) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                    final String mDep = spinner.getSelectedItem().toString().trim();
                    final String mCode = content.getText().toString().trim();
                    final String mTitle = title.getText().toString().trim();
                    final String mCred = credit.getText().toString().trim();
                    final String mQuote = quoted.getText().toString().trim();
                    if (mDep.equals("Select Department")) {
                        Toast.makeText(this, "You have decided not to Select Department", Toast.LENGTH_SHORT).show();
                    } else if (mCode.isEmpty()) {
                        content.setError("This was not empty!!");
                        content.requestFocus();
                    } else if (mTitle.isEmpty()) {
                        title.setError("This was not empty!!");
                        title.requestFocus();
                    } else if (mCred.isEmpty()) {
                        credit.setError("This was empty!!");
                        credit.requestFocus();
                    } else if (Float.parseFloat(mCred) == 0) {
                        credit.setError("Which course has\n" + mCred + " here??");
                        credit.requestFocus();
                    } else if (mQuote.isEmpty()) {
                        quoted.setError("This field wasn't empty!!!\nDid you tamper???");
                        quoted.requestFocus();
                    } else {
                        AlertDialog.Builder theme = new AlertDialog.Builder(this);
                        theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Update!!</b></font>"));
                        theme.setMessage("Term: " + relaxMode.getTerm() + "\ndepartment: " + mDep + "\ncrsCODE: " + mCode + "\ncrsTitle: " + mTitle + "\ncrsCredits: " + mCred);
                        theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b>"), (dialogInterface, pi) -> {
                        });
                        theme.setNeutralButton("Exit", (dialogInterface, iii) -> {
                        });
                        AlertDialog why = theme.create();
                        why.show();
                        why.setCancelable(false);
                        why.setCanceledOnTouchOutside(false);
                        why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                        why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                            requestQueue.add(new StringRequest(Request.Method.POST, Connect.summerTime,
                                    respo -> {
                                        try {
                                            jsonObject = new JSONObject(respo);
                                            int sta = jsonObject.getInt("success");
                                            String mseg = jsonObject.getString("message");
                                            Toast.makeText(this, mseg, Toast.LENGTH_SHORT).show();
                                            if (sta == 1) {
                                                startActivity(new Intent(getApplicationContext(), ManageCourses.class));
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
                                    para.put("code", mCode);
                                    para.put("title", mTitle);
                                    para.put("credits", mCred);
                                    para.put("quote", mQuote);
                                    para.put("department", mDep);
                                    para.put("cs", relaxMode.getCs());
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
                    theme.setMessage("Term: " + relaxMode.getTerm() + "\ndepartment: " + relaxMode.getDepartment() + "\ncrsCODE: " + relaxMode.getCode() + "\ncrsTitle: " + relaxMode.getTitle() + "\ncrsCredits: " + relaxMode.getCredits());
                    theme.setPositiveButton(Html.fromHtml("<font color='#ff0000'><b>I_Know!!</b>"), (dialogInterface, pi) -> {
                    });
                    theme.setNeutralButton("Exit", (dialogInterface, iii) -> {
                    });
                    AlertDialog why = theme.create();
                    why.show();
                    why.setCancelable(false);
                    why.setCanceledOnTouchOutside(false);
                    why.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                    why.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view2 -> {
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.dropcs,
                                respo -> {
                                    try {
                                        jsonObject = new JSONObject(respo);
                                        int sta = jsonObject.getInt("success");
                                        String mseg = jsonObject.getString("message");
                                        Toast.makeText(this, mseg, Toast.LENGTH_SHORT).show();
                                        if (sta == 1) {
                                            if (!relaxModeArrayList.isEmpty()){
                                                relaxModeArrayList.clear();
                                                relaxAda.notifyDataSetChanged();
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
                                para.put("cs", relaxMode.getCs());
                                return para;
                            }
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
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.dont,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                relaxMode = new RelaxMode(jsonObject.getString("cs"), jsonObject.getString("ses"),
                                        jsonObject.getString("term"), jsonObject.getString("code"),
                                        jsonObject.getString("title"), jsonObject.getString("credits"),
                                        jsonObject.getString("department"), jsonObject.getString("closure"),
                                        jsonObject.getString("quote"), jsonObject.getString("entry_date"));
                                relaxModeArrayList.add(relaxMode);
                            }
                            relaxAda = new RelaxAda(ManageCourses.this, R.layout.ndugu, relaxModeArrayList);
                            listView.setAdapter(relaxAda);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    relaxAda.getFilter().filter(newText);
                                    return false;
                                }
                            });
                            findViewById(R.id.btnOld).setVisibility(View.VISIBLE);
                            findViewById(R.id.btnOld).setOnClickListener(view -> {
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
                para.put("common","COMMON UNIT");
                para.put("department", model.getDepartment());
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
                startActivity(new Intent(getApplicationContext(), PAstCourses.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}