package com.example.a1mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a1mobile.R;
import com.example.a1mobile.databinding.ActivityLoginBinding;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.button2);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText passwordText = (EditText) findViewById(R.id.registerPassword);
        EditText emailText = (EditText) findViewById(R.id.registerEmail);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isPasswordValid(passwordText.toString()) && isUserNameValid(emailText.toString())){
                    registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openLoginActivity();
                        }
                    });
                } else {
                    return;
                }
            }
        };
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // A placeholder username validation check
    // YOINKED FROM LOGINVIEWMODEL
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // YOINKED FROM LOGINVIEWMODEL
    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}