package com.example.todo_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseHelper db;
    public register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register.
     */
    // TODO: Rename and change types and number of parameters
    public static register newInstance(String param1, String param2) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static register newInstance2(String EMAIL) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,EMAIL);
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
        View rootview = inflater.inflate(R.layout.fragment_register, container, false);

        EditText accountEdit = rootview.findViewById(R.id.account);
        EditText passwordEdit = rootview.findViewById(R.id.password);
        EditText confirmEdit = rootview.findViewById(R.id.confirm);
        Button registerBtn = rootview.findViewById(R.id.registerBtn);
        String accountPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; // 简单的邮箱格式
        String passwordPattern = ".{8,}"; // 至少8个字符的密码
        db = new DatabaseHelper(getContext());
        accountEdit.setText(mParam1);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String confirm = confirmEdit.getText().toString();
                if(!account.matches(accountPattern)){
                    Toast.makeText(getContext(),"账号应为邮箱格式！",Toast.LENGTH_SHORT).show();
                }else{
                    if(!password.matches(passwordPattern)){
                        Toast.makeText(getContext(),"密码应为八位及以上！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(!confirm.equals(password)){
                            Toast.makeText(getContext(),"两次密码输入不同！",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(checkUserExists(account)){
                                Toast.makeText(getContext(),"该用户已存在！",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                db.addUser(account,password);
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("注册提示");
                                builder.setMessage("注册成功！是否立即登录？");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        login Login = login.newInstance(account,password);
                                        getParentFragmentManager().beginTransaction().replace(R.id.log_fragment,Login).commit();
                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create(); // 创建 AlertDialog
                                dialog.show(); // 显示 AlertDialog
                            }
                        }
                    }
                }
            }
        });
        return rootview;
    }

    private boolean checkUserExists(String account){
        Cursor cursor = db.getUser(account);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}