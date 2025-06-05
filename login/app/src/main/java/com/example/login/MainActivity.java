package com.example.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button loginBtn;
    private TextView agreement;
    private TextView privacy;
    private TextView passwordLogin;
    private TextView problem;
    private Fragment fragment;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
        loginBtn = findViewById(R.id.loginBtn);
        agreement = findViewById(R.id.agreement);
        privacy = findViewById(R.id.privacy);
        passwordLogin = findViewById(R.id.passwordLogin);
        problem = findViewById(R.id.problem);
        checkBox = findViewById(R.id.checkbox);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是返回上一页的按钮",Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.option_normal_1){
                    Toast.makeText(MainActivity.this,"这是登录页面的帮助",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是用户协议",Toast.LENGTH_SHORT).show();
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是隐私政策",Toast.LENGTH_SHORT).show();
            }
        });
        problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是遇到问题",Toast.LENGTH_SHORT).show();
            }
        });
        fragment = new captcha();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
        passwordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordLogin.getText().toString().equals("密码登录")){
                    fragment = new password();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
                    loginBtn.setText("登录");
                    passwordLogin.setText("验证码登录");
                }
                else {
                    fragment = new captcha();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
                    loginBtn.setText("获取短信验证码");
                    passwordLogin.setText("密码登录");
                }

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("登录失败");
                    builder.setMessage("请先阅读并同意《美团用户协议》和《隐私政策》！");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

}