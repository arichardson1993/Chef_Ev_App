package com.tcdata.andrewrichardson.chef_ev_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    //variables

    private EditText editTextResetPassword;
    private Button buttonResetPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextResetPassword = (EditText) findViewById(R.id.editTextResetEmail);
        buttonResetPassword = (Button) findViewById(R.id.buttonResetPassword);


        buttonResetPassword.setOnClickListener(this);
    }

    public void forgotPassword(){


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = editTextResetPassword.getText().toString().trim();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Reset Password Email", "Email sent.");
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == buttonResetPassword){
            forgotPassword();
        }
    }
}
