package com.example.kemussit.Stud;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.print.PrintManager;
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
import android.widget.Spinner;
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
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OnceAda;
import com.example.kemussit.Kitendawili.OnceMode;
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.RelaxAda;
import com.example.kemussit.Kitendawili.RelaxMode;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Courses extends AppCompatActivity {
    Model model;
    StuSess stuSess;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Terminal Courses");
        setContentView(R.layout.activity_courses);
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            relaxMode = (RelaxMode) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Course Uploaded\non: " + relaxMode.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.sympathy, null);
            content = viewer.findViewById(R.id.myCode);
            content.setText(relaxMode.getCode());
            content.setEnabled(false);
            title = viewer.findViewById(R.id.myTitle);
            title.setText(relaxMode.getTitle());
            title.setEnabled(false);
            credit = viewer.findViewById(R.id.myCred);
            credit.setText(relaxMode.getCredits());
            credit.setEnabled(false);
            quoted = viewer.findViewById(R.id.myScrip);
            quoted.setText(relaxMode.getQuote());
            quoted.setEnabled(false);
            Button btn = viewer.findViewById(R.id.btnSpinner);
            btn.setText(relaxMode.getDepartment());
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setNegativeButton("Cancel", (dialogInterface, ii) -> {
            });
            builder.setNeutralButton("Pdf", (dialogInterface, iu) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(view1 -> {
                alertDialog.cancel();
            });
            alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view1 -> {
                viewer.findViewById(R.id.circle_center).clearAnimation();
                PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
                printManager.print(getString(R.string.app_name), new PrintF(this, viewer.findViewById(R.id.printif)), null);
            });
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.den,
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
                            relaxAda = new RelaxAda(Courses.this, R.layout.ndugu, relaxModeArrayList);
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
                para.put("department", model.getDepartment());
                para.put("depart", "COMMON UNIT");
                para.put("closure", "0");
                return para;
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.salome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Memes:
                //startActivity(new Intent(getApplicationContext(), CrsHist.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }*/
}