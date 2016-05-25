package com.example.wim.android_eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wim.android_eventbus.model.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView username, realname, phone, aboutme;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (TextView) findViewById(R.id.username);
        realname = (TextView) findViewById(R.id.realname);
        phone = (TextView) findViewById(R.id.phone);
        aboutme = (TextView) findViewById(R.id.aboutme);

        btnEdit = (Button) findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(this);

        User user = new User();
        user.setUsername("wimsonevel");
        user.setRealname("Wim Sonevel");
        user.setPhone("088888888888");
        user.setAboutme("Hello Everyone! I'm Android Developer");

        username.setText("Username : "+user.getUsername());
        realname.setText("Realname : "+user.getRealname());
        phone.setText("Phone Number : "+user.getPhone());
        aboutme.setText("About Me : "+user.getAboutme());

        // Register EventBus
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onMessageEvent(User user){
        if(user != null){
            username.setText("Username : "+user.getUsername());
            realname.setText("Realname : "+user.getRealname());
            phone.setText("Phone Number : "+user.getPhone());
            aboutme.setText("About Me : "+user.getAboutme());
        }

        Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEdit:
                EditActivity.start(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister EventBus
        EventBus.getDefault().unregister(this);
    }
}
