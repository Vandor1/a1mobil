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
import android.widget.Toast;

import com.example.a1mobile.R;
import com.example.a1mobile.databinding.ActivityLoginBinding;
import com.example.a1mobile.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    /*private ActivityRegisterBinding binding;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.button2);

        EditText passwordText = findViewById(R.id.registerPassword);
        EditText emailText = findViewById(R.id.registerEmail);

        /*EditText emailText = binding.registerEmail;
        EditText passwordText = binding.registerPassword;*/
        /*registerButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  if(isPasswordValid(passwordText.getText().toString())
                                                          && isUserNameValid(emailText.getText().toString())){
                                                      System.out.println(passwordText.getText().toString());
                                                    openLoginActivity();
                                                  } else {
                                                      Toast.makeText(getApplicationContext(),
                                                              "Fix username/password", Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          });*/

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
                if (isPasswordValid(passwordText.getText().toString()) && isUserNameValid(emailText.getText().toString())){
                    registerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO: Add invalid input message.
                            openLoginActivity();
                        }
                    });
                } /*else {
                    Toast.makeText(getApplicationContext(),
                            "Fix username/password", Toast.LENGTH_SHORT).show();
                }*/
            }
        };
        passwordText.addTextChangedListener(afterTextChangedListener);
        emailText.addTextChangedListener(afterTextChangedListener);
    }

    public void openLoginActivity(){ startActivity(new Intent(this, LoginActivity.class)); }

    /**
     * A placeholder username validation check - YOINKED FROM LOGINVIEWMODEL
     */
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

    /**
     * YOINKED FROM LOGINVIEWMODEL: A placeholder password validation check
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}