package com.example.a1mobile.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a1mobile.R;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String murl = "http://10.0.2.2:8080/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton = findViewById(R.id.button2);

        EditText passwordText = findViewById(R.id.registerPassword);
        EditText emailText = findViewById(R.id.registerEmail);

        TextView toLoginPage = findViewById(R.id.registerLogin);
        toLoginPage.setOnClickListener(v -> openLoginActivity());

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
                //
            }
        };
        passwordText.addTextChangedListener(afterTextChangedListener);
        emailText.addTextChangedListener(afterTextChangedListener);

        registerButton.setOnClickListener(v -> {
            System.out.println(emailText.getText().toString() + " " + passwordText.getText().toString());
            registerRequest(emailText.getText().toString(), passwordText.getText().toString());
        });
    }

    /**
     * Opens login page.
     */
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    /**
     * POST register request.
     */
    private void registerRequest(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = murl + "auth/create?"
                + "uid=" + username + "&pwd=" + password;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                login(username, password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("VOLLEY", error.toString())) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=utf-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", username);
                params.put("pwd", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void login(String username, String password) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = murl + "auth/login?uid=" + username + "&pwd=" + password;
        User user = User.getInstance();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            user.setUserid(username);
            user.setPassword(password);
            user.setToken(response.toString());
            System.out.println(response.toString());
            finish();
        }, error -> System.out.println("Error in loginRequest + " + error.toString()));
        queue.add(stringRequest);
    }
}