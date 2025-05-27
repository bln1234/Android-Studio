package com.example.contactpicker;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final Cursor c = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String[] from = new String[] { ContactsContract.Contacts.DISPLAY_NAME_PRIMARY };
        int[] to = new int[] { R.id.itemTextView };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.listitemlayout,
                c,
                from,
                to);
        ListView lv = (ListView)findViewById(R.id.contactListView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id) {
                //将 cursor移至选中项
                c.moveToPosition(pos);
                //获得行 id
                int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));
                //构建 result URI
                Uri outURI = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
                Intent outData = new Intent();
                outData.setData(outURI);
                setResult(Activity.RESULT_OK, outData);

                finish();
            }
        });
    }
}