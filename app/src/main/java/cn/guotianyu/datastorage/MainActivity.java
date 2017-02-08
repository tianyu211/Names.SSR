package cn.guotianyu.datastorage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBHelper db;

    private Button bt_login;
    private EditText et_user;
    private EditText et_password;
    private TextView tv_forget;
    private TextView tv_new;

    private String username;
    private String password;

    private SQLiteDatabase dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据打开帮助类
        db = new DBHelper(this);

        bt_login = (Button) findViewById(R.id.bt_login);
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_new = (TextView) findViewById(R.id.tv_new);

        //设置单行
        et_user.setSingleLine(true);
        et_password.setSingleLine(true);
        //设置密码不可见
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);



        //登陆按钮的监听事件
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        //忘记密码
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        //新用户
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Reg.class);
                startActivity(intent);
            }
        });
    }
    //登陆按钮
    private void login(){
        username = et_user.getText().toString().trim();
        password = et_password.getText().toString().trim();
        //判断是否输入账号密码
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"用户名或者密码不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }

        //得到可读数据库
        dbr = db.getReadableDatabase();
        //把输入的账号密码与数据库对比
        Cursor cursor = dbr.rawQuery("select * from qq where username=? and password=?",new String[]{username,password});
        if(cursor.moveToNext()){
            //开启新Activity并传递数据
            Intent intent = new Intent(MainActivity.this,Home.class);
            intent.putExtra("user",username);
            intent.putExtra("password",password);
            startActivity(intent);
        }else{
            Toast.makeText(this,"登录失败，用户名或密码不正确!",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        dbr.close();
    }

}
