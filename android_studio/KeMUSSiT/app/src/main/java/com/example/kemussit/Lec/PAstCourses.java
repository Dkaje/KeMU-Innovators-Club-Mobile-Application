package com.example.kemussit.Lec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PAstCourses extends AppCompatActivity {
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Past Courses");
        setContentView(R.layout.activity_past_courses);
        stuSess = new LecSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
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
                            relaxAda = new RelaxAda(PAstCourses.this, R.layout.ndugu, relaxModeArrayList);
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
                para.put("closure", "1");
                para.put("department", model.getDepartment());
                para.put("common","COMMON UNIT");
                return para;
            }
        });
    }
}