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
import android.widget.Toast;

import com.example.three.siqiyan.fragment.LeftMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
    @Bind(R.id.loginemail_login)
    Button loginemailLogin;
    private String username;
    private String password;
    private String email;
    private BmobUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_email_ativity);
        ButterKnife.bind(this);
        user = new BmobUser();

        loginemailBack.setOnClickListener(this);
        loginemailLogin.setOnClickListener(this);


    }

    //操作bomb,保存用户数据
    private void saveUser() {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginEmailAtivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginEmailAtivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //获取用户输入信息
    private void getUserInfo() {
        username = loginemailUsername.getText().toString().trim();
        password = loginemailPassword.getText().toString().trim();
        email = loginemailEmail.getText().toString().trim();

    }
    //清除用户数据
    private void cleanInfo() {
        loginemailUsername.setText("");
        loginemailEmail.setText("");
        loginemailPassword.setText("");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginemail_back:
                Intent intent = new Intent(LoginEmailAtivity.this, LoginInActivity.class);
                startActivity(intent);
                break;
            case R.id.loginemail_login:
                getUserInfo();
                saveUser();
                cleanInfo();
                break;
        }
    }




}
