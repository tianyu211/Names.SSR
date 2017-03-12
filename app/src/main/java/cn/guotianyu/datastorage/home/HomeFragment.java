package cn.guotianyu.datastorage.home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.guotianyu.datastorage.R;

/**
 * Created by 郭天宇 on 2017/3/6.
 */

public class HomeFragment extends Fragment {

    private ListView lv;
    private List<HomeInfo> homeInfos;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        lv = (ListView) view.findViewById(R.id.lv_home);
        homeInfos = new ArrayList<HomeInfo>();

        //设置listView的Item点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),HomeDetail.class);
//                System.out.println(position);
                //从数据适配器获得点击位置的数据
                String title = homeInfos.get(position).getTitle();
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        //ListView数据
        HomeInfo homeInfo01 = new HomeInfo();
        homeInfo01.setIcon(R.mipmap.read_normal);
        homeInfo01.setTitle("读书使我快乐");
        homeInfo01.setTime("23:07");
        homeInfo01.setNum(90);
        homeInfos.add(homeInfo01);

        HomeInfo homeInfo02 = new HomeInfo();
        homeInfo02.setIcon(R.mipmap.read_clicked);
        homeInfo02.setTitle("没有，快滚");
        homeInfo02.setTime("12:14");
        homeInfo02.setNum(111);
        homeInfos.add(homeInfo02);

        lv.setAdapter(new MyAdapter());
        return view;
    }



    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return homeInfos.size();
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
        public View getView(int position, View view, ViewGroup parent) {
            Task s  = null;
            if(view == null){
                view = View.inflate(getActivity(), R.layout.item_lv_home, null);
                s = new Task();
                s.iv_home =  (ImageView) view.findViewById(R.id.item_iv_home);
                s.tv_num = (TextView) view.findViewById(R.id.item_tv_num);
                s.tv_title = (TextView) view.findViewById(R.id.item_tv_title);
                s.tv_time = (TextView) view.findViewById(R.id.item_tv_time);
                view.setTag(s);
            }else{
                s = (Task) view.getTag();
            }
            //把当前位置的对象取出来
            HomeInfo homeInfo = homeInfos.get(position);
            s.iv_home.setImageResource(homeInfo.getIcon());
            s.tv_title.setText(homeInfo.getTitle());
            s.tv_time.setText(homeInfo.getTime());
            s.tv_num.setText(""+homeInfo.getNum());

            return view;
        }

        class Task {
            private ImageView iv_home;
            private TextView tv_title;
            private TextView tv_time;
            private TextView tv_num;
        }
    }
}
