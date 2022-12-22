package com.example.kemussit.Secgen;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kemussit.Kitendawili.Connect;
import com.example.kemussit.Kitendawili.Keeper;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.Kitendawili.SummMode;
import com.example.kemussit.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ManageFeed extends AppCompatActivity {
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
    EditText subjecting;
    TextView textv;
    RelativeLayout relative;
    Model model;
    StuSess stuSess;
    Keeper adaMess;
    SummMode mesMode;
    ArrayList<Keeper> mesModeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ratings");
        setContentView(R.layout.activity_manage_feed);
        listView = findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);
        searchView = findViewById(R.id.seacrhed);
        getCard();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            adaMess = (Keeper) adapterView.getItemAtPosition(i);
            startActivity(new Intent(getApplicationContext(), Midder.class).putExtra("stud_id", adaMess.getStud_id()).putExtra("phone", adaMess.getPhone()).putExtra("name", adaMess.getName()));
        });
    }

    private void getCard() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(new StringRequest(Request.Method.POST, Connect.interior,
                response -> {
                    try {
                        jsonObject = new JSONObject(response);
                        int st = jsonObject.getInt("success");
                        if (st == 1) {
                            jsonArray = jsonObject.getJSONArray("victory");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                adaMess = new Keeper(jsonObject.getString("stud_id"),
                                        jsonObject.getString("phone"), jsonObject.getString("name"),
                                        jsonObject.getString("message"), jsonObject.getString("date"), jsonObject.getString("time"));
                                mesModeArrayList.add(adaMess);
                            }

                            mesMode = new SummMode(ManageFeed.this, R.layout.revenge, mesModeArrayList);
                            listView.setAdapter(mesMode);
                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String s) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String s) {
                                    mesMode.getFilter().filter(s);
                                    return false;
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(this, "Network", Toast.LENGTH_SHORT).show();
        }));
    }
}