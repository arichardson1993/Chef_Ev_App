package com.tcdata.andrewrichardson.chef_ev_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    Button buttonHomeViewOrder;
    Button buttonHomeViewMenu;
    Button buttonHomeViewMyAccount;
    Button buttonHomeViewSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonHomeViewOrder = (Button) findViewById(R.id.buttonOrderButton);
        buttonHomeViewMenu = (Button) findViewById(R.id.buttonViewMenuButton);
        buttonHomeViewMyAccount = (Button) findViewById(R.id.buttonMyAccountButton);
        buttonHomeViewSignOut = (Button) findViewById(R.id.buttonHomeViewLogOut);

        buttonHomeViewOrder.setOnClickListener(this);
        buttonHomeViewMenu.setOnClickListener(this);
        buttonHomeViewMyAccount.setOnClickListener(this);
        buttonHomeViewSignOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonHomeViewOrder){
            startActivity(new Intent(this, OrderActivity.class));
        }

        if(view == buttonHomeViewMenu){

        }

        if(view == buttonHomeViewMyAccount){

        }

        if(view == buttonHomeViewSignOut){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
