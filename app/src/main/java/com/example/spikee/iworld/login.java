package com.example.spikee.iworld;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private static final String TAG = "this";
    private Button login;
    private TextInputEditText email;
    private TextInputEditText password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.lo);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null) {
//            Intent in = new Intent(this, category.class);
//            startActivity(in);
//        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log();
            }
        });
    }

    public void log() {
        String em = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(em)) {
            //email is empty
            Toast.makeText(login.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            //password is empty
            Toast.makeText(login.this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("LoginIn User....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(login.this, addproduct.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

}
//  login.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String em = email.getText().toString().trim();
//            String pass = password.getText().toString().trim();
//
//            if (TextUtils.isEmpty(em)) {
//                //email is empty
//                Toast.makeText(login.this, "Please enter Email", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (TextUtils.isEmpty(pass)) {
//                //password is empty
//                Toast.makeText(login.this, "Please enter Password", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            progressDialog.setMessage("LoginIn User....");
//            progressDialog.show();
//            firebaseAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressDialog.dismiss();
//
//                    if (task.isSuccessful())
//                        startActivity(new Intent(login.this, addproduct.class));
//                    else {
//                        Toast.makeText(login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//
//            });
//        }
//    });
