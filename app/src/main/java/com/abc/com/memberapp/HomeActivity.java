package com.abc.com.memberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity implements View.OnClickListener {
    Button bt_contacts, bt_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bt_contacts = (Button) findViewById(R.id.bt_contacts);
        bt_img = (Button) findViewById(R.id.bt_img);
        bt_contacts.setOnClickListener(this);
        bt_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_contacts:
                startActivity(new Intent(this, MemberListActivity.class));
                break;
            case R.id.bt_img:
                startActivity(new Intent(this, ImageActivity.class));
                break;
        }
    }
}