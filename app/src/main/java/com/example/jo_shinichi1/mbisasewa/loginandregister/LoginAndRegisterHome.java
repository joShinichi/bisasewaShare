package com.example.jo_shinichi1.mbisasewa.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jo_shinichi1.mbisasewa.R;

public class LoginAndRegisterHome extends AppCompatActivity {
    Button btnMasukHome,btnDaftarHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register_home);
        btnMasukHome = (Button) findViewById(R.id.btnLgoinHome);
        btnDaftarHome = (Button) findViewById(R.id.btnDafyatHome);

        btnMasukHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndRegisterHome.this,LoggedIn.class);
                startActivity(intent);
            }
        });
        btnDaftarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAndRegisterHome.this,RegisterIn.class);
                startActivity(intent);
            }
        });
    }
}
