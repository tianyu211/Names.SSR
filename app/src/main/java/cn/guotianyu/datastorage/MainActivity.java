package cn.guotianyu.datastorage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.guotianyu.datastorage.fragment.FindFragment;
import cn.guotianyu.datastorage.fragment.HomeFragment;
import cn.guotianyu.datastorage.fragment.MeFragment;
import cn.guotianyu.datastorage.fragment.MovieFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mTabHome;
    private LinearLayout mTabMovie;
    private LinearLayout mTabFind;
    private LinearLayout mTabMe;

    private FragmentManager fm;

    private HomeFragment mHome;
    private MovieFragment mMovie;
    private FindFragment mFind;
    private MeFragment mMe;

    private ImageView iv_home;
    private ImageView iv_movie;
    private ImageView iv_find;
    private ImageView iv_me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//将这一行注释掉，阻止activity保存fragment的状态
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        //初始化空间和声明事件
        mTabHome = (LinearLayout) findViewById(R.id.ll_tab_home);
        mTabMovie = (LinearLayout) findViewById(R.id.ll_tab_movie);
        mTabFind = (LinearLayout) findViewById(R.id.ll_tab_find);
        mTabMe = (LinearLayout) findViewById(R.id.ll_tab_me);

        iv_home = (ImageView) findViewById(R.id.iv_bottom_home);
        iv_movie = (ImageView) findViewById(R.id.iv_bottom_movie);
        iv_find = (ImageView) findViewById(R.id.iv_bottom_find);
        iv_me = (ImageView) findViewById(R.id.iv_bottom_me);

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
            case R.id.ll_tab_home:
//                Log.i("main","切换到home界面");
                if (mHome == null) {
                    mHome = new HomeFragment();
                }
                //使用当前Fragment的布局代替id_fragment_content的控件
                transaction.replace(R.id.id_fragment_content, mHome);
                //切换图标
                iconCheck("home");
                break;
            case R.id.ll_tab_movie:
//                Log.i("main","切换到message界面");
                if (mMovie == null) {
                    mMovie = new MovieFragment();
                }
                transaction.replace(R.id.id_fragment_content, mMovie);
                //切换图标
                iconCheck("movie");
                break;
            case R.id.ll_tab_find:
                if(mFind == null){
                    mFind = new FindFragment();
                }
                transaction.replace(R.id.id_fragment_content,mFind);
                //切换图标
                iconCheck("find");
                break;
            case R.id.ll_tab_me:
                if(mMe == null){
                    mMe = new MeFragment();
                }
                transaction.replace(R.id.id_fragment_content,mMe);
                //切换图标
                iconCheck("me");
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
        //设置图标
        iv_home.setImageResource(R.mipmap.home_clicked);
    }

    public void iconCheck(String s){
        iv_me.setImageResource(R.mipmap.fingerprint_normal);
        iv_find.setImageResource(R.mipmap.find_normal);
        iv_movie.setImageResource(R.mipmap.movie_normal);
        iv_home.setImageResource(R.mipmap.home_normal);
        switch(s) {
            case "home":
                //首页icon
                    iv_home.setImageResource(R.mipmap.home_clicked);
                break;
            case "movie":
                    iv_movie.setImageResource(R.mipmap.movie_clicked);
                break;
            case "find":
                    iv_find.setImageResource(R.mipmap.find_clicked);
                break;
            case "me":
                    iv_me.setImageResource(R.mipmap.fingerprint_clicked);
                break;
        }
    }
}
