package com.example.kemussit;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kemussit.Kitendawili.LecSess;
import com.example.kemussit.Kitendawili.Model;
import com.example.kemussit.Kitendawili.OrgSess;
import com.example.kemussit.Kitendawili.PatrSess;
import com.example.kemussit.Kitendawili.SecSess;
import com.example.kemussit.Kitendawili.StuSess;
import com.example.kemussit.Kitendawili.TresSess;
import com.example.kemussit.Lec.LecLog;
import com.example.kemussit.Orggen.OrgLog;
import com.example.kemussit.Patron.PatLog;
import com.example.kemussit.Secgen.SecLog;
import com.example.kemussit.Stud.StuLog;
import com.example.kemussit.Treas.TreLog;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    CircleImageView circleImageView;
    Model model;
    StuSess stuSess;
    TresSess tresSess;
    PatrSess patrSess;
    OrgSess orgSess;
    SecSess secSess;
    LecSess lecSess;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleImageView = findViewById(R.id.circle_center);
        circleImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anti));
        stuSess = new StuSess(getApplicationContext());
        model = stuSess.getUser();
        lecSess = new LecSess(getApplicationContext());
        model = lecSess.getUser();
        patrSess = new PatrSess(getApplicationContext());
        model = patrSess.getUser();
        tresSess = new TresSess(getApplicationContext());
        model = tresSess.getUser();
        orgSess = new OrgSess(getApplicationContext());
        model = orgSess.getUser();
        secSess = new SecSess(getApplicationContext());
        model = secSess.getUser();
        findViewById(R.id.champee).setAnimation(AnimationUtils.loadAnimation(this, R.anim.maker));
        findViewById(R.id.Student).setOnClickListener(view -> {
            /*if (stuSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), StuHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), StuLog.class));
            //}
        });
        findViewById(R.id.Org).setOnClickListener(view -> {
            /*if (orgSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), OrgHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), OrgLog.class));
            // }
        });
        findViewById(R.id.Lecture).setOnClickListener(view -> {
            /*if (lecSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), LecHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), LecLog.class));
            //}
        });
        findViewById(R.id.Sec).setOnClickListener(view -> {
            /*if (secSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), SecHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), SecLog.class));
            // }
        });
        findViewById(R.id.Treasurer).setOnClickListener(view -> {
            /*if (tresSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), TreHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), TreLog.class));
            //}
        });
        findViewById(R.id.Patron).setOnClickListener(view -> {
            /*if (patrSess.userLog()) {
                startActivity(new Intent(getApplicationContext(), PatHome.class));
            } else {*/
            startActivity(new Intent(getApplicationContext(), PatLog.class));
            //}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.abt:
                AlertDialog.Builder keypa = new AlertDialog.Builder(this);
                keypa.setTitle(Html.fromHtml("<font color='#950365'><strong><b>About US</b></strong></font>"));
                keypa.setMessage(getString(R.string.about));
                keypa.setPositiveButton(Html.fromHtml("<font color='#950365'><strong><b>Exit</b></strong></font>"), (dialogInterface, i) -> {
                });
                AlertDialog alertDialog = keypa.create();
                alertDialog.show();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                alertDialog.getWindow().setGravity(Gravity.TOP);
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                    alertDialog.cancel();
                });
                break;
            case R.id.help:
                AlertDialog.Builder help = new AlertDialog.Builder(this);
                help.setTitle(Html.fromHtml("<font color='#950365'><strong><b>Help FAQs</b></strong></font>"));
                help.setMessage(getString(R.string.help));
                help.setPositiveButton(Html.fromHtml("<font color='#950365'><strong><b>Exit</b></strong></font>"), (dialogInterface, i) -> {
                });
                AlertDialog summer = help.create();
                summer.show();
                summer.setCancelable(false);
                summer.setCanceledOnTouchOutside(false);
                summer.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                summer.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                summer.getWindow().setGravity(Gravity.TOP);
                summer.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                    summer.cancel();
                });
                break;
            case R.id.contact:
                AlertDialog.Builder conta = new AlertDialog.Builder(this);
                conta.setTitle(Html.fromHtml("<font color='#950365'><strong><b>Contact</b></strong></font>"));
                conta.setMessage("+254 729 037902");
                conta.setPositiveButton(Html.fromHtml("<font color='#950365'><strong><b>Exit</b></strong></font>"), (dialogInterface, i) -> {
                });
                AlertDialog sum = conta.create();
                sum.show();
                sum.setCancelable(false);
                sum.setCanceledOnTouchOutside(false);
                sum.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                sum.getWindow().setBackgroundDrawableResource(R.drawable.integral);
                sum.getWindow().setGravity(Gravity.TOP);
                sum.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> {
                    sum.cancel();
                });
                break;
            case R.id.exit:
                finishAffinity();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}