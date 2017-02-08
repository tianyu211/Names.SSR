package cn.guotianyu.datastorage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 郭天宇 on 2017/2/7.
 */

public class Reg extends AppCompatActivity {

    private EditText et_reguser;
    private EditText et_regpassword;
    private Button bt_reg;

    private DBHelper db;
    private String reg_username;
    private String reg_password;

    private SQLiteDatabase dbw;
    private SQLiteDatabase dbr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        db = new DBHelper(this);
        et_reguser = (EditText) findViewById(R.id.et_reguser);
        et_regpassword = (EditText) findViewById(R.id.et_regpassword);
        bt_reg = (Button) findViewById(R.id.bt_reg);
        //设置单行显示
        et_reguser.setSingleLine(true);
        et_regpassword.setSingleLine(true);
        //设置注册按钮的监听事件
        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });
    }

    private void reg(){
        reg_username = et_reguser.getText().toString().trim();
        reg_password = et_regpassword.getText().toString().trim();
        if(reg_username.isEmpty() || reg_password.isEmpty()){
            Toast.makeText(Reg.this,"用户名或者密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        //获取可读数据库,Activity销毁时记得关闭
        dbr = db.getReadableDatabase();
        //得到可以写入的数据库dbw,Activity销毁时记得关闭
        dbw = db.getWritableDatabase();

        Cursor cursor = dbr.rawQuery("select * from qq where username=?", new String[]{reg_username});
        if(cursor.moveToNext()){
            Toast.makeText(Reg.this,"用户名已存在！",Toast.LENGTH_SHORT).show();
            return;
        }else {
            dbw.execSQL("insert into qq(username,password) values(?,?)",new String[]{reg_username,reg_password});
            Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
            cursor.close();
            dbr.close();
            dbw.close();
            finish();
        }
    }

}

