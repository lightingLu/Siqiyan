package com.example.three.siqiyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.three.siqiyan.fragment.LeftMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginEmailAtivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.loginemail_title)
    TextView loginemailTitle;
    @Bind(R.id.loginemail_back)
    ImageButton loginemailBack;
    @Bind(R.id.btn_notifacation)
    ImageButton btnNotifacation;
    @Bind(R.id.loginemail_username)
    EditText loginemailUsername;
    @Bind(R.id.loginemail_email)
    EditText loginemailEmail;
    @Bind(R.id.loginemail_password)
    EditText loginemailPassword;
    @Bind(R.id.login_login)
    Button loginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_email_ativity);
        ButterKnife.bind(this);
        loginemailBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginemail_back:
                Intent intent = new Intent(LoginEmailAtivity.this, LoginInActivity.class);
                startActivity(intent);
                break;
        }
    }
}
