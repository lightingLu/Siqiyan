package com.example.three.siqiyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.three.siqiyan.fragment.LeftMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginInActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.login_title)
    TextView loginTitle;
    @Bind(R.id.loginin_back)
    ImageButton loginBack;
    @Bind(R.id.btn_notifacation)
    ImageButton btnNotifacation;
    @Bind(R.id.login_number)
    EditText loginNumber;
    @Bind(R.id.login_yanzheng)
    Button loginYanzheng;
    @Bind(R.id.login_yanzhengma)
    EditText loginYanzhengma;
    @Bind(R.id.login_login)
    Button loginLogin;
    @Bind(R.id.login_email)
    TextView loginEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        ButterKnife.bind(this);
        loginEmail.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_email:
                Intent intent = new Intent(LoginInActivity.this,LoginEmailAtivity.class);
                startActivity(intent);
                break;
            case R.id.loginin_back:
                Intent intent2 = new Intent(LoginInActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
