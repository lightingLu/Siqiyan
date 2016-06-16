package com.example.three.siqiyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.three.siqiyan.fragment.LeftMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.loginup_title)
    TextView loginupTitle;
    @Bind(R.id.loginup_back)
    ImageButton loginupBack;
    @Bind(R.id.loginup_zhuce)
    TextView loginupZhuce;
    @Bind(R.id.loginup_username)
    EditText loginupUsername;
    @Bind(R.id.login_yanzheng)
    EditText loginYanzheng;
    @Bind(R.id.login_login)
    Button loginLogin;
    @Bind(R.id.loginup_findpass)
    TextView loginupFindpass;
    @Bind(R.id.loginup_qq)
    ImageView loginupQq;
    @Bind(R.id.loginup_wechat)
    ImageView loginupWechat;
    @Bind(R.id.loginup_weibo)
    ImageView loginupWeibo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
        ButterKnife.bind(this);
        loginupBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginup_back:
                Intent intent = new Intent(LoginUpActivity.this, LeftMenuFragment.class);
                startActivity(intent);
                break;
        }
    }
}
