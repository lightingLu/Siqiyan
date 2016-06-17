package com.example.three.siqiyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.three.siqiyan.fragment.LeftMenuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

public class LoginInActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.loginin_back)
    ImageButton loginBack;
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
    private String phoneNumber;
    private String loginYanzheng1;
    private int MysmsId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        ButterKnife.bind(this);
        loginEmail.setOnClickListener(this);
        loginYanzheng.setOnClickListener(this);
        loginLogin.setOnClickListener(this);
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
            case R.id.login_yanzheng:
                phoneNumber = loginNumber.getText().toString().trim();
                BmobSMS.requestSMSCode(LoginInActivity.this,phoneNumber,"思齐焉手机注册验证", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer smsId,BmobException ex) {
                        // TODO Auto-generated method stub
                        if(ex==null){//验证码发送成功
                        MysmsId = smsId;
                        }
                    }
                });
                break;
            case R.id.login_login:
                loginYanzheng1 = loginYanzhengma.getText().toString().trim();
                BmobUser.signOrLoginByMobilePhone(LoginInActivity.this, phoneNumber, loginYanzheng1, new LogInListener<BmobUser>() {

                    @Override
                    public void done(BmobUser user, BmobException e) {
                        // TODO Auto-generated method stub
                        if(user!=null){
                            Toast.makeText(LoginInActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginInActivity.this, LoginUpActivity.class);
                            startActivity(intent);
                            loginYanzhengma.setText("");
                            loginNumber.setText("");
                        }
                    }
                });
                break;
        }

    }
}
