package com.example.fraglearn;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Activity activity){ //调用该方法时Fragment会被连接到它的父Fragment上
        super.onAttach(activity);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) { //Fragment初始创建
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //Fragment已被创建时，创建它自己的用户界面时调用该方法
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){ //在父activity和Fragment的UI已被创建，则调用该方法
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart(){ //可见生命周期开始时调用
        super.onStart();
    }
    @Override
    public void onResume(){ //活动生命周期开始时调用
        super.onResume();
    }
    @Override
    public void onPause(){ //活动生命周期结束时调用
        super.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){ //活动生命周期结束时调用该方法保存UI状态变化
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onStop(){ //当Fragment不可见时，暂停其余的UI更新、挂起线程或者暂停不再需要的处理
        super.onStop();
    }
    @Override
    public void onDestroyView(){ //清除资源相关的View
        super.onDestroyView();
    }
    @Override
    public void onDestroy(){ //清除所有的资源，包括结束线程和关闭数据库连接等
        super.onDestroy();
    }
    @Override
    public void onDetach(){ //当Fragment从它的父Activity上分离时，调用该方法
        super.onDetach();
    }
}