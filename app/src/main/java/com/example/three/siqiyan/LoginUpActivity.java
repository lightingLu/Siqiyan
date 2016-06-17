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
import android.widget.Toast;

import com.example.three.siqiyan.fragment.LeftMenuFragment;
import com.example.three.siqiyan.view.CreateFindPassDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginUpActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.loginup_back)
    ImageButton loginupBack;
    @Bind(R.id.loginup_zhuce)
    TextView loginupZhuce;
    @Bind(R.id.loginup_username)
    EditText loginupUsername;
    @Bind(R.id.loginup_password)
    EditText loginupPassword;
    @Bind(R.id.loginup_login)
    Button loginLogin;
    @Bind(R.id.loginup_findpass)
    TextView loginupFindpass;
    @Bind(R.id.loginup_qq)
    ImageView loginupQq;
    @Bind(R.id.loginup_wechat)
    ImageView loginupWechat;
    @Bind(R.id.loginup_weibo)
    ImageView loginupWeibo;
    private String username;
    private String password;
    BmobUser user;
    CreateFindPassDialog dialog;
    private View.OnClickListener mClickListener;
    private String userLoginEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
        ButterKnife.bind(this);
        user = new BmobUser();
        loginupBack.setOnClickListener(this);//返回事件
        loginupZhuce.setOnClickListener(this);//登录注册界面
        loginLogin.setOnClickListener(this);//登录事件
        loginupFindpass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginup_back:
                Intent back = new Intent(LoginUpActivity.this, LeftMenuFragment.class);
                startActivity(back);
                break;
            case R.id.loginup_zhuce:
                Intent zhuce = new Intent(LoginUpActivity.this, LoginInActivity.class);
                startActivity(zhuce);
                break;
            case R.id.loginup_login:
                getInfo();
                infoVerify();
                break;
            case R.id.loginup_findpass:
                showEditDialog();
                break;
            case R.id.btn_findpass:
                //获取用户输入的邮箱
                userLoginEmail = dialog.text_email.getText().toString().trim();
                //重置密码
                BmobUser.resetPasswordByEmail(this, userLoginEmail, new ResetPasswordByEmailListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(LoginUpActivity.this, "请求成功，请到" + userLoginEmail+ "邮箱进行密码修改", Toast.LENGTH_LONG).show();
                        dialog.text_email.setText("");
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(LoginUpActivity.this, "重置密码失败:\" + e", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    //验证用户信息
    private void infoVerify() {
        user.setUsername(username);
        user.setPassword(password);
        user.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                if (user.getEmailVerified()) {

                    Toast.makeText(LoginUpActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    //此处处理登录成功后的信息
                    //1.跳转到主界面
                    Intent intent = new Intent(LoginUpActivity.this, MainActivity.class);
                    intent.putExtra("loginState",true);
                    intent.putExtra("isHasLeftFragment",true);
                    startActivity(intent);
                    clearInfo();
                    //2.更改侧边栏的信息，显示为登录状态

                } else {
                    Toast.makeText(LoginUpActivity.this, "登录失败，请去邮箱激活！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginUpActivity.this, "用户名或密码错误，登录失败！", Toast.LENGTH_SHORT).show();

            }
        });

    }

    //清除用户信息
    private void clearInfo() {
        loginupUsername.setText("");
        loginupPassword.setText("");
    }

    //获取输入的信息
    private void getInfo() {
        username = loginupUsername.getText().toString().trim();
        password = loginupPassword.getText().toString().trim();
    }
    public void showEditDialog() {
        dialog = new CreateFindPassDialog(LoginUpActivity.this,0,this);
        dialog.show();
    }
}
