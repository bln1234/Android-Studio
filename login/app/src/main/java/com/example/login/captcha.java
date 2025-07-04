package com.example.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link captcha#newInstance} factory method to
 * create an instance of this fragment.
 */
public class captcha extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public captcha() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment captcha.
     */
    // TODO: Rename and change types and number of parameters
    public static captcha newInstance(String param1, String param2) {
        captcha fragment = new captcha();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_captcha, container, false);
        final EditText phoneNumber = rootview.findViewById(R.id.phoneNumber);
        final Drawable clearDrawable = getResources().getDrawable(R.drawable.ic_clear);
        Button loginBtn = getActivity().findViewById(R.id.loginBtn);
        loginBtn.setEnabled(false);
        phoneNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasText = s.length() > 0;
                if (hasText) {
                    // 如果有输入内容，显示叉图标
                    phoneNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, clearDrawable, null);
                } else {
                    // 如果无输入内容，隐藏叉图标
                    phoneNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
                loginBtn.setEnabled(hasText);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        phoneNumber.setOnTouchListener((v, event) -> {
            // 判断是否点击了右侧图标区域
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // 获取 drawableEnd 的宽度
                Drawable drawable = phoneNumber.getCompoundDrawables()[2]; // DRAWABLE_RIGHT = 2
                if (drawable != null && event.getRawX() >= (phoneNumber.getRight() - drawable.getBounds().width() - phoneNumber.getPaddingRight())) {
                    // 清空输入框
                    phoneNumber.setText("");
                    return true;
                }
            }
            return false;
        });
        return rootview;
    }

}