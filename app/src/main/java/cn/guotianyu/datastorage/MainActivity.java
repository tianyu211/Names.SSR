package cn.guotianyu.datastorage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import static cn.guotianyu.datastorage.R.id.tab_lv_home;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mTabHome;
    private LinearLayout mTabMovie;
    private LinearLayout mTabFind;
    private LinearLayout mTabMe;

    private FragmentManager fm;

    private HomeFragment mHome;
    private MovieFragment mMovie;
    private FindFragment mFind;
    private  MeFragment mMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//将这一行注释掉，阻止activity保存fragment的状态
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        //初始化空间和声明事件
        mTabHome = (LinearLayout) findViewById(tab_lv_home);
        mTabMovie = (LinearLayout) findViewById(R.id.tab_lv_movie);
        mTabFind = (LinearLayout) findViewById(R.id.tab_lv_find);
        mTabMe = (LinearLayout) findViewById(R.id.tab_lv_me);
        mTabHome.setOnClickListener(this);
        mTabMovie.setOnClickListener(this);
        mTabFind.setOnClickListener(this);
        mTabMe.setOnClickListener(this);

        //设置默认的fragment
        setDefaultFragment();
    }

    @Override
    public void onClick(View v) {
        fm = getFragmentManager();
        //开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()) {
            case tab_lv_home:
//                Log.i("main","切换到home界面");
                if (mHome == null) {
                    mHome = new HomeFragment();
                }
                //使用当前Fragment的布局代替id_fragment_content的控件
                transaction.replace(R.id.id_fragment_content, mHome);
                break;
            case R.id.tab_lv_movie:
//                Log.i("main","切换到message界面");
                if (mMovie == null) {
                    mMovie = new MovieFragment();
                }
                transaction.replace(R.id.id_fragment_content, mMovie);
                break;
            case R.id.tab_lv_find:
                if(mFind == null){
                    mFind = new FindFragment();
                }
                transaction.replace(R.id.id_fragment_content,mFind);
                break;
            case R.id.tab_lv_me:
                if(mMe == null){
                    mMe = new MeFragment();
                }
                transaction.replace(R.id.id_fragment_content,mMe);
                break;
        }
        //事务提交
        transaction.commit();
    }

    private void setDefaultFragment() {
        fm = getFragmentManager();
        //开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        mHome = new HomeFragment();
        //id_fragment_content是activity_main中的内容布局
        transaction.replace(R.id.id_fragment_content,mHome);

        //提交事务
        transaction.commit();
    }
}
