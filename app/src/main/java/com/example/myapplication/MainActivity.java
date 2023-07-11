package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
    private String mTokenResult;
    private EditText mCaptchaEditText;
    private Button mVerifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .build();
        mGoogleApiClient.connect();

        mCaptchaEditText = findViewById(R.id.editTextCaptcha);
        mVerifyButton = findViewById(R.id.buttonVerify);

        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyNet.getClient(MainActivity.this).verifyWithRecaptcha("6LfQfBInAAAAAMR5UhBGXWPApEWmKu5SVkZjEFIa")
                        .addOnSuccessListener(MainActivity.this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                mTokenResult = response.getTokenResult();
                                // Muestra el captcha en una vista WebView o realiza otra acción
                            }
                        })
                        .addOnFailureListener(MainActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error en la verificación del captcha
                            }
                        });
            }
        });
    }
}


