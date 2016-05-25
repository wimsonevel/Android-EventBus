package com.example.wim.android_eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wim.android_eventbus.model.User;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wim on 5/19/16.
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText username, realname, phone, about;

    public static void start(Context context){
        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        username = (EditText) findViewById(R.id.txtUsername);
        realname = (EditText) findViewById(R.id.txtRealname);
        phone = (EditText) findViewById(R.id.txtPhone);
        about = (EditText) findViewById(R.id.txtAboutMe);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setRealname(realname.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setAboutme(about.getText().toString());

                // Post
                EventBus.getDefault().post(user);

                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
