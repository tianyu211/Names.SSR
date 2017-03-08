package cn.guotianyu.datastorage.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.guotianyu.datastorage.HomeInfo;
import cn.guotianyu.datastorage.R;

/**
 * Created by 郭天宇 on 2017/3/6.
 */

public class HomeFragment extends Fragment {

    private ListView lv;
    private List<HomeInfo> homeInfos;

    //这里不用onCreate而是onStart
//    @Override
//    public void onStart() {
//        super.onStart();
//        lv = (ListView) getView().findViewById(R.id.lv_home);
//        homeInfos = new ArrayList<HomeInfo>();
//
//        HomeInfo homeInfo01 = new HomeInfo();
//        homeInfo01.setIcon(R.mipmap.read_normal);
//        homeInfo01.setTitle("读书使我快乐");
//        homeInfo01.setTime("23:07");
//        homeInfo01.setNum(90);
//        homeInfos.add(homeInfo01);
//
//        lv.setAdapter(new MyAdapter());
//
//    }
    public class MyAdapter extends BaseAdapter{

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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(),R.layout.item_lv_home,null);

            ImageView iv = (ImageView) view.findViewById(R.id.item_iv_home);
            TextView tv_title = (TextView) view.findViewById(R.id.item_tv_title);
            TextView tv_time = (TextView) view.findViewById(R.id.item_tv_time);
            TextView tv_num = (TextView) view.findViewById(R.id.item_tv_num);

            //把当前位置的对象取出来
            HomeInfo homeInfo = homeInfos.get(position);
            iv.setImageResource(homeInfo.getIcon());
            tv_title.setText(homeInfo.getTitle());
            tv_time.setText(homeInfo.getTime());
            tv_num.setText(homeInfo.getNum());

            return view;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
