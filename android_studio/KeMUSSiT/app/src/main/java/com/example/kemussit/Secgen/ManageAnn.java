package com.example.kemussit.Secgen;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.example.kemussit.Kitendawili.PrintF;
import com.example.kemussit.Kitendawili.SemaAnn;
import com.example.kemussit.Kitendawili.SemaModel;
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

public class ManageAnn extends AppCompatActivity {
    SemaAnn semaAnn;
    SemaModel semaModel;
    ArrayList<SemaModel> semaModelArrayList = new ArrayList<>();
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
    EditText content, subjecting;
    TextView textView, textv;
    RelativeLayout relativeLayout, relative;
    String mTerm, mId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Terminal Notices");
        setContentView(R.layout.activity_manage_ann);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        if (getIntent() != null) {
            mTerm = getIntent().getStringExtra("term");
            mId = getIntent().getStringExtra("ses");
        }
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Announcement");
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.sawa_ann, null);
            content = viewer.findViewById(R.id.myCode);
            textView = viewer.findViewById(R.id.remTimer);
            relativeLayout = viewer.findViewById(R.id.relative);
            subjecting = viewer.findViewById(R.id.mySub);
            textv = viewer.findViewById(R.id.kaleshe);
            relative = viewer.findViewById(R.id.Mkuu);
            subjecting.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = subjecting.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textv.setText(String.valueOf(50 - (subjecting.getText().toString().length())));
                        relative.setVisibility(View.VISIBLE);
                    } else {
                        textv.setText("50");
                        relative.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = content.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textView.setText(String.valueOf(250 - (content.getText().toString().length())));
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
                final String mFee = content.getText().toString().trim();
                final String mSub = subjecting.getText().toString().trim();
                if (mSub.isEmpty()) {
                    subjecting.setError("Why empty???");
                    subjecting.requestFocus();
                } else if (mFee.isEmpty()) {
                    content.setError("Why empty???");
                    content.requestFocus();
                } else {
                    String mYr = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(new Date());
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Send!!</b></font>"));
                    theme.setMessage("Term: " + mTerm + "\nsubject: " + mSub + "\n\ncontent: " + mFee + "\n\npublishDate: " + mYr);
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
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.adda,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            if (!semaModelArrayList.isEmpty()){
                                                semaModelArrayList.clear();
                                                semaAnn.notifyDataSetChanged();
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
                                para.put("notice", mFee);
                                para.put("subject", mSub);
                                para.put("term", mTerm);
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
            semaModel = (SemaModel) adapterView.getItemAtPosition(i);
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Update Announcement\nEntry: " + semaModel.getEntry_date());
            rect = new Rect();
            window = this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            viewer = layoutInflater.inflate(R.layout.sawa_ann, null);
            content = viewer.findViewById(R.id.myCode);
            content.setText(semaModel.getNotice());
            textView = viewer.findViewById(R.id.remTimer);
            relativeLayout = viewer.findViewById(R.id.relative);
            content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = content.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textView.setText(String.valueOf(250 - (content.getText().toString().length())));
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
            subjecting = viewer.findViewById(R.id.mySub);
            subjecting.setText(semaModel.getSubject());
            textv = viewer.findViewById(R.id.kaleshe);
            relative = viewer.findViewById(R.id.Mkuu);
            subjecting.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = subjecting.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textv.setText(String.valueOf(50 - (subjecting.getText().toString().length())));
                        relative.setVisibility(View.VISIBLE);
                    } else {
                        textv.setText("50");
                        relative.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mF = content.getText().toString().trim();
                    if (!mF.isEmpty()) {
                        textView.setText(String.valueOf(250 - (content.getText().toString().length())));
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
            viewer.findViewById(R.id.circle_center).setAnimation(AnimationUtils.loadAnimation(this, R.anim.clock));
            frameLayout = new FrameLayout(this);
            params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.sized);
            viewer.setLayoutParams(params);
            frameLayout.addView(viewer);
            builder.setView(frameLayout);
            builder.setPositiveButton("Edit", (dialogInterface, i5i) -> {
            });
            builder.setNeutralButton("Delete", (dialogInterface, i5i) -> {
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i5i) -> {
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view1 -> {
                final String mFee = content.getText().toString().trim();
                final String mSub = subjecting.getText().toString().trim();
                if (mSub.isEmpty()) {
                    subjecting.setError("Why empty???");
                    subjecting.requestFocus();
                } else if (mFee.isEmpty()) {
                    content.setError("Was this empty???");
                    content.requestFocus();
                } else {
                    AlertDialog.Builder theme = new AlertDialog.Builder(this);
                    theme.setTitle(Html.fromHtml("<font color='#ff0000'><b>Confirm Before Update!!</b></font>"));
                    theme.setMessage("Term: " + semaModel.getTerm() + "\nSubject: " + mSub + "\n\ncontent: " + mFee);
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
                        requestQueue.add(new StringRequest(Request.Method.POST, Connect.bubaa,
                                response -> {
                                    try {
                                        jsonObject = new JSONObject(response);
                                        int st = jsonObject.getInt("success");
                                        String msg = jsonObject.getString("message");
                                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                        if (st == 1) {
                                            if (!semaModelArrayList.isEmpty()){
                                                semaModelArrayList.clear();
                                                semaAnn.notifyDataSetChanged();
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
                                para.put("notice", mFee);
                                para.put("subject", mSub);
                                para.put("ann", semaModel.getAnn());
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
                theme.setMessage("Term: " + semaModel.getTerm() + "\nSubject: " + semaModel.getSubject() + "\n\nncontent: " + semaModel.getNotice());
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
                    requestQueue.add(new StringRequest(Request.Method.POST, Connect.rightOntime,
                            response -> {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int st = jsonObject.getInt("success");
                                    String msg = jsonObject.getString("message");
                                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                                    if (st == 1) {
                                        if (!semaModelArrayList.isEmpty()){
                                            semaModelArrayList.clear();
                                            semaAnn.notifyDataSetChanged();
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
                            para.put("ann", semaModel.getAnn());
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
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.getAn,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        Log.e("response ", response);
                        int success = jsonObject.getInt("trust");
                        if (success == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                semaModel = new SemaModel(jsonObject.getString("ann"), jsonObject.getString("ses"),
                                        jsonObject.getString("term"), jsonObject.getString("subject"), jsonObject.getString("notice"),
                                        jsonObject.getString("closure"), jsonObject.getString("entry_date"));
                                semaModelArrayList.add(semaModel);
                            }

                            semaAnn = new SemaAnn(ManageAnn.this, R.layout.rhyme, semaModelArrayList);
                            listView.setAdapter(semaAnn);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String text) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    semaAnn.getFilter().filter(newText);
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
                startActivity(new Intent(getApplicationContext(), PastAnn.class));
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}