package cn.guotianyu.datastorage.me;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.guotianyu.datastorage.R;

/**
 * Created by 郭天宇 on 2017/2/7.
 */

public class ForgetPassword extends AppCompatActivity {
    private EditText et_forgotUser;
    private Button bt_findPassword;
    private ListView lv_forgotPassword;
    private List<Info> infos;
    private SQLiteDatabase dbr;
    private DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        et_forgotUser = (EditText) findViewById(R.id.et_forgotUser);
        bt_findPassword = (Button) findViewById(R.id.bt_findPassword);
        lv_forgotPassword = (ListView) findViewById(R.id.lv_forgotPassword);
        //设置单行显示
        et_forgotUser.setSingleLine(true);

        /*!!!!!!!!!!!!!!!*/
        db = new DBHelper(this);

        //按钮监听事件
        bt_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPassword();
            }
        });

    }
    //找回密码
    private void findPassword(){
        //获取忘记的用户名
        String forgotUser = et_forgotUser.getText().toString().trim();
        String forgotPasswrod = "";
        if(forgotUser.isEmpty()){
            Toast.makeText(this,"请输入账户，并不要逗我，谢谢！",Toast.LENGTH_SHORT).show();
            return;
        }
        //获取可读数据库,Activity销毁时记得关闭
        dbr = db.getReadableDatabase();
        Cursor c = dbr.rawQuery("select * from qq where username =?",new String[]{forgotUser});
        if (c.moveToNext()){
            int id = c.getInt(0);//_id
            forgotUser = c.getString(1);//用户名
//            System.out.println(c.getString(c.getColumnIndex("password")));
            forgotPasswrod = c.getString(2);//获取密码
//            System.out.println("用户名："+forgotUser+"密码："+forgotPasswrod);
            //给ListView绑定数据
            infos = new ArrayList<Info>();
            Info f1 = new Info();
            f1.setUsername("用户名");
            f1.setPassword("密码");

            Info f2 = new Info();
            f2.setUsername(forgotUser);
            f2.setPassword(forgotPasswrod);

            infos.add(f1);
            infos.add(f2);

            lv_forgotPassword.setAdapter(new myAdapter());
            Toast.makeText(this,"盒盒，请查看你的密码...",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"逗我呢吧？哪有这个账号？滚去注册！",Toast.LENGTH_SHORT).show();
            hideKeyBoard();
            return;
        }
        hideKeyBoard();
        c.close();
        dbr.close();
    }
    //开启或者隐藏键盘
    private void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }


    //给listView设置数据适配器
    private class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(ForgetPassword.this,R.layout.item_forgorpassword,null);
            //在item中绑定控件
            TextView user = (TextView) v.findViewById(R.id.tv_forgotUser);
            TextView password = (TextView) v.findViewById(R.id.tv_forgetPassword);
            //把当前位置的对象取出来
            Info info = infos.get(position);
            user.setText(info.getUsername());
            password.setText(info.getPassword());
            return v;
        }
    }
}
