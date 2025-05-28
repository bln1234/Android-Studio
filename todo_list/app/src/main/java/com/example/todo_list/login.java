package com.example.todo_list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseHelper db;
    private EditText accountEdit;
    private EditText passwordEdit;
    public login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        String accountPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // 简单的邮箱格式
        String passwordPattern = ".{8,}"; // 至少8个字符的密码
        accountEdit = rootView.findViewById(R.id.editAccount);
        passwordEdit = rootView.findViewById(R.id.editPassword);
        Button loginBtn = rootView.findViewById(R.id.loginBtn);
        accountEdit.setText(mParam1);
        passwordEdit.setText(mParam2);
        db = new DatabaseHelper(getContext());
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountInput = accountEdit.getText().toString();
                String passwordInput = passwordEdit.getText().toString();
                Cursor cursor = db.getUser(accountInput);
                if(!accountInput.matches(accountPattern)){
                    Toast.makeText(getContext(),"账号应为邮箱格式！",Toast.LENGTH_SHORT).show();
                }
                else if(!passwordInput.matches(passwordPattern)){
                    Toast.makeText(getContext(),"密码至少为八位！",Toast.LENGTH_SHORT).show();
                }
                else if(cursor == null || !cursor.moveToFirst()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("登录失败");
                    builder.setMessage("您还未进行注册，是否前往注册？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            register Register = register.newInstance2(accountInput);
                            getParentFragmentManager().beginTransaction().replace(R.id.log_fragment,Register).commit();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
                else {
                    String storedPassword = cursor.getString(cursor.getColumnIndex(db.COLUMN_PASSWORD));
                    if(!storedPassword.equals(passwordInput)){
                        Toast.makeText(getContext(),"密码错误！",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        return rootView;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("account", accountEdit.getText().toString());
        outState.putString("password", passwordEdit.getText().toString());
    }
}