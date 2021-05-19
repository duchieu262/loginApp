package com.example.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private EditText edUsernameCreate;
    private EditText edPasswordCreate;
    private EditText edEmail;
    private Button btnCreate;

    SQLiteConnector sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edUsernameCreate = findViewById(R.id.edPasswordCreate);
        edPasswordCreate = findViewById(R.id.edPasswordCreate);
        edEmail = findViewById(R.id.edEmail);
        btnCreate = findViewById(R.id.btnCreate);
        sql = new SQLiteConnector(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String strPassword = edPasswordCreate.getText().toString();
                String strEmail = edEmail.getText().toString();
                String strUsername = edUsernameCreate.getText().toString();
                if(!(strPassword.equals("") || strEmail.equals("") ||strUsername.equals(""))){
                    User user = new User();
                    user.setEmail(strEmail);
                    user.setName(strUsername);
                    user.setPassword(strPassword);
                    if(sql.checkUser(strEmail)){
                        Toast.makeText(SignupActivity.this, "Email exist", Toast.LENGTH_SHORT).show();
                    }else{
                        sql.addUser(user);
                        List<User> listUser = sql.getAllUser();
                        for(User u : listUser){
                            System.out.println(u.getPassword());
                        }

                        SignupActivity.this.finish();

                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().trim().length()==0){
                    btnCreate.setEnabled(false);
                } else {
                    btnCreate.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0){
                    btnCreate.setEnabled(false);
                } else {
                    btnCreate.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}