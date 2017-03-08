package cn.guotianyu.datastorage.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.guotianyu.datastorage.R;


/**
 * Created by 郭天宇 on 2017/3/6.
 */

public class TitleFragment extends Fragment{
    private ImageButton mLeftMenu;
    private ImageButton mRightMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title,container,false);

        mLeftMenu = (ImageButton) view.findViewById(R.id.id_title_left_btn);
        mRightMenu = (ImageButton) view.findViewById(R.id.id_title_right_btn);
        mLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"欢迎来到这不是微信",Toast.LENGTH_SHORT).show();
            }
        });
        mRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"你猜我要干什么？",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
