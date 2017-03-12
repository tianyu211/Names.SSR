package cn.guotianyu.datastorage.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.guotianyu.datastorage.R;

import static cn.guotianyu.datastorage.R.id.btn_home_back;

/**
 * Created by 郭天宇 on 2017/3/12.
 */

public class HomeDetail extends Activity {

    private TextView tv_content;
    private TextView tv_title;
    private ImageButton bt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_detail);

        tv_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_content = (TextView) findViewById(R.id.tv_detail_content);
        bt = (ImageButton) findViewById(btn_home_back);

        Intent intent = getIntent();
        tv_title.setText(intent.getExtras().get("title").toString().trim());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
