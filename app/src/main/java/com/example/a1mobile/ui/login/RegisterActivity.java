package com.example.a1mobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a1mobile.R;
import com.example.a1mobile.databinding.ActivityLoginBinding;
import com.example.a1mobile.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

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

    /**
     * POST register request.
     */
    private void registerRequest(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.10.158:8081/api/";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("uid", username);
            jsonBody.put("pwd", password);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType(){
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try{
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsupported encoding in request body.");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response){
                    String responseString = "";
                    if(response != null){
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            /*@Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("data_1_post", "value 1 data");
                return params;
            }*/
           /* @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }*/
            };
            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
}