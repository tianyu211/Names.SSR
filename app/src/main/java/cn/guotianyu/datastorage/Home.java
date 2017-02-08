package cn.guotianyu.datastorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by 郭天宇 on 2017/2/7.
 */

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Intent intent = getIntent();
        TextView user = (TextView) findViewById(R.id.tv_user);
        TextView password = (TextView) findViewById(R.id.tv_password);
        user.setText(intent.getStringExtra("user"));
        password.setText(intent.getStringExtra("password"));
    }
}
